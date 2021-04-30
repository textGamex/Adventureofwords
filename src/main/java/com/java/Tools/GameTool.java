package com.java.Tools;


import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * ������
 * @version 0.21
 * @author Millennium
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
     * @param probability ������ĸ���
     * @return true��false
     */
    public static boolean randomRun (double probability)
    {
        if (probability >= 1.0)
            return true;
        if (probability <= 0.0)
            return false;
        return probability > Math.random();
    }

    /**
     *@Date 2021/3/13 11:45
    */
    public static int floatingNumber(int number, int floatingRange)//����������
    {
        var randomNumber = current().nextInt(floatingRange + 1);
        return current().nextBoolean() ? number + randomNumber : number - randomNumber;
    }

    public static int floatingNumber(int number, double floatingPercentage)//���ٷֱȸ���
    {
        var randomNumber = current().nextInt((int) floatingPercentage * number + 1);
        return current().nextBoolean() ? number + randomNumber : number - randomNumber;
    }

    public static int floatingNumber(int number, int floatingRange, String sign)//����������
    {
        var randomNumber = current().nextInt(floatingRange + 1);
        return switch (sign) {
            case "+" -> number + randomNumber;
            case "-" -> number - randomNumber;
            default -> throw new IllegalArgumentException("�쳣����: " + sign);
        };
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
