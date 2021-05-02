package com.java.Tools;


import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * 辅助类
 * @version 1.2.1
 * @author Millennium
 * @since 15
 */
public final class GameTool
{
    public static void main(String[] args)
    {
    }

//    static
//    {
//        System.loadLibrary("AdventureWords-DLL");TODO:重新编译一下,头文件现在路径不对了
//    }

    /**
     * 根据传入的概率随机返回真或假
     *
     * @param probability 返回真的概率
     * @return {@code true}或{@code false}
     */
    public static boolean randomRun (double probability)
    {
        if (probability >= 1.0)
            return true;
        if (probability <= 0.0)
            return false;
        return probability > current().nextDouble();
    }

    /**
     * @param number 要进行加工的整数
     * @param floatingIntRange 浮动的整数范围
     * @return {@code number}加或减 0(包含) ~ {@code floatingIntRange}(包含)的一个数
     * @throws IllegalArgumentException 如果{@code floatingIntRange}小于0
     */
    public static int floatingNumber(int number, int floatingIntRange)//按整数浮动
    {
        if (floatingIntRange < 0)
            throw new IllegalArgumentException("错误范围:" + floatingIntRange);

        var randomNumber = current().nextInt(floatingIntRange + 1);
        return current().nextBoolean() ? number + randomNumber : number - randomNumber;
    }

    public static int floatingNumber(int number, int floatingIntRange, String sign)//按整数浮动
    {
        if (floatingIntRange < 0)
            throw new IllegalArgumentException("错误范围:" + floatingIntRange);

        var randomNumber = current().nextInt(floatingIntRange + 1);
        return switch (sign) {
            case "+" -> number + randomNumber;
            case "-" -> number - randomNumber;
            default -> throw new IllegalArgumentException("异常参数: " + sign);
        };
    }

    /**
     * @param number 要进行加工的整数
     * @param floatingPercentage 浮动的百分比范围
     * @return {@code number}加或减 0(包含) ~ {@code number * floatingPercentage}(包含)的一个数
     * @throws IllegalArgumentException 如果{@code floatingIntRange}小于0
     */
    public static int floatingNumber(int number, double floatingPercentage)//按百分比浮动
    {
        if (floatingPercentage < 0.0)
            throw new IllegalArgumentException("错误范围:" + floatingPercentage);

        var randomNumber = current().nextInt((int) floatingPercentage * number + 1);
        return current().nextBoolean() ? number + randomNumber : number - randomNumber;
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
