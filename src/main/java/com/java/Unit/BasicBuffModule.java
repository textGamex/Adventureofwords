package com.java.Unit;

import com.java.CombatSystem.BuffModule.BuffMessage;
import com.java.CombatSystem.BuffModule.BuffType;

import java.util.EnumMap;
/**
 *单位buff模块
 *@author Millennium
 *@version 1.2.1
 *@Date 2021/4/3
*/
public class BasicBuffModule
{
    private final EnumMap<BuffType, BuffMessage> possessBuffs = new EnumMap<BuffType, BuffMessage>(BuffType.class);

    public final void addBuff(BuffType aBuffType, BuffMessage aBuffMessage)
    {
        possessBuffs.put(aBuffType, aBuffMessage);//TODO:如果存在,叠加,未完成
    }
    public final BuffMessage getBuffMessage(BuffType type)
    {
         if (buffNotExist(type))
             throw new NullPointerException("buff不存在: " + type);

        return possessBuffs.get(type);
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

    public final void removeBuff(BuffType type)
    {
        if (buffNotExist(type))
            throw new NullPointerException("Buff不存在: " + type);
        possessBuffs.remove(type);
    }

    public final void removeBuff(BuffType type, int reduceTime)
    {
        if (buffNotExist(type))
            throw new NullPointerException("Buff不存在: " + type);

        var buff = possessBuffs.get(type);
        var originalTime = buff.getTime();
        if (originalTime <= reduceTime)
            possessBuffs.remove(type);
        else
        {
            buff.setTime(originalTime - reduceTime);
            possessBuffs.put(type, buff);
        }
    }
    private boolean buffNotExist(BuffType Type)
    {
        return !possessBuffs.containsKey(Type);
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
