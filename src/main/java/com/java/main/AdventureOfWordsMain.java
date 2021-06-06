package com.java.main;

import com.ui.LoginUi;
import com.ui.payment.Alipay;
import com.ui.payment.WeChat;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 开始类
 * <p>游戏项目地址:</p>
 * <a href="https://gitee.com/mengxin_C/adventure-of-words">gitee</a>
 * <a href="https://github.com/mengxinC/Adventureofwords">github</a>
 *@author Millennium
 *@version 0.2.1
*/
public final class AdventureOfWordsMain
{
    private final static String VERSION_PARAMETER = "-version";
    public static void main(String[] args)
    {
        Logger.getGlobal().setLevel(Level.ALL);
        if (args.length != 0 && VERSION_PARAMETER.equalsIgnoreCase(args[0]))
        {
            System.out.println("版本号: 0.1Beta");
            System.exit(0);
        }
        System.out.println("大爷看着赏");
        Alipay.showCollectionCode();
        WeChat.showCollectionCode();
        var account = LoginUi.loginMain();

    }
}
