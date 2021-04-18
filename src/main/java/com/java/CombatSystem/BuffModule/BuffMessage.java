package com.java.CombatSystem.BuffModule;

import java.io.Serializable;
import java.util.Objects;

public final class BuffMessage implements Serializable
{
    /**持续回合*/
    private int time;
    /**效果层数*/
    private int layers;
    /**是否是Debuff*/
    private final boolean debuff;
    /**是否是被动*/
    private final boolean passive;

    public BuffMessage(int time, int layers, boolean debuff, boolean passive)
    {
        this.time = time;
        this.layers = layers;
        this.debuff = debuff;
        this.passive = passive;
    }
    public BuffMessage(int time, int layers, boolean isDebuff)
    {
        this(time, layers, isDebuff, false);
    }

    public int getTime()
    {
        return time;
    }
    public int getLayers()
    {
        return layers;
    }
    public boolean isDebuff()
    {
        return debuff;
    }
    public boolean isPassive()
    {
        return passive;
    }
    public void setTime(int time)
    {
        this.time = time;
    }
    public void setLayers(int layers)
    {
        this.layers = layers;
    }

    @Override
    public String toString()
    {
        return "BuffMessage[" +
                "持续回合:" + time +
                ", 效果层数:" + layers +
                ", Debuff:" + debuff +
                ", 特性:" + passive +
                ']';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuffMessage that = (BuffMessage) o;
        return time == that.time && layers == that.layers && debuff == that.debuff && passive == that.passive;
    }
}
