package com.java.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameToolTest
{

    @Test
    void randomRun()
    {
        assertTrue(GameTool.randomRun(1));
        assertFalse(GameTool.randomRun(0));
    }
}