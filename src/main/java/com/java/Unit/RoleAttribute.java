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
    private final int id;
    /**��ҵ�id*/
    static int nextId = 0;
    /**���л���*/
    private int cash;
    /**ӵ�о���*/
    private int EXP;
    /**������һ�����辭��*/
    private int upgradeNeedXP;
    /**��Ϸ�÷�*/
    private int totalGameScore;
    /**��ɫ��������*/
    private final LocalDateTime CreatingDateTime;

    public static void main(String[] args)
    {
        LocalDateTime time = LocalDateTime.now();
        LocalDate date = LocalDate.now();
          System.out.println(time);
          System.out.println(date);
    }
    //��ʼ�����id
    {
        nextId++;
        id = nextId;
    }
    public RoleAttribute() //*Ĭ����ֵ
    {
        super("���");
        cash = 100;
        totalGameScore = 0;
        EXP = 0;
        upgradeNeedXP = 20;
        CreatingDateTime = LocalDateTime.now();
    }
    public RoleAttribute(String name) //*������ҽ�ɫ
    {
        super(name);
        cash = 100;
        totalGameScore = 0;
        EXP = 0;
        upgradeNeedXP = 20;
        CreatingDateTime = LocalDateTime.now();
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
        return CreatingDateTime;
    }
    public final int getEXP()
    {
        return EXP;
    }
    public final int getUpgradeNeedXP()
    {
        return upgradeNeedXP;
    }
    public final void addUpgradeNeedXP(int addUpgradeXP)
    {
        upgradeNeedXP += addUpgradeXP;
    }
    public final void addEXP(int gainEXP)
    {
        EXP += gainEXP;
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
                ", ��ɫӵ�о���:" + EXP +
                ", ������һ�����辭��:" + upgradeNeedXP +
                ", ��������:" + CreatingDateTime +
                ']';
    }
}
