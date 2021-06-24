package com.java.battleSystem.BuffModule;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuffEffectTest
{
    @Test
    void testBuffEffectIllegalArgument()
    {
        assertThrows(IllegalArgumentException.class, () -> new BuffEffect(-10, 10));
        assertThrows(IllegalArgumentException.class, () -> new BuffEffect(10, -10));
        assertThrows(IllegalArgumentException.class, () -> new BuffEffect(-10, -10));
        assertThrows(IllegalArgumentException.class, () -> new BuffEffect(0, -10));
        assertThrows(IllegalArgumentException.class, () -> new BuffEffect(-10, 0));
        assertThrows(IllegalArgumentException.class, () -> new BuffEffect(0, 0));
    }

    @Test
    void testEquals()
    {
        var object = new BuffEffect(1, 1);
        var o = object.clone();

        assertEquals(object, object);
        assertEquals(o, object);
        assertNotEquals(null, object);
    }

    @Test
    void testSet()
    {
        var object = new BuffEffect(1, 1);

        object.setTimeLimit(10);
        object.setLayers(11);

        assertEquals(10, object.getTimeLimit());
        assertEquals(11, object.getLayers());
    }
}