package com.java.tools;

import org.junit.jupiter.api.Test;

import static com.java.tools.GameTool.*;
import static org.junit.jupiter.api.Assertions.*;

class GameToolTest
{

    @Test
    void testRandomRun()
    {
        assertTrue(randomRun(1));
        assertFalse(randomRun(0));
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

    @Test
    void testFloatingNumberIllegalArgument()
    {
        assertThrows(IllegalArgumentException.class, () -> floatingNumber(10, -10));
        assertThrows(IllegalArgumentException.class, () -> floatingNumber(10, -10,
                SpecifiedDirection.ONLY_INCREASE));
        assertThrows(IllegalArgumentException.class, () -> floatingNumber(10, -1.1));
        assertThrows(IllegalArgumentException.class, () -> floatingNumber(10, -1.1,
                SpecifiedDirection.ONLY_INCREASE));
        assertThrows(IllegalArgumentException.class, () -> floatingNumber(10.1, -1.1));
    }

    @Test
    void testFloatingNumberNullPointer()
    {
        assertThrows(NullPointerException.class, () -> floatingNumber(10, 10, null));
        assertThrows(NullPointerException.class, () -> floatingNumber(10, 1.1, null));
    }
}