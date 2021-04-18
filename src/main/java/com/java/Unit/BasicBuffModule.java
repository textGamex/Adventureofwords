package com.java.Unit;

import com.java.CombatSystem.BuffModule.BuffMessage;
import com.java.CombatSystem.BuffModule.BuffType;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.logging.Logger;

/**
 *单位buff模块
 *@author Millennium
 *@version 1.3.2
 *@Date 2021/4/3
*/
public class BasicBuffModule implements Serializable
{
    private EnumMap<BuffType, BuffMessage> owningBuffs = new EnumMap<>(BuffType.class);

    public final void addBuff(BuffType aBuffType, BuffMessage aBuffMessage)
    {
        owningBuffs.put(aBuffType, aBuffMessage);//TODO:如果存在,叠加; 未完成
    }

    public final BuffMessage getBuffMessage(BuffType type)
    {
         if (buffNotExist(type))
             throw new NullPointerException("buff不存在: " + type);

        return owningBuffs.get(type);
    }

    public final boolean hasBuff(BuffType aBuffType)
    {
        return owningBuffs.containsKey(aBuffType);
    }

    public final boolean isEmptyBuff()
    {
        return owningBuffs.isEmpty();
    }

    public final int buffSize()
    {
        return owningBuffs.size();
    }

    public final void removeBuff(BuffType type)
    {
        if (buffNotExist(type))
            throw new NullPointerException("Buff不存在: " + type);
        owningBuffs.remove(type);
    }

    public final void removeBuff(BuffType type, int reduceTime)
    {
        if (buffNotExist(type))
            throw new NullPointerException("Buff不存在: " + type);

        var buff = owningBuffs.get(type);
        var originalTime = buff.getTime();
        if (originalTime <= reduceTime)
            owningBuffs.remove(type);
        else
        {
            buff.setTime(originalTime - reduceTime);
            owningBuffs.put(type, buff);
        }
    }
    private boolean buffNotExist(BuffType Type)
    {
        return !owningBuffs.containsKey(Type);
    }

    public final void removeAll()
    {
        owningBuffs.clear();
    }

    protected void resetBuffs()
    {
        if (owningBuffs == null)
            Logger.getGlobal().severe("owningBuffs为Null");
        owningBuffs = new EnumMap<>(BuffType.class);
    }
    @Override
    public String toString()
    {
        return "BasicBuffModule[" +
                "hasBuff:" + owningBuffs +
                ']';
    }
}
