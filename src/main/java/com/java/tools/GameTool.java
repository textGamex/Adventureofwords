package com.java.tools;


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
    public enum SpecifiedDirection
    {
        /**������*/
        ONLY_INCREASE,
        /**������*/
        ONLY_REDUCED
    };

    public static void main(String[] args)
    {
    }

    private GameTool()
    {
        throw new AssertionError();
    }
//    static
//    {
//        System.loadLibrary("AdventureWords-DLL");TODO:���±���һ��,ͷ�ļ�����·��������
//    }

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
        return trueProbability > current().nextDouble();
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

//    public static native void cls();
}
