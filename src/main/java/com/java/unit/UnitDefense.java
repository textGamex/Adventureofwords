package com.java.unit;

import java.io.Serializable;

import static com.java.antiSpam.MemoryEncryption.*;

/**
 * @author ����ǧ��
 */
public class UnitDefense implements Serializable
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

    /**
     *
     * @return ���ص�λ���������ֵ
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
     * @return ���ص�λ������ֵ
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
     * @return ���ص�λ��
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
     * @return ���ص�λ������
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
     * @return ���ص�λ������
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
     * @return ���ص�λ��ÿ�غ������ظ�
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
     * @return ���ص�λ�ı�������
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
     * @return ���ص�λ��ħ������
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
