package com.java.unit;

import java.io.Serializable;

/**
 * @author 留恋千年
 */
public class UnitDefense implements Serializable
{
    /**最大生命值*/
    private int maxHp;
    /**生命值*/
    private int hp;
    /**物理抗性*/
    private double physicalResistance;
    /**魔法抗性*/
    private double magicResistance;
    /**护甲*/
    private int armor;
    /**闪避*/
    private int evade;
    /**每回合生命回复*/
    private int lifeRegeneration;
    /**暴击抗性 */
    private int critResistance;

    /**
     *
     * @return 返回单位的最大生命值
     */
    public int getMaxHp()
    {
        return maxHp;
    }

    public void setMaxHp(final int maxHp)
    {
        this.maxHp = maxHp;
    }

    /**
     *
     * @return 返回单位的生命值
     */
    public int getHp()
    {
        return hp;
    }

    public void setHp(final int hp)
    {
        this.hp = hp;
    }

    /**
     *
     * @return 返回单位的
     */
    public double getPhysicalResistance()
    {
        return physicalResistance;
    }

    public void setPhysicalResistance(final double physicalResistance)
    {
        this.physicalResistance = physicalResistance;
    }

    /**
     *
     * @return 返回单位的闪避
     */
    public int getArmor()
    {
        return  armor;
    }

    public void setArmor(final int armor)
    {
        this.armor = armor;
    }

    /**
     *
     * @return 返回单位的闪避
     */
    public int getEvade()
    {
        return evade;
    }


    public void setEvade(final int evade)
    {
        this.evade = evade;
    }

    /**
     *
     * @return 返回单位的每回合生命回复
     */
    public int getLifeRegeneration()
    {
        return lifeRegeneration;
    }

    public void setLifeRegeneration(final int lifeRegeneration)
    {
        this.lifeRegeneration = lifeRegeneration;
    }

    /**
     *
     * @return 返回单位的暴击抗性
     */
    public int getCritResistance()
    {
        return critResistance;
    }

    public void setCritResistance(final int critResistance)
    {
        this.critResistance = critResistance;
    }

    /**
     *
     * @return 返回单位的魔法抗性
     */
    public double getMagicResistance()
    {
        return magicResistance;
    }

    public void setMagicResistance(final double magicResistance)
    {
        this.magicResistance = magicResistance;
    }
}