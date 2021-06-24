package com.java.tools;

import com.java.unit.BasicUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.java.battleSystem.BattleAttributeCalculation.*;

class BattleAttributeCalculationTest
{
    private final BasicUnit testUnit = new BasicUnit.Builder<>("").build();

    @Test
    void testAttackHitRate()
    {
        var victimEvade1 = attackHitRate(1, -1);
        var victimEvade2 = attackHitRate(-1, 1);

        assertEquals(victimEvade1, 0.0);
        assertEquals(victimEvade2, 1.0);
    }

    @Test
    void testAttackHitRateNullPointer()
    {
        assertThrows(NullPointerException.class, () -> attackHitRate(null, testUnit));
        assertThrows(NullPointerException.class, () -> attackHitRate(testUnit, null));
        assertThrows(NullPointerException.class, () -> attackHitRate(null, null));
    }

    @Test
    void testAttackerCritChanceNullPointer()
    {
        assertThrows(NullPointerException.class, () -> attackerCritChance(null, testUnit));
        assertThrows(NullPointerException.class, () -> attackerCritChance(testUnit, null));
        assertThrows(NullPointerException.class, () -> attackerCritChance(null, null));
    }

    @Test
    void testAttackerPhyAtkDamageNullPointer()
    {
        assertThrows(NullPointerException.class, () -> attackerPhysicalDamage(null, testUnit));
        assertThrows(NullPointerException.class, () -> attackerPhysicalDamage(testUnit, null));
        assertThrows(NullPointerException.class, () -> attackerPhysicalDamage(null, null));
    }

    @Test
    void testDprNullPointer()
    {
        assertThrows(NullPointerException.class, () -> dpr(null, testUnit));
        assertThrows(NullPointerException.class, () -> dpr(testUnit, null));
        assertThrows(NullPointerException.class, () -> dpr(null, null));
    }

    @Test
    void testVictimEffectiveHpNullPointer()
    {
        assertThrows(NullPointerException.class, () -> victimEffectiveHp(null, testUnit));
        assertThrows(NullPointerException.class, () -> victimEffectiveHp(testUnit, null));
        assertThrows(NullPointerException.class, () -> victimEffectiveHp(null, null));
    }

    @Test
    void testCriticalDamageNullPointer()
    {
        assertThrows(NullPointerException.class, () -> criticalDamage(null, testUnit));
        assertThrows(NullPointerException.class, () -> criticalDamage(testUnit, null));
        assertThrows(NullPointerException.class, () -> criticalDamage(null, null));
    }
}