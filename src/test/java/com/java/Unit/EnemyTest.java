package com.java.Unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest
{
    @Test
    void Builder()
    {
       var enemy = new Enemy.Builder("µÐ¶Ôµ¥Î»").cash(1).build();

       assertTrue(enemy instanceof Enemy);
    }
}