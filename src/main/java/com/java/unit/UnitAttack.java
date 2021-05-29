package com.java.unit;

/**
 * @author 千年
 */
public class UnitAttack
{
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
    /**法力值*/
    private int mana;
    /**每回合法力值恢复*/
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
