package Game.Main;

import Game.CombatSystem.BattleSystem;
import Game.Unit.RoleAttribute;
/**
 *开始类
 *@author Millennium
 *@version 0.11
 *@Date 2021/3/13 0:28
*/
public class AdventureOfWordsMain
{
    public static void main(String[] args)
    {
        if (args.length != 0 && args[0].equalsIgnoreCase("-version"))
        {
            System.out.println("版本号: 0.1Beta");
            System.exit(0);
        }

        var role = new RoleAttribute();
        System.out.println("123");
        var enemy = new RoleAttribute();
//        BattleSystem.Main(role, enemy);
    }
}
