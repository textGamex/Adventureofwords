package com.java.unit;

import static java.util.Objects.requireNonNull;

/**
 * {@inheritDoc}
 * <p>且具有一下额外属性</p>
 * <em>
 *     <li>所值经验</li>
 *     <li>所值货币</li>
 *     <li>所值分数</li>
 *     <li>敌对单位类型</li>
 * </em>
 * @version 0.3.2
 * @author Millennium
 */
public class Enemy extends BasicUnit
{
    /**
     * 敌对单位级别.
     */
    enum EnemyType
    {
        COMMON,
        ELITE,
        BOSS
    }
    /**所值分数 */
    private final int value;
    /**所值经验 */
    private final int xp;
    /**所值硬币 */
    private final int cash;
    /**敌对单位类型*/
    private final EnemyType type;

    public Enemy(Builder builder)
    {
        super(builder);
        value = builder.value;
        cash = builder.cash;
        xp = builder.xp;
        type = builder.type;
    }

    /**
     * 用于构建{@code Enemy}对象
     * @see BasicUnit
     * @since 15
     * @version 1.1.0
     * @author 留恋千年
     */
    public static class Builder extends BasicUnit.Builder<Builder>
    {
        private int value      = 0;
        private int xp         = 0;
        private int cash       = 0;
        private EnemyType type = EnemyType.COMMON;

        /**
         * @param name 敌对单位的名称
         * @throws NullPointerException 如果{@code name}为null
         */
        public Builder(String name)
        {
            super(requireNonNull(name));
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

        /**
         * 敌对单位的类型, 应为{@link EnemyType}中的一个
         * @throws NullPointerException 如果{@code type}为null
         * @see EnemyType
         */
        public Builder type(EnemyType type)
        {
            this.type = requireNonNull(type);
            return this;
        }

        public Builder cash(int cash)
        {
            this.cash = cash;
            return this;
        }

        /**
         * @return 一个已经构建好的 {@code Enemy}对象
         */
        @Override
        public Enemy build()
        {
            return new Enemy(this);
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

    /**
     * @return 字符串表示的对象
     */
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