package com.java.CombatSystem.BuffModule;

import java.io.Serializable;

public final class BuffMessage implements Serializable
{
    /**持续回合*/
    private int timeLimit;
    /**效果层数*/
    private int layers;
    /**是否是Debuff*/
    private final boolean debuff;
    /**是否是被动*/
    private final boolean timeLess;

    public BuffMessage(int timeLimit, int layers, boolean debuff, boolean timeLess)
    {
        this.timeLimit = timeLimit;
        this.layers = layers;
        this.debuff = debuff;
        this.timeLess = timeLess;
    }
    public BuffMessage(int timeLimit, int layers, boolean isDebuff)
    {
        this(timeLimit, layers, isDebuff, false);
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
        BuffMessage that = (BuffMessage) o;
        return timeLimit == that.timeLimit && layers == that.layers && debuff == that.debuff && timeLess == that.timeLess;
    }
}
