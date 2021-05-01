package com.java.Unit;

import com.alibaba.fastjson.JSONObject;
import com.java.Account.AccountMessage;
import com.java.Account.Identity;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * 玩家角色属性
 * @version 0.3.1
 * @author Millennium
 */
public class RoleAttribute extends BasicUnitAttribute
{
    public static void main(String[] args)
    {
    }
    /**玩家的id*/
    private static int nextId = 70000;
    private final int id = ++nextId;
    /**持有货币*/
    private int cash;
    /**拥有经验*/
    private int exp;
    /**升到下一级所需经验*/
    private int upgradeNeedXp;
    /**游戏得分*/
    private int totalGameScore;
    /**角色创建日期*/
    private final LocalDateTime creatingDateTime = LocalDateTime.now();

    protected RoleAttribute(Builder builder)
    {
        super(builder);
        cash           = builder.cash;
        exp            = builder.exp;
        upgradeNeedXp  = builder.upgradeNeedXp;
        totalGameScore = builder.totalGameScore;
    }

    public static class Builder extends BasicUnitAttribute.Builder<Builder>
    {
        private int cash           = 0;
        private int exp            = 0;
        private int upgradeNeedXp  = 10;
        private int totalGameScore = 0;

        public Builder(String name)
        {
            super(name);
        }
        public Builder cash(int cash)
        {
            this.cash = cash;
            return this;
        }

        public Builder exp(int exp)
        {
            this.exp = exp;
            return this;
        }

        public Builder upgradeNeedXp(int upgradeNeedXp)
        {
            this.upgradeNeedXp = upgradeNeedXp;
            return this;
        }

        public Builder totalGameScore(int totalGameScore)
        {
            this.totalGameScore = totalGameScore;
            return this;
        }
        @Override
        public RoleAttribute build()
        {
            return new RoleAttribute(this);
        }
    }

    public final int getCash()
    {
        return cash;
    }

    public final int getTotalGameScore()
    {
        return totalGameScore;
    }

    public final LocalDateTime getCreatingDateTime()
    {
        return creatingDateTime;
    }

    public final int getExp()
    {
        return exp;
    }

    public final int getUpgradeNeedXp()
    {
        return upgradeNeedXp;
    }

    public final void addUpgradeNeedXP(int addUpgradeXP)
    {
        upgradeNeedXp += addUpgradeXP;
    }

    public final void addEXP(int gainEXP)
    {
        exp += gainEXP;
    }

    public final void addCash(int addCash)
    {
        cash += addCash;
    }

    public final void addTotalGameScore(int addGameScore)
    {
        totalGameScore += addGameScore;
    }

    @Override
    public final int getId()
    {
        return id;
    }

    public static int getNextId()
    {
        return nextId;
    }

    @Override
    public String toString()
    {
        return  super.toString() +
                "[持有货币:" + cash +
                ", 游戏得分:" + totalGameScore +
                ", 角色拥有经验:" + exp +
                ", 升到下一级所需经验:" + upgradeNeedXp +
                ", 创建日期:" + creatingDateTime +
                ']' +
                buff().toString();
    }

    public void saveData(AccountMessage acc)
    {
        if (acc == null)
            throw new NullPointerException();
        if (acc.getId() == Identity.GAME_MANAGER || acc.getId() == Identity.NEW_GAME_MANAGER)
        {
            saveGameManagerData(acc);
        }
        else
            savePlayerData(acc);
    }
    private void saveGameManagerData(AccountMessage account)
    {
        assert account != null;

        var jsonFile = new JSONObject();
        jsonFile.put("名称", super.getName());
        jsonFile.put("单位等级", super.getLevel());
        jsonFile.put("最大生命值", super.getHp());
        jsonFile.put("魔法值", super.getMana());
        jsonFile.put("物理攻击", super.getAtk());
        jsonFile.put("暴击率", super.getCritRate());
        jsonFile.put("暴击效果", super.getCritsEffect());
        jsonFile.put("物理抗性", super.getPhysicalResistance());
        jsonFile.put("每回合生命回复", super.getLifeRegeneration());
        jsonFile.put("闪避率", super.getEvade());
        jsonFile.put("持有货币", cash);
        jsonFile.put("游戏得分", totalGameScore);
        jsonFile.put("角色拥有经验", exp);
        jsonFile.put("升到下一级所需经验", upgradeNeedXp);

        try (var out = new PrintWriter(
                account.getPlayerDataResolveFile("RoleAttribute.json"), StandardCharsets.UTF_8))
        {
            out.println(jsonFile.toJSONString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private void savePlayerData(AccountMessage account)
    {
        assert account != null;
        try (var out = new ObjectOutputStream(
                new FileOutputStream(account.getPlayerDataResolveFile("RoleAttribute.dat"))))
        {
            out.writeObject(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static RoleAttribute loadData(AccountMessage account) throws FileNotFoundException
    {
        if (account == null)
            throw new NullPointerException();
        //如果不存在,那怎么能读取呢?
        if (fileNotExist(account))
            throw new IllegalStateException("文件不存在,Id: " + account.getId());

        if (account.getId() == Identity.PLAYER)
        {
            var archive = loadPlayerData(account);
            if (archive == null)
                throw new NullPointerException("loadPlayer方法返回Null");
            return archive;
        }
        else
        {
            return loadGameManagerData(account);
        }
    }
    private static boolean fileNotExist(AccountMessage acc)
    {
        assert acc != null;

        return acc.getId() == Identity.NONE || acc.getId() == Identity.NEW_GAME_MANAGER
                || acc.getId() == Identity.NEW_PLAYER;
    }
    private static RoleAttribute loadPlayerData(AccountMessage acc)
    {
        assert acc != null;

        RoleAttribute archive = null;
        try (var in = new ObjectInputStream(new FileInputStream(
                acc.getPlayerDataResolveFile("RoleAttribute.dat"))))
        {
            archive = (RoleAttribute) in.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return archive;
    }

    private static RoleAttribute loadGameManagerData(AccountMessage acc) throws FileNotFoundException
    {
        assert acc != null;

        String line;
        try (var in = new Scanner(acc.getPlayerDataResolveFile(
                "RoleAttribute.json"), StandardCharsets.UTF_8))
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

        var name = json.getString("名称");
        var level = json.getIntValue("单位等级");
        var hp = json.getIntValue("最大生命值");
        var mana = json.getIntValue("魔法值");
        var atk = json.getIntValue("物理攻击");
        var critRate = json.getDoubleValue("暴击率");
        var critsEffect = json.getDoubleValue("暴击效果");
        var physicalResistance = json.getDoubleValue("物理抗性");
        var evade = json.getDoubleValue("闪避率");
        var cash = json.getIntValue("持有货币");
        var lifeRegeneration = json.getIntValue("每回合生命回复");
        var totalGameScore = json.getIntValue("游戏得分");
        var exp = json.getIntValue("角色拥有经验");
        var upgradeNeedXp = json.getIntValue("升到下一级所需经验");

        return new Builder(name).level(level).maxHp(hp).mana(mana).atk(atk).cash(cash).critRate(critRate)
                .physicalResistance(physicalResistance).critsEffect(critsEffect).evade(evade).exp(exp)
                .lifeRegeneration(lifeRegeneration).totalGameScore(totalGameScore).upgradeNeedXp(upgradeNeedXp).build();
    }
}