package com.java.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest
{
    @Test
    void Builder()
    {
       var enemy = new Enemy.Builder("敌对单位").cash(1).build();

       assertTrue(enemy instanceof Enemy);
    }
}