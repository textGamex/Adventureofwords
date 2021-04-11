package com.java.Unit;
/**
 * �жԵ�λ����
 * @version 0.11
 * @author Millennium
 */
public class EnemyAttribute extends BasicUnitAttribute
{
    /**��ֵ���� */
    private final int value;
    /**��ֵ���� */
    private final int xp;
    /**��ֵӲ�� */
    private final int coin;
    public EnemyAttribute() //*Ĭ����ֵ
    {
        super();
        value = 10;
        xp = 10;
        coin = 10;
    }
    public EnemyAttribute(String name,int grade, int MAXHP, int ATK, float CRIT, float critsEffect,
                          int fixArmorPen, float perArmorPen, int armor, float physicalResistanc,
                          int lifeRegeneration, int id, int value, int xp, int coin)//*������Ե�λ
    {
        super(name, grade, MAXHP, ATK, CRIT, critsEffect, fixArmorPen, perArmorPen, armor, physicalResistanc
        , lifeRegeneration, id);
        this.value = value;
        this.xp = xp;
        this.coin = coin;
    }
    public final int getValue()
    {
        return value;
    }
    public final int getXp()
    {
        return xp;
    }
    public final int getCoin()
    {
        return coin;
    }

    @Override
    public String toString()
    {
        return  super.toString()
                + "[��ֵ����:" + value +
                ", ��ֵ����:" + xp +
                ", ��ֵӲ��:" + coin +
                ']';
    }
}
