package com.java.tools;

import org.slf4j.LoggerFactory;

import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.Objects.requireNonNull;

/**
 * ���ڸ����Ĺ�����.
 *
 * <p>�����޷�ʵ����</p>
 * @version 1.2.3
 * @author ����ǧ��
 * @since 15
 */
public final class GameTool
{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GameTool.class);
    public enum SpecifiedDirection
    {
        /**������*/
        ONLY_INCREASE,
        /**������*/
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
     * ���ݴ���ĸ����������{@code false}��{@code true}.
     *
     * @param trueProbability ����{@code true}�ĸ���
     * @return {@code true}��{@code false}
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
     * ��������ּӻ��һ����Χ�ڵ���.
     *
     * @param number Ҫ���мӹ�������
     * @param floatingIntRange ������������Χ(�Ǹ���)
     * @return {@code number}�ӻ�� 0(����) ~ {@code floatingIntRange}(����)��һ����
     * @throws IllegalArgumentException ���{@code floatingIntRange}С��0
     */
    public static int floatingNumber(final int number, final int floatingIntRange)//����������
    {
        if (floatingIntRange < 0)
        {
            throw new IllegalArgumentException("����Χ:" + floatingIntRange);
        }

        var randomNumber = current().nextInt(floatingIntRange + 1);
        return current().nextBoolean() ? number + randomNumber : number - randomNumber;
    }

    /**
     * ��������ּӻ��һ����Χ�ڵ���.
     *
     * @param number Ҫ���мӹ�������
     * @param floatingIntRange ������������Χ(�Ǹ���)
     * @param sign �ֶ�ָ���ĸ�������, ֻ֧��(+, -)
     * @return {@code number}�ӻ��(����{@code sign}��ֵ������) 0(����) ~ {@code floatingIntRange}(����)��һ����
     * @throws IllegalArgumentException ���{@code floatingIntRange}С��0��sign��ֵ�ǷǷ���
     * @throws NullPointerException ���{@code sign}Ϊnull
     */
    public static int floatingNumber(final int number, final int floatingIntRange,
                                     final SpecifiedDirection sign)//����������
    {
        if (floatingIntRange < 0)
        {
            throw new IllegalArgumentException("����Χ:" + floatingIntRange);
        }
        requireNonNull(sign);

        var randomNumber = current().nextInt(floatingIntRange + 1);
        return switch (sign) {
            case ONLY_INCREASE -> number + randomNumber;
            case ONLY_REDUCED -> number - randomNumber;
        };
    }

    /**
     * ��������ּӻ��һ����Χ�ڵ���.
     *
     * @param number Ҫ���мӹ�������
     * @param floatingPercentage �����İٷֱȷ�Χ
     * @return {@code number}�ӻ�� 0(����) ~ {@code number * floatingPercentage}(����)��һ����
     * @throws IllegalArgumentException ���{@code floatingPercentage}С��0.0
     */
    public static int floatingNumber(final int number, final double floatingPercentage)
    {
        if (floatingPercentage < 0.0)
        {
            throw new IllegalArgumentException("����Χ:" + floatingPercentage);
        }

        var randomNumber = current().nextInt((int) floatingPercentage * number + 1);
        return current().nextBoolean() ? number + randomNumber : number - randomNumber;
    }

    /**
     * ��������ּӻ��һ����Χ�ڵ���.
     *
     * @param number Ҫ���мӹ�������
     * @param floatingPercentage �����İٷֱȷ�Χ
     * @return {@code number}�ӻ��(����{@code sign}��ֵ������) 0(����) ~ {@code number * floatingPercentage}(����)��һ����
     * @param sign �ֶ�ָ���ĸ�������, ֻ֧��(+, -)
     * @throws IllegalArgumentException ���{@code floatingPercentage}С��0.0��sign��ֵ�ǷǷ���
     * @throws NullPointerException ���{@code sign}Ϊnull
     */
    public static int floatingNumber(final int number, final double floatingPercentage, final SpecifiedDirection sign)
    {
        if (floatingPercentage < 0.0)
        {
            throw new IllegalArgumentException("����Χ:" + floatingPercentage);
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
            throw new IllegalArgumentException("����Χ:" + floatingPercentage);
        }

        final int max = Math.round((float) (floatingPercentage * number)) + 1;
        var randomNumber = current().nextInt(max);
        LOGGER.trace("�������Ϊ:{}, ���������:{}", max - 1, randomNumber);
        return (int) (current().nextBoolean() ? number + randomNumber : number - randomNumber);
    }

    public static int bytesToInt(byte[] b)
    {
        //�ɸ�λ����λ
        int res = 0;
        for (int i = 0; i < b.length; i++)
        {
            res += (b[i] & 0xff) << (i * 8);
        }
        return res;
    }

    public static byte[] intToBytes(final int n)
    {
        //�ɸ�λ����λ
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
        // ���λ
        long s0 = b[0] & 0xff;
        long s1 = b[1] & 0xff;
        long s2 = b[2] & 0xff;
        long s3 = b[3] & 0xff;
        // ���λ
        long s4 = b[4] & 0xff;
        long s5 = b[5] & 0xff;
        long s6 = b[6] & 0xff;
        long s7 = b[7] & 0xff;

        // s0����
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
            //�����λ���������λ
            b[i] = Long.valueOf(temp & 0xff).byteValue();
            // ������8λ
            temp = temp >> 8;
        }
        return b;
    }
    public static native void cls();

    /**
     * ���ݸ����ĸ��ʷ���һ������ֵ.
     *
     * <p>ͨ������DLL��ʵ��</p>
     * @param seed ����ֵ
     * @param trueProbability ����{@code true}�ĸ���
     * @return {@code true}��{@code false}
     */
    public static native boolean randomReturnBoolean(int seed, double trueProbability);
}
