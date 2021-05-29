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
 * 用于统计玩家的游戏信息.
 *
 * <p>现已记录一下信息</p>
 *
 * <li>游戏总场数</li>
 * <li>普通攻击次数</li>
 * <li>总伤害</li>
 * <li>胜利场数</li>
 * <li>获得的所有分数</li>
 * <li>获得的所有经验</li>
 * <li>获得的所有货币</li>
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
    /**总伤害*/
    private long totalHarm;
    /**胜利场数*/
    private long totalVictory;
    private long totalXp;
    private long totalCash;
    private long totalValue;
    //游玩总时间 TODO:未实现
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
     * @param totalXp 一共获得的经验
     * @throws IllegalArgumentException 如果{@code totalXp}小于0
     */
    public void setTotalXp(long totalXp)
    {
        if (totalXp < 0)
        {
            throw new IllegalArgumentException("不能为负数:" + totalXp);
        }
        this.totalXp = totalXp;
    }

    public long getTotalCash()
    {
        return totalCash;
    }

    /**
     * @param totalCash 一共获得的货币
     * @throws IllegalArgumentException 如果{@code totalCash}小于0
     */
    public void setTotalCash(long totalCash)
    {
        if (totalCash < 0)
        {
            throw new IllegalArgumentException("不能为负数:" + totalCash);
        }
        this.totalCash = totalCash;
    }

    public long getTotalValue()
    {
        return totalValue;
    }

    /**
     * @param totalValue 一共获得的分数
     * @throws IllegalArgumentException 如果{@code totalValue}小于0
     */
    public void setTotalValue(long totalValue)
    {
        if (totalValue < 0)
        {
            throw new IllegalArgumentException("不能为负数:" + totalValue);
        }
        this.totalValue = totalValue;
    }

    /**
     * @param totalKill 总击杀
     * @throws IllegalArgumentException 如果{@code totalKill}小于0
     */
    public void setTotalKill(long totalKill)
    {
        if (totalKill < 0)
        {
            throw new IllegalArgumentException("不能为负数:" + totalKill);
        }
        this.totalKill = totalKill;
    }

    /**
     * @param totalRound 游戏总回合数
     * @throws IllegalArgumentException 如果{@code totalRound}小于0
     */
    public void setTotalRound(long totalRound)
    {
        if (totalRound < 0)
        {
            throw new IllegalArgumentException("不能为负数:" + totalRound);
        }
        this.totalRound = totalRound;
    }

    /**
     * @param totalAttack 总击杀
     * @throws IllegalArgumentException 如果{@code totalAttack}小于0
     */
    public void setTotalAttack(long totalAttack)
    {
        if (totalAttack < 0)
        {
            throw new IllegalArgumentException("不能为负数:" + totalAttack);
        }
        this.totalAttack = totalAttack;
    }

    /**
     * @param totalHarm 总伤害
     * @throws IllegalArgumentException 如果{@code totalHarm}小于0
     */
    public void setTotalHarm(long totalHarm)
    {
        if (totalHarm < 0)
        {
            throw new IllegalArgumentException("不能为负数:" + totalHarm);
        }
        this.totalHarm = totalHarm;
    }

    /**
     * @param totalVictory 总胜场
     * @throws IllegalArgumentException 如果{@code totalVictory}小于0
     */
    public void setTotalVictory(long totalVictory)
    {
        if (totalVictory < 0)
        {
            throw new IllegalArgumentException("不能为负数:" + totalVictory);
        }
        this.totalVictory = totalVictory;
    }

    /**
     * @return 字符串表示的对象
     */
    @Override
    public String toString()
    {
        return  "PlayerStatistics" +
                "[玩家总击杀数:" + totalKill +
                ", 总场数:" + totalRound +
                ", 普通攻击次数:" + totalAttack +
                ", 总伤害:" + totalHarm +
                ", 胜利场数:" + totalVictory +
                ", 一共获得的经验:" + totalXp +
                ", 一共获得的货币:" + totalCash +
                ", 一共获得的分数:" + totalValue +
                "]";
    }

    /**
     * 根据账号类型选择不同的保存方式来保存统计信息.
     *
     * <p>如果账户类型是玩家,则用序列化方式保存,如果是内部人员,则用json保存</p>
     * @throws NullPointerException 如果{@code acc}为null
     * @param acc 账号
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
            LOGGER.debug("{}账号采用json方式保存", acc.getAccountName());
            saveGameManagerStatistics(acc);
        }
        else
        {
            LOGGER.debug("{}账号采用可序化方式保存", acc.getAccountName());
            savePlayerStatistics(acc);
        }
    }
    private void saveGameManagerStatistics(final AccountMessage account)
    {
        assert account != null;

        var jsonFile = new JSONObject();
        jsonFile.put("玩家总击杀数", totalKill);
        jsonFile.put("总场数", totalRound);
        jsonFile.put("普通攻击次数", totalAttack);
        jsonFile.put("总伤害", totalHarm);
        jsonFile.put("胜利场数", totalVictory);
        jsonFile.put("一共获得的经验", totalXp);
        jsonFile.put("一共获得的货币", totalCash);
        jsonFile.put("一共获得的分数", totalValue);

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
     * @param account 账号信息
     * @return  此账号对应的游戏统计信息
     * @throws FileNotFoundException 如果读取的文件不存在
     * @throws NullPointerException 如果{@code account}为null
     * @throws IllegalStateException 如果{@code account}的id字段为{@code NEW_PLAYER}或{@code NEW_GAME_MANAGER}
     * @see AccountMessage
     * @since 15
     */
    public static PlayerStatistics loadStatistics(final AccountMessage account) throws FileNotFoundException
    {
        requireNonNull(account);
        //如果不存在,那怎么能读取呢?
        if (fileNotExist(account))
        {
            throw new IllegalStateException("文件不存在,Id: " + account.getId());
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

        var totalKill = json.getLongValue("玩家总击杀数");
        var totalRound = json.getLongValue("总场数");
        var totalAttack = json.getLongValue("普通攻击次数");
        var totalHarm = json.getLongValue("总伤害");
        var totalVictory = json.getLongValue("胜利场数");
        var totalXp = json.getLongValue("一共获得的经验");
        var totalCash = json.getLongValue("一共获得的货币");
        var totalValue = json.getLongValue("一共获得的分数");

        return new PlayerStatistics(totalKill, totalRound, totalAttack, totalHarm, totalVictory, totalXp, totalCash,
                totalValue);
    }
}