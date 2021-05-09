package com.java.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameToolTest
{
    @Test
    void randomRun()
    {
        Assertions.assertTrue(1 > Math.random());
    }

}