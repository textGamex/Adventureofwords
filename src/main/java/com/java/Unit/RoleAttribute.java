package com.java.Unit;

import java.time.LocalDateTime;

/**
 * 玩家角色属性
 * @version 0.3.1
 * @author Millennium
 */
public class RoleAttribute extends BasicUnitAttribute
{
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
        cash = builder.cash;
        exp = builder.exp;
        upgradeNeedXp = builder.upgradeNeedXp;
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
}
