package javaLogic.Account;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;
import static javaLogic.Message.PrivateData.*;

public class AccountModule
{
    public static void main(String[] args)
    {

    }

    public AccountModule(String account)
    {
        this.account = account;
        Authentication();
    }
    private static final Path gameDataPath = Paths.get(System.getProperty("user.home"), "AppData", "Local", "Adventure of words");
    private Identity id = Identity.NONE;
    private  Path playerDataPath;
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

    public static AccountModule readAccount(@NotNull Scanner in)//从指定地方读取账户
    {
        return new AccountModule(in.nextLine());
    }
    private void Authentication()//身份验证模块
    {
        var path = gameDataPath.resolve(account);//构造账号路径
        Logger.getGlobal().info(path.toString());
        var file = path.toFile();

        playerDataPath = path;//保存账号目录
        /*账号不存在且是内部人员*/
        if (!file.exists() && (account.equals(ACCOUNT1) || account.equals(ACCOUNT2)))
        {
            id = Identity.NEWGM;
            Logger.getGlobal().info(account + "是新的内部人员账号");
        }
        else if (file.exists() && (account.equals(ACCOUNT1) || account.equals(ACCOUNT2)))
        {
            id = Identity.GM;
            Logger.getGlobal().info(account + "是内部人员账号");
        }
        else if (!file.exists())//账号不存在而且是玩家
        {
            id = Identity.NEWPLAYER;
            Logger.getGlobal().info(account + "是新的玩家账号");
        }
        else {
            id = Identity.PLAYER;
            Logger.getGlobal().info(account + "是玩家账号");
        }
    }
    public static void createAccountDataFolder(String account)//创建相关账户的文件夹
    {
        var file = gameDataPath.resolve(account).toFile();
        if (!file.exists())
        {
            Logger.getGlobal().info(file + " 文件夹不存在");
            try
            {
                Files.createDirectories(gameDataPath);
                Logger.getGlobal().info(file + " 文件夹创建成功");
            }
            catch (IOException e)
            {
                e.printStackTrace();
                Logger.getGlobal().severe(file + " 文件夹创建失败");
            }
        }
        else
            Logger.getGlobal().info(file + " 文件夹存在");
    }

    public void setPlayerDataPath(Path playerDataPath) {
        this.playerDataPath = playerDataPath;
    }
}