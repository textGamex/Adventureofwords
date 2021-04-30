package com.java.CombatSystem.BuffModule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuffModuleTest
{
    private static BuffModule buffs;

    @BeforeEach
    void setUp()
    {
        buffs = new BuffModule();
    }

//    @AfterEach
//    void tearDown()
//    {
//
//    }

    @Test
    void add()
    {
        buffs.add(BuffType.POISON, new BuffMessage(10,1,true));
        buffs.add(BuffType.BLEED, new BuffMessage(10,1,true));
        buffs.add(BuffType.DEBILITY, new BuffMessage(10,1,true));

        assertThrows(NullPointerException.class, () -> buffs.add(BuffType.BLEED, null));
        assertTrue(buffs.have(BuffType.POISON));
        assertTrue(buffs.have(BuffType.BLEED));
        assertTrue(buffs.have(BuffType.DEBILITY));
    }

    @Test
    void getMessage()
    {
    }

    @Test
    void isEmpty()
    {
        assertTrue(buffs.isEmpty());
    }

    @Test
    void size()
    {
        buffs.add(BuffType.POISON, new BuffMessage(10,1,true));
        buffs.add(BuffType.BLEED, new BuffMessage(10,1,true));
        buffs.add(BuffType.DEBILITY, new BuffMessage(10,1,true));

        assertEquals(3, buffs.size());
    }

    @Test
    void remove()
    {
        buffs.add(BuffType.POISON, new BuffMessage(10,1,true));
        buffs.remove(BuffType.POISON);

        assertThrows(NullPointerException.class, () -> buffs.remove(BuffType.DEBILITY));
        assertFalse(buffs.have(BuffType.POISON));
    }

    @Test
    void testRemove()
    {
        buffs.add(BuffType.POISON, new BuffMessage(10,1,true));
        buffs.add(BuffType.BLEED, new BuffMessage(10,1,true));
        buffs.remove(BuffType.POISON, 3);
        buffs.remove(BuffType.BLEED, 11);

        assertFalse(buffs.have(BuffType.BLEED));//因为移除的回合大于持续回合,所以buff被删除
        assertEquals(7, buffs.getMessage(BuffType.POISON).getTimeLimit());
    }

    @Test
    void clear()
    {
        buffs.add(BuffType.POISON, new BuffMessage(10,1,true));
        buffs.add(BuffType.BLEED, new BuffMessage(10,1,true));
        buffs.clear();

        assertTrue(buffs.isEmpty());
    }

    @Test
    void testEquals()
    {
        var buffs = new BuffModule();
    }

    @Test
    void testHashCode()
    {
    }

}