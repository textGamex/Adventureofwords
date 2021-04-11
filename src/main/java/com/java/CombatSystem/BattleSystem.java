package com.java.CombatSystem;

import com.java.Unit.EnemyAttribute;
import com.java.Unit.EnemyUnit;
import com.java.Unit.RoleAttribute;
import com.java.Tools.GameTool;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * ս��ϵͳ
 * @version 0.15
 * @author Millennium
 */
public class BattleSystem
{
    public static void main(String[] args)
    {
//        Locale aLocale = new Locale("zh","CN");
//        ResourceBundle bundle = ResourceBundle.getBundle("BattleSystemMessage", aLocale);

        var role = new RoleAttribute();
        var enemy = EnemyUnit.getEnemyUnit("�粼��");
        BattleSystem.Main(role, enemy);
    }
    public static void Main(RoleAttribute role, EnemyAttribute enemy)
    {
        int ATK = 0;
//        ResourceBundle bundle = ResourceBundle.getBundle("BattleSystemMessage", locale);

        var in = new Scanner(System.in);
        System.out.println("�����������:");
        System.out.println(role.toString());
        System.out.println(enemy.getName() + "����������:");
        System.out.println(enemy.toString());

        System.out.println("ս����ʼ!!!");

        while (true)
        {
            System.out.println("[ϵͳ]�ֵ����ж���");
            System.out.println("1.���� 2.����");
            byte userDecision = 0;
            userDecision = (byte)in.nextInt();
            switch (userDecision)
            {
                case 1:
                    ATK = (int) (GameTool.randomRun(role.getCRIT()) ? role.getATK() * role.getCritsEffect() : role.getATK());
                    ATK = GameTool.floatingNumber(ATK, 3, "+");
                    System.out.println("�������" + enemy.subtractHP(ATK, role.getFixArmorPen(), role.getPerArmorPen()) + "���˺�");
                    System.out.println(enemy.getName() + "��ʣ" + enemy.getHP());
                    break;
                case 2: ;
            }
            if (enemy.getHP() <= 0 || role.getHP() <= 0)
            {
                System.out.println("Game Over!");
                break;
            }
            ATK = (int) (GameTool.randomRun(enemy.getCRIT()) ? enemy.getATK() * enemy.getCritsEffect() : enemy.getATK());
            ATK = GameTool.floatingNumber(ATK, 3);
            System.out.println(enemy.getName() + "���������" + role.subtractHP(ATK, enemy.getFixArmorPen(), enemy.getPerArmorPen()) + "���˺�");
            System.out.println(role.getName() + "��ʣ" + role.getHP());
            if (enemy.getHP() <= 0 || role.getHP() <= 0)
            {
                System.out.println("Game Over!");
                break;
            }
        }
    }
}
