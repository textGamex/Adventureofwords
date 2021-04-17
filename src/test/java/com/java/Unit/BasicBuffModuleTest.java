package com.java.Unit;

import com.java.CombatSystem.BuffModule.BuffMessage;
import com.java.CombatSystem.BuffModule.BuffType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BasicBuffModuleTest
{

    @Test
    void addBuff()
    {
        var buffs = new BasicBuffModule();

        buffs.addBuff(BuffType.POISON, new BuffMessage(10,1,true));
        buffs.addBuff(BuffType.BLEED, new BuffMessage(10,1,true));
        buffs.addBuff(BuffType.DEBILITY, new BuffMessage(10,1,true));

        assertTrue(buffs.hasBuff(BuffType.POISON));
        assertTrue(buffs.hasBuff(BuffType.BLEED));
        assertTrue(buffs.hasBuff(BuffType.DEBILITY));
    }


    @Test
    void isEmptyBuff()
    {
        var buff = new BasicBuffModule();
        assertTrue(buff.isEmptyBuff());
    }

    @Test
    void buffSize()
    {
        var buffs = new BasicBuffModule();

        buffs.addBuff(BuffType.POISON, new BuffMessage(10,1,true));
        buffs.addBuff(BuffType.BLEED, new BuffMessage(10,1,true));
        buffs.addBuff(BuffType.DEBILITY, new BuffMessage(10,1,true));

        assertEquals(3, buffs.buffSize());
    }

    @Test
    void removeBuff()
    {
        var buffs = new BasicBuffModule();

        buffs.addBuff(BuffType.POISON, new BuffMessage(10,1,true));
        buffs.removeBuff(BuffType.POISON);

        assertFalse(buffs.hasBuff(BuffType.POISON));
    }

    @Test
    void testRemoveBuff()
    {
        var buffs = new BasicBuffModule();

        buffs.addBuff(BuffType.POISON, new BuffMessage(10,1,true));
        buffs.addBuff(BuffType.BLEED, new BuffMessage(10,1,true));
        buffs.removeBuff(BuffType.POISON, 3);
        buffs.removeBuff(BuffType.BLEED, 11);//reduceTime大于持续回合, 应移除buff

        assertFalse(buffs.hasBuff(BuffType.BLEED));
        assertEquals(7, buffs.getBuffMessage(BuffType.POISON).getTime());
    }

    @Test
    void removeAll()
    {
        var buffs = new BasicBuffModule();

        buffs.addBuff(BuffType.POISON, new BuffMessage(10,1,true));
        buffs.addBuff(BuffType.BLEED, new BuffMessage(10,1,true));
        buffs.removeAll();

        assertTrue(buffs.isEmptyBuff());
    }
}