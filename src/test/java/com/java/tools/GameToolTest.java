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

    @Test
    void testBytesToInt()
    {
        byte[] actual = {1, 0, 0, 0};
        var num = GameTool.bytesToInt(actual);

        assertEquals(1, 1);
    }

    @Test
    void testIntToBytes()
    {
        var bytes1 = GameTool.intToBytes(1);
        byte[] actual1 = {1, 0, 0, 0};

        assertArrayEquals(actual1, bytes1);
    }
}