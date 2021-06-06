package com.ui;

import java.text.DecimalFormat;

/**
 * @author ����ǧ��
 * @author 1.1.0
 */
public final class ConsoleProgressBar
{
    /**
     * ����������
     */
    private final int barLen;

    /**
     * ���ڽ�������ʾ���ַ�
     */
    private final char showChar;

    private final DecimalFormat formater = new DecimalFormat("#.##%");

    /**
     * ʹ��ϵͳ��׼�������ʾ�ַ�����������ٷֱ�
     */
    public ConsoleProgressBar(final int barLen, final char showChar)
    {
        this.barLen = barLen;
        this.showChar = showChar;
    }

    /**
     * ʹ��ָ�����ַ�����ָ��ʱ��.
     *
     * @param totalMillis ������ʱ��(ms)
     * @param length ����������
     * @param showChar �����������ַ�
     */
    public static void loadSpecifiedTime(long totalMillis, int length, char showChar)
    {
        long each = totalMillis / 100;
        showProgressBar(each, length, showChar);
    }

    public static void showProgressBar(final long sleepTime, final int length, final char showChar)
    {
        var cpb = new ConsoleProgressBar(length, showChar);
        for (int i = 1; i <= 100; i++)
        {
            cpb.show(i);
            try
            {
                Thread.sleep(sleepTime);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    /**
     * ��ʾ������.
     *
     * @param value ������
     */
    public void show(final int value)
    {
        if (value < 0 || value > 100)
        {
            return;
        }

        reset();

        // ����
        float rate = (float) (value * 1.0 / 100);
        // ����*�������ܳ���=��ǰ����
        draw(barLen, rate);
        if (value == 100L)
        {
            afterComplete();
        }
    }

    /**
     * ��ָ�����ȸ�showChar.
     *
     * @param rate ���Ȱٷֱ�
     * @param barLength �������ܳ���
     */
    private void draw(final int barLength, final float rate)
    {
        int len = (int) (rate * barLength);
        System.out.print("����: ");
        for (int i = 0; i < len; i++)
        {
            System.out.print(showChar);
        }
        for (int i = 0; i < barLength - len; i++)
        {
            System.out.print(" ");
        }
        System.out.print("|" + format(rate));
    }

    /**
     * ����ƶ�������.
     */
    private void reset()
    {
        System.out.print('\r');
    }

    /**
     * ��ɺ���.
     */
    private void afterComplete()
    {
        System.out.print('\n');
    }

    private String format(float num)
    {
        return formater.format(num);
    }
}
