package com.java.main;

import com.java.localPersistence.DataPath;
import com.java.localPersistence.JsonBaseTool;
import com.java.message.PlayerStatistics;
import com.java.message.attackMessage.AttackType;
import com.java.message.attackMessage.BattleTip;
import com.java.tools.GameTool;
import com.java.tools.UiTool;
import com.java.unit.BasicUnit;
import com.java.unit.Role;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

import static com.java.battleSystem.BattleAttributeCalculation.criticalDamage;
import static com.java.battleSystem.BattleSystem.*;
import static java.util.Objects.requireNonNull;

/**
 * 用于提供给测试和策划进行游戏测试.
 *
 * @author 留恋千年
 * @version 1.3.0
 * @since 2021-6-6
 */
public class TestUse
{
    private final File ROLE_FILE = DataPath.DESKTOP.resolve("玩家属性.json").toFile();
    private final File ENEMY_FILE = DataPath.DESKTOP.resolve("敌对单位属性.json").toFile();
    private static final String SETTING_FILE_NAME = "setting.json";
    private static final File SETTING_FILE_PATH = DataPath.GAME_DATA_PATH.resolve(SETTING_FILE_NAME).toFile();
    private static final GameSetting SETTING = GameSetting.getGameSetting();
    private static final Locale LOCALE = Locale.getDefault();
    private static final ResourceBundle language = ResourceBundle.getBundle(
            "language/UI_TestUse", LOCALE);

    static
    {
        if (!SETTING_FILE_PATH.exists())
        {
            println(language.getString("initialization"));
            GameSetting.getGameSetting().saveSetting(SETTING_FILE_PATH);
            println(language.getString("initialization_Ok"));
        }
        else
        {
            try
            {
                GameSetting.loadSettingFile(SETTING_FILE_PATH);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            loadSetting(SETTING_FILE_PATH);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void start() throws FileNotFoundException
    {
        Role role = null;
        Role enemy = null;
        if (ROLE_FILE.exists() && ENEMY_FILE.exists())
        {
            println(language.getString("loadPropertiesFile"));
            role = Role.loadData(ROLE_FILE);
            enemy = Role.loadData(ENEMY_FILE);
        }

        if (!ROLE_FILE.exists() && !ENEMY_FILE.exists())
        {
            println(language.getString("createPropertyFile"));
            role = Role.newStandardPrimaryLevelRole(language.getString("UnitName1"));
            enemy = Role.newStandardPrimaryLevelRole(language.getString("UnitName2"));
            role.saveData(ROLE_FILE);
            enemy.saveData(ENEMY_FILE);
            println(language.getString("createPropertyFile_OK"));
        }

        assert role != null;
        final var in = new Scanner(System.in);
        String[] uiArray =
        {
             language.getString("viewPropertiesOfBothParties"),
             language.getString("startFighting"),
             language.getString("reloadUnitPropertiesFileProperties"),
             language.getString("exitProgram"),
             language.getString("executeCls")
        };

        while (true)
        {
            separator();

            println(UiTool.toUi(uiArray));
            switch (in.nextInt())
            {
                case 1 -> {
                    separator();
                    UiTool.printUnitProperties(role, LOCALE);
                    System.out.println();
                    UiTool.printUnitProperties(enemy, LOCALE);
                }
                case 2 -> {
                    GameTool.cls();
                    fight(role, enemy);
                }
                case 3 -> {
                    separator();
                    println(language.getString("case3_loading"));
                    role = Role.loadData(ROLE_FILE);
                    enemy = Role.loadData(ENEMY_FILE);
                    println(language.getString("case3_OkLoad"));
                }
                case 4 -> System.exit(0);
                case 6 -> GameTool.cls();
                default -> {
                    separator();
                    println(language.getString("defaultText"));
                    separator();
                }
            }
        }
    }

    public static void fight(final BasicUnit role, final BasicUnit enemy)
    {
        Objects.requireNonNull(role);
        Objects.requireNonNull(enemy);

        var start = Instant.now();
        final var roleStatistics = new PlayerStatistics();
        final var enemyStatistics = new PlayerStatistics();

        while (true)
        {
            roleStatistics.addTotalRound(1);
            enemyStatistics.addTotalRound(1);
            w(role, enemy, roleStatistics, normalAttackDamage(role, enemy), roleStatistics.getTotalAttack());

            if (enemy.defense().getHp() <= 0)
            {
                println(role.getName() + "仅剩" + role.defense().getHp() + "点血");
                println("玩家胜利!");
                stateRecovery(role);
                stateRecovery(enemy);
                roleStatistics.setTotalKill(roleStatistics.getTotalKill() + 1);
                roleStatistics.setTotalVictory(roleStatistics.getTotalVictory() + 1);
                break;
            }

            w(enemy, role, enemyStatistics, normalAttackDamage(enemy, enemy), roleStatistics.getTotalAttack());

            if (role.defense().getHp() <= 0)
            {
                println("敌对单位胜利!");
                println(enemy.getName() + "仅剩" + enemy.defense().getHp() + "点血");
                enemyStatistics.setTotalVictory(enemyStatistics.getTotalVictory() + 1);
                enemyStatistics.setTotalKill(enemyStatistics.getTotalKill() + 1);
                stateRecovery(role);
                stateRecovery(enemy);
                break;
            }
        }

        var endTime = Instant.now();
        var time = Duration.between(start, endTime);
        println("游戏结束");
        separator();
        println("耗时:" + time.toMinutesPart() + "分" + time.toSecondsPart() + "秒" + time.toMillisPart() + "毫秒");
        separator();
        println("统计信息");
        separator();
        println(String.valueOf(roleStatistics));
        println(String.valueOf(enemyStatistics));
    }

    private static void w(final BasicUnit role, final BasicUnit enemy, final PlayerStatistics roleStatistics, final int i, final long totalAttack)
    {
        if (canMove(role))
        {
            if (canHit(role, enemy))
            {
                var originalMessage = new BattleTip.AttackMessage(
                        role.getName(), 0, AttackType.COMMON_ATTACK, enemy.getName(), enemy.defense().getHp());
                int hurt;
                if (attackCanCrit(role, enemy))
                {
                    hurt = criticalDamage(i, role.attack().getCritsEffect());
                    originalMessage.setAttackType(AttackType.CRIT);
                }
                else
                {
                    hurt = normalAttackDamage(role, enemy);
                    originalMessage.setAttackType(AttackType.COMMON_ATTACK);
                }
                enemy.subtractHp(hurt);
                originalMessage.setHarm(hurt);
                println(BattleTip.returnAttackMessage(originalMessage, LOCALE));
                roleStatistics.addTotalHarm(hurt);
                roleStatistics.setTotalAttack(totalAttack + 1);
            }
            else
            {
                println(role.getName() + language.getString("attackMiss") + enemy.getName());
            }
        }
        else
        {
            println(language.getString("unableToAct"));
        }
    }

    public static void separator()
    {
        UiTool.separator(SETTING.getSeparatorCharacter(), 90);
    }

    private static void stateRecovery(final BasicUnit unit)
    {
        assert unit != null;
        unit.defense().setHp(unit.defense().getMaxHp());
    }

    private static void println(final String s)
    {
        println(s, LOCALE);
    }

    private static void println(final String s, final Locale locale)
    {
        final var language = ResourceBundle.getBundle("language/UI_testUse",
                requireNonNull(locale));
        System.out.println(language.getString("statementPrefix") + Objects.requireNonNull(s));
    }

    public static void loadSetting(final File path) throws FileNotFoundException
    {
        var json = JsonBaseTool.loadJsonFile(requireNonNull(path));

        var loadAnimation = json.getBooleanValue("显示加载动画");
        GameSetting.getGameSetting().setLoadAnimation(loadAnimation);
    }
}