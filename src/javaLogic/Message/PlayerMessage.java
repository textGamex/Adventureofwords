package javaLogic.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *统计玩家信息
 *@author Millennium
 *@version 0.1
 *@Date 2021/3/13 22:42
*/
public final class PlayerMessage
{
    public static void main(String[] args)
    {
        try (var out = new PrintWriter("Mwssage.txt", StandardCharsets.UTF_8);)
        {
            var a = new PlayerMessage(1);
            a.savePlayerMessage(out);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    //玩家ID
    private final int id;
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
    public PlayerMessage()
    {
        this(0);
    }
    public PlayerMessage(int id)
    {
        this.id = id;
        totalKill = 0;
        totalRound = 0;
        totalAttack = 0;
        totalHarm = 0;
        totalVictory = 0;
    }
    public PlayerMessage(int id, int totalKill, int totalRound, int totalAttack,int totalHarm, int totalVictory)
    {
        this.id = id;
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
    public int getId()
    {
        return id;
    }
    public void addTotalKill(int addNumber)
    {
        totalKill += addNumber;
    }
    public void addTotalRound(int addNumber)
    {
        totalRound += addNumber;
    }
    public String toString()
    {
        return getClass().getName() +
                "[Id:" + id +
                ", 玩家击杀数: " + totalKill +
                ", 总场数:" + totalRound +
                ", 普通攻击次数:" + totalAttack +
                ", 总伤害:" + totalHarm +
                ", 胜利场数:" + totalVictory +
                "]";
    }
    /**保存统计信息*/
    public void savePlayerMessage(PrintWriter out)
    {
        out.println(id + "|" + totalKill + "|" + totalRound + "|" + totalAttack
        + "|" + totalHarm + "|" + totalVictory);
    }
    public static PlayerMessage loadPlayerMessage(Scanner in)
    {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        int id = Integer.parseInt(tokens[0]);
        int totalKill = Integer.parseInt(tokens[1]);
        int totalRound = Integer.parseInt(tokens[2]);
        int totalAttack = Integer.parseInt(tokens[3]);
        int totalHarm = Integer.parseInt(tokens[4]);
        int totalVictory = Integer.parseInt(tokens[5]);

        return new PlayerMessage(id, totalKill, totalRound, totalAttack, totalHarm, totalVictory);
    }
}