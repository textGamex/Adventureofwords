package com.java.Message;

import com.alibaba.fastjson.JSONObject;
import com.java.Account.AccountMessage;
import com.java.Account.Identity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
/**
 *ͳ�������Ϣ
 *@author Millennium
 *@version 0.3.2
 *@Date 2021/3/13 22:42
*/
public final class PlayerStatistics implements Serializable
{
    public static void main(String[] args)
    {

    }
    @Serial
    private static final long serialVersionUID = 7935923925807359121L;
    private long totalKill;
    //�ܳ���
    private long totalRound;
    //��ͨ��������
    private long totalAttack;
    //���˺�
    private long totalHarm;
    //ʤ������
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

    public void setTotalXp(long totalXp)
    {
        if (totalXp < 0)
            throw new IllegalArgumentException("����Ϊ����:" + totalXp);
        this.totalXp = totalXp;
    }

    public long getTotalCash()
    {
        return totalCash;
    }

    public void setTotalCash(long totalCash)
    {
        if (totalCash < 0)
            throw new IllegalArgumentException("����Ϊ����:" + totalCash);
        this.totalCash = totalCash;
    }

    public long getTotalValue()
    {
        return totalValue;
    }

    public void setTotalValue(long totalValue)
    {
        if (totalValue < 0)
            throw new IllegalArgumentException("����Ϊ����:" + totalValue);
        this.totalValue = totalValue;
    }

    public void setTotalKill(long totalKill)
    {
        if (totalKill < 0)
            throw new IllegalArgumentException("����Ϊ����:" + totalKill);
        this.totalKill = totalKill;
    }

    public void setTotalRound(long totalRound)
    {
        if (totalRound < 0)
            throw new IllegalArgumentException("����Ϊ����:" + totalRound);
        this.totalRound = totalRound;
    }

    public void setTotalAttack(long totalAttack)
    {
        if (totalAttack < 0)
            throw new IllegalArgumentException("����Ϊ����:" + totalAttack);
        this.totalAttack = totalAttack;
    }

    public void setTotalHarm(long totalHarm)
    {
        if (totalHarm < 0)
            throw new IllegalArgumentException("����Ϊ����:" + totalHarm);
        this.totalHarm = totalHarm;
    }

    public void setTotalVictory(long totalVictory)
    {
        if (totalVictory < 0)
            throw new IllegalArgumentException("����Ϊ����:" + totalVictory);
        this.totalVictory = totalVictory;
    }

    public void addTotalKill(long addNumber)
    {
        totalKill += addNumber;
    }
    public void addTotalRound(long addNumber)
    {
        totalRound += addNumber;
    }

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

    /**�����˺�����ѡ��ͬ�ı��淽ʽ������ͳ����Ϣ*/
    public void saveStatistics(AccountMessage acc)
    {
        if (acc == null)
            throw new NullPointerException();
        if (acc.getId() == Identity.GAME_MANAGER || acc.getId() == Identity.NEW_GAME_MANAGER)
        {
            saveGameManagerStatistics(acc);
        }
        else
            savePlayerStatistics(acc);
    }
    private void saveGameManagerStatistics(AccountMessage account)
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
    private void savePlayerStatistics(AccountMessage account)
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

    public static PlayerStatistics loadStatistics(AccountMessage account) throws FileNotFoundException
    {
        if (account == null)
            throw new NullPointerException();
        //���������,����ô�ܶ�ȡ��?
        if (fileNotExist(account))
            throw new IllegalStateException("�ļ�������,Id: " + account.getId());

        if (account.getId() == Identity.PLAYER)
        {
            var archive = loadPlayerStatistics(account);
            if (archive == null)
                throw new NullPointerException("loadPlayerStatistics��������Null");
            return archive;
        }
        else
        {
            return loadGameManagerStatistics(account);
        }
    }
    private static boolean fileNotExist(AccountMessage acc)
    {
        assert acc != null;

        return acc.getId() == Identity.NONE || acc.getId() == Identity.NEW_GAME_MANAGER
                || acc.getId() == Identity.NEW_PLAYER;
    }
    private static PlayerStatistics loadPlayerStatistics(AccountMessage acc)
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

    private static PlayerStatistics loadGameManagerStatistics(AccountMessage acc) throws FileNotFoundException
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

        long totalKill = json.getLong("����ܻ�ɱ��");
        long totalRound = json.getLong("�ܳ���");
        long totalAttack = json.getLong("��ͨ��������");
        long totalHarm = json.getLong("���˺�");
        long totalVictory = json.getLong("ʤ������");
        long totalXp = json.getLong("һ����õľ���");
        long totalCash = json.getLong("һ����õĻ���");
        long totalValue = json.getLong("һ����õķ���");

        return new PlayerStatistics(totalKill, totalRound, totalAttack, totalHarm, totalVictory, totalXp, totalCash,
                totalValue);
    }
}