package com.java.unit;

/**
 * @author ǧ��
 */
public class UnitAttack
{
    /**������*/
    private int physicalAttack;
    /**�����˺�*/
    private int magicAttack;
    /**���� */
    private int crit;
    /**����Ч��*/
    private double critsEffect;
    /**����*/
    private int hit;
    /**����ֵ*/
    private int mana;
    /**ÿ�غϷ���ֵ�ָ�*/
    private int manaRecovery;

    public int getPhysicalAttack()
    {
        return physicalAttack;
    }

    public void setPhysicalAttack(final int physicalAttack)
    {
        this.physicalAttack = physicalAttack;
    }

    public int getMagicAttack()
    {
        return magicAttack;
    }

    public void setMagicAttack(final int magicAttack)
    {
        this.magicAttack = magicAttack;
    }

    public int getCrit()
    {
        return crit;
    }

    public void setCrit(final int crit)
    {
        this.crit = crit;
    }

    public double getCritsEffect()
    {
        return critsEffect;
    }

    public void setCritsEffect(final double critsEffect)
    {
        this.critsEffect = critsEffect;
    }

    public int getHit()
    {
        return hit;
    }

    public void setHit(final int hit)
    {
        this.hit = hit;
    }

    public int getMana()
    {
        return mana;
    }

    public void setMana(final int mana)
    {
        this.mana = mana;
    }

    public int getManaRecovery()
    {
        return manaRecovery;
    }

    public void setManaRecovery(final int manaRecovery)
    {
        this.manaRecovery = manaRecovery;
    }
}
