package com.java.message;

import com.alibaba.fastjson.JSONObject;
import com.java.localPersistence.JsonBaseTool;
import com.java.main.GameSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
 * @author 留恋千年
 * @version 1.0.0
 * @since 15
*/
public final class PlayerStatistics implements Serializable
{
    public static final Logger LOGGER = LoggerFactory.getLogger(PlayerStatistics.class);
    @Serial
    private static final long serialVersionUID = 7935923925807359121L;
    /**总击杀*/
    private long totalKill;
    /**总回合数*/
    private long totalRound;
//    /**总战斗次数*/
//    private long total;
    /**攻击总次数*/
    private long totalAttack;
    /**总伤害*/
    private long totalHarm;
    /**胜利场数*/
    private long totalVictory;
    private long totalXp;
    private long totalCash;
    private long totalValue;

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

    public void addTotalRound(final long addRound)
    {
        totalRound += addRound;
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

    public void addTotalHarm(final int harm)
    {
        totalHarm += harm;
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
     * @param totalAttack 总攻击次数
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
                "[总击杀数:" + totalKill +
                ", 总回合数:" + totalRound +
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
     * @param path 保存路径
     * @since 16
     */
    public void saveStatistics(final File path)
    {
        assert path != null;

        var jsonFile = new JSONObject();
        jsonFile.put("总击杀数", totalKill);
        jsonFile.put("总回合数", totalRound);
        jsonFile.put("普通攻击次数", totalAttack);
        jsonFile.put("总伤害", totalHarm);
        jsonFile.put("胜利场数", totalVictory);
        jsonFile.put("一共获得的经验", totalXp);
        jsonFile.put("一共获得的货币", totalCash);
        jsonFile.put("一共获得的分数", totalValue);

        try (var out = new PrintWriter(
                GameSetting.GAME_DATA_PATH + "\\PlayerStatistics.json", StandardCharsets.UTF_8))
        {
            out.println(jsonFile.toJSONString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param path 数据路径
     * @return 此账号对应的统计信息
     * @throws FileNotFoundException 文件不存在
     * @throws NullPointerException 如果{@code path}为null
     */
    public static PlayerStatistics loadStatistics(final File path) throws FileNotFoundException
    {
        var json = JsonBaseTool.loadJsonFile(requireNonNull(path));

        var totalKill = json.getLongValue("总击杀数");
        var totalRound = json.getLongValue("总回合数");
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