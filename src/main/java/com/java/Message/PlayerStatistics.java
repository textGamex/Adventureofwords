package com.java.Message;

import com.java.Account.AccountMessage;
import com.java.Account.Identity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

/**
 *统计玩家信息
 *@author Millennium
 *@version 0.3.0
 *@Date 2021/3/13 22:42
*/
public final class PlayerStatistics implements Serializable
{
    public static void main(String[] args) throws IOException
    {
        var a = new Scanner(Path.of("123"));
    }
    @Serial
    private static final long serialVersionUID = 7935923925807359121L;
    //玩家击杀数
    private long totalKill;
    //总场数
    private long totalRound;
    //普通攻击次数
    private long totalAttack;
    //总伤害
    private long totalHarm;
    //胜利场数
    private long totalVictory;
    //游玩总时间 TODO:未实现
//    private long9
//    public PlayerStatistics()
//    {
//        this();
//    }
    public PlayerStatistics()
    {
        totalKill = 0;
        totalRound = 0;
        totalAttack = 0;
        totalHarm = 0;
        totalVictory = 0;
    }

    public PlayerStatistics(long totalKill, long totalRound, long totalAttack, long totalHarm, long totalVictory)
    {
        this.totalKill = totalKill;
        this.totalRound = totalRound;
        this.totalAttack = totalAttack;
        this.totalHarm = totalHarm;
        this.totalVictory = totalVictory;
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

    public void setTotalKill(long totalKill)
    {
        this.totalKill = totalKill;
    }

    public void setTotalRound(long totalRound)
    {
        this.totalRound = totalRound;
    }

    public void setTotalAttack(long totalAttack)
    {
        this.totalAttack = totalAttack;
    }

    public void setTotalHarm(long totalHarm)
    {
        this.totalHarm = totalHarm;
    }

    public void setTotalVictory(long totalVictory)
    {
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
        return getClass().getName() +
                "[玩家总击杀数:" + totalKill +
                ", 总场数:" + totalRound +
                ", 普通攻击次数:" + totalAttack +
                ", 总伤害:" + totalHarm +
                ", 胜利场数:" + totalVictory +
                "]";
    }

    /**根据账号类型选择不同的保存方式来保存统计信息*/
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
        try (var out = new PrintWriter(
                account.getPlayerDataResolveFile("PlayerStatistics.txt"), StandardCharsets.UTF_8))
        {
            out.println(totalKill + "|" + totalRound + "|" + totalAttack
                    + "|" + totalHarm + "|" + totalVictory);
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

    public static PlayerStatistics loadStatistics(AccountMessage account) throws IOException, ClassNotFoundException
    {
        if (account == null)
            throw new NullPointerException("account为Null");
        //如果不存在,那怎么能读取呢?
        if (fileNotExist(account))
            throw new IllegalStateException("文件不存在,Id: " + account.getId());

        if (account.getId() == Identity.PLAYER)
        {
            var archive = loadPlayerStatistics(account);
            if (archive == null)
                throw new NullPointerException("loadPlayerStatistics方法返回Null");
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
    private static PlayerStatistics loadPlayerStatistics(AccountMessage acc)throws ClassNotFoundException
    {
        assert acc != null;

        PlayerStatistics archive = null;
        try (var in = new ObjectInputStream(new FileInputStream(
                acc.getPlayerDataResolveFile("PlayerStatistics.dat"))))
        {
            archive = (PlayerStatistics) in.readObject();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return archive;
    }
    private static PlayerStatistics loadGameManagerStatistics(AccountMessage acc) throws IOException
    {
        assert acc != null;
        //TODO:找不到文件时会抛出NoSuchFileException, 但现在不知道是谁会抛出
        String line;
        try (var in = new Scanner(acc.getPlayerDataResolveFile(
                "PlayerStatistics.txt"), StandardCharsets.UTF_8))
        {
            line = in.nextLine();
        }

        String[] tokens = line.split("\\|");
        long totalKill = Integer.parseInt(tokens[0]);
        long totalRound = Integer.parseInt(tokens[1]);
        long totalAttack = Integer.parseInt(tokens[2]);
        long totalHarm = Integer.parseInt(tokens[3]);
        long totalVictory = Integer.parseInt(tokens[4]);

        return new PlayerStatistics(totalKill, totalRound, totalAttack, totalHarm, totalVictory);
    }
}