package com.java.unit;

/**
 * @author ǧ��
 */
public class UnitDefense
{
    /**�������ֵ*/
    private int maxHp;
    /**����ֵ*/
    private int hp;
    /**������*/
    private double physicalResistance;
    /**ħ������*/
    private double magicResistance;
    /**����*/
    private int armor;
    /**����*/
    private int evade;
    /**ÿ�غ������ظ�*/
    private int lifeRegeneration;
    /**�������� */
    private int critResistance;

    public int getMaxHp()
    {
        return maxHp;
    }

    public void setMaxHp(final int maxHp)
    {
        this.maxHp = maxHp;
    }

    public int getHp()
    {
        return hp;
    }

    public void setHp(final int hp)
    {
        this.hp = hp;
    }

    public double getPhysicalResistance()
    {
        return physicalResistance;
    }

    public void setPhysicalResistance(final double physicalResistance)
    {
        this.physicalResistance = physicalResistance;
    }

    public int getArmor()
    {
        return armor;
    }

    public void setArmor(final int armor)
    {
        this.armor = armor;
    }

    public int getEvade()
    {
        return evade;
    }

    public void setEvade(final int evade)
    {
        this.evade = evade;
    }

    public int getLifeRegeneration()
    {
        return lifeRegeneration;
    }

    public void setLifeRegeneration(final int lifeRegeneration)
    {
        this.lifeRegeneration = lifeRegeneration;
    }


    public int getCritResistance()
    {
        return critResistance;
    }

    public void setCritResistance(final int critResistance)
    {
        this.critResistance = critResistance;
    }

    public double getMagicResistance()
    {
        return magicResistance;
    }

    public void setMagicResistance(final double magicResistance)
    {
        this.magicResistance = magicResistance;
    }
}
