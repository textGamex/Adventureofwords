package com.java.Unit;

import com.java.CombatSystem.BuffModule.BuffMessage;
import com.java.CombatSystem.BuffModule.BuffType;

import java.util.EnumMap;
/**
 *��λbuffģ��
 *@author Millennium
 *@version 1.2.0
 *@Date 2021/4/3
*/
public class BasicBuffModule
{
    public static void main(String[] args)
    {
        var buff = new BasicBuffModule();
        buff.addBuff(BuffType.POISON, new BuffMessage(10,1,true));
        System.out.println(buff.hasBuff(BuffType.POISON));
        System.out.println(buff.toString());
        buff.removeBuff(BuffType.POISON, 2);
        System.out.println(buff.toString());
    }
    private final EnumMap<BuffType, BuffMessage> hasBuff = new EnumMap<BuffType, BuffMessage>(BuffType.class);
    public final void addBuff(BuffType aBuffType, BuffMessage aBuffMessage)
    {
        hasBuff.put(aBuffType, aBuffMessage);
    }
    public final boolean hasBuff(BuffType aBuffType)
    {
        return hasBuff.containsKey(aBuffType);
    }
    public final boolean isEmptyBuff()
    {
        return hasBuff.isEmpty();
    }
    public final int buffSize()
    {
        return hasBuff.size();
    }
    public final void removeBuff(BuffType Type)
    {
        if (!hasBuff.containsKey(Type))
            throw new NullPointerException("Buff������: " + Type);
        hasBuff.remove(Type);
    }
    public final void removeBuff(BuffType Type, int reduceTime)
    {
        if (!hasBuff.containsKey(Type))
            throw new NullPointerException("Buff������: " + Type);

        var buff = hasBuff.get(Type);
        var oldTime = buff.getTime();
        if (oldTime <= reduceTime)
            hasBuff.remove(Type);
        else {
            buff.setTime(oldTime - reduceTime);
            hasBuff.put(Type, buff);
        }
    }
    public final void removeAll()
    {
        hasBuff.clear();
    }

    @Override
    public String toString()
    {
        return "BasicBuffModule[" +
                "hasBuff:" + hasBuff +
                ']';
    }
}