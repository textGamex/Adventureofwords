package com.java.combatSystem.BuffModule;

import com.java.battleSystem.BuffModule.BuffEffect;
import com.java.battleSystem.BuffModule.BuffModule;
import com.java.battleSystem.BuffModule.BuffType;
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

    @Test
    void add()
    {
        buffs.add(BuffType.POISON, new BuffEffect(10,1,true));
        buffs.add(BuffType.BLEED, new BuffEffect(10,1,true));
        buffs.add(BuffType.DEBILITY, new BuffEffect(10,1,true));

        assertThrows(NullPointerException.class, () -> buffs.add(BuffType.BLEED, null));
        assertThrows(NullPointerException.class, () -> buffs.add(null, new BuffEffect(1, 1)));
        assertThrows(NullPointerException.class, () -> buffs.add(null, null));
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
        buffs.add(BuffType.POISON, new BuffEffect(10,1,true));
        buffs.add(BuffType.BLEED, new BuffEffect(10,1,true));
        buffs.add(BuffType.DEBILITY, new BuffEffect(10,1,true));

        assertEquals(3, buffs.size());
    }

    @Test
    void remove()
    {
        buffs.add(BuffType.POISON, new BuffEffect(10,1,true));
        buffs.remove(BuffType.POISON);

        assertThrows(NullPointerException.class, () -> buffs.remove(BuffType.DEBILITY));
        assertThrows(NullPointerException.class, () -> buffs.remove(BuffType.DEBILITY, 1));
        assertThrows(NullPointerException.class, () -> buffs.remove(null));
        assertThrows(NullPointerException.class, () -> buffs.remove(null, 1));
        assertFalse(buffs.have(BuffType.POISON));
    }

    @Test
    void testRemove()
    {
        buffs.add(BuffType.POISON, new BuffEffect(10,1,true));
        buffs.add(BuffType.BLEED, new BuffEffect(10,1,true));
        buffs.remove(BuffType.POISON, 3);
        buffs.remove(BuffType.BLEED, 11);

        assertFalse(buffs.have(BuffType.BLEED));//��Ϊ�Ƴ��Ļغϴ��ڳ����غ�,����buff��ɾ��
        assertEquals(7, buffs.getMessage(BuffType.POISON).getTimeLimit());
    }

    @Test
    void clear()
    {
        buffs.add(BuffType.POISON, new BuffEffect(10,1,true));
        buffs.add(BuffType.BLEED, new BuffEffect(10,1,true));
        buffs.clear();

        assertTrue(buffs.isEmpty());
    }

    void clearDebuff()
    {
        buffs.add(BuffType.POISON, new BuffEffect(10,1,true));
        buffs.add(BuffType.BLEED, new BuffEffect(10,1,true));
        buffs.add(BuffType.DEBILITY, new BuffEffect(10,1,false));
        buffs.clearDebuff();

        assertFalse(buffs.have(BuffType.POISON));
        assertFalse(buffs.have(BuffType.BLEED));
        assertTrue(buffs.have(BuffType.DEBILITY));
    }

}