package com.java.battleSystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleCoefficientTest
{
    @Test
    void Builder()
    {
        var object = new BattleCoefficient.Builder().enemyDamageCoefficient(0.1).enemyAttackFactor(0.2)
                .cashRewardsFactor(0.3).expRewardsFactor(0.4).enemyCritRateFactor(0.5).enemyCritsEffectFactor(0.6)
                .enemyMagicResistanceFactor(0.7).enemyPhysicalResistanceFactor(0.8).roleMagicResistanceFactor(0.9)
                .roleAttackFactor(1.0).roleCritRateFactor(1.1).roleCritsEffectFactor(1.2).roleDamageCoefficient(1.3)
                .valueFactor(1.4).build();

        assertEquals(0.1, object.getEnemyDamageFactor());
        assertEquals(0.2, object.getEnemyAttackFactor());
        assertEquals(0.3, object.getCashRewardsFactor());
        assertEquals(0.4, object.getExpRewardsFactor());
        assertEquals(0.5, object.getEnemyCritRateFactor());
        assertEquals(0.6, object.getEnemyCritsEffectFactor());
        assertEquals(0.7, object.getEnemyMagicResistanceFactor());
        assertEquals(0.8, object.getEnemyPhysicalResistanceFactor());
        assertEquals(0.9, object.getRoleMagicResistanceFactor());
        assertEquals(1.0, object.getRoleAttackFactor());
        assertEquals(1.1, object.getRoleCritRateFactor());
        assertEquals(1.2, object.getRoleCritsEffectFactor());
        assertEquals(1.3, object.getRoleDamageFactor());
        assertEquals(1.4, object.getValueFactor());
    }
}