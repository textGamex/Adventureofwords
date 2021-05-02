package com.java.Unit;

import com.java.CombatSystem.BuffModule.BuffModule;

import java.io.*;
import static java.util.Objects.requireNonNull;
/**
 * 游戏的单位, 用于实现基本的游戏对战
 *
 * <p>现已实现以下属性</p>
 * <ul>
 *     <li>名称</li>
 *     <li>等级</li>
 *     <li>最大生命值</li>
 *     <li>物理攻击</li>
 *     <li>魔法攻击</li>
 *     <li>暴击率</li>
 *     <li>暴击效果</li>
 *     <li>命中率</li>
 *     <li>闪避率</li>
 *     <li>物理抗性</li>
 *     <li>魔法抗性</li>
 *     <li>每回合生命回复</li>
 * </ul>
 * @version 0.3.3
 * @author Millennium
 * @see BuffModule
 *
 */
public class BasicUnit
        implements Comparable<BasicUnit>, Serializable
{
    @Serial
    private static final long serialVersionUID = 7938388190739071271L;

    private static int nextTextId = 50000;//测试单位id
    private final int id = ++nextTextId;

    private final BuffModule buff = new BuffModule();
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

    /**物理抗性*/
    private double physicalResistance;
    /**魔法抗性*/
    private double magicResistance;
    /**命中率*/
    private double hitRate;
    /**闪避*/
    private double evade;
    /**每回合生命回复*/
    private int lifeRegeneration;
    /**单位等级*/
    private int level;

    /**
     * 构建{@code BasicUnit}对象
     *
     * <p>现已实现以下属性</p>
     * <ul>
     *     <li>名称</li>
     *     <li>等级</li>
     *     <li>最大生命值</li>
     *     <li>物理攻击</li>
     *     <li>魔法攻击</li>
     *     <li>暴击率</li>
     *     <li>暴击效果</li>
     *     <li>命中率</li>
     *     <li>闪避率</li>
     *     <li>物理抗性</li>
     *     <li>魔法抗性</li>
     *     <li>每回合生命回复</li>
     * </ul>
     * <strong>参考Effective Java第二章</strong>
     * <p>采用泛型来使子类能正常工作</p>
     * @see BasicUnit
     * @since 15
     * @author 千年
     * @version 1.3.2
     */
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
        private double hitRate            = 0.7;
        private double magicResistance    = 0.0;
        /**
         * @param name 单位名称
         * @throws NullPointerException 如果{@code name}是null
         */
        public Builder(String name)
        {
            this.name = requireNonNull(name);
        }

        /**
         * @throws IllegalArgumentException 如果{@code maxHp}小于等于0
         */
        public T maxHp(int maxHp)
        {
            if (maxHp <= 0)
                throw new IllegalArgumentException("异常参数: " + maxHp);
            this.maxHp = maxHp;
            return (T) this;
        }

        public T magicResistance(double magicResistance)
        {
            this.magicResistance = magicResistance;
            return (T) this;
        }

        public T hitRate(double hitRate)
        {
            this.hitRate = hitRate;
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

        /**
         * @throws IllegalArgumentException 如果{@code level}小于等于0
         */
        public T level(int level)
        {
            if (level < 0)
                throw new IllegalArgumentException("异常参数:" + level);
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

        public BasicUnit build()
        {
            return new BasicUnit(this);
        }

    }

    /**
     * @throws NullPointerException 如果{@code builder}是null
     */
    protected BasicUnit(Builder builder)
    {
        if (builder == null)
            throw new NullPointerException();
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
        hitRate            = builder.hitRate;
        magicResistance    = builder.magicResistance;
    }

    /**
     * @throws NullPointerException 如果{@code buff}为null
     * @see BuffModule
     * @return 此单位的buff对象
     */
    public BuffModule buff()
    {
        return requireNonNull(buff);
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

    public double getMagicResistance()
    {
        return magicResistance;
    }

    public void setMagicResistance(double magicResistance)
    {
        this.magicResistance = magicResistance;
    }

    public double getHitRate()
    {
        return hitRate;
    }

    public void setHitRate(double hitRate)
    {
        this.hitRate = hitRate;
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

    /**
     * @throws IllegalArgumentException 如果{@code level}小于等于0
     */
    public void setLevel(int level)
    {
        if (level <= 0)
            throw new IllegalArgumentException("异常参数:" + level);
        this.level = level;
    }

    /**无视物抗直接扣血并返回扣除的HP */
    //    public int directHp(int reducedHp)
//    {
//        HP -= reducedHp;
//        return reducedHp;
//    }

    @Override
    public final boolean equals(Object otherObject)
    {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (!(otherObject instanceof BasicUnit other)) return false;
        return this.id == other.getId();
    }

    public boolean equalsAll(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicUnit that = (BasicUnit) o;

        return id == that.id && maxHp == that.maxHp && hp == that.hp && mana == that.mana && atk == that.atk
                && Double.compare(that.critRate, critRate) == 0 && Double.compare(that.critsEffect, critsEffect) == 0
                && Double.compare(that.physicalResistance, physicalResistance) == 0
                && Double.compare(that.evade, evade) == 0 && lifeRegeneration == that.lifeRegeneration
                && level == that.level && name.equals(that.name) && buff.equals(that.buff);
    }

    @Override
    public int hashCode()
    {
        return id * 31;
    }

    @Override
    public String toString()
    {
        return "BasicUnitAttribute"
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

    /**
     * @throws NullPointerException 如果{@code other}为null
     */
    public int compareTo(BasicUnit other)
    {
        return Integer.compare(id, requireNonNull(other).getId());
    }
}