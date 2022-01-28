package com.java.unit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author 留恋千年
 * @version 1.1.1
 */
public class UnitAttack implements Serializable
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UnitAttack.class);
    /**物理攻击*/
    private int physicalAttack;
    /**法术伤害*/
    private int magicAttack;
    /**暴击 */
    private int crit;
    /**暴击效果*/
    private double critsEffect;
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
        return physicalAttack;
    }

    public void setPhysicalAttack(final int physicalAttack)
    {
        this.physicalAttack = physicalAttack;
    }

    /**
     *
     * @return 返回此单位的魔法攻击
     */
    public int getMagicAttack()
    {
        return magicAttack;
    }

    public void setMagicAttack(final int magicAttack)
    {
        this.magicAttack = magicAttack;
    }

    /**
     *
     * @return 返回此单位的暴击
     */
    public int getCrit()
    {
        return crit;
    }

    public void setCrit(final int crit)
    {
        this.crit = crit;
    }

    /**
     *
     * @return 返回此单位的暴击效果
     */
    public double getCritsEffect()
    {
        return critsEffect;
    }

    public void setCritsEffect(final double critsEffect)
    {
        this.critsEffect = critsEffect;
    }

    /**
     *
     * @return 返回此单位的命中
     */
    public int getHit()
    {
        return hit;
    }

    public void setHit(final int hit)
    {
        this.hit = hit;
    }

    /**
     * @return 返回此单位的最大法力值
     */
    public int getMaxMana()
    {
        return maxMana;
    }

    /**
     * @param maxMana 此单位的最大法力值
     */
    public void setMaxMana(int maxMana)
    {
        this.maxMana = maxMana;
    }

    /**
     *
     * @return 返回此单位的法力值
     */
    public int getMana()
    {
        return mana;
    }

    public void setMana(final int mana)
    {
        this.mana = mana;
    }

    /**
     *
     * @return 返回此单位的每回合法力值回复
     */
    public int getManaRecovery()
    {
        return manaRecovery;
    }

    public void setManaRecovery(final int manaRecovery)
    {
        this.manaRecovery = manaRecovery;
    }
}
