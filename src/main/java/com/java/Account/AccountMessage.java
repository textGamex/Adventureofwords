package com.java.Account;

import com.java.Message.PrivateData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;
import static java.util.Objects.requireNonNull;

/**
 * 账号模块
 *
 * @version 1.2.1
 * @since 15
 * @author 千年
 */
public final class AccountMessage
{
    /**
     * @param accountName 账号名, 根据账号名创建同名的存放游戏数据的文件夹
     * @throws NullPointerException 如果{@code accountName}为null
     * @since 15
     */
    public AccountMessage(String accountName)
    {
        this.account = requireNonNull(accountName);
        playerPath = gameDataPath.resolve(accountName);//保存账号目录
        id = authentication();
    }

    private static final Path gameDataPath = Paths.get(System.getProperty("user.home"), "AppData",
            "Local", "Adventure of words");
    private Identity id = Identity.NONE;
    /**保存玩家的各种数据的目录*/
    private Path playerPath;
    private final String account;
    private static final Logger log = Logger.getLogger("AdventureOfWords");

    public Path getPlayerPath()
    {
        return playerPath;
    }

    /**
     * @return XXX/Adventure_of_words/玩家账户名/Data/fileName
     * @param fileName 保存的文件名
     * @throws NullPointerException 如果{@code fileName}为null
     */
    public File getPlayerDataResolveFile(String fileName)
    {
       return playerPath.resolve("Data").resolve(requireNonNull(fileName)).toFile();
    }

    public final Identity getId()
    {
        return id;
    }

    public String getAccount()
    {
        return account;
    }

    public Path getGameDataPath()
    {
        return gameDataPath;
    }

    /**
     * @throws NullPointerException 如果{@code id}为null
     */
    public void setId(Identity id)
    {
        this.id = requireNonNull(id);
    }

    /**
     * @throws NullPointerException 如果{@code playerPath}为null
     */
    public void setPlayerPath(Path playerPath)
    {
        this.playerPath = requireNonNull(playerPath);
    }

    /**
     * @throws NullPointerException 如果{@code in}为null
     */
    public static AccountMessage readAccount(Scanner in)//从指定地方读取账户
    {
        return new AccountMessage(requireNonNull(in).nextLine());
    }

    private Identity authentication()//身份验证模块
    {
        var folder = playerPath.toFile();//构造账号路径
        Logger.getGlobal().fine(folder.toString());

        /*账号不存在且是内部人员*/
        if (fileNotExist(folder) && (account.equals(PrivateData.ACCOUNT1) || account.equals(PrivateData.ACCOUNT2)))
        {
            log.fine(account + "是新的内部人员账号");
            return Identity.NEW_GAME_MANAGER;
        }
        else if (folder.exists() && (account.equals(PrivateData.ACCOUNT1) || account.equals(PrivateData.ACCOUNT2)))
        {
            Logger.getGlobal().fine(account + "是内部人员账号");
            return Identity.GAME_MANAGER;
        }
        else if (fileNotExist(folder))//账号不存在而且是玩家
        {
            Logger.getGlobal().fine(account + "是新的玩家账号");
            return Identity.NEW_PLAYER;
        }
        else
        {
            Logger.getGlobal().fine(account + "是玩家账号");
            return Identity.PLAYER;
        }
    }

    /**
     * 创建相关账户的文件夹
     * @since 15
     */
    public void createAccountDataFolder()//
    {
        var file = gameDataPath.resolve(account).toFile();
        if (fileNotExist(file))
        {
            Logger.getGlobal().fine(file + " 文件夹不存在");
            try
            {
                Files.createDirectories(playerPath.resolve("Data"));
//                TODO:log未完成
//                Files.createDirectories(playerDataPath.resolve("Log"));
                Logger.getGlobal().fine(file + " 文件夹创建成功");
            }
            catch (IOException e)
            {
                e.printStackTrace();
                Logger.getGlobal().severe(file + " 文件夹创建失败");
            }
        }
        else
            Logger.getGlobal().fine(file + " 文件夹存在");
    }

    /**
     * 创建相关账户的文件夹
     * @param message 账号
     * @since 15
     * @throws NullPointerException 如果{@code account}为null
     */
    public static void createAccountDataFolder(AccountMessage message)//
    {
        requireNonNull(message);
        var file = gameDataPath.resolve(message.account).toFile();
        if (fileNotExist(file))
        {
            Logger.getGlobal().fine(file + " 文件夹不存在");
            try
            {
                Files.createDirectories(message.playerPath.resolve("Data"));
//                TODO:log未完成
//                Files.createDirectories(playerDataPath.resolve("Log"));
                Logger.getGlobal().fine(file + " 文件夹创建成功");
            }
            catch (IOException e)
            {
                e.printStackTrace();
                Logger.getGlobal().severe(file + " 文件夹创建失败");
            }
        }
        else
            Logger.getGlobal().fine(file + " 文件夹存在");
    }
    private static boolean fileNotExist(File file)
    {
        assert file != null;
        return !file.exists();
    }

    @Override
    public String toString()
    {
        return "AccountMessage[" +
                "身份:" + id +
                ", 游戏数据保存目录:" + gameDataPath +
                ", playerPath:" + playerPath +
                ", 账号:" + account +
                ']';
    }
}