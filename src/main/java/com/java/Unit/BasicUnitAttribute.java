package com.java.Unit;

import com.java.CombatSystem.BuffModule.BuffMessage;
import com.java.CombatSystem.BuffModule.BuffType;

import java.io.*;

/**
 * 基本单位属性
 * @version 0.30
 * @author Millennium
 */
public class BasicUnitAttribute extends BasicBuffModule implements Comparable<BasicUnitAttribute>
{
    public static void main(String[] args) throws CloneNotSupportedException
    {
        var s = new BasicUnitAttribute.Builder<Builder>("1").build();
        s.addBuff(BuffType.POISON, new BuffMessage(1,1,true));
        System.out.println(s);
        var ss = (BasicUnitAttribute) s.clone();
        System.out.println(ss);
        System.out.println(s.equalsAll(ss));
    }
    private static int nextTextId = 50000;//测试单位id
    private final int id = ++nextTextId;

    private final String name;
    /**最大生命值*/
    private int maxHp;
    /**生命值*/
    private int hp;
    /**魔法值*/
    private int mana;
    /**物理攻击*/
    private int atk;
    /**暴击率 */
    private double critRate;
    /**暴击效果*/
    private double critsEffect;

    /**    <防御类>    */

    /**物理抗性 */
    private double physicalResistance;
    /**闪避*/
    private double evade;
    /**每回合生命回复*/
    private int lifeRegeneration;
    /**单位等级*/
    private int level;

    /**参考<Effective Java第二章>*/
    public static class Builder<T extends Builder>
    {
        private final String name;

        private int maxHp                 = 100;
        private int atk                   = 0;
        private double critRate           = 0.0;//暴击率
        private double critsEffect        = 2.0;//暴击效果
        private double physicalResistance = 0.0;//物理抗性
        private double evade              = 0.0;//闪避
        private int lifeRegeneration      = 0;//每回合生命回复
        private int level                 = 1;
        private int mana                  = 0;

        public Builder(String name)
        {
            if (name == null)
                throw new NullPointerException();
            this.name = name;
        }
        public T maxHp(int maxHp)
        {
            if (maxHp <= 0)
                throw new IllegalArgumentException("异常参数: " + maxHp);
            this.maxHp = maxHp;
            return (T) this;
        }
        public T atk(int atk)
        {
            this.atk = atk;
            return (T) this;
        }
        public T critRate(double critRate)
        {
            this.critRate = critRate;
            return (T) this;
        }
        public T critsEffect(double critsEffect)
        {
            this.critsEffect = critsEffect;
            return (T) this;
        }
        public T physicalResistance(double physicalResistance)
        {
            this.physicalResistance = physicalResistance;
            return (T) this;
        }
        public T lifeRegeneration(int lifeRegeneration)
        {
            this.lifeRegeneration = lifeRegeneration;
            return (T) this;
        }
        public T level(int level)
        {
            if (level < 0)
                throw new IllegalArgumentException("异常参数: " + level);
            this.level = level;
            return (T) this;
        }
        public T evade(double evade)
        {
            this.evade = evade;
            return (T) this;
        }
        public T mana(int mana)
        {
            this.mana = mana;
            return (T) this;
        }
        public BasicUnitAttribute build()
        {
            return new BasicUnitAttribute(this);
        }

    }

    protected BasicUnitAttribute(Builder builder)
    {
        name               = builder.name;
        hp                 = builder.maxHp;
        maxHp              = builder.maxHp;
        level              = builder.level;
        atk                = builder.atk;
        critRate           = builder.critRate;
        critsEffect        = builder.critsEffect;
        physicalResistance = builder.physicalResistance;
        lifeRegeneration   = builder.lifeRegeneration;
        evade              = builder.evade;
        mana               = builder.mana;
    }
    public final String getName()
    {
        return name;
    }
    public final int getHp()
    {
        return hp;
    }
    public final int getAtk()
    {
        return atk;
    }
    public final double getCritRate()
    {
        return critRate;
    }
    public final double getCritsEffect()
    {
        return critsEffect;
    }

    public int getMana()
    {
        return mana;
    }

    public final double getPhysicalResistance()
    {
        return physicalResistance;
    }
    public int getId()
    {
        return id;
    }
    public final int getLifeRegeneration()
    {
        return lifeRegeneration;
    }
    public final int getLevel()
    {
        return level;
    }

    public double getEvade()
    {
        return evade;
    }

    public void setMaxHp(int maxHp)
    {
        this.maxHp = maxHp;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    public void setAtk(int atk)
    {
        this.atk = atk;
    }

    public void setCritRate(double critRate)
    {
        this.critRate = critRate;
    }

    public void setCritsEffect(double critsEffect)
    {
        this.critsEffect = critsEffect;
    }

    public void setPhysicalResistance(double physicalResistance)
    {
        this.physicalResistance = physicalResistance;
    }

    public void setEvade(double evade)
    {
        this.evade = evade;
    }
    public void setMana(int mana)
    {
        this.mana = mana;
    }
    public void setLifeRegeneration(int lifeRegeneration)
    {
        this.lifeRegeneration = lifeRegeneration;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }
    //    public int directHP(int reducedHP)//*无视物抗直接扣血并返回扣除的HP */
//    {
//        HP -= reducedHP;
//        return reducedHP;
//    }

//    public int subtractHP(int ATK, int fixArmorPen, double perArmorPen)//*计算护甲并扣血
//    {
//        int sumArmorPen = (int) ((this.armor * perArmorPen) + fixArmorPen);//*计算护甲穿透总和
//        if (sumArmorPen >= this.armor)
//            return directHP(ATK);
//        else if (ATK >(this.armor - sumArmorPen))
//            return directHP(ATK - (this.armor - sumArmorPen));
//        else return 0;
//    }
    @Override
    public final boolean equals(Object otherObject)
    {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (!(otherObject instanceof BasicUnitAttribute other)) return false;
        return this.id == other.getId();
    }

    public boolean equalsAll(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicUnitAttribute that = (BasicUnitAttribute) o;

        return id == that.id && maxHp == that.maxHp && hp == that.hp && mana == that.mana && atk == that.atk
                && Double.compare(that.critRate, critRate) == 0 && Double.compare(that.critsEffect, critsEffect) == 0
                && Double.compare(that.physicalResistance, physicalResistance) == 0
                && Double.compare(that.evade, evade) == 0 && lifeRegeneration == that.lifeRegeneration
                && level == that.level && name.equals(that.name);
    }

    @Override
    public int hashCode()
    {
        return id * 31;
    }
    @Override
    public String toString()
    {
        return super.toString()
                + "BasicUnitAttribute"
                + "[id:" + id
                + ", 名称:" + name
                + ", 最大生命值:" + maxHp
                + ", 等级:" + level
                + ", 物理攻击:" + atk
                + ", 魔法攻击:" + mana
                + ", 暴击率:" + critRate * 100 + "%"
                + ", 暴击效果:" + critsEffect * 100 + "%"
                + ", 物理抗性:" + physicalResistance
                + ", 每回合生命回复:" +lifeRegeneration
                + ", 闪避率:" + evade * 100 + "%"
                + "]";
    }
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        try
        {
            var bout = new ByteArrayOutputStream();
            try (var out = new ObjectOutputStream(bout))
            {
                out.writeObject(this);
            }

            try (var bin = new ByteArrayInputStream(bout.toByteArray()))
            {
                var in = new ObjectInputStream(bin);
                return in.readObject();
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            var e2 = new CloneNotSupportedException();
            e2.initCause(e);
            throw e2;
        }
    }

    /*sort接口实现*/
    public int compareTo(BasicUnitAttribute other)
    {
        return Integer.compare(id, other.getId());
    }
}
