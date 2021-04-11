package com.java.CombatSystem;

import com.java.Unit.EnemyAttribute;
import com.java.Unit.EnemyUnit;
import com.java.Unit.RoleAttribute;
import com.java.Tools.GameTool;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * 战斗系统
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
        var enemy = EnemyUnit.getEnemyUnit("哥布林");
        BattleSystem.Main(role, enemy);
    }
    public static void Main(RoleAttribute role, EnemyAttribute enemy)
    {
        int ATK = 0;
//        ResourceBundle bundle = ResourceBundle.getBundle("BattleSystemMessage", locale);

        var in = new Scanner(System.in);
        System.out.println("你的属性如下:");
        System.out.println(role.toString());
        System.out.println(enemy.getName() + "的属性如下:");
        System.out.println(enemy.toString());

        System.out.println("战斗开始!!!");

        while (true)
        {
            System.out.println("[系统]轮到你行动了");
            System.out.println("1.攻击 2.跳过");
            byte userDecision = 0;
            userDecision = (byte)in.nextInt();
            switch (userDecision)
            {
                case 1:
                    ATK = (int) (GameTool.randomRun(role.getCRIT()) ? role.getATK() * role.getCritsEffect() : role.getATK());
                    ATK = GameTool.floatingNumber(ATK, 3, "+");
                    System.out.println("你造成了" + enemy.subtractHP(ATK, role.getFixArmorPen(), role.getPerArmorPen()) + "的伤害");
                    System.out.println(enemy.getName() + "还剩" + enemy.getHP());
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
            System.out.println(enemy.getName() + "对你造成了" + role.subtractHP(ATK, enemy.getFixArmorPen(), enemy.getPerArmorPen()) + "的伤害");
            System.out.println(role.getName() + "还剩" + role.getHP());
            if (enemy.getHP() <= 0 || role.getHP() <= 0)
            {
                System.out.println("Game Over!");
                break;
            }
        }
    }
}
