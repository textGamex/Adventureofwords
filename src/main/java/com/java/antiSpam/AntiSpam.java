package com.java.antiSpam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author ǧ��
 * @since 2021-5-30
 */
public class AntiSpam
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AntiSpam.class);
    private static int keyInt;
    private static long keyLong;
    static
    {
        try
        {
            //��֪����ʹ����ʹ
            keyInt = SecureRandom.getInstanceStrong().nextInt();;
            keyLong = SecureRandom.getInstanceStrong().nextLong();;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }
    public static final long DOUBLE_ACCURACY = 100000000L;

    public static int getKeyInt()
    {
        return keyInt;
    }

    public static long getKeyLong()
    {
        return keyLong;
    }

    /**
     * ����.
     *
     * @param value Ҫ���ܵ�ֵ
     * @return ���ؾ������ܵ� {@code value}
     */
    public static int encryptionInt(final int value)
    {
        return value ^ keyInt;
    }

    /**
     * ����.
     *
     * @param value Ҫ���ܵ�ֵ
     * @return ���ؾ������ܵ� {@code value}
     */
    public static long encryptionDouble(final double value)
    {
        //TODO:��bug, ��double�ܴ�ʱ�����, ��ʱ���doubleȥ��
        LOGGER.trace("����ֵ:{}", value);
        long v = (long) ( value * DOUBLE_ACCURACY);
        LOGGER.trace("ת��Ϊ����:{}", v);
        var result =  v ^ keyLong;
        LOGGER.trace("���ܺ�:{}", result);
        return result;
    }

    /**
     * ����.
     *
     * @param valueLock Ҫ���ܵ�ֵ
     * @return ����δ����֮ǰ��ֵ
     */
    public static int decryptInt(final int valueLock)
    {
        return valueLock ^ keyInt;
    }

    /**
     * ����.
     *
     * @param valueLock Ҫ���ܵ�ֵ
     * @return ����δ����֮ǰ��ֵ
     */
    public static double decryptDouble(final long valueLock)
    {
        double result = (double) (valueLock ^ keyLong) / DOUBLE_ACCURACY;
        LOGGER.trace("����ǰ:{}, ���ܺ�:{}", valueLock, result);
        return result;
    }
}