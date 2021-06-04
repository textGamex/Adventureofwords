package com.java.tools;

import com.java.battleSystem.BattleAttributeCalculation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleAttributeCalculationTest
{

    @Test
    void victimEvade()
    {
        var victimEvade1 = BattleAttributeCalculation.attackHitRate(1, -1);
        var victimEvade2 = BattleAttributeCalculation.attackHitRate(-1, 1);

        assertEquals(victimEvade1, 0.0);
        assertEquals(victimEvade2, 1.0);
    }

    @Test
    void attackerCritChance()
    {
    }

    @Test
    void attackerPhyAtkDamage()
    {
    }
}