package com.java.battleSystem;

import com.java.battleSystem.BuffModule.BuffEffect;
import com.java.battleSystem.BuffModule.BuffType;
import com.java.unit.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.java.battleSystem.BattleSystem.*;

class BattleSystemTest
{
    final Role unit = Role.newStandardPrimaryLevelRole("测试");

    @Test
    void testAttackCanCrit()
    {
        assertThrows(NullPointerException.class, () -> attackCanCrit(unit, null));
        assertThrows(NullPointerException.class, () -> attackCanCrit(null, unit));
        assertThrows(NullPointerException.class, () -> attackCanCrit(null, null));
    }

    @Test
    void testCanHit()
    {
        assertThrows(NullPointerException.class, () -> canHit(null, unit));
        assertThrows(NullPointerException.class, () -> canHit(unit, null));
        assertThrows(NullPointerException.class, () -> canHit(null, null));
    }

    @Test
    void testCanAct()
    {
        final var role = Role.newStandardPrimaryLevelRole("unit");

        role.buff().add(BuffType.VERTIGO, new BuffEffect(1, 1));

        assertTrue(canAct(unit));
        assertFalse(canAct(role));
        assertThrows(NullPointerException.class, () -> canAct(null));
    }

    @Test
    void testNormalAttackDamageNullPointer()
    {
        assertThrows(NullPointerException.class, () -> normalAttackDamage(null, unit));
        assertThrows(NullPointerException.class, () -> normalAttackDamage(unit, null));
        assertThrows(NullPointerException.class, () -> normalAttackDamage(null, null));
    }
}