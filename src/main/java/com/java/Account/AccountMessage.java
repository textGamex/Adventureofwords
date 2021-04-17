package com.java.Account;

import com.java.Message.PrivateData;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class AccountMessage
{
    public static void main(String[] args)
    {
        var log = Logger.getLogger("123");
        log.setLevel(Level.FINER);
        log.config("{0}s");
    }

    public AccountMessage(String account)
    {
        this.account = account;
        playerPath = gameDataPath.resolve(account);//�����˺�Ŀ¼
        id = Authentication();
    }
    private static final Path gameDataPath = Paths.get(System.getProperty("user.home"), "AppData", "Local", "Adventure of words");
    private Identity id = Identity.NONE;
    /**������ҵĸ������ݵ�Ŀ¼*/
    private Path playerPath;
    private String account = "";
    private static final Logger log = Logger.getLogger("AdventureOfWords");

    public Path getPlayerPath()
    {
        return playerPath;
    }
    public Path getPlayerDataResolvePath(String fileName)
    {
       return playerPath.resolve("Data").resolve(fileName);
    }
    public File getPlayerDataResolveFile(String fileName)
    {
       return playerPath.resolve("Data").resolve(fileName).toFile();
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
    public void setId(Identity id)
    {
        this.id = id;
    }
    public void setPlayerPath(Path playerPath)
    {
        this.playerPath = playerPath;
    }

    public static AccountMessage readAccount(@NotNull Scanner in)//��ָ���ط���ȡ�˻�
    {
        return new AccountMessage(in.nextLine());
    }
    private Identity Authentication()//�����֤ģ��
    {
        var folder = playerPath.toFile();//�����˺�·��
        Logger.getGlobal().info(folder.toString());

        /*�˺Ų����������ڲ���Ա*/
        if (!folder.exists() && (account.equals(PrivateData.ACCOUNT1) || account.equals(PrivateData.ACCOUNT2)))
        {
            log.info(account + "���µ��ڲ���Ա�˺�");
            return Identity.NEW_GAME_MANAGER;
        }
        else if (folder.exists() && (account.equals(PrivateData.ACCOUNT1) || account.equals(PrivateData.ACCOUNT2)))
        {
            Logger.getGlobal().info(account + "���ڲ���Ա�˺�");
            return Identity.GAME_MANAGER;
        }
        else if (!folder.exists())//�˺Ų����ڶ��������
        {
            Logger.getGlobal().info(account + "���µ�����˺�");
            return Identity.NEW_PLAYER;
        }
        else {
            Logger.getGlobal().info(account + "������˺�");
            return Identity.PLAYER;
        }
    }
    public void createAccountDataFolder()//��������˻����ļ���
    {
        var file = gameDataPath.resolve(account).toFile();
        if (!file.exists())
        {
            Logger.getGlobal().info(file + " �ļ��в�����");
            try
            {
                Files.createDirectories(playerPath.resolve("Data"));
//                Files.createDirectories(playerDataPath.resolve("Log"));//TODO:logδ���
                Logger.getGlobal().info(file + " �ļ��д����ɹ�");
            }
            catch (IOException e)
            {
                e.printStackTrace();
                Logger.getGlobal().severe(file + " �ļ��д���ʧ��");
            }
        } else
            Logger.getGlobal().info(file + " �ļ��д���");
    }

    @Override
    public String toString()
    {
        return "AccountMessage[" +
                "���:" + id +
                ", ��Ϸ���ݱ���Ŀ¼:" + gameDataPath +
                ", playerPath:" + playerPath +
                ", �˺�:" + account +
                ']';
    }
}