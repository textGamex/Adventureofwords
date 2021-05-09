package com.java.tools;


import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.Objects.requireNonNull;

/**
 * 用于辅助的工具类
 * @version 1.2.1
 * @author 千年
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
     * 随机对数字加或减一个范围内的数
     *
     * @param number 要进行加工的整数
     * @param floatingIntRange 浮动的整数范围(非负数)
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

    /**
     * 随机对数字加或减一个范围内的数
     *
     * @param number 要进行加工的整数
     * @param floatingIntRange 浮动的整数范围(非负数)
     * @param sign 手动指定的浮动方向, 只支持(+, -)
     * @return {@code number}加或减(根据{@code sign}的值来决定) 0(包含) ~ {@code floatingIntRange}(包含)的一个数
     * @throws IllegalArgumentException 如果{@code floatingIntRange}小于0或sign的值是非法的
     * @throws NullPointerException 如果{@code sign}为null
     */
    public static int floatingNumber(int number, int floatingIntRange, String sign)//按整数浮动
    {
        if (floatingIntRange < 0)
            throw new IllegalArgumentException("错误范围:" + floatingIntRange);
        requireNonNull(sign);

        var randomNumber = current().nextInt(floatingIntRange + 1);
        return switch (sign) {
            case "+" -> number + randomNumber;
            case "-" -> number - randomNumber;
            default -> throw new IllegalArgumentException("异常参数:" + sign);
        };
    }

    /**
     * 随机对数字加或减一个范围内的数
     *
     * @param number 要进行加工的整数
     * @param floatingPercentage 浮动的百分比范围
     * @return {@code number}加或减 0(包含) ~ {@code number * floatingPercentage}(包含)的一个数
     * @throws IllegalArgumentException 如果{@code floatingPercentage}小于0.0
     */
    public static int floatingNumber(int number, double floatingPercentage)
    {
        if (floatingPercentage < 0.0)
            throw new IllegalArgumentException("错误范围:" + floatingPercentage);

        var randomNumber = current().nextInt((int) floatingPercentage * number + 1);
        return current().nextBoolean() ? number + randomNumber : number - randomNumber;
    }

    /**
     * 随机对数字加或减一个范围内的数
     *
     * @param number 要进行加工的整数
     * @param floatingPercentage 浮动的百分比范围
     * @return {@code number}加或减(根据{@code sign}的值来决定) 0(包含) ~ {@code number * floatingPercentage}(包含)的一个数
     * @param sign 手动指定的浮动方向, 只支持(+, -)
     * @throws IllegalArgumentException 如果{@code floatingPercentage}小于0.0或sign的值是非法的
     * @throws NullPointerException 如果{@code sign}为null
     */
    public static int floatingNumber(int number, double floatingPercentage, String sign)
    {
        if (floatingPercentage < 0.0)
            throw new IllegalArgumentException("错误范围:" + floatingPercentage);

        var randomNumber = current().nextInt((int) floatingPercentage * number + 1);
        return switch (sign) {
            case "+" -> number + randomNumber;
            case "-" -> number - randomNumber;
            default -> throw new IllegalArgumentException("异常参数:" + sign);
        };
    }
//    public static native void cls();
}
