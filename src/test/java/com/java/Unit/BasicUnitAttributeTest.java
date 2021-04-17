package com.java.Unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicUnitAttributeTest
{
    @DisplayName("Builderģʽ����")
    @Test
    void Builder()
    {
        var unit = new BasicUnitAttribute.Builder("��λ", 100).atk(2)
                .evade(3.0).mana(101).build();

        Assertions.assertEquals("��λ", unit.getName());
        Assertions.assertEquals(100, unit.getHp());
        Assertions.assertEquals(2, unit.getAtk());
        Assertions.assertEquals(3, unit.getEvade());
        Assertions.assertEquals(101, unit.getMana());
    }
}