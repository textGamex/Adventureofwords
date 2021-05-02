package com.java.Tools;


import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * ������
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
     * @param number Ҫ���мӹ�������
     * @param floatingIntRange ������������Χ
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

    public static int floatingNumber(int number, int floatingIntRange, String sign)//����������
    {
        if (floatingIntRange < 0)
            throw new IllegalArgumentException("����Χ:" + floatingIntRange);

        var randomNumber = current().nextInt(floatingIntRange + 1);
        return switch (sign) {
            case "+" -> number + randomNumber;
            case "-" -> number - randomNumber;
            default -> throw new IllegalArgumentException("�쳣����: " + sign);
        };
    }

    /**
     * @param number Ҫ���мӹ�������
     * @param floatingPercentage �����İٷֱȷ�Χ
     * @return {@code number}�ӻ�� 0(����) ~ {@code number * floatingPercentage}(����)��һ����
     * @throws IllegalArgumentException ���{@code floatingIntRange}С��0
     */
    public static int floatingNumber(int number, double floatingPercentage)//���ٷֱȸ���
    {
        if (floatingPercentage < 0.0)
            throw new IllegalArgumentException("����Χ:" + floatingPercentage);

        var randomNumber = current().nextInt((int) floatingPercentage * number + 1);
        return current().nextBoolean() ? number + randomNumber : number - randomNumber;
    }

    public static int floatingNumber(int number, double floatingPercentage, String sign)//���ٷֱȸ���
    {
        var randomNumber = current().nextInt((int) floatingPercentage * number + 1);
        return switch (sign) {
            case "+" -> number + randomNumber;
            case "-" -> number - randomNumber;
            default -> throw new IllegalArgumentException("�쳣����: " + sign);
        };
    }
//    public static native void cls();
}
