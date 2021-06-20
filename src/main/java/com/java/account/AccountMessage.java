package com.java.account;

import com.java.message.PrivateData;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import static java.util.Objects.requireNonNull;

/**
 * 账号模块.
 *
 * @version 1.2.1
 * @since 15
 * @author 留恋千年
 */
public final class AccountMessage
{
    public static void main(String[] args)
    {
        System.out.println("123");
        System.out.println("\033[31;37;5m hello world\033[0m");
//        System.out.println("\[2J");
        System.out.println("\033[31mThe ......\033[0m");
        System.out.println("我吃饭");
    }
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AccountMessage.class);
    private static final Path gameDataPath = Paths.get(System.getProperty("user.home"), "AppData",
            "Local", "Adventure of words");
    private Identity id;

    /**保存玩家的各种数据的目录*/
    private Path playerPath;
    private final String accountName;

    /**
     * @param accountName 账号名, 根据账号名创建同名的存放游戏数据的文件夹
     * @throws NullPointerException 如果{@code accountName}为null
     * @since 15
     */
    public AccountMessage(final String accountName)
    {
        this.accountName = requireNonNull(accountName);
        //保存账号目录
        playerPath = gameDataPath.resolve(accountName);
        id = authentication();
    }

    /**
     * @return XXX/Adventure_of_words/玩家账户名/Data/fileName
     * @param fileName 保存的文件名
     * @throws NullPointerException 如果{@code fileName}为null
     */
    public File getPlayerDataResolveFile(final String fileName)
    {
       return playerPath.resolve("Data").resolve(requireNonNull(fileName)).toFile();
    }

    public final Identity getId()
    {
        return id;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public Path getGameDataPath()
    {
        return gameDataPath;
    }

    public Path getPlayerPath()
    {
        return playerPath;
    }

    /**
     * 设置账号的身份.
     *
     * @param id 账号的身份
     * @throws NullPointerException 如果{@code id}为null
     */
    public void setId(final Identity id)
    {
        this.id = requireNonNull(id);
    }

    /**
     * 设置保存玩家的各种数据的目录.
     *
     * @param playerPath 设置保存玩家的各种数据的目录
     * @throws NullPointerException 如果{@code playerPath}为null
     */
    public void setPlayerPath(final Path playerPath)
    {
        this.playerPath = requireNonNull(playerPath);
    }

    /**
     * 从指定位置读取账户.
     *
     * @throws NullPointerException 如果{@code in}为null
     */
    public static AccountMessage readAccount(final Scanner in)//从指定地方读取账户
    {
        return new AccountMessage(requireNonNull(in).nextLine());
    }

    //身份验证模块
    private Identity authentication()
    {
        //构造账号路径
        var folder = playerPath.toFile();
        LOGGER.debug(folder.toString());

        /*账号不存在且是内部人员*/
        final var isGameManager = accountName.equals(PrivateData.ACCOUNT1) || accountName.equals(PrivateData.ACCOUNT2);
        if (fileNotExist(folder) && isGameManager)
        {
            LOGGER.debug("[{}]是新的内部人员账号", accountName);
            return Identity.NEW_GAME_MANAGER;
        }
        else if (folder.exists() && isGameManager)
        {
            LOGGER.debug("[{}]是内部人员账号", accountName);
            return Identity.GAME_MANAGER;
        }
        //账号不存在而且是玩家
        else if (fileNotExist(folder))
        {
            LOGGER.debug("[{}]是新的玩家账号", accountName);
            return Identity.NEW_PLAYER;
        }
        else
        {
            LOGGER.debug("[{}]是玩家账号", accountName);
            return Identity.PLAYER;
        }
    }

    /**
     * 创建相关账户的文件夹
     * @since 15
     */
    public void createAccountDataFolder()//
    {
        createAccountDataFolder(this);
    }

    /**
     * 使用给定的账号创建文件夹.
     *
     * @param message 账号
     * @since 15
     * @throws NullPointerException 如果{@code account}为null
     */
    public static void createAccountDataFolder(final AccountMessage message)//
    {
        requireNonNull(message);
        LOGGER.debug("开始创建[{}]账号的数据文件夹", message.accountName);

        var file = gameDataPath.resolve(message.accountName).toFile();
        if (fileNotExist(file))
        {
            LOGGER.debug("{}文件夹不存在", file);
            try
            {
                Files.createDirectories(message.playerPath.resolve("Data"));
                LOGGER.debug("{}文件夹创建成功", file);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                LOGGER.error("{}文件夹创建失败", file);
            }
        }
        else
        {
            LOGGER.debug("{}文件夹存在", file);
        }
    }
    private static boolean fileNotExist(final File file)
    {
        assert file != null;
        return !file.exists();
    }

    /**
     * @return 字符串表示的对象
     */
    @Override
    public String toString()
    {
        return "AccountMessage[" +
                "身份:" + id +
                ", 游戏数据保存目录:" + gameDataPath +
                ", playerPath:" + playerPath +
                ", 账号名:" + accountName +
                ']';
    }
}