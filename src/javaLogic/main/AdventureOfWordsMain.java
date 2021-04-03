package javaLogic.main;

import javaLogic.CombatSystem.BattleSystem;
import javaLogic.Unit.EnemyUnit;
import javaLogic.Unit.RoleAttribute;
import ui.LoginUi;

/**
 *开始类
 *@author Millennium
 *@version 0.13
 *@Date 2021/3/13 0:28
*/
public final class AdventureOfWordsMain
{
    public static void main(String[] args)
    {
        if (args.length != 0 && args[0].equalsIgnoreCase("-version"))
        {
            System.out.println("版本号: 0.1Beta");
            System.exit(0);
        }

        var account = LoginUi.loginMain();
        var role = new RoleAttribute();
        var enemy = EnemyUnit.getEnemyUnit("哥布林");
        
        BattleSystem.Main(role, enemy);
    }
}
