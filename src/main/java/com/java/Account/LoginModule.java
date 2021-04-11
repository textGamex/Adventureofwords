package com.java.Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public final class LoginModule
{
    public static void main(String[] args)
    {
        final Path playerDataPath = Paths.get(System.getProperty("user.home"), "AppData", "Local", "Adventure of words");
        var file = playerDataPath.toFile();
        if (!file.exists())
        {
            Logger.getGlobal().info("文件不存在");
            try
            {
                Files.createDirectories(playerDataPath);
                Logger.getGlobal().info("文件创建成功");
            }
            catch (IOException e)
            {
                e.printStackTrace();
                Logger.getGlobal().severe("文件创建失败");
            }
        }
        else
            Logger.getGlobal().info("文件存在");
    }
//    public int ()
//    {
//
//    }
}
