package com.java.main;

import com.java.localPersistence.DataPath;
import com.java.localPersistence.JsonBase;
import com.java.message.PlayerStatistics;
import com.java.message.attackMessage.AttackType;
import com.java.message.attackMessage.BattleTip;
import com.java.tools.GameTool;
import com.java.tools.UiTool;
import com.java.unit.BasicUnit;
import com.java.unit.Role;
import com.ui.ConsoleProgressBar;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Scanner;

import static com.java.battleSystem.BattleAttributeCalculation.criticalDamage;
import static com.java.battleSystem.BattleSystem.*;
import static com.java.battleSystem.BattleSystem.normalAttackDamage;
import static java.util.Objects.requireNonNull;

/**
 * @author ����ǧ��
 * @version 1.2.0
 * @since 2021-6-6
 */
public class TestUse
{
    private static final File ROLE_FILE = DataPath.DESKTOP.resolve("�������.json").toFile();
    private static final File ENEMY_FILE = DataPath.DESKTOP.resolve("�жԵ�λ����.json").toFile();
    private static final String SETTING_FILE_NAME = "setting.json";
    private static final File SETTING_FILE_PATH = DataPath.GAME_DATA_PATH.resolve(SETTING_FILE_NAME).toFile();
    private static final GameSetting SETTING = GameSetting.getGameSetting();
    private static char separatorCharacter = '-';

    static
    {
        if (!SETTING_FILE_PATH.exists())
        {
            println("���ڳ�ʼ��...");
            printProgressBar();
            GameSetting.getGameSetting().saveSetting(SETTING_FILE_PATH);
            println("��ʼ���ɹ�");
        }
        else
        {
            try
            {
                SETTING.loadSettingFile(SETTING_FILE_PATH);
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

    public static void main(String[] args) throws FileNotFoundException
    {
        Role role = null;
        Role enemy = null;

        if (ROLE_FILE.exists() && ENEMY_FILE.exists())
        {
            println("��⵽�����ļ�, ������...");
            if (SETTING.isOpenLoadAnimation())
            {
                printProgressBar();
            }
            role = Role.loadGameManagerData(ROLE_FILE);
            enemy = Role.loadGameManagerData(ENEMY_FILE);
        }

        if (!ROLE_FILE.exists() && !ENEMY_FILE.exists())
        {
            println("�����ļ�������, ���ڴ�����...");
            if (SETTING.isOpenLoadAnimation())
            {
                printProgressBar();
            }
            role = Role.newStandardPrimaryLevelRole("�ײ�ɭ");
            enemy = Role.newStandardPrimaryLevelRole("����");
            role.saveGameManagerData(ROLE_FILE);
            enemy.saveGameManagerData(ENEMY_FILE);
            println("�����ɹ�");
        }

        assert role != null;
        final var in = new Scanner(System.in);
        String[] uiArray =
        {
             "�鿴˫������",
             "��ʼս��",
             "�������뵥λ�����ļ�����",
             "�˳�",
             "�������ض���",
             "��տ���̨"
        };

        while (true)
        {
            separator();
            if (SETTING.isOpenLoadAnimation())
            {
                uiArray[4] = "�رռ��ض���";
            }
            else
            {
               uiArray[4] = "�������ض���";
            }
            println(UiTool.generateUi(uiArray));
            switch (in.nextInt())
            {
                case 1 -> {
                    separator();
                    println("���:" + role);
                    println("�жԵ�λ:" + enemy);
                }
                case 2 -> {
                    GameTool.cls();
                    fight(role, enemy);
                }
                case 3 -> {
                    separator();
                    println("����������...");
                    if (SETTING.isOpenLoadAnimation())
                    {
                        printProgressBar();
                    }
                    role = Role.loadGameManagerData(ROLE_FILE);
                    enemy = Role.loadGameManagerData(ENEMY_FILE);
                    println("����ɹ�");
                }
                case 4 -> System.exit(0);
                case 5 -> {
                    SETTING.setLoadAnimation(!SETTING.isOpenLoadAnimation());
                    GameSetting.getGameSetting().saveSetting(SETTING_FILE_PATH);
                }
                case 6 -> GameTool.cls();
                default -> {
                    separator();
                    println("������֧�ֵ�ѡ��");
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
                    var originalMessage = new BattleTip.AttackMessage(
                            role.getName(), 0, AttackType.COMMON_ATTACK, enemy.getName(), enemy.defense().getHp());
                    int hurt;
                    if (attackCanCrit(role, enemy))
                    {
                        int commonHurt = normalAttackDamage(role, enemy);
                        hurt = criticalDamage(commonHurt, role.attack().getCritsEffect());
                        originalMessage.setAttackType(AttackType.CRIT);
                    }
                    else
                    {
                        hurt = normalAttackDamage(role, enemy);
                        originalMessage.setAttackType(AttackType.COMMON_ATTACK);
                    }
                    enemy.subtractHp(hurt);
                    originalMessage.setHarm(hurt);
                    println(BattleTip.returnAttackMessage(originalMessage));
                    roleStatistics.addTotalHarm(hurt);
                    roleStatistics.setTotalAttack(roleStatistics.getTotalAttack() + 1);
                }
                else
                {
                    println(role.getName() + "δ����" + enemy.getName());
                }
            }
            else
            {
                println("�����ж�");
            }

            if (enemy.defense().getHp() <= 0)
            {
                println(role.getName() + "��ʣ" + role.defense().getHp() + "��Ѫ");
                println("���ʤ��!");
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
                    var originalMessage = new BattleTip.AttackMessage(
                            enemy.getName(), 0, AttackType.COMMON_ATTACK, role.getName(), role.defense().getHp());
                    int hurt;
                    if (attackCanCrit(enemy, role))
                    {
                        int commonHurt = normalAttackDamage(enemy, enemy);
                        hurt = criticalDamage(commonHurt, enemy.attack().getCritsEffect());
                        originalMessage.setAttackType(AttackType.CRIT);
                    }
                    else
                    {
                        hurt = normalAttackDamage(enemy, role);
                        originalMessage.setAttackType(AttackType.COMMON_ATTACK);
                    }
                    role.subtractHp(hurt);
                    originalMessage.setHarm(hurt);
                    println(BattleTip.returnAttackMessage(originalMessage));
                    enemyStatistics.addTotalHarm(hurt);
                    enemyStatistics.setTotalAttack(roleStatistics.getTotalAttack() + 1);
                }
                else
                {
                    println(enemy.getName() + "δ����" + role.getName());
                }
            }
            else
            {
                println("�����ж�");
            }

            if (role.defense().getHp() <= 0)
            {
                println("�жԵ�λʤ��!");
                println(enemy.getName() + "��ʣ" + enemy.defense().getHp() + "��Ѫ");
                enemyStatistics.setTotalVictory(enemyStatistics.getTotalVictory() + 1);
                enemyStatistics.setTotalKill(enemyStatistics.getTotalKill() + 1);
                stateRecovery(role);
                stateRecovery(enemy);
                break;
            }
        }

        var endTime = Instant.now();
        var time = Duration.between(start, endTime);
        println("��Ϸ����");
        separator();
        println("��ʱ:" + time.toMinutesPart() + "��" + time.toSecondsPart() + "��" + time.toMillisPart() + "����");
        separator();
        println("ͳ����Ϣ");
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
        System.out.println("[ϵͳ]" + Objects.requireNonNull(s));
    }

    private static void printProgressBar()
    {
       ConsoleProgressBar.loadSpecifiedTime(1700, 65, '|');
    }

    public static void loadSetting(final File path) throws FileNotFoundException
    {
        var json = JsonBase.loadJsonFile(requireNonNull(path));

        var loadAnimation = json.getBooleanValue("��ʾ���ض���");
        GameSetting.getGameSetting().setLoadAnimation(loadAnimation);
    }

    /**
     * �޸������ļ�.
     */
    private void repairPropertiesFile()
    {

    }
}