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
 * @author ����ǧ��
 * @version 1.0.0
 * @since 2021-6-6
 */
public class TestUse
{
    private static final File ROLE_FILE = DataPath.DESKTOP.resolve("�������.json").toFile();
    private static final File ENEMY_FILE = DataPath.DESKTOP.resolve("�жԵ�λ����.json").toFile();
    private static final String SETTING_FILE_NAME = "setting.json";
    private static final File SETTING_FILE_PATH = DataPath.GAME_DATA_PATH.resolve(SETTING_FILE_NAME).toFile();
    private static GameSetting setting = new GameSetting(true);

    static
    {
        if (!SETTING_FILE_PATH.exists())
        {
            println("���ڳ�ʼ��...");
            printProgressBar();
            saveSetting(SETTING_FILE_PATH);
            println("��ʼ���ɹ�");
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
            println("��⵽�����ļ�, ������...");
            if (setting.isOpenLoadAnimation())
            {
                printProgressBar();
            }
            role = Role.loadGameManagerData(ROLE_FILE);
            enemy = Role.loadGameManagerData(ENEMY_FILE);
        }

        if (!ROLE_FILE.exists() && !ENEMY_FILE.exists())
        {
            println("�����ļ�������, ���ڴ�����...");
            if (setting.isOpenLoadAnimation())
            {
                printProgressBar();
            }
            role = Role.newStandardPrimaryLevelRole("�ײ�ɭ");
            enemy = Role.newStandardPrimaryLevelRole("����");
            role.saveGameManagerData(ROLE_FILE);
            enemy.saveGameManagerData(ENEMY_FILE);
            println("�����ɹ�");
        }

        if (!ROLE_FILE.exists() || !ENEMY_FILE.exists())
        {
            println("�����ļ�����, �����޸���...");
            if (!ROLE_FILE.exists())
            {
                role = Role.newStandardPrimaryLevelRole("�ײ�ɭ");
                role.saveGameManagerData(ROLE_FILE);
                if (setting.isOpenLoadAnimation())
                {
                    printProgressBar();
                }
            }
            else
            {
                enemy = Role.newStandardPrimaryLevelRole("����");
                enemy.saveGameManagerData(ENEMY_FILE);
                if (setting.isOpenLoadAnimation())
                {
                    printProgressBar();
                }
            }
            println("�����ļ��޸��ɹ�");
        }

        assert role != null;

        final var in = new Scanner(System.in);
        while (true)
        {
            separator();
            //TODO:�кܴ���Ż��ռ�
            if (setting.isOpenLoadAnimation())
            {
                println("1.�鿴˫������   2.��ʼս��   3.�������뵥λ�����ļ�����   4.�˳�   5.�رռ��ض���");
            }
            else
            {
                println("1.�鿴˫������   2.��ʼս��   3.�������뵥λ�����ļ�����   4.�˳�   5.�������ض���");
            }
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
                    if (setting.isOpenLoadAnimation())
                    {
                        printProgressBar();
                    }
                    role = Role.loadGameManagerData(ROLE_FILE);
                    enemy = Role.loadGameManagerData(ENEMY_FILE);
                    println("����ɹ�");
                }
                case 4 -> System.exit(0);
                case 5 -> {
                    setting.setLoadAnimation(!setting.isOpenLoadAnimation());
                    saveSetting(SETTING_FILE_PATH);
                }
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
                println(enemy.getName() + "��ʣ{}��Ѫ" + enemy.defense().getHp());
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

    public static void saveSetting(final File path)
    {
        requireNonNull(path);
        var jsonFile = new JSONObject();
        jsonFile.put("��ʾ���ض���", setting.isOpenLoadAnimation());

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

        var loadAnimation = json.getBooleanValue("��ʾ���ض���");

        return new GameSetting(loadAnimation);
    }
}