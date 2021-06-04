package com.java.localPersistence;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class DataPath
{
    public static final Path GAME_DATA_PATH = Paths.get(System.getProperty("user.home"), "AppData",
            "Local", "Adventure of words");
}
