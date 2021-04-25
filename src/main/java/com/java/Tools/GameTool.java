package com.java.Tools;


import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * 辅助类
 * @version 0.21
 * @author Millennium
 */
public final class GameTool
{
    public static void main(String[] args)
    {
//        int a = floatingNumber(1, 1, "+");
//        System.out.println("asjnffs");
//        GameTool.cls();
//        System.out.println("成功");
    }
//    static
//    {
//        System.loadLibrary("AdventureWords-DLL");TODO:重新编译一下,头文件现在路径不对了
//    }
    /**
     * 根据传入的概率随机返回真或假
     * runSize是返回真的概率
     * @return true或false
     */
    public static boolean randomRun (double runSize)
    {
        return runSize > Math.random();
    }
    /**
     *@author Millennium
     *@Date 2021/3/13 11:45
    */
    public static int floatingNumber(int number, int floatingRange)//按整数浮动
    {
        var randomNumber = current().nextInt(floatingRange + 1);
        return current().nextBoolean() ? number + randomNumber : number - randomNumber;
    }
    public static int floatingNumber(int number, double floatingPercentage)//按百分比浮动
    {
        var randomNumber = current().nextInt((int) floatingPercentage * number + 1);
        return current().nextBoolean() ? number + randomNumber : number - randomNumber;
    }
    public static int floatingNumber(int number, int floatingRange, String sign)//按整数浮动
    {
        var randomNumber = current().nextInt(floatingRange + 1);
        return switch (sign) {
            case "+" -> number + randomNumber;
            case "-" -> number - randomNumber;
            default -> throw new IllegalArgumentException("异常参数: " + sign);
        };
    }
    public static int floatingNumber(int number, double floatingPercentage, String sign)//按百分比浮动
    {
        var randomNumber = current().nextInt((int) floatingPercentage * number + 1);
        return switch (sign) {
            case "+" -> number + randomNumber;
            case "-" -> number - randomNumber;
            default -> throw new IllegalArgumentException("异常参数: " + sign);
        };
    }
//    public static native void cls();
}
