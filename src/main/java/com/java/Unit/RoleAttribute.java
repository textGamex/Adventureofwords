package com.java.Unit;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ��ҽ�ɫ����
 * @version 0.13
 * @author Millennium
 */
public class RoleAttribute extends BasicUnitAttribute
{
    public static void main(String[] args)
    {
        RoleAttribute role = (RoleAttribute) new Builder().cash(10).atk(1).build();
    }
    private final int id;
    /**��ҵ�id*/
    static int nextId = 0;
    /**���л���*/
    private int cash;
    /**ӵ�о���*/
    private int exp;
    /**������һ�����辭��*/
    private int upgradeNeedXp;
    /**��Ϸ�÷�*/
    private int totalGameScore;
    /**��ɫ��������*/
    private final LocalDateTime CreatingDateTime = null;

    public static class Builder extends BasicUnitAttribute.Builder
    {
        private int cash           = 0;
        private int exp            = 0;
        private int upgradeNeedXp  = 10;
        private int totalGameScore = 0;

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

//        public RoleAttribute build()
//        {
//            super(this);
//        }
    }
    //��ʼ�����id
    {
        nextId++;
        id = nextId;
    }
    private RoleAttribute(Builder builder)
    {
        cash = builder.cash;
        exp = builder.exp;
        upgradeNeedXp = builder.upgradeNeedXp;
        totalGameScore = builder.totalGameScore;
    }
//    public RoleAttribute() //*Ĭ����ֵ
//    {
//        super("���");
//        cash = 100;
//        totalGameScore = 0;
//        EXP = 0;
//        upgradeNeedXP = 20;
//        CreatingDateTime = LocalDateTime.now();
//    }
//    public RoleAttribute(String name) //*������ҽ�ɫ
//    {
//        super(name);
//        cash = 100;
//        totalGameScore = 0;
//        EXP = 0;
//        upgradeNeedXP = 20;
//        CreatingDateTime = LocalDateTime.now();
//    }

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
        return CreatingDateTime;
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
    public final int getId()
    {
        return id;
    }
    @Override
    public String toString()
    {
        return  super.toString() +
                "[���л���:" + cash +
                ", ��Ϸ�÷�:" + totalGameScore +
                ", ��ɫӵ�о���:" + exp +
                ", ������һ�����辭��:" + upgradeNeedXp +
                ", ��������:" + CreatingDateTime +
                ']';
    }
}
