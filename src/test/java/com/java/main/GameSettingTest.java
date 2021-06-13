package com.java.main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.java.main.GameSetting.*;

class GameSettingTest
{
    @Test
    void testLoadSettingFileNullPointer()
    {
        assertThrows(NullPointerException.class, () -> loadSettingFile(null));
    }

    @Test
    void testSetSeparatorCharacterNullPointer()
    {
        assertThrows(NullPointerException.class, () -> getGameSetting().setSeparatorCharacter(null));
    }

    @Test
    void testSaveSettingNullPointer()
    {
        assertThrows(NullPointerException.class, () -> getGameSetting().saveSetting(null));
    }
}