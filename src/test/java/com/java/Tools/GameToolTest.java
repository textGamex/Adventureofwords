package com.java.Tools;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameToolTest
{
    @Test
    void randomRun()
    {
        Assertions.assertTrue(1 > Math.random());
    }

}