package com.java.account;

import com.java.message.PrivateData;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;
import static java.util.Objects.requireNonNull;

/**
 * �˺�ģ��.
 *
 * @version 1.2.1
 * @since 15
 * @author ǧ��
 */
public final class AccountMessage
{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AccountMessage.class);
    private static final Path gameDataPath = Paths.get(System.getProperty("user.home"), "AppData",
            "Local", "Adventure of words");
    private Identity id;

    /**������ҵĸ������ݵ�Ŀ¼*/
    private Path playerPath;
    private final String accountName;

    /**
     * @param accountName �˺���, �����˺�������ͬ���Ĵ����Ϸ���ݵ��ļ���
     * @throws NullPointerException ���{@code accountName}Ϊnull
     * @since 15
     */
    public AccountMessage(String accountName)
    {
        this.accountName = requireNonNull(accountName);
        playerPath = gameDataPath.resolve(accountName);//�����˺�Ŀ¼
        id = authentication();
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
     * �����˺ŵ����.
     *
     * @param id �˺ŵ����
     * @throws NullPointerException ���{@code id}Ϊnull
     */
    public void setId(Identity id)
    {
        this.id = requireNonNull(id);
    }

    /**
     * ���ñ�����ҵĸ������ݵ�Ŀ¼.
     *
     * @param playerPath ���ñ�����ҵĸ������ݵ�Ŀ¼
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
        final var isGameManager = accountName.equals(PrivateData.ACCOUNT1) || accountName.equals(PrivateData.ACCOUNT2);
        if (fileNotExist(folder) && isGameManager)
        {
            LOGGER.debug("{}���µ��ڲ���Ա�˺�", accountName);
            return Identity.NEW_GAME_MANAGER;
        }
        else if (folder.exists() && isGameManager)
        {
            LOGGER.debug("{}���ڲ���Ա�˺�", accountName);
            return Identity.GAME_MANAGER;
        }
        else if (fileNotExist(folder))//�˺Ų����ڶ��������
        {
            LOGGER.debug("{}���µ�����˺�", accountName);
            return Identity.NEW_PLAYER;
        }
        else
        {
            LOGGER.debug("{}������˺�", accountName);
            return Identity.PLAYER;
        }
    }

    /**
     * ��������˻����ļ���
     * @since 15
     */
    public void createAccountDataFolder()//
    {
        createAccountDataFolder(this);
    }

    /**
     * ʹ�ø������˺Ŵ����ļ���.
     *
     * @param message �˺�
     * @since 15
     * @throws NullPointerException ���{@code account}Ϊnull
     */
    public static void createAccountDataFolder(AccountMessage message)//
    {
        requireNonNull(message);
        LOGGER.debug("��ʼ����{}�˺ŵ������ļ���", message.accountName);

        var file = gameDataPath.resolve(message.accountName).toFile();
        if (fileNotExist(file))
        {
            LOGGER.debug("{}�ļ��в�����", file);
            try
            {
                Files.createDirectories(message.playerPath.resolve("Data"));
                LOGGER.debug("{}�ļ��д����ɹ�", file);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                LOGGER.warn("{}�ļ��д���ʧ��", file);
            }
        }
        else
            LOGGER.debug("{}�ļ��д���", file);
    }
    private static boolean fileNotExist(File file)
    {
        assert file != null;
        return !file.exists();
    }

    /**
     * @return �ַ�����ʾ�Ķ���
     */
    @Override
    public String toString()
    {
        return "AccountMessage[" +
                "���:" + id +
                ", ��Ϸ���ݱ���Ŀ¼:" + gameDataPath +
                ", playerPath:" + playerPath +
                ", �˺�:" + accountName +
                ']';
    }
}