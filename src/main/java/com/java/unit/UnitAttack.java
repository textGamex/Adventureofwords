package com.java.unit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static com.java.antiSpam.MemoryEncryption.*;

/**
 * @author 留恋千年
 * @version 1.1.1
 */
public class UnitAttack implements Serializable
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
            //不知道好使不好使
            keyInt = SecureRandom.getInstanceStrong().nextInt();
            keyLong = SecureRandom.getInstanceStrong().nextLong();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }

    private static final long DOUBLE_ACCURACY = 10000000000000L;
    /**物理攻击*/
    private int physicalAttack;
    /**法术伤害*/
    private int magicAttack;
    /**暴击 */
    private int crit;
    /**暴击效果*/
    private long critsEffect;
    /**命中*/
    private int hit;
    /**最大法力值*/
    private int maxMana;
    /**法力值*/
    private int mana;
    /**每回合法力值恢复*/
    private int manaRecovery;

    /**
     *
     * @return 返回此单位的物理攻击
     */
    public int getPhysicalAttack()
    {
        return physicalAttack ^ keyInt;
    }

    public void setPhysicalAttack(final int physicalAttack)
    {
        this.physicalAttack = physicalAttack ^ keyInt;
    }

    /**
     *
     * @return 返回此单位的魔法攻击
     */
    public int getMagicAttack()
    {
        return magicAttack ^ keyInt;
    }

    public void setMagicAttack(final int magicAttack)
    {
        this.magicAttack = magicAttack ^ keyInt;
    }

    /**
     *
     * @return 返回此单位的暴击
     */
    public int getCrit()
    {
        return crit ^ keyInt;
    }

    public void setCrit(final int crit)
    {
        this.crit = crit ^ keyInt;
    }

    /**
     *
     * @return 返回此单位的暴击效果
     */
    public double getCritsEffect()
    {
        return (double) (critsEffect ^ keyLong) / DOUBLE_ACCURACY;
    }

    public void setCritsEffect(final double critsEffect)
    {
        LOGGER.trace("传入值:{}", critsEffect);
        long v = (long) (critsEffect * DOUBLE_ACCURACY);
        LOGGER.trace("转换为整数:{}", v);
        this.critsEffect = v ^ keyLong;
        LOGGER.trace("实际存储值:{}", this.critsEffect);
    }

    /**
     *
     * @return 返回此单位的命中
     */
    public int getHit()
    {
        return hit ^ keyInt;
    }

    public void setHit(final int hit)
    {
        this.hit = hit ^ keyInt;
    }

    /**
     * @return 返回此单位的最大法力值
     */
    public int getMaxMana()
    {
        return decryptInt(maxMana);
    }

    /**
     * @param maxMana 此单位的最大法力值
     */
    public void setMaxMana(int maxMana)
    {
        this.maxMana = encryptionInt(maxMana);
    }

    /**
     *
     * @return 返回此单位的法力值
     */
    public int getMana()
    {
        return mana ^ keyInt;
    }

    public void setMana(final int mana)
    {
        this.mana = mana ^ keyInt;
    }

    /**
     *
     * @return 返回此单位的每回合法力值回复
     */
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
