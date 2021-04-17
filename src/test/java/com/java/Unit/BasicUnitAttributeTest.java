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
        var unit = new BasicUnitAttribute.Builder("单位", 100).atk(2)
                .evade(3.0).mana(101).build();

        Assertions.assertEquals("单位", unit.getName());
        Assertions.assertEquals(100, unit.getHp());
        Assertions.assertEquals(2, unit.getAtk());
        Assertions.assertEquals(3, unit.getEvade());
        Assertions.assertEquals(101, unit.getMana());
    }
}