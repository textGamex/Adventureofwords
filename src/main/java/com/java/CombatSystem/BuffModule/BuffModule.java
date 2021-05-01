package com.java.CombatSystem.BuffModule;

import java.io.Serial;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Objects;

public class BuffModule implements Serializable
{
    public static void main(String[] args)
    {
    }
    @Serial
    private static final long serialVersionUID = 6182039129119023911L;

    private final EnumMap<BuffType, BuffMessage> haveBuffs = new EnumMap<>(BuffType.class);

    public final void add(BuffType type, BuffMessage aBuffMessage)
    {
        if (aBuffMessage == null)
            throw new NullPointerException();

        if (haveBuffs.containsKey(type))
        {
            if (aBuffMessage.isTimeLess())
                haveBuffs.put(type, aBuffMessage);
            else
            {
                var existentBuff = haveBuffs.get(type);
                var addTimeLimit = aBuffMessage.getTimeLimit();
                var addLayers = aBuffMessage.getLayers();

                existentBuff.setTimeLimit(existentBuff.getTimeLimit() + (addTimeLimit == 1 ? 1 : addTimeLimit / 2));
                existentBuff.setLayers(existentBuff.getLayers() + (addLayers == 1 ? 1 : addLayers / 2));
            }
        }
        else
            haveBuffs.put(type, aBuffMessage);
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
        var originalTime = buff.getTimeLimit();

        //如果移除的回合大于现有的回合, 则直接移除
        if (originalTime <= reduceTime)
            haveBuffs.remove(type);
        else
            buff.setTimeLimit(originalTime - reduceTime);
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
