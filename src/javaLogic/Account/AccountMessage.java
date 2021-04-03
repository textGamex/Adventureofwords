package javaLogic.Account;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;
import static javaLogic.Message.PrivateData.*;

public final class AccountMessage
{
    public static void main(String[] args)
    {

    }

    public AccountMessage(String account)
    {
        this.account = account;
        playerDataPath = gameDataPath.resolve(account);//保存账号目录
        id = Authentication();
    }
    private static final Path gameDataPath = Paths.get(System.getProperty("user.home"), "AppData", "Local", "Adventure of words");
    private Identity id = Identity.NONE;
    /**保存玩家的各种数据的目录*/
    private Path playerDataPath;
    private String account = "";

    public Path getPlayerDataPath()
    {
        return playerDataPath;
    }
    public Identity getId()
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
    public void setId(Identity id)
    {
        this.id = id;
    }
    public void setPlayerDataPath(Path playerDataPath)
    {
        this.playerDataPath = playerDataPath;
    }

    public static AccountMessage readAccount(@NotNull Scanner in)//从指定地方读取账户
    {
        return new AccountMessage(in.nextLine());
    }
    private Identity Authentication()//身份验证模块
    {
        var folder = playerDataPath.toFile();//构造账号路径
        Logger.getGlobal().info(folder.toString());

        /*账号不存在且是内部人员*/
        if (!folder.exists() && (account.equals(ACCOUNT1) || account.equals(ACCOUNT2)))
        {
            Logger.getGlobal().info(account + "是新的内部人员账号");
            return Identity.NEWGM;
        }
        else if (folder.exists() && (account.equals(ACCOUNT1) || account.equals(ACCOUNT2)))
        {
            Logger.getGlobal().info(account + "是内部人员账号");
            return Identity.GM;
        }
        else if (!folder.exists())//账号不存在而且是玩家
        {
            Logger.getGlobal().info(account + "是新的玩家账号");
            return Identity.NEWPLAYER;
        }
        else {
            Logger.getGlobal().info(account + "是玩家账号");
            return Identity.PLAYER;
        }
    }
    public static void createAccountDataFolder(String account)//创建相关账户的文件夹
    {
        var file = gameDataPath.resolve(account).toFile();
        if (!file.exists()) {
            Logger.getGlobal().info(file + " 文件夹不存在");
            try {
                Files.createDirectories(gameDataPath);
                Logger.getGlobal().info(file + " 文件夹创建成功");
            } catch (IOException e) {
                e.printStackTrace();
                Logger.getGlobal().severe(file + " 文件夹创建失败");
            }
        } else
            Logger.getGlobal().info(file + " 文件夹存在");
    }
}