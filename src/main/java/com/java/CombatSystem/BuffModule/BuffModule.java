package com.java.CombatSystem.BuffModule;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Objects;

public class BuffModule implements Serializable
{
    public static void main(String[] args)
    {
    }
    private final EnumMap<BuffType, BuffMessage> haveBuffs = new EnumMap<>(BuffType.class);

    public final void add(BuffType aBuffType, BuffMessage aBuffMessage)
    {
        if (aBuffMessage == null)
            throw new NullPointerException();
        //TODO:如果存在,叠加; 未完成
        haveBuffs.put(aBuffType, aBuffMessage);
    }

    public final BuffMessage getMessage(BuffType type)
    {
        if (buffNotExist(type))
            throw new NullPointerException("buff不存在: " + type);

        return haveBuffs.get(type);
    }

    public final boolean have(BuffType aBuffType)
    {
        return haveBuffs.containsKey(aBuffType);
    }

    public final boolean isEmpty()
    {
        return haveBuffs.isEmpty();
    }

    public final int size()
    {
        return haveBuffs.size();
    }

    public final void remove(BuffType type)
    {
        if (buffNotExist(type))
            throw new NullPointerException("Buff不存在: " + type);
        haveBuffs.remove(type);
    }

    public final void remove(BuffType type, int reduceTime)
    {
        if (reduceTime < 0)
            throw new IllegalArgumentException("非法参数: " + reduceTime);

        if (buffNotExist(type))
            throw new NullPointerException("Buff不存在: " + type);

        var buff = haveBuffs.get(type);
        var originalTime = buff.getTime();

        //如果移除的回合大于现有的回合, 则直接移除
        if (originalTime <= reduceTime)
            haveBuffs.remove(type);
        else
        {
            buff.setTime(originalTime - reduceTime);
            haveBuffs.put(type, buff);
        }
    }
    private boolean buffNotExist(BuffType type)
    {
        return !haveBuffs.containsKey(type);
    }

    public final void clear()
    {
        haveBuffs.clear();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuffModule that = (BuffModule) o;
        return haveBuffs.equals(that.haveBuffs);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(haveBuffs);
    }

    @Override
    public String toString()
    {
        return "BasicBuffModule[" +
                "hasBuff:" + haveBuffs +
                ']';
    }
}
