package com.java.Tools;

import java.util.Random;

/**
 * ������
 * @version 0.2
 * @author Millennium
 */
public final class GameTool
{
    public static void main(String[] args)
    {
//        final int MAX = 10000000;
//        int count = 0;
//        for (int i = 0; i < MAX; i++)
//        {
//            if(randomRun(0.5f))
//                count++;
//        }
//        System.out.println(count);
        int a = floatingNumber(1, 1, "+");
        System.out.println("asjnffs");
//        GameTool.cls();
        System.out.println("�ɹ�");
    }
//    static
//    {
//        System.loadLibrary("AdventureWords-DLL");TODO:���±���һ��,ͷ�ļ�����·��������
//    }
    /**
     * ���ݴ���ĸ��������������
     * runSize�Ƿ�����ĸ���
     * @return true��false
     */
    public static boolean randomRun (float runSize)
    {
        return runSize > Math.random();
    }
    /**
     *
     *@author Millennium
     *@Date 2021/3/13 11:45
    */
    public static int floatingNumber(int number, int floatingRange)//����������
    {
        var rand = new Random();
        int randomNumber = rand.nextInt(floatingRange + 1);
        return randomRun(0.5f) ? number + randomNumber : number - randomNumber;
    }
    public static int floatingNumber(int number, float floatingPercentage)//���ٷֱȸ���
    {
        var rand = new Random();
        int randomNumber = rand.nextInt((int) floatingPercentage * number + 1);
        return randomRun(0.5f) ? number + randomNumber : number - randomNumber;
    }
    public static int floatingNumber(int number, int floatingRange, String sign) throws IllegalArgumentException//����������
    {
        var rand = new Random();
        int randomNumber = rand.nextInt(floatingRange + 1);
        return switch (sign) {
            case "+" -> number + randomNumber;
            case "-" -> number - randomNumber;
            default -> throw new IllegalArgumentException("�쳣����: " + sign);
        };
    }
    public static int floatingNumber(int number, float floatingPercentage, String sign) throws IllegalArgumentException//���ٷֱȸ���
    {
        var rand = new Random();
        int randomNumber = rand.nextInt((int) floatingPercentage * number + 1);
        return switch (sign) {
            case "+" -> number + randomNumber;
            case "-" -> number - randomNumber;
            default -> throw new IllegalArgumentException("�쳣����: " + sign);
        };
    }
//    public static native void cls();
}
