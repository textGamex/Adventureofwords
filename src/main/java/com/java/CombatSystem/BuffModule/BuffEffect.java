package com.java.CombatSystem.BuffModule;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用于描述buff的具体效果
 *
 * @see BuffModule
 * @see BuffType
 * @version 1.3.5
 * @since 15
 */
public final class BuffEffect implements Serializable
{
    @Serial
    private static final long serialVersionUID = 6938381291107023911L;
    /**持续回合*/
    private int timeLimit;
    /**效果层数*/
    private int layers;
    /**是否是Debuff*/
    private final boolean debuff;
    /**是否是被动*/
    private final boolean timeLess;

    /**
     * @param timeLimit 效果持续回合
     * @param layers 效果层数
     * @param debuff 是debuff
     * @param timeLess 是特性
     * @throws IllegalArgumentException 如果{@code timeLimit}或{@code layers}小于等于0
     */
    public BuffEffect(int timeLimit, int layers, boolean debuff, boolean timeLess)
    {
        if (timeLimit <= 0)
            throw new IllegalArgumentException("非法参数,timeLimit:" + timeLimit);
        if (layers <= 0)
            throw new IllegalArgumentException("非法参数,layers:" + layers);
        this.timeLimit = timeLimit;
        this.layers = layers;
        this.debuff = debuff;
        this.timeLess = timeLess;
    }

    /**
     * {@code timeLess}默认为{@code false}
     *
     * @param timeLimit 效果持续回合
     * @param layers 效果层数
     * @param isDebuff 是debuff
     */
    public BuffEffect(int timeLimit, int layers, boolean isDebuff)
    {
        this(timeLimit, layers, isDebuff, false);
    }

    /**
     * {@code timeLess}和{@code debug}默认为{@code false}
     *
     * @param timeLimit 效果持续回合
     * @param layers 效果层数
     */
    public BuffEffect(int timeLimit, int layers)
    {
        this(timeLimit, layers, false, false);
    }

    public int getTimeLimit()
    {
        return timeLimit;
    }

    public int getLayers()
    {
        return layers;
    }

    public boolean isDebuff()
    {
        return debuff;
    }

    public boolean isTimeLess()
    {
        return timeLess;
    }

    public void setTimeLimit(int timeLimit)
    {
        this.timeLimit = timeLimit;
    }

    public void setLayers(int layers)
    {
        this.layers = layers;
    }

    @Override
    public String toString()
    {
        return "BuffMessage[" +
                "持续回合:" + timeLimit +
                ", 效果层数:" + layers +
                ", Debuff:" + debuff +
                ", 特性:" + timeLess +
                ']';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuffEffect that = (BuffEffect) o;
        return timeLimit == that.timeLimit && layers == that.layers && debuff == that.debuff && timeLess == that.timeLess;
    }
}
