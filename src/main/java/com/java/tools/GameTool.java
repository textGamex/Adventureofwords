package com.java.tools;


import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.Objects.requireNonNull;

/**
 * ���ڸ����Ĺ�����
 * @version 1.2.1
 * @author ǧ��
 * @since 15
 */
public final class GameTool
{
    public static void main(String[] args)
    {
    }

//    static
//    {
//        System.loadLibrary("AdventureWords-DLL");TODO:���±���һ��,ͷ�ļ�����·��������
//    }

    /**
     * ���ݴ���ĸ��������������
     *
     * @param probability ������ĸ���
     * @return {@code true}��{@code false}
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
     * ��������ּӻ��һ����Χ�ڵ���
     *
     * @param number Ҫ���мӹ�������
     * @param floatingIntRange ������������Χ(�Ǹ���)
     * @return {@code number}�ӻ�� 0(����) ~ {@code floatingIntRange}(����)��һ����
     * @throws IllegalArgumentException ���{@code floatingIntRange}С��0
     */
    public static int floatingNumber(int number, int floatingIntRange)//����������
    {
        if (floatingIntRange < 0)
            throw new IllegalArgumentException("����Χ:" + floatingIntRange);

        var randomNumber = current().nextInt(floatingIntRange + 1);
        return current().nextBoolean() ? number + randomNumber : number - randomNumber;
    }

    /**
     * ��������ּӻ��һ����Χ�ڵ���
     *
     * @param number Ҫ���мӹ�������
     * @param floatingIntRange ������������Χ(�Ǹ���)
     * @param sign �ֶ�ָ���ĸ�������, ֻ֧��(+, -)
     * @return {@code number}�ӻ��(����{@code sign}��ֵ������) 0(����) ~ {@code floatingIntRange}(����)��һ����
     * @throws IllegalArgumentException ���{@code floatingIntRange}С��0��sign��ֵ�ǷǷ���
     * @throws NullPointerException ���{@code sign}Ϊnull
     */
    public static int floatingNumber(int number, int floatingIntRange, String sign)//����������
    {
        if (floatingIntRange < 0)
            throw new IllegalArgumentException("����Χ:" + floatingIntRange);
        requireNonNull(sign);

        var randomNumber = current().nextInt(floatingIntRange + 1);
        return switch (sign) {
            case "+" -> number + randomNumber;
            case "-" -> number - randomNumber;
            default -> throw new IllegalArgumentException("�쳣����:" + sign);
        };
    }

    /**
     * ��������ּӻ��һ����Χ�ڵ���
     *
     * @param number Ҫ���мӹ�������
     * @param floatingPercentage �����İٷֱȷ�Χ
     * @return {@code number}�ӻ�� 0(����) ~ {@code number * floatingPercentage}(����)��һ����
     * @throws IllegalArgumentException ���{@code floatingPercentage}С��0.0
     */
    public static int floatingNumber(int number, double floatingPercentage)
    {
        if (floatingPercentage < 0.0)
            throw new IllegalArgumentException("����Χ:" + floatingPercentage);

        var randomNumber = current().nextInt((int) floatingPercentage * number + 1);
        return current().nextBoolean() ? number + randomNumber : number - randomNumber;
    }

    /**
     * ��������ּӻ��һ����Χ�ڵ���
     *
     * @param number Ҫ���мӹ�������
     * @param floatingPercentage �����İٷֱȷ�Χ
     * @return {@code number}�ӻ��(����{@code sign}��ֵ������) 0(����) ~ {@code number * floatingPercentage}(����)��һ����
     * @param sign �ֶ�ָ���ĸ�������, ֻ֧��(+, -)
     * @throws IllegalArgumentException ���{@code floatingPercentage}С��0.0��sign��ֵ�ǷǷ���
     * @throws NullPointerException ���{@code sign}Ϊnull
     */
    public static int floatingNumber(int number, double floatingPercentage, String sign)
    {
        if (floatingPercentage < 0.0)
            throw new IllegalArgumentException("����Χ:" + floatingPercentage);

        var randomNumber = current().nextInt((int) floatingPercentage * number + 1);
        return switch (sign) {
            case "+" -> number + randomNumber;
            case "-" -> number - randomNumber;
            default -> throw new IllegalArgumentException("�쳣����:" + sign);
        };
    }
//    public static native void cls();
}
