package com.java.message;

import com.alibaba.fastjson.JSONObject;
import com.java.account.AccountMessage;
import com.java.account.Identity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import static java.util.Objects.requireNonNull;
/**
 * ����ͳ����ҵ���Ϸ��Ϣ.
 *
 * <p>���Ѽ�¼һ����Ϣ</p>
 *
 * <li>��Ϸ�ܳ���</li>
 * <li>��ͨ��������</li>
 * <li>���˺�</li>
 * <li>ʤ������</li>
 * <li>��õ����з���</li>
 * <li>��õ����о���</li>
 * <li>��õ����л���</li>
 * @author Millennium
 * @version 0.3.2
 * @since 15
*/
public final class PlayerStatistics implements Serializable
{
    public static void main(String[] args)
    {
    }
    public static final Logger LOGGER = LoggerFactory.getLogger(PlayerStatistics.class);
    @Serial
    private static final long serialVersionUID = 7935923925807359121L;
    private long totalKill;
    private long totalRound;
    private long totalAttack;
    /**���˺�*/
    private long totalHarm;
    /**ʤ������*/
    private long totalVictory;
    private long totalXp;
    private long totalCash;
    private long totalValue;
    //������ʱ�� TODO:δʵ��
//    private long

    public PlayerStatistics()
    {
        totalKill = 0;
        totalRound = 0;
        totalAttack = 0;
        totalHarm = 0;
        totalVictory = 0;
        totalXp = 0;
        totalCash = 0;
        totalValue = 0;
    }

    public PlayerStatistics(long totalKill, long totalRound, long totalAttack, long totalHarm, long totalVictory,
                            long totalXp, long totalCash, long totalValue)
    {
        this.totalKill = totalKill;
        this.totalRound = totalRound;
        this.totalAttack = totalAttack;
        this.totalHarm = totalHarm;
        this.totalVictory = totalVictory;
        this.totalXp = totalXp;
        this.totalCash = totalCash;
        this.totalValue = totalValue;
    }

    public long getTotalKill()
    {
        return totalKill;
    }

    public long getTotalRound()
    {
        return totalRound;
    }

    public long getTotalAttack()
    {
        return totalAttack;
    }

    public long getTotalHarm()
    {
        return totalHarm;
    }

    public long getTotalVictory()
    {
        return totalVictory;
    }

    public long getTotalXp()
    {
        return totalXp;
    }

    /**
     * @param totalXp һ����õľ���
     * @throws IllegalArgumentException ���{@code totalXp}С��0
     */
    public void setTotalXp(long totalXp)
    {
        if (totalXp < 0)
        {
            throw new IllegalArgumentException("����Ϊ����:" + totalXp);
        }
        this.totalXp = totalXp;
    }

    public long getTotalCash()
    {
        return totalCash;
    }

    /**
     * @param totalCash һ����õĻ���
     * @throws IllegalArgumentException ���{@code totalCash}С��0
     */
    public void setTotalCash(long totalCash)
    {
        if (totalCash < 0)
        {
            throw new IllegalArgumentException("����Ϊ����:" + totalCash);
        }
        this.totalCash = totalCash;
    }

    public long getTotalValue()
    {
        return totalValue;
    }

    /**
     * @param totalValue һ����õķ���
     * @throws IllegalArgumentException ���{@code totalValue}С��0
     */
    public void setTotalValue(long totalValue)
    {
        if (totalValue < 0)
        {
            throw new IllegalArgumentException("����Ϊ����:" + totalValue);
        }
        this.totalValue = totalValue;
    }

    /**
     * @param totalKill �ܻ�ɱ
     * @throws IllegalArgumentException ���{@code totalKill}С��0
     */
    public void setTotalKill(long totalKill)
    {
        if (totalKill < 0)
        {
            throw new IllegalArgumentException("����Ϊ����:" + totalKill);
        }
        this.totalKill = totalKill;
    }

    /**
     * @param totalRound ��Ϸ�ܻغ���
     * @throws IllegalArgumentException ���{@code totalRound}С��0
     */
    public void setTotalRound(long totalRound)
    {
        if (totalRound < 0)
        {
            throw new IllegalArgumentException("����Ϊ����:" + totalRound);
        }
        this.totalRound = totalRound;
    }

    /**
     * @param totalAttack �ܻ�ɱ
     * @throws IllegalArgumentException ���{@code totalAttack}С��0
     */
    public void setTotalAttack(long totalAttack)
    {
        if (totalAttack < 0)
        {
            throw new IllegalArgumentException("����Ϊ����:" + totalAttack);
        }
        this.totalAttack = totalAttack;
    }

    /**
     * @param totalHarm ���˺�
     * @throws IllegalArgumentException ���{@code totalHarm}С��0
     */
    public void setTotalHarm(long totalHarm)
    {
        if (totalHarm < 0)
        {
            throw new IllegalArgumentException("����Ϊ����:" + totalHarm);
        }
        this.totalHarm = totalHarm;
    }

    /**
     * @param totalVictory ��ʤ��
     * @throws IllegalArgumentException ���{@code totalVictory}С��0
     */
    public void setTotalVictory(long totalVictory)
    {
        if (totalVictory < 0)
        {
            throw new IllegalArgumentException("����Ϊ����:" + totalVictory);
        }
        this.totalVictory = totalVictory;
    }

    /**
     * @return �ַ�����ʾ�Ķ���
     */
    @Override
    public String toString()
    {
        return  "PlayerStatistics" +
                "[����ܻ�ɱ��:" + totalKill +
                ", �ܳ���:" + totalRound +
                ", ��ͨ��������:" + totalAttack +
                ", ���˺�:" + totalHarm +
                ", ʤ������:" + totalVictory +
                ", һ����õľ���:" + totalXp +
                ", һ����õĻ���:" + totalCash +
                ", һ����õķ���:" + totalValue +
                "]";
    }

    /**
     * �����˺�����ѡ��ͬ�ı��淽ʽ������ͳ����Ϣ.
     *
     * <p>����˻����������,�������л���ʽ����,������ڲ���Ա,����json����</p>
     * @throws NullPointerException ���{@code acc}Ϊnull
     * @param acc �˺�
     * @see AccountMessage
     * @since 16
     */
    public void saveStatistics(final AccountMessage acc)
    {
        if (acc == null)
        {
            throw new NullPointerException();
        }
        if (acc.getId() == Identity.GAME_MANAGER || acc.getId() == Identity.NEW_GAME_MANAGER)
        {
            LOGGER.debug("{}�˺Ų���json��ʽ����", acc.getAccountName());
            saveGameManagerStatistics(acc);
        }
        else
        {
            LOGGER.debug("{}�˺Ų��ÿ��򻯷�ʽ����", acc.getAccountName());
            savePlayerStatistics(acc);
        }
    }
    private void saveGameManagerStatistics(final AccountMessage account)
    {
        assert account != null;

        var jsonFile = new JSONObject();
        jsonFile.put("����ܻ�ɱ��", totalKill);
        jsonFile.put("�ܳ���", totalRound);
        jsonFile.put("��ͨ��������", totalAttack);
        jsonFile.put("���˺�", totalHarm);
        jsonFile.put("ʤ������", totalVictory);
        jsonFile.put("һ����õľ���", totalXp);
        jsonFile.put("һ����õĻ���", totalCash);
        jsonFile.put("һ����õķ���", totalValue);

        try (var out = new PrintWriter(
                account.getPlayerDataResolveFile("PlayerStatistics.json"), StandardCharsets.UTF_8))
        {
            out.println(jsonFile.toJSONString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void savePlayerStatistics(final AccountMessage account)
    {
        assert account != null;
        try (var out = new ObjectOutputStream(
                new FileOutputStream(account.getPlayerDataResolveFile("PlayerStatistics.dat"))))
        {
            out.writeObject(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param account �˺���Ϣ
     * @return  ���˺Ŷ�Ӧ����Ϸͳ����Ϣ
     * @throws FileNotFoundException �����ȡ���ļ�������
     * @throws NullPointerException ���{@code account}Ϊnull
     * @throws IllegalStateException ���{@code account}��id�ֶ�Ϊ{@code NEW_PLAYER}��{@code NEW_GAME_MANAGER}
     * @see AccountMessage
     * @since 15
     */
    public static PlayerStatistics loadStatistics(final AccountMessage account) throws FileNotFoundException
    {
        requireNonNull(account);
        //���������,����ô�ܶ�ȡ��?
        if (fileNotExist(account))
        {
            throw new IllegalStateException("�ļ�������,Id: " + account.getId());
        }

        if (account.getId() == Identity.PLAYER)
        {
            var archive = loadPlayerStatistics(account);
            return requireNonNull(archive);
        }
        else
        {
            return loadGameManagerStatistics(account);
        }
    }

    private static boolean fileNotExist(final AccountMessage acc)
    {
        assert acc != null;

        return acc.getId() == Identity.NONE || acc.getId() == Identity.NEW_GAME_MANAGER
                || acc.getId() == Identity.NEW_PLAYER;
    }

    private static PlayerStatistics loadPlayerStatistics(final AccountMessage acc)
    {
        assert acc != null;

        PlayerStatistics archive = null;
        try (var in = new ObjectInputStream(new FileInputStream(
                acc.getPlayerDataResolveFile("PlayerStatistics.dat"))))
        {
            archive = (PlayerStatistics) in.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return archive;
    }

    private static PlayerStatistics loadGameManagerStatistics(final AccountMessage acc) throws FileNotFoundException
    {
        assert acc != null;

        String line;
        try (var in = new Scanner(acc.getPlayerDataResolveFile(
                "PlayerStatistics.json"), StandardCharsets.UTF_8))
        {
            line = in.nextLine();
        }
        catch (IOException e)
        {
            var e2 = new FileNotFoundException();
            e2.initCause(e);
            throw e2;
        }
        var json = JSONObject.parseObject(line);

        var totalKill = json.getLongValue("����ܻ�ɱ��");
        var totalRound = json.getLongValue("�ܳ���");
        var totalAttack = json.getLongValue("��ͨ��������");
        var totalHarm = json.getLongValue("���˺�");
        var totalVictory = json.getLongValue("ʤ������");
        var totalXp = json.getLongValue("һ����õľ���");
        var totalCash = json.getLongValue("һ����õĻ���");
        var totalValue = json.getLongValue("һ����õķ���");

        return new PlayerStatistics(totalKill, totalRound, totalAttack, totalHarm, totalVictory, totalXp, totalCash,
                totalValue);
    }
}