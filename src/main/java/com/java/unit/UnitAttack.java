package com.java.unit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author ǧ��
 */
public class UnitAttack
{
    public static void main(String[] args)
    {
        var a = new UnitAttack();
        a.setCritsEffect(0.15555555555558);
        System.out.println(a.getCritsEffect());
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(UnitAttack.class);
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
    private static final long DOUBLE_ACCURACY = 10000000000000L;
    /**������*/
    private int physicalAttack;
    /**�����˺�*/
    private int magicAttack;
    /**���� */
    private int crit;
    /**����Ч��*/
    private long critsEffect;
    /**����*/
    private int hit;
    /**����ֵ*/
    private int mana;
    /**ÿ�غϷ���ֵ�ָ�*/
    private int manaRecovery;

    public int getPhysicalAttack()
    {
        return physicalAttack ^ keyInt;
    }

    public void setPhysicalAttack(final int physicalAttack)
    {
        this.physicalAttack = physicalAttack ^ keyInt;
    }

    public int getMagicAttack()
    {
        return magicAttack ^ keyInt;
    }

    public void setMagicAttack(final int magicAttack)
    {
        this.magicAttack = magicAttack ^ keyInt;
    }

    public int getCrit()
    {
        return crit ^ keyInt;
    }

    public void setCrit(final int crit)
    {
        this.crit = crit ^ keyInt;
    }

    public double getCritsEffect()
    {
        return (double) (critsEffect ^ keyLong) / DOUBLE_ACCURACY;
    }

    public void setCritsEffect(final double critsEffect)
    {
        LOGGER.trace("����ֵ:{}", critsEffect);
        long v = (long) (critsEffect * DOUBLE_ACCURACY);
        LOGGER.trace("ת��Ϊ����:{}", v);
        this.critsEffect = v ^ keyLong;
        LOGGER.trace("ʵ�ʴ洢ֵ:{}", this.critsEffect);
    }

    public int getHit()
    {
        return hit ^ keyInt;
    }

    public void setHit(final int hit)
    {
        this.hit = hit ^ keyInt;
    }

    public int getMana()
    {
        return mana ^ keyInt;
    }

    public void setMana(final int mana)
    {
        this.mana = mana ^ keyInt;
    }

    public int getManaRecovery()
    {
        return manaRecovery ^ keyInt;
    }

    public void setManaRecovery(final int manaRecovery)
    {
        this.manaRecovery = manaRecovery ^ keyInt;
    }

    public static int getKeyInt()
    {
        return keyInt;
    }
}
