package com.java.message;

import com.java.account.AccountMessage;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerStatisticsTest
{

    @Test
    void loadStatistics() throws FileNotFoundException
    {
        var testAccount = new AccountMessage(PrivateData.ACCOUNT2);
        var gameManagerStatistics = new PlayerStatistics(1, 2, 3, 4, 5, 6, 7,8);

        gameManagerStatistics.saveStatistics(testAccount);
        var data = PlayerStatistics.loadStatistics(testAccount);

        assertEquals(1, data.getTotalKill());
        assertEquals(2, data.getTotalRound());
        assertEquals(3, data.getTotalAttack());
        assertEquals(4, data.getTotalHarm());
        assertEquals(5, data.getTotalVictory());
        assertEquals(6, data.getTotalXp());
        assertEquals(7, data.getTotalCash());
        assertEquals(8, data.getTotalValue());
    }
}