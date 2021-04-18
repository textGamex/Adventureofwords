package com.java.Unit;
/**
 * 敌对单位属性
 * @version 0.11
 * @author Millennium
 */
public class EnemyAttribute extends BasicUnitAttribute
{
    /**所值分数 */
    private int value = 0;
    /**所值经验 */
    private int xp = 0;
    /**所值硬币 */
    private int cash = 0;
    public EnemyAttribute(Builder builder) //*默认数值
    {
        super(builder);
        value = builder.value;
        cash = builder.cash;
        xp = builder.xp;
    }

    public static class Builder extends BasicUnitAttribute.Builder<Builder>
    {
        private int value = 0;
        private int xp    = 0;
        private int cash  = 0;

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

    @Override
    public String toString()
    {
        return  super.toString()
                + "[所值分数:" + value +
                ", 所值经验:" + xp +
                ", 所值硬币:" + cash +
                ']';
    }
}
