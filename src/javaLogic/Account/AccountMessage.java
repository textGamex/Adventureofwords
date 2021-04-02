package javaLogic.Account;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AccountMessage
{
    private final Path playerDataPath = Paths.get(System.getProperty("user.home"), "AppData", "Local", "Adventure of words");
    private Identity id;

    public Path getPlayerDataPath()
    {
        return playerDataPath;
    }

    public Identity getId()
    {
        return id;
    }
}
