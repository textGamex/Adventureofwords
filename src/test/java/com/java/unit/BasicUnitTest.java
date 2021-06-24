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
                .physicalResistance(10.0).manaRecovery(11).magicResistance(12).build();

        assertEquals("单位", unit.getName());
        assertEquals(2, unit.attack().getPhysicalAttack());
        assertEquals(3, unit.defense().getEvade());
        assertEquals(101, unit.attack().getMana());
        assertEquals(5, unit.defense().getHp());
        assertEquals(6, unit.getLevel());
        assertEquals(7, unit.attack().getCrit());
        assertEquals(8.0, unit.attack().getCritsEffect());
        assertEquals(9, unit.defense().getLifeRegeneration());
        assertEquals(10.0, unit.defense().getPhysicalResistance());
        assertEquals(11, unit.attack().getManaRecovery());
        assertEquals(12, unit.defense().getMagicResistance());
        assertThrows(IllegalArgumentException.class, () -> new BasicUnit.Builder("单位").maxHp(0).build());
        assertThrows(NullPointerException.class, () -> new BasicUnit.Builder(null).build());
    }

    @Test
    void testAttributesIsGrowWithLevel()
    {
        final var unit = new BasicUnit.Builder("单位").physicalAttack(100).magicAttack(100)
                .evade(100).mana(100).maxHp(100).crit(100).critsEffect(100).lifeRegeneration(100)
                .physicalResistance(100.0).attributesIsGrowWithLevel(true).level(2).build();

        assertEquals(107, unit.attack().getPhysicalAttack());
        assertEquals(107, unit.attack().getCrit());
        assertEquals(107, unit.attack().getMana());
        assertEquals(107, unit.attack().getMagicAttack());
    }

    @Test
    void testAttributesIsGrowWithLevelInvalid()
    {
        final var unit1 = new BasicUnit.Builder("单位").physicalAttack(2)
                .evade(3).mana(101).maxHp(5).crit(7).critsEffect(8.0).lifeRegeneration(9)
                .physicalResistance(10.0).attributesIsGrowWithLevel(true).level(1).build();

        final var unit2 = new BasicUnit.Builder("单位").physicalAttack(2)
                .evade(3).mana(101).maxHp(5).crit(7).critsEffect(8.0).lifeRegeneration(9)
                .physicalResistance(10.0).attributesIsGrowWithLevel(false).level(1).build();

        final var unit3 = new BasicUnit.Builder("单位").physicalAttack(2)
                .evade(3).mana(101).maxHp(5).crit(7).critsEffect(8.0).lifeRegeneration(9)
                .physicalResistance(10.0).attributesIsGrowWithLevel(false).level(99).build();

        assertAllAttributesUnchanged(unit1);
        assertAllAttributesUnchanged(unit2);
        assertAllAttributesUnchanged(unit3);
    }

    private void assertAllAttributesUnchanged(BasicUnit unit)
    {
        assertEquals("单位", unit.getName());
        assertEquals(2, unit.attack().getPhysicalAttack());
        assertEquals(3, unit.defense().getEvade());
        assertEquals(101, unit.attack().getMana());
        assertEquals(5, unit.defense().getHp());
        assertEquals(7, unit.attack().getCrit());
        assertEquals(8.0, unit.attack().getCritsEffect());
        assertEquals(9, unit.defense().getLifeRegeneration());
        assertEquals(10.0, unit.defense().getPhysicalResistance());
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
    void testCalculationLevelGrowth()
    {
        final int result1 = calculationLevelGrowth(10, -0.5, 1);
        final int result2 = calculationLevelGrowth(10, 0.5, 1);

        assertEquals(5, result1);
        assertEquals(15, result2);
    }

    @Test
    void testClone() throws Exception
    {
        var unit = new BasicUnit.Builder<>(".").build();

        var copy = unit.clone();

        assertTrue(unit.equals(copy));
    }

//    @Test
//    void testBasicUnitSave()
//    {
//        var object = new BasicUnit.Builder<BasicUnit.Builder>("测试").build();
//
//
//    }
}