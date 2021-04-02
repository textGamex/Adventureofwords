package javaLogic.Account;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import static javaLogic.Message.PrivateData.*;

public class AccountMessage
{
    public static void main(String[] args)
    {
        System.out.println(Authentication("program-0"));
    }
    private static final Path playerDataPath = Paths.get(System.getProperty("user.home"), "AppData", "Local", "Adventure of words");
    private Identity id;

    public Path getPlayerDataPath()
    {
        return playerDataPath;
    }
    public Identity getId()
    {
        return id;
    }
    public void setId(Identity id)
    {
        this.id = id;
    }

    public static Identity Authentication(String account)//身份验证模块
    {
        var path = playerDataPath.resolve(account);//构造账号路径
        Logger.getGlobal().info(path.toString());
        var file = path.toFile();

        /*账号不存在且是内部人员*/
        if (!file.exists() && (account.equals(ACCOUNT1) || account.equals(ACCOUNT2)))
            return Identity.NEWGM;
        else if (file.exists() && (account.equals(ACCOUNT1) || account.equals(ACCOUNT2)))
            return Identity.GM;
        else if (!file.exists())//账号不存在而且是玩家
            return Identity.NEWPLAYER;
        else
            return Identity.PLAYER;
    }
}