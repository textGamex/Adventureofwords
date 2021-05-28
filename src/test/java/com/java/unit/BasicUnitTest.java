package com.java.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.java.unit.BasicUnit.UnitGrowth.calculationLevelGrowth;

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

    @Test
    void testAttributesIsGrowWithLevel()
    {
        final var unit = new BasicUnit.Builder("��λ").physicalAttack(100).magicAttack(100)
                .evade(100).mana(100).maxHp(100).crit(100).critsEffect(100).lifeRegeneration(100)
                .physicalResistance(100.0).attributesIsGrowWithLevel(true).level(2).build();

        assertEquals(107, unit.getPhysicalAttack());
        assertEquals(107, unit.getCrit());
        assertEquals(107, unit.getMana());
        assertEquals(107, unit.getMagicAttack());
    }

    @Test
    void testAttributesIsGrowWithLevelInvalid()
    {
        final var unit1 = new BasicUnit.Builder("��λ").physicalAttack(2)
                .evade(3).mana(101).maxHp(5).crit(7).critsEffect(8.0).lifeRegeneration(9)
                .physicalResistance(10.0).attributesIsGrowWithLevel(true).level(1).build();

        final var unit2 = new BasicUnit.Builder("��λ").physicalAttack(2)
                .evade(3).mana(101).maxHp(5).crit(7).critsEffect(8.0).lifeRegeneration(9)
                .physicalResistance(10.0).attributesIsGrowWithLevel(false).level(1).build();

        final var unit3 = new BasicUnit.Builder("��λ").physicalAttack(2)
                .evade(3).mana(101).maxHp(5).crit(7).critsEffect(8.0).lifeRegeneration(9)
                .physicalResistance(10.0).attributesIsGrowWithLevel(false).level(99).build();

        assertAllAttributesUnchanged(unit1);
        assertAllAttributesUnchanged(unit2);
        assertAllAttributesUnchanged(unit3);
    }

    private void assertAllAttributesUnchanged(BasicUnit unit)
    {
        assertEquals("��λ", unit.getName());
        assertEquals(2, unit.getPhysicalAttack());
        assertEquals(3, unit.getEvade());
        assertEquals(101, unit.getMana());
        assertEquals(5, unit.getHp());
        assertEquals(7, unit.getCrit());
        assertEquals(8.0, unit.getCritsEffect());
        assertEquals(9, unit.getLifeRegeneration());
        assertEquals(10.0, unit.getPhysicalResistance());
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