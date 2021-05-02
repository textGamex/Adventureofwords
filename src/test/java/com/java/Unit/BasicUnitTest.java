package com.java.Unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicUnitTest
{
    @DisplayName("Builder模式测试")
    @Test
    void Builder()
    {
        var unit = new BasicUnit.Builder("单位").atk(2)
                .evade(3.0).mana(101).maxHp(5).level(6).critRate(7.0).critsEffect(8.0).lifeRegeneration(9)
                .physicalResistance(10.0).build();

        assertEquals("单位", unit.getName());
        assertEquals(2, unit.getAtk());
        assertEquals(3.0, unit.getEvade());
        assertEquals(101, unit.getMana());
        assertEquals(5, unit.getHp());
        assertEquals(6, unit.getLevel());
        assertEquals(7.0, unit.getCritRate());
        assertEquals(8.0, unit.getCritsEffect());
        assertEquals(9, unit.getLifeRegeneration());
        assertEquals(10.0, unit.getPhysicalResistance());

        assertThrows(IllegalArgumentException.class, () -> new BasicUnit.Builder("单位").maxHp(0).build());
        assertThrows(NullPointerException.class, () -> new BasicUnit.Builder(null).build());
    }
}