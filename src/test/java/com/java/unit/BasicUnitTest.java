package com.java.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicUnitTest
{
    @DisplayName("Builderģʽ����")
    @Test
    void Builder()
    {
        var unit = new BasicUnit.Builder("��λ").physicalAttack(2)
                .evade(3).mana(101).maxHp(5).level(6).crit(7).critsEffect(8.0).lifeRegeneration(9)
                .physicalResistance(10.0).build();

        assertEquals("��λ", unit.getName());
        assertEquals(2, unit.getPhysicalAttack());
        assertEquals(3, unit.getEvade());
        assertEquals(101, unit.getMana());
        assertEquals(5, unit.getHp());
        assertEquals(6, unit.getLevel());
        assertEquals(7, unit.getCrit());
        assertEquals(8.0, unit.getCritsEffect());
        assertEquals(9, unit.getLifeRegeneration());
        assertEquals(10.0, unit.getPhysicalResistance());

        assertThrows(IllegalArgumentException.class, () -> new BasicUnit.Builder("��λ").maxHp(0).build());
        assertThrows(NullPointerException.class, () -> new BasicUnit.Builder(null).build());
    }
}