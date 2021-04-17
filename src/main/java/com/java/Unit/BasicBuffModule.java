package com.java.Unit;

import com.java.CombatSystem.BuffModule.BuffMessage;
import com.java.CombatSystem.BuffModule.BuffType;

import java.util.EnumMap;
/**
 *单位buff模块
 *@author Millennium
 *@version 1.2.0
 *@Date 2021/4/3
*/
public class BasicBuffModule
{
    private final EnumMap<BuffType, BuffMessage> possessBuffs = new EnumMap<BuffType, BuffMessage>(BuffType.class);

    public final void addBuff(BuffType aBuffType, BuffMessage aBuffMessage)
    {
        possessBuffs.put(aBuffType, aBuffMessage);
    }
    public final boolean hasBuff(BuffType aBuffType)
    {
        return possessBuffs.containsKey(aBuffType);
    }

    public final boolean isEmptyBuff()
    {
        return possessBuffs.isEmpty();
    }

    public final int buffSize()
    {
        return possessBuffs.size();
    }

    public final void removeBuff(BuffType Type)
    {
        if (!possessBuffs.containsKey(Type))
            throw new NullPointerException("Buff不存在: " + Type);
        possessBuffs.remove(Type);
    }

    public final void removeBuff(BuffType Type, int reduceTime)
    {
        if (!possessBuffs.containsKey(Type))
            throw new NullPointerException("Buff不存在: " + Type);

        var buff = possessBuffs.get(Type);
        var oldTime = buff.getTime();
        if (oldTime <= reduceTime)
            possessBuffs.remove(Type);
        else {
            buff.setTime(oldTime - reduceTime);
            possessBuffs.put(Type, buff);
        }
    }

    public final void removeAll()
    {
        possessBuffs.clear();
    }

    @Override
    public String toString()
    {
        return "BasicBuffModule[" +
                "hasBuff:" + possessBuffs +
                ']';
    }
}
