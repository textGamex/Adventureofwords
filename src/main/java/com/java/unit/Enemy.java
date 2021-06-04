package com.java.unit;

import static java.util.Objects.requireNonNull;

/**
 * {@inheritDoc}
 * <p>�Ҿ���һ�¶�������</p>
 * <em>
 *     <li>��ֵ����</li>
 *     <li>��ֵ����</li>
 *     <li>��ֵ����</li>
 *     <li>�жԵ�λ����</li>
 * </em>
 * @version 0.3.2
 * @author Millennium
 */
public class Enemy extends BasicUnit
{
    /**
     * �жԵ�λ����.
     */
    enum EnemyType
    {
        COMMON,
        ELITE,
        BOSS
    }
    /**��ֵ���� */
    private final int value;
    /**��ֵ���� */
    private final int xp;
    /**��ֵӲ�� */
    private final int cash;
    /**�жԵ�λ����*/
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
     * ���ڹ���{@code Enemy}����
     * @see BasicUnit
     * @since 15
     * @version 1.1.0
     * @author ����ǧ��
     */
    public static class Builder extends BasicUnit.Builder<Builder>
    {
        private int value      = 0;
        private int xp         = 0;
        private int cash       = 0;
        private EnemyType type = EnemyType.COMMON;

        /**
         * @param name �жԵ�λ������
         * @throws NullPointerException ���{@code name}Ϊnull
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
         * �жԵ�λ������, ӦΪ{@link EnemyType}�е�һ��
         * @throws NullPointerException ���{@code type}Ϊnull
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
         * @return һ���Ѿ������õ� {@code Enemy}����
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
     * @return �ַ�����ʾ�Ķ���
     */
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