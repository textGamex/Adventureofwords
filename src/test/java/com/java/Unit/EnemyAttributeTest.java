package com.java.Unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyAttributeTest
{
    @Test
    void Builder()
    {
       var enemy = new EnemyAttribute.Builder("�жԵ�λ").cash(1).build();

       assertTrue(enemy instanceof EnemyAttribute);
    }
}