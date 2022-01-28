package com.java.main;

import java.io.FileNotFoundException;

/**
 * 开始类
 * <p>游戏项目地址:</p>
 * <a href="https://gitee.com/mengxin_C/adventure-of-words">gitee</a>
 * <a href="https://github.com/mengxinC/Adventureofwords">github</a>
 *@author 留恋千年
 *@version 1.2.1
*/
public final class AdventureOfWordsMain
{
    private final static String VERSION_PARAMETER = "-version";
    public static void main(String[] args)
    {
        if (args.length != 0 && VERSION_PARAMETER.equalsIgnoreCase(args[0]))
        {
            System.out.println("版本号: 0.3.1Beta");
            System.exit(0);
        }
        var object = new TestUse();
        try
        {
            object.start();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
