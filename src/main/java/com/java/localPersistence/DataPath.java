package com.java.localPersistence;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author ¡Ù¡µ«ßƒÍ
 * @version 1.1.0
 */
public final class DataPath
{
    public static final Path GAME_DATA_PATH = Paths.get(System.getProperty("user.home"), "AppData",
            "Local", "Adventure of words");
    public static final Path DESKTOP = Paths.get(System.getProperty("user.home"), "Desktop");
}