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
 * �˺�ģ��
 *
 * @version 1.2.1
 * @since 15
 * @author ǧ��
 */
public final class AccountMessage
{
    /**
     * @param accountName �˺���, �����˺�������ͬ���Ĵ����Ϸ���ݵ��ļ���
     * @throws NullPointerException ���{@code accountName}Ϊnull
     * @since 15
     */
    public AccountMessage(String accountName)
    {
        this.account = requireNonNull(accountName);
        playerPath = gameDataPath.resolve(accountName);//�����˺�Ŀ¼
        id = authentication();
    }

    private static final Path gameDataPath = Paths.get(System.getProperty("user.home"), "AppData",
            "Local", "Adventure of words");
    private Identity id = Identity.NONE;
    /**������ҵĸ������ݵ�Ŀ¼*/
    private Path playerPath;
    private final String account;
    private static final Logger log = Logger.getLogger("AdventureOfWords");

    public Path getPlayerPath()
    {
        return playerPath;
    }

    /**
     * @return XXX/Adventure_of_words/����˻���/Data/fileName
     * @param fileName ������ļ���
     * @throws NullPointerException ���{@code fileName}Ϊnull
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
     * @throws NullPointerException ���{@code id}Ϊnull
     */
    public void setId(Identity id)
    {
        this.id = requireNonNull(id);
    }

    /**
     * @throws NullPointerException ���{@code playerPath}Ϊnull
     */
    public void setPlayerPath(Path playerPath)
    {
        this.playerPath = requireNonNull(playerPath);
    }

    /**
     * @throws NullPointerException ���{@code in}Ϊnull
     */
    public static AccountMessage readAccount(Scanner in)//��ָ���ط���ȡ�˻�
    {
        return new AccountMessage(requireNonNull(in).nextLine());
    }

    private Identity authentication()//�����֤ģ��
    {
        var folder = playerPath.toFile();//�����˺�·��
        Logger.getGlobal().fine(folder.toString());

        /*�˺Ų����������ڲ���Ա*/
        if (fileNotExist(folder) && (account.equals(PrivateData.ACCOUNT1) || account.equals(PrivateData.ACCOUNT2)))
        {
            log.fine(account + "���µ��ڲ���Ա�˺�");
            return Identity.NEW_GAME_MANAGER;
        }
        else if (folder.exists() && (account.equals(PrivateData.ACCOUNT1) || account.equals(PrivateData.ACCOUNT2)))
        {
            Logger.getGlobal().fine(account + "���ڲ���Ա�˺�");
            return Identity.GAME_MANAGER;
        }
        else if (fileNotExist(folder))//�˺Ų����ڶ��������
        {
            Logger.getGlobal().fine(account + "���µ�����˺�");
            return Identity.NEW_PLAYER;
        }
        else
        {
            Logger.getGlobal().fine(account + "������˺�");
            return Identity.PLAYER;
        }
    }

    /**
     * ��������˻����ļ���
     * @since 15
     */
    public void createAccountDataFolder()//
    {
        var file = gameDataPath.resolve(account).toFile();
        if (fileNotExist(file))
        {
            Logger.getGlobal().fine(file + " �ļ��в�����");
            try
            {
                Files.createDirectories(playerPath.resolve("Data"));
//                TODO:logδ���
//                Files.createDirectories(playerDataPath.resolve("Log"));
                Logger.getGlobal().fine(file + " �ļ��д����ɹ�");
            }
            catch (IOException e)
            {
                e.printStackTrace();
                Logger.getGlobal().severe(file + " �ļ��д���ʧ��");
            }
        }
        else
            Logger.getGlobal().fine(file + " �ļ��д���");
    }

    /**
     * ��������˻����ļ���
     * @param message �˺�
     * @since 15
     * @throws NullPointerException ���{@code account}Ϊnull
     */
    public static void createAccountDataFolder(AccountMessage message)//
    {
        requireNonNull(message);
        var file = gameDataPath.resolve(message.account).toFile();
        if (fileNotExist(file))
        {
            Logger.getGlobal().fine(file + " �ļ��в�����");
            try
            {
                Files.createDirectories(message.playerPath.resolve("Data"));
//                TODO:logδ���
//                Files.createDirectories(playerDataPath.resolve("Log"));
                Logger.getGlobal().fine(file + " �ļ��д����ɹ�");
            }
            catch (IOException e)
            {
                e.printStackTrace();
                Logger.getGlobal().severe(file + " �ļ��д���ʧ��");
            }
        }
        else
            Logger.getGlobal().fine(file + " �ļ��д���");
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
                "���:" + id +
                ", ��Ϸ���ݱ���Ŀ¼:" + gameDataPath +
                ", playerPath:" + playerPath +
                ", �˺�:" + account +
                ']';
    }
}