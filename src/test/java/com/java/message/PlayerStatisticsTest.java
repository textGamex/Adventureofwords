package com.java.message;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import static com.java.message.PlayerStatistics.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerStatisticsTest
{
    PlayerStatistics testThrow = new PlayerStatistics(1, 2, 3, 4,
            5, 6, 7,8);
//    @Test
//    void testLoadStatistics() throws FileNotFoundException
//    {
//        var gameManagerStatistics = new PlayerStatistics(1, 2, 3, 4,
//                5, 6, 7,8);
//
//        gameManagerStatistics.saveStatistics(testAccount);
//        var data = PlayerStatistics.loadStatistics(testAccount);
//
//        assertEquals(1, data.getTotalKill());
//        assertEquals(2, data.getTotalRound());
//        assertEquals(3, data.getTotalAttack());
//        assertEquals(4, data.getTotalHarm());
//        assertEquals(5, data.getTotalVictory());
//        assertEquals(6, data.getTotalXp());
//        assertEquals(7, data.getTotalCash());
//        assertEquals(8, data.getTotalValue());
//    }

    @Test
    void testSaveStatisticsNullPointer()
    {
        assertThrows(NullPointerException.class, () -> testThrow.saveStatistics(null));
    }

    @Test
    void testLoadStatisticsNullPointer()
    {
        assertThrows(NullPointerException.class, () -> loadStatistics(null));
    }

    @Test
    void testSetIllegalArgument()
    {
        assertThrows(IllegalArgumentException.class, () -> testThrow.setTotalKill(-1));
        assertThrows(IllegalArgumentException.class, () -> testThrow.setTotalValue(-1));
        assertThrows(IllegalArgumentException.class, () -> testThrow.setTotalVictory(-1));
        assertThrows(IllegalArgumentException.class, () -> testThrow.setTotalCash(-1));
        assertThrows(IllegalArgumentException.class, () -> testThrow.setTotalHarm(-1));
        assertThrows(IllegalArgumentException.class, () -> testThrow.setTotalXp(-1));
        assertThrows(IllegalArgumentException.class, () -> testThrow.setTotalAttack(-1));
        assertThrows(IllegalArgumentException.class, () -> testThrow.setTotalRound(-1));
    }
}