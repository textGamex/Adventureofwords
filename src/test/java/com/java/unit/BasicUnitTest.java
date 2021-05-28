package com.java.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.java.unit.BasicUnit.UnitGrowth.calculationLevelGrowth;

import static org.junit.jupiter.api.Assertions.*;

class BasicUnitTest
{
    @DisplayName("Builder模式测试")
    @Test
    void Builder()
    {
        var unit = new BasicUnit.Builder("单位").physicalAttack(2)
                .evade(3).mana(101).maxHp(5).level(6).crit(7).critsEffect(8.0).lifeRegeneration(9)
                .physicalResistance(10.0).build();

        assertEquals("单位", unit.getName());
        assertEquals(2, unit.getPhysicalAttack());
        assertEquals(3, unit.getEvade());
        assertEquals(101, unit.getMana());
        assertEquals(5, unit.getHp());
        assertEquals(6, unit.getLevel());
        assertEquals(7, unit.getCrit());
        assertEquals(8.0, unit.getCritsEffect());
        assertEquals(9, unit.getLifeRegeneration());
        assertEquals(10.0, unit.getPhysicalResistance());
        assertThrows(IllegalArgumentException.class, () -> new BasicUnit.Builder("单位").maxHp(0).build());
        assertThrows(NullPointerException.class, () -> new BasicUnit.Builder(null).build());
    }

    @Test
    void testCalculationLevelGrowthReturnZero()
    {
        final int result1 = calculationLevelGrowth(0, 0.5, 5);
        final int result2 = calculationLevelGrowth(0, 0.0, 5);
        final int result3 = calculationLevelGrowth(0, 0.5, 0);
        final int result4 = calculationLevelGrowth(0, 0.0, 0);

        assertEquals(0, result1);
        assertEquals(0, result2);
        assertEquals(0, result3);
        assertEquals(0, result4);
    }

    @Test
    void testCalculationLevelGrowthException()
    {
        assertThrows(IllegalArgumentException.class, () -> calculationLevelGrowth(1, 0.5, -1));
        assertThrows(IllegalArgumentException.class, () -> calculationLevelGrowth(1, -0.5, -1));
        assertThrows(IllegalArgumentException.class, () -> calculationLevelGrowth(-1, -0.5, -1));
    }

    @Test
    void testCalculationLevelGrowthReturnUnchangedAttribute()
    {
        final int result1 = calculationLevelGrowth(10, 0.5, 0);
        final int result2 = calculationLevelGrowth(10, 0.0, 5);
        final int result3 = calculationLevelGrowth(10, 0.0, 0);

        assertEquals(10, result1);
        assertEquals(10, result2);
        assertEquals(10, result3);
    }

    @Test
    void textCalculationLevelGrowth()
    {
        final int result1 = calculationLevelGrowth(10, -0.5, 1);
        final int result2 = calculationLevelGrowth(10, 0.5, 1);

        assertEquals(5, result1);
        assertEquals(15, result2);
    }
}