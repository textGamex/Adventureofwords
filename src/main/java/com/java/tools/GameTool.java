package com.java.tools;

import org.slf4j.LoggerFactory;

import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.Objects.requireNonNull;

/**
 * 用于辅助的工具类.
 *
 * <p>此类无法实例化</p>
 * @version 1.2.3
 * @author 留恋千年
 * @since 15
 */
public final class GameTool
{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GameTool.class);
    public enum SpecifiedDirection
    {
        /**仅增加*/
        ONLY_INCREASE,
        /**仅减少*/
        ONLY_REDUCED
    };

    private GameTool()
    {
        throw new AssertionError();
    }

    static
    {
        System.loadLibrary("randomEngine");
    }

    /**
     * 根据传入的概率随机返回{@code false}或{@code true}.
     *
     * @param trueProbability 返回{@code true}的概率
     * @return {@code true}或{@code false}
     */
    public static boolean randomRun (final double trueProbability)
    {
        if (trueProbability >= 1.0)
        {
            return true;
        }
        if (trueProbability <= 0.0)
        {
            return false;
        }
        return randomReturnBoolean(current().nextInt(), trueProbability);
    }

    /**
     * 随机对数字加或减一个范围内的数.
     *
     * @param number 要进行加工的整数
     * @param floatingIntRange 浮动的整数范围(非负数)
     * @return {@code number}加或减 0(包含) ~ {@code floatingIntRange}(包含)的一个数
     * @throws IllegalArgumentException 如果{@code floatingIntRange}小于0
     */
    public static int floatingNumber(final int number, final int floatingIntRange)//按整数浮动
    {
        if (floatingIntRange < 0)
        {
            throw new IllegalArgumentException("错误范围:" + floatingIntRange);
        }

        var randomNumber = current().nextInt(floatingIntRange + 1);
        return current().nextBoolean() ? number + randomNumber : number - randomNumber;
    }

    /**
     * 随机对数字加或减一个范围内的数.
     *
     * @param number 要进行加工的整数
     * @param floatingIntRange 浮动的整数范围(非负数)
     * @param sign 手动指定的浮动方向, 只支持(+, -)
     * @return {@code number}加或减(根据{@code sign}的值来决定) 0(包含) ~ {@code floatingIntRange}(包含)的一个数
     * @throws IllegalArgumentException 如果{@code floatingIntRange}小于0或sign的值是非法的
     * @throws NullPointerException 如果{@code sign}为null
     */
    public static int floatingNumber(final int number, final int floatingIntRange,
                                     final SpecifiedDirection sign)//按整数浮动
    {
        if (floatingIntRange < 0)
        {
            throw new IllegalArgumentException("错误范围:" + floatingIntRange);
        }
        requireNonNull(sign);

        var randomNumber = current().nextInt(floatingIntRange + 1);
        return switch (sign) {
            case ONLY_INCREASE -> number + randomNumber;
            case ONLY_REDUCED -> number - randomNumber;
        };
    }

    /**
     * 随机对数字加或减一个范围内的数.
     *
     * @param number 要进行加工的整数
     * @param floatingPercentage 浮动的百分比范围
     * @return {@code number}加或减 0(包含) ~ {@code number * floatingPercentage}(包含)的一个数
     * @throws IllegalArgumentException 如果{@code floatingPercentage}小于0.0
     */
    public static int floatingNumber(final int number, final double floatingPercentage)
    {
        if (floatingPercentage < 0.0)
        {
            throw new IllegalArgumentException("错误范围:" + floatingPercentage);
        }

        var randomNumber = current().nextInt((int) floatingPercentage * number + 1);
        return current().nextBoolean() ? number + randomNumber : number - randomNumber;
    }

    /**
     * 随机对数字加或减一个范围内的数.
     *
     * @param number 要进行加工的整数
     * @param floatingPercentage 浮动的百分比范围
     * @return {@code number}加或减(根据{@code sign}的值来决定) 0(包含) ~ {@code number * floatingPercentage}(包含)的一个数
     * @param sign 手动指定的浮动方向, 只支持(+, -)
     * @throws IllegalArgumentException 如果{@code floatingPercentage}小于0.0或sign的值是非法的
     * @throws NullPointerException 如果{@code sign}为null
     */
    public static int floatingNumber(final int number, final double floatingPercentage, final SpecifiedDirection sign)
    {
        if (floatingPercentage < 0.0)
        {
            throw new IllegalArgumentException("错误范围:" + floatingPercentage);
        }
        requireNonNull(sign);

        var randomNumber = current().nextInt((int) floatingPercentage * number + 1);
        return switch (sign) {
            case ONLY_INCREASE -> number + randomNumber;
            case ONLY_REDUCED -> number - randomNumber;
        };
    }

    public static int floatingNumber(final double number, final double floatingPercentage)
    {
        if (floatingPercentage < 0.0)
        {
            throw new IllegalArgumentException("错误范围:" + floatingPercentage);
        }

        final int max = Math.round((float) (floatingPercentage * number)) + 1;
        var randomNumber = current().nextInt(max);
        LOGGER.trace("随机上限为:{}, 随机出的数:{}", max - 1, randomNumber);
        return (int) (current().nextBoolean() ? number + randomNumber : number - randomNumber);
    }

    public static int bytesToInt(byte[] b)
    {
        //由高位到低位
        int res = 0;
        for (int i = 0; i < b.length; i++)
        {
            res += (b[i] & 0xff) << (i * 8);
        }
        return res;
    }

    public static byte[] intToBytes(final int n)
    {
        //由高位到低位
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (n & 0xff);
        bytes[1] = (byte) (n >> 8 & 0xff);
        bytes[2] = (byte) (n >> 16 & 0xff);
        bytes[3] = (byte) (n >> 24 & 0xff);
        return bytes;
    }

    public static long bytesToLong(byte[] b)
    {
        long s = 0;
        // 最低位
        long s0 = b[0] & 0xff;
        long s1 = b[1] & 0xff;
        long s2 = b[2] & 0xff;
        long s3 = b[3] & 0xff;
        // 最低位
        long s4 = b[4] & 0xff;
        long s5 = b[5] & 0xff;
        long s6 = b[6] & 0xff;
        long s7 = b[7] & 0xff;

        // s0不变
        s1 <<= 8;
        s2 <<= 16;
        s3 <<= 24;
        s4 <<= 8 * 4;
        s5 <<= 8 * 5;
        s6 <<= 8 * 6;
        s7 <<= 8 * 7;
        s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
        return s;
    }

    public static byte[] longToBytes(long number)
    {
        long temp = number;
        byte[] b = new byte[8];
        for (int i = 0; i < b.length; i++)
        {
            //将最低位保存在最低位
            b[i] = Long.valueOf(temp & 0xff).byteValue();
            // 向右移8位
            temp = temp >> 8;
        }
        return b;
    }
    public static native void cls();

    /**
     * 根据给定的概率返回一个布尔值.
     *
     * <p>通过调用DLL来实现</p>
     * @param seed 种子值
     * @param trueProbability 返回{@code true}的概率
     * @return {@code true}或{@code false}
     */
    public static native boolean randomReturnBoolean(int seed, double trueProbability);
}
