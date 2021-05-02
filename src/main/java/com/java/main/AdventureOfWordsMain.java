package com.java.main;

import com.ui.LoginUi;
import com.ui.payment.Alipay;
import com.ui.payment.WeChat;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *开始类
 *@author Millennium
 *@version 0.2.0
 *@Date 2021/3/13 0:28
*/
public final class AdventureOfWordsMain
{
    public static void main(String[] args)
    {
        Logger.getGlobal().setLevel(Level.ALL);
        if (args.length != 0 && args[0].equalsIgnoreCase("-version"))
        {
            System.out.println("版本号: 0.1Beta");
            System.exit(0);
        }
        System.out.println("大爷看着赏");
        Alipay.showCollectionCode();
        WeChat.showCollectionCode();
        var account = LoginUi.loginMain();

//        var role = new RoleAttribute();
//        var enemy = EnemyUnit.getEnemyUnit("哥布林");
        
//        BattleSystem.Main(role, enemy);
    }
}
