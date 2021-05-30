package com.java.antiSpam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author 千年
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
            //不知道好使不好使
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
     * 加密.
     *
     * @param value 要加密的值
     * @return 返回经过加密的 {@code value}
     */
    public static int encryptionInt(final int value)
    {
        return value ^ keyInt;
    }

    /**
     * 加密.
     *
     * @param value 要加密的值
     * @return 返回经过加密的 {@code value}
     */
    public static long encryptionDouble(final double value)
    {
        //TODO:有bug, 当double很大时会溢出, 有时间把double去了
        LOGGER.trace("传入值:{}", value);
        long v = (long) ( value * DOUBLE_ACCURACY);
        LOGGER.trace("转换为整数:{}", v);
        var result =  v ^ keyLong;
        LOGGER.trace("加密后:{}", result);
        return result;
    }

    /**
     * 解密.
     *
     * @param valueLock 要解密的值
     * @return 返回未加密之前的值
     */
    public static int decryptInt(final int valueLock)
    {
        return valueLock ^ keyInt;
    }

    /**
     * 解密.
     *
     * @param valueLock 要解密的值
     * @return 返回未加密之前的值
     */
    public static double decryptDouble(final long valueLock)
    {
        double result = (double) (valueLock ^ keyLong) / DOUBLE_ACCURACY;
        LOGGER.trace("解密前:{}, 解密后:{}", valueLock, result);
        return result;
    }
}