package com.ui;

import java.text.DecimalFormat;

/**
 * @author 留恋千年
 * @author 1.1.0
 */
public final class ConsoleProgressBar
{
    /**
     * 进度条长度
     */
    private final int barLen;

    /**
     * 用于进度条显示的字符
     */
    private final char showChar;

    private final DecimalFormat formater = new DecimalFormat("#.##%");

    /**
     * 使用系统标准输出，显示字符进度条及其百分比
     */
    public ConsoleProgressBar(final int barLen, final char showChar)
    {
        this.barLen = barLen;
        this.showChar = showChar;
    }

    /**
     * 使用指定的字符加载指定时间.
     *
     * @param totalMillis 加载总时间(ms)
     * @param length 进度条长度
     * @param showChar 进度条所用字符
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
     * 显示进度条.
     *
     * @param value 最大进度
     */
    public void show(final int value)
    {
        if (value < 0 || value > 100)
        {
            return;
        }

        reset();

        // 比例
        float rate = (float) (value * 1.0 / 100);
        // 比例*进度条总长度=当前长度
        draw(barLen, rate);
        if (value == 100L)
        {
            afterComplete();
        }
    }

    /**
     * 画指定长度个showChar.
     *
     * @param rate 进度百分比
     * @param barLength 进度条总长度
     */
    private void draw(final int barLength, final float rate)
    {
        int len = (int) (rate * barLength);
        System.out.print("进度: ");
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
     * 光标移动到行首.
     */
    private void reset()
    {
        System.out.print('\r');
    }

    /**
     * 完成后换行.
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
