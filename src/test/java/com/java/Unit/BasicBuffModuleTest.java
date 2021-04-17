package com.java.Unit;

import com.java.CombatSystem.BuffModule.BuffMessage;
import com.java.CombatSystem.BuffModule.BuffType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BasicBuffModuleTest
{
    private static BasicBuffModule buffs;
    @BeforeEach
    void setUp()
    {
        buffs = new BasicBuffModule();
    }

    @Test
    void addBuff()
    {
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
    }

    @Test
    void removeBuff()
    {
    }

    @Test
    void testRemoveBuff()
    {
    }

    @Test
    void removeAll()
    {
    }
}