package com.java.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttributeCalculationTest
{

    @Test
    void victimEvade()
    {
        var victimEvade1 = AttributeCalculation.victimEvade(1, -1);
        var victimEvade2 = AttributeCalculation.victimEvade(-1, 1);

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