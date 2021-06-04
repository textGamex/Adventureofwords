package com.java.unit;

import java.io.Serializable;

import static com.java.antiSpam.MemoryEncryption.*;

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
    private long physicalResistance;
    /**魔法抗性*/
    private long magicResistance;
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
        return decryptInt(maxHp);
    }

    public void setMaxHp(final int maxHp)
    {
        this.maxHp = encryptionInt(maxHp);
    }

    /**
     *
     * @return 返回单位的生命值
     */
    public int getHp()
    {
        return decryptInt(hp);
    }

    public void setHp(final int hp)
    {
        this.hp = encryptionInt(hp);
    }

    /**
     *
     * @return 返回单位的
     */
    public double getPhysicalResistance()
    {
        return decryptDouble(physicalResistance);
    }

    public void setPhysicalResistance(final double physicalResistance)
    {
        this.physicalResistance = encryptionDouble(physicalResistance);
    }

    /**
     *
     * @return 返回单位的闪避
     */
    public int getArmor()
    {
        return  decryptInt(armor);
    }

    public void setArmor(final int armor)
    {
        this.armor = encryptionInt(armor);
    }

    /**
     *
     * @return 返回单位的闪避
     */
    public int getEvade()
    {
        return decryptInt(evade);
    }


    public void setEvade(final int evade)
    {
        this.evade = encryptionInt(evade);
    }

    /**
     *
     * @return 返回单位的每回合生命回复
     */
    public int getLifeRegeneration()
    {
        return decryptInt(lifeRegeneration);
    }

    public void setLifeRegeneration(final int lifeRegeneration)
    {
        this.lifeRegeneration = encryptionInt(lifeRegeneration);
    }

    /**
     *
     * @return 返回单位的暴击抗性
     */
    public int getCritResistance()
    {
        return decryptInt(critResistance);
    }

    public void setCritResistance(final int critResistance)
    {
        this.critResistance = encryptionInt(critResistance);
    }

    /**
     *
     * @return 返回单位的魔法抗性
     */
    public double getMagicResistance()
    {
        return decryptDouble(magicResistance);
    }

    public void setMagicResistance(final double magicResistance)
    {
        this.magicResistance = encryptionDouble(magicResistance);
    }
}
