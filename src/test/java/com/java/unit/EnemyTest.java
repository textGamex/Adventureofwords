package com.java.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest
{
    @Test
    void Builder()
    {
       var enemy = new Enemy.Builder("�жԵ�λ").cash(1).build();

       assertTrue(enemy instanceof Enemy);
    }
}