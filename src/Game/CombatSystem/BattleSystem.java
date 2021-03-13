package Game.CombatSystem;

import Game.Tools.GameTool;
import Game.Unit.RoleAttribute;
/**
 * 战斗系统
 * @version 0.13
 * @author Millennium
 */
public class BattleSystem
{
    public static void main(String[] args)
    {
        var role = new RoleAttribute();
        var enemy = new RoleAttribute();
        BattleSystem.Main(role, enemy);
    }
    public static void Main(RoleAttribute role, RoleAttribute enemy)
    {
        int ATK = 0;
        System.out.println("战斗开始!!!");
        while (true)
        {
            if (enemy.getHP() <= 0 || role.getHP() <= 0)
            {
                System.out.println("Game Over!");
                break;
            }
            ATK = (int) (GameTool.randomRun(enemy.getCRIT()) ? enemy.getATK() * enemy.getCritsEffect() : enemy.getATK());
            System.out.println("你造成了" + role.subtractHP(ATK, enemy.getFixArmorPen(), enemy.getPerArmorPen()) + "的伤害");
            System.out.println("他还剩" + role.getHP());
        }
    }
}
