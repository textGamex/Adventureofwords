package com.java.Unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicUnitAttributeTest
{
    @DisplayName("Builder模式测试")
    @Test
    void Builder()
    {
        var unit = new BasicUnitAttribute.Builder("单位").atk(2)
                .evade(3.0).mana(101).maxHp(5).level(6).critRate(7.0).critsEffect(8.0).lifeRegeneration(9)
                .physicalResistance(10.0).build();

        Assertions.assertEquals("单位", unit.getName());
        Assertions.assertEquals(2, unit.getAtk());
        Assertions.assertEquals(3.0, unit.getEvade());
        Assertions.assertEquals(101, unit.getMana());
        Assertions.assertEquals(5, unit.getHp());
        Assertions.assertEquals(6, unit.getLevel());
        Assertions.assertEquals(7.0, unit.getCritRate());
        Assertions.assertEquals(8.0, unit.getCritsEffect());
        Assertions.assertEquals(9, unit.getLifeRegeneration());
        Assertions.assertEquals(10.0, unit.getPhysicalResistance());
    }
}