package com.java.Unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyAttributeTest
{
    @Test
    void Builder()
    {
       var enemy = new EnemyAttribute.Builder("µÐ¶Ôµ¥Î»").cash(1).build();

       assertTrue(enemy instanceof EnemyAttribute);
    }
}