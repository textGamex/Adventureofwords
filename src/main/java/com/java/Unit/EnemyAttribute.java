package com.java.Unit;
/**
 * �жԵ�λ����
 * @version 0.32
 * @author Millennium
 */
public class EnemyAttribute extends BasicUnitAttribute
{
    /**��ֵ���� */
    private final int value;
    /**��ֵ���� */
    private final int xp;
    /**��ֵӲ�� */
    private final int cash;
    /**�жԵ�λ����*/
    private final EnemyType type;

    public EnemyAttribute(Builder builder) //*Ĭ����ֵ
    {
        super(builder);
        value = builder.value;
        cash = builder.cash;
        xp = builder.xp;
        type = builder.type;
    }

    public static class Builder extends BasicUnitAttribute.Builder<Builder>
    {
        private int value      = 0;
        private int xp         = 0;
        private int cash       = 0;
        private EnemyType type = EnemyType.COMMON;

        public Builder(String name)
        {
            super(name);
        }
        public Builder value(int value)
        {
            this.value = value;
            return this;
        }

        public Builder xp(int xp)
        {
            this.xp = xp;
            return this;
        }

        public Builder type(EnemyType type)
        {
            this.type = type;
            return this;
        }
        public Builder cash(int cash)
        {
            this.cash = cash;
            return this;
        }
        @Override
        public EnemyAttribute build()
        {
            return new EnemyAttribute(this);
        }
    }
    public final int getValue()
    {
        return value;
    }
    public final int getXp()
    {
        return xp;
    }
    public final int getCash()
    {
        return cash;
    }
    public EnemyType getType()
    {
        return type;
    }

    @Override
    public String toString()
    {
        return  super.toString()
                + "[��ֵ����:" + value +
                ", ��ֵ����:" + xp +
                ", ��ֵӲ��:" + cash +
                ']';
    }
}
