package com.java.Tools;

import com.java.CombatSystem.BuffModule.BuffType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitToolsTest
{
    @Test
    void randomExtraAttribute()
    {
        var attribute = new UnitTools();
        assertThrows(NullPointerException.class, () -> attribute.randomExtraAttribute(null));
    }
}