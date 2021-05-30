package com.java.unit;

import static com.java.antiSpam.AntiSpam.*;
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
    private long physicalResistance;
    /**ħ������*/
    private long magicResistance;
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
        return decryptInt(maxHp);
    }

    public void setMaxHp(final int maxHp)
    {
        this.maxHp = encryptionInt(maxHp);
    }

    public int getHp()
    {
        return decryptInt(hp);
    }

    public void setHp(final int hp)
    {
        this.hp = encryptionInt(hp);
    }

    public double getPhysicalResistance()
    {
        return decryptDouble(physicalResistance);
    }

    public void setPhysicalResistance(final double physicalResistance)
    {
        this.physicalResistance = encryptionDouble(physicalResistance);
    }

    public int getArmor()
    {
        return  decryptInt(armor);
    }

    public void setArmor(final int armor)
    {
        this.armor = encryptionInt(armor);
    }

    public int getEvade()
    {
        return decryptInt(evade);
    }

    public void setEvade(final int evade)
    {
        this.evade = encryptionInt(evade);
    }

    public int getLifeRegeneration()
    {
        return decryptInt(lifeRegeneration);
    }

    public void setLifeRegeneration(final int lifeRegeneration)
    {
        this.lifeRegeneration = encryptionInt(lifeRegeneration);
    }


    public int getCritResistance()
    {
        return decryptInt(critResistance);
    }

    public void setCritResistance(final int critResistance)
    {
        this.critResistance = encryptionInt(critResistance);
    }

    public double getMagicResistance()
    {
        return decryptDouble(magicResistance);
    }

    public void setMagicResistance(final double magicResistance)
    {
        this.magicResistance = encryptionDouble(magicResistance);
    }
}
