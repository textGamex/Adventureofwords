package com.java.main;

import com.alibaba.fastjson.JSONObject;
import com.java.localPersistence.DataPath;
import com.java.localPersistence.JsonBase;
import com.java.message.PlayerStatistics;
import com.java.message.attackMessage.AttackType;
import com.java.message.attackMessage.BattleTip;
import com.java.tools.GameTool;
import com.java.unit.BasicUnit;
import com.java.unit.Role;
import com.ui.ConsoleProgressBar;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Scanner;

import static com.java.battleSystem.BattleAttributeCalculation.criticalDamage;
import static com.java.battleSystem.BattleSystem.*;
import static com.java.battleSystem.BattleSystem.normalAttackDamage;
import static java.util.Objects.requireNonNull;

/**
 * @author 留恋千年
 * @version 1.0.0
 * @since 2021-6-6
 */
public class TestUse
{
    private static final File ROLE_FILE = DataPath.DESKTOP.resolve("玩家属性.json").toFile();
    private static final File ENEMY_FILE = DataPath.DESKTOP.resolve("敌对单位属性.json").toFile();
    private static final String SETTING_FILE_NAME = "setting.json";
    private static final File SETTING_FILE_PATH = DataPath.GAME_DATA_PATH.resolve(SETTING_FILE_NAME).toFile();
    private static GameSetting setting = new GameSetting(true);

    static
    {
        if (!SETTING_FILE_PATH.exists())
        {
            println("正在初始化...");
            printProgressBar();
            saveSetting(SETTING_FILE_PATH);
            println("初始化成功");
        }
        try
        {
            setting = loadSetting(SETTING_FILE_PATH);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        Role role = null;
        Role enemy = null;

        if (ROLE_FILE.exists() && ENEMY_FILE.exists())
        {
            println("检测到属性文件, 加载中...");
            if (setting.isOpenLoadAnimation())
            {
                printProgressBar();
            }
            role = Role.loadGameManagerData(ROLE_FILE);
            enemy = Role.loadGameManagerData(ENEMY_FILE);
        }

        if (!ROLE_FILE.exists() && !ENEMY_FILE.exists())
        {
            println("属性文件不存在, 正在创建中...");
            if (setting.isOpenLoadAnimation())
            {
                printProgressBar();
            }
            role = Role.newStandardPrimaryLevelRole("白博森");
            enemy = Role.newStandardPrimaryLevelRole("张羽");
            role.saveGameManagerData(ROLE_FILE);
            enemy.saveGameManagerData(ENEMY_FILE);
            println("创建成功");
        }

        if (!ROLE_FILE.exists() || !ENEMY_FILE.exists())
        {
            println("属性文件已损坏, 正在修复中...");
            if (!ROLE_FILE.exists())
            {
                role = Role.newStandardPrimaryLevelRole("白博森");
                role.saveGameManagerData(ROLE_FILE);
                if (setting.isOpenLoadAnimation())
                {
                    printProgressBar();
                }
            }
            else
            {
                enemy = Role.newStandardPrimaryLevelRole("张羽");
                enemy.saveGameManagerData(ENEMY_FILE);
                if (setting.isOpenLoadAnimation())
                {
                    printProgressBar();
                }
            }
            println("属性文件修复成功");
        }

        assert role != null;

        final var in = new Scanner(System.in);
        while (true)
        {
            separator();
            //TODO:有很大的优化空间
            if (setting.isOpenLoadAnimation())
            {
                println("1.查看双方属性   2.开始战斗   3.重新载入单位属性文件属性   4.退出   5.关闭加载动画");
            }
            else
            {
                println("1.查看双方属性   2.开始战斗   3.重新载入单位属性文件属性   4.退出   5.开启加载动画");
            }
            switch (in.nextInt())
            {
                case 1 -> {
                    separator();
                    println("玩家:" + role);
                    println("敌对单位:" + enemy);
                }
                case 2 -> {
                    GameTool.cls();
                    fight(role, enemy);
                }
                case 3 -> {
                    separator();
                    println("正在载入中...");
                    if (setting.isOpenLoadAnimation())
                    {
                        printProgressBar();
                    }
                    role = Role.loadGameManagerData(ROLE_FILE);
                    enemy = Role.loadGameManagerData(ENEMY_FILE);
                    println("载入成功");
                }
                case 4 -> System.exit(0);
                case 5 -> {
                    setting.setLoadAnimation(!setting.isOpenLoadAnimation());
                    saveSetting(SETTING_FILE_PATH);
                }
                default -> {
                    separator();
                    println("请输入支持的选项");
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
            if (canAct(role))
            {
                if (canHit(role, enemy))
                {
                    if (attackCanCrit(role, enemy))
                    {
                        final var hurt = normalAttackDamage(role, enemy);
                        final var critHurt = criticalDamage(hurt, role.attack().getCritsEffect());
                        enemy.subtractHp(critHurt);

                        roleStatistics.addTotalHarm(critHurt);

                        BattleTip.printAttackMessage(new BattleTip.AttackMessage(role.getName(), critHurt,
                                AttackType.CRIT, enemy.getName(), enemy.defense().getHp()));
                    }
                    else
                    {
                        final var hurt = normalAttackDamage(role, enemy);
                        enemy.subtractHp(hurt);

                        roleStatistics.addTotalHarm(hurt);

                        BattleTip.printAttackMessage(new BattleTip.AttackMessage(role.getName(), hurt,
                                AttackType.COMMON_ATTACK, enemy.getName(), enemy.defense().getHp()));
                    }
                    roleStatistics.setTotalAttack(roleStatistics.getTotalAttack() + 1);
                }
                else
                {
                    println(role.getName() + "未击中" + enemy.getName());
                }
            }
            else
            {
                println("不能行动");
            }

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

            if (canAct(enemy))
            {
                if (canHit(enemy, role))
                {
                    if (attackCanCrit(enemy, role))
                    {
                        final var hurt = normalAttackDamage(enemy, role);
                        final var critHurt = criticalDamage(hurt, enemy.attack().getCritsEffect());
                        role.subtractHp(critHurt);

                        enemyStatistics.addTotalHarm(critHurt);

                        BattleTip.printAttackMessage(new BattleTip.AttackMessage(enemy.getName(), critHurt,
                                AttackType.CRIT, role.getName(), role.defense().getHp()));
                    }
                    else
                    {
                        final var hurt = normalAttackDamage(enemy, role);
                        role.subtractHp(hurt);

                        enemyStatistics.addTotalHarm(hurt);

                        BattleTip.printAttackMessage(new BattleTip.AttackMessage(enemy.getName(), hurt,
                                AttackType.COMMON_ATTACK, role.getName(), role.defense().getHp()));

                        enemyStatistics.setTotalAttack(enemyStatistics.getTotalAttack() + 1);
                    }
                }
                else
                {
                    println(enemy.getName() + "未击中" + role.getName());
                }
            }
            else
            {
                println("不能行动");
            }

            if (role.defense().getHp() <= 0)
            {
                println("敌对单位胜利!");
                println(enemy.getName() + "仅剩{}点血" + enemy.defense().getHp());
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

    public static void separator()
    {
        System.out.println("-".repeat(65));
    }

    private static void stateRecovery(final BasicUnit unit)
    {
        assert unit != null;
        unit.defense().setHp(unit.defense().getMaxHp());
    }

    private static void println(final String s)
    {
        System.out.println("[系统]" + Objects.requireNonNull(s));
    }

    private static void printProgressBar()
    {
       ConsoleProgressBar.loadSpecifiedTime(1700, 65, '|');
    }

    public static void saveSetting(final File path)
    {
        requireNonNull(path);
        var jsonFile = new JSONObject();
        jsonFile.put("显示加载动画", setting.isOpenLoadAnimation());

        try (var out = new PrintWriter(path, StandardCharsets.UTF_8))
        {
            out.println(jsonFile.toJSONString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static GameSetting loadSetting(final File path) throws FileNotFoundException
    {
        var json = JsonBase.loadJsonFile(requireNonNull(path));

        var loadAnimation = json.getBooleanValue("显示加载动画");

        return new GameSetting(loadAnimation);
    }
}