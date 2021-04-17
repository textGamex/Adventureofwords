package com.java.Message;

import com.java.Account.AccountMessage;
import com.java.Account.Identity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *统计玩家信息
 *@author Millennium
 *@version 0.3.0
 *@Date 2021/3/13 22:42
*/
public final class PlayerStatistics
{
    public static void main(String[] args)
    {
        var data = new PlayerStatistics(0,0,0,0,0);
        var a = new AccountMessage("1");
        var b = new AccountMessage(PrivateData.ACCOUNT1);
        a.createAccountDataFolder();
        b.createAccountDataFolder();
        data.saveStatistics(a);
        data.saveStatistics(b);
    }
    //玩家ID
//    private final Identity id;
    //玩家击杀数
    private int totalKill;
    //总场数
    private int totalRound;
    //普通攻击次数
    private int totalAttack;
    //总伤害
    private int totalHarm;
    //胜利场数
    private int totalVictory;
    //游玩总时间 TODO:未实现
//    private int
//    public PlayerStatistics()
//    {
//        this();
//    }
    public PlayerStatistics(Identity id)
    {
//        this.id = id;
        totalKill = 0;
        totalRound = 0;
        totalAttack = 0;
        totalHarm = 0;
        totalVictory = 0;
    }
    public PlayerStatistics(int totalKill, int totalRound, int totalAttack, int totalHarm, int totalVictory)
    {
//        this.id = id;
        this.totalKill = totalKill;
        this.totalRound = totalRound;
        this.totalAttack = totalAttack;
        this.totalHarm = totalHarm;
        this.totalVictory = totalVictory;
    }
    public int getTotalKill()
    {
        return totalKill;
    }
    public int getTotalRound()
    {
        return totalRound;
    }
    public int getTotalAttack()
    {
        return totalAttack;
    }
    public int getTotalHarm()
    {
        return totalHarm;
    }
    public int getTotalVictory()
    {
        return totalVictory;
    }

    public void setTotalKill(int totalKill)
    {
        this.totalKill = totalKill;
    }

    public void setTotalRound(int totalRound)
    {
        this.totalRound = totalRound;
    }

    public void setTotalAttack(int totalAttack)
    {
        this.totalAttack = totalAttack;
    }

    public void setTotalHarm(int totalHarm)
    {
        this.totalHarm = totalHarm;
    }

    public void setTotalVictory(int totalVictory)
    {
        this.totalVictory = totalVictory;
    }

    public void addTotalKill(int addNumber)
    {
        totalKill += addNumber;
    }
    public void addTotalRound(int addNumber)
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
        if (acc.getId() == Identity.GAME_MANAGER || acc.getId() == Identity.NEW_GAME_MANAGER)
        {
            try (var out = new PrintWriter(acc.getPlayerDataResolveFile("PlayerStatistics.txt"), StandardCharsets.UTF_8))
            {
                out.println(totalKill + "|" + totalRound + "|" + totalAttack
                        + "|" + totalHarm + "|" + totalVictory);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try (var out = new ObjectOutputStream(new FileOutputStream(acc.getPlayerDataResolveFile("PlayerStatistics.dat"))))
            {
                out.writeObject(this);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static PlayerStatistics loadGmStatistics(AccountMessage acc)
    {
        //如果不存在,那怎么能读取呢?
        if (acc.getId() == Identity.NONE || acc.getId() == Identity.NEW_GAME_MANAGER || acc.getId() == Identity.NEW_PLAYER)
            throw new IllegalArgumentException("Id:" + acc.getId());

        if (acc.getId() == Identity.PLAYER)
        {
            try (var in = new ObjectInputStream(new FileInputStream(acc.getPlayerDataResolveFile("PlayerStatistics.dat"))))
            {
                return (PlayerStatistics) in.readObject();
            }
            catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            String line = "";
            try (var in = new Scanner(acc.getPlayerDataResolveFile("PlayerStatistics.txt"), StandardCharsets.UTF_8))
            {
                line = in.nextLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            String[] tokens = line.split("\\|");
            int totalKill = Integer.parseInt(tokens[0]);
            int totalRound = Integer.parseInt(tokens[1]);
            int totalAttack = Integer.parseInt(tokens[2]);
            int totalHarm = Integer.parseInt(tokens[3]);
            int totalVictory = Integer.parseInt(tokens[4]);

            return new PlayerStatistics(totalKill, totalRound, totalAttack, totalHarm, totalVictory);
        }
        return null;
    }
}