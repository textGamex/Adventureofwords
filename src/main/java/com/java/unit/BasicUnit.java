package com.java.unit;

import com.java.combatSystem.BuffModule.BuffModule;

import java.io.*;
import static java.util.Objects.requireNonNull;

/**
 * 游戏的单位, 用于实现基本的游戏对战.
 *
 * <p>现已实现以下属性</p>
 * <ul>
 *     <li>单位名称, 无默认值</li>
 *     <li>单位等级, 默认值为1</li>
 *     <li>最大生命值, 默认值为100</li>
 *     <li>单位属性成长值, 默认值参见{@link UnitGrowth}</li>
 *     <li>速度, 默认值为50</li>
 *     <li>物理攻击, 默认值为0</li>
 *     <li>魔法攻击, 默认值为0</li>
 *     <li>魔法值, 默认值为0</li>
 *     <li>暴击, 默认值为0</li>
 *     <li>暴击抗性, 默认值为0</li>
 *     <li>暴击效果, 默认值为2.0</li>
 *     <li>命中, 默认值为50</li>
 *     <li>闪避, 默认值为5</li>
 *     <li>物理抗性, 默认值为0</li>
 *     <li>护甲, 默认值为0</li>
 *     <li>魔法抗性, 默认值为0</li>
 *     <li>每回合生命回复, 默认值为0</li>
 *     <li>每回合法力值恢复, 默认值为0</li>
 * </ul>
 * @version 1.4.0
 * @author 千年
 * @see BuffModule
 * @see Role
 * @see Enemy
 */
public class BasicUnit implements Comparable<BasicUnit>, Serializable
{
    @Serial
    private static final long serialVersionUID = 7938388190739071271L;

    private static int nextTextId = 50000;//测试单位id
    private final int id = ++nextTextId;

    private final BuffModule buff = new BuffModule();
    private final UnitGrowth growth = new UnitGrowth();

    private final String name;
    /**最大生命值*/
    private int maxHp;
    /**生命值*/
    private int hp;
    private int speed;
    /**魔法值*/
    private int mana;
    /**物理攻击*/
    private int physicalAttack;
    /**法术伤害*/
    private int magicAttack;
    /**暴击 */
    private int crit;
    /**暴击抗性 */
    private int critResistance;
    /**暴击效果*/
    private double critsEffect;

    /*     防御类    */

    /**物理抗性*/
    private double physicalResistance;
    private int armor;
    /**魔法抗性*/
    private double magicResistance;
    /**命中*/
    private int hit;
    /**闪避*/
    private int evade;
    /**每回合生命回复*/
    private int lifeRegeneration;
    /**每回合法力值恢复*/
    private int manaRecovery;
    /**单位等级*/
    private int level;

    /**
     * 构建{@link BasicUnit}对象
     *
     * <p>现已实现以下属性</p>
     *
     * <li>单位名称, 无默认值</li>
     * <li>单位等级, 默认值为1</li>
     * <li>最大生命值, 默认值为100</li>
     * <li>速度, 默认值为50</li>
     * <li>物理攻击, 默认值为0</li>
     * <li>魔法攻击, 默认值为0</li>
     * <li>魔法值, 默认值为0</li>
     * <li>暴击, 默认值为0</li>
     * <li>暴击抗性, 默认值为0</li>
     * <li>暴击效果, 默认值为2.0</li>
     * <li>命中, 默认值为50</li>
     * <li>闪避, 默认值为5</li>
     * <li>物理抗性, 默认值为0</li>
     * <li>护甲, 默认值为0</li>
     * <li>魔法抗性, 默认值为0</li>
     * <li>每回合生命回复, 默认值为0</li>
     * <li>每回合法力值恢复, 默认值为0</li>
     *
     * <strong>参考Effective Java(第三版)第二条</strong>
     * <p>采用泛型来使子类能正常工作</p>
     * <strong>注意:此构建器无法直接操作{@link UnitGrowth}对象, 请创建对象后再对{@link UnitGrowth}对象进行操作</strong>
     * @see BasicUnit
     * @see Role.Builder
     * @see Enemy.Builder
     * @since 15
     * @author 千年
     * @version 1.4.1
     */
    public static class Builder<T extends Builder<T>>
    {
        private final String name;

        private int maxHp                 = 100;
        private int physicalAttack        = 0;
        private int armor                 = 0;
        private int magicAttack           = 0;
        private int crit                  = 0;//暴击
        private int critResistance        = 0;
        private double critsEffect        = 2.0;//暴击效果
        private double physicalResistance = 0.0;//物理抗性
        private int evade                 = 5;//闪避
        private int lifeRegeneration      = 0;//每回合生命回复
        private double magicResistance    = 0.0;
        private int manaRecovery          = 0;
        private int level                 = 1;
        private int mana                  = 0;//法力值
        private int hit                   = 50;
        private int speed                 = 50;

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

        public T speed(int speed)
        {
            this.speed = speed;
            return (T) this;
        }

        public T hit(int hit)
        {
            this.hit = hit;
            return (T) this;
        }

        public T armor(int armor)
        {
            this.armor = armor;
            return (T) this;
        }

        public T critResistance(int critResistance)
        {
            this.critResistance = critResistance;
            return (T) this;
        }

        public T physicalAttack(int physicalAttack)
        {
            this.physicalAttack = physicalAttack;
            return (T) this;
        }

        public T magicAttack(int magicAttack)
        {
            this.magicAttack = magicAttack;
            return (T) this;
        }

        public T crit(int crit)
        {
            this.crit = crit;
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

        public T manaRecovery(int manaRecovery)
        {
            this.manaRecovery = manaRecovery;
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

        public T evade(int evade)
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
     * 构建{@link BasicUnit}对象.
     *
     * <p>要用如下格式</p>
     * <pre>{@code var Object = new BasicUnit.Builder("unitName").build();}</pre>
     * <p>而不是</p><pre>{@code var Object = new BasicUnit.Builder();}</pre>
     * @throws NullPointerException 如果{@code builder}是null
     * @see Builder
     */
    protected BasicUnit(Builder builder)
    {
        requireNonNull(builder);
        name               = builder.name;
        hp                 = builder.maxHp;
        maxHp              = builder.maxHp;
        level              = builder.level;
        speed              = builder.speed;
        physicalAttack     = builder.physicalAttack;
        magicAttack        = builder.magicAttack;
        crit               = builder.crit;
        critResistance     = builder.critResistance;
        critsEffect        = builder.critsEffect;
        physicalResistance = builder.physicalResistance;
        armor              = builder.armor;
        lifeRegeneration   = builder.lifeRegeneration;
        manaRecovery       = builder.manaRecovery;
        evade              = builder.evade;
        mana               = builder.mana;
        hit                = builder.hit;
        magicResistance    = builder.magicResistance;
    }

    /**
     * 单位成长值, 用来实现单位升级时的属性成长
     *
     * <p>用来服务{@link BasicUnit}及其子类</p>
     * <p>已经实现的属性成长值</p>
     * <em>
     *     <li>最大生命值, 默认值为10</li>
     *     <li>物理攻击, 默认值为2</li>
     *     <li>魔法攻击, 默认值为1</li>
     *     <li>法力值, 默认值为5</li>
     *     <li>闪避, 默认值为1</li>
     * </em>
     * @author 千年
     * @version 1.0.1
     * @since 2021-5-3
     * @see BasicUnit
     * @see Role
     * @see Enemy
     */
    protected static class UnitGrowth//TODO:改
    {
        private int maxHpGrowth = 10;
        private int physicalAttackGrowth = 2;
        private int magicAttackGrowth = 1;
        private int manaGrowth = 5;
        private int evadeGrowth = 1;

        public UnitGrowth(int maxHpGrowth, int physicalAttackGrowth, int magicAttackGrowth, int manaGrowth, int evadeGrowth)
        {
            this.maxHpGrowth = maxHpGrowth;
            this.physicalAttackGrowth = physicalAttackGrowth;
            this.magicAttackGrowth = magicAttackGrowth;
            this.manaGrowth = manaGrowth;
            this.evadeGrowth = evadeGrowth;
        }
        public UnitGrowth()
        {
        }

        /**
         * 单位升级后提升属性
         *
         * @see Role
         * @see Enemy
         * @since 2021-5-3
         */
        public void levelUpgrade()
        {

        }

        public int getMaxHpGrowth()
        {
            return maxHpGrowth;
        }

        public void setMaxHpGrowth(int maxHpGrowth)
        {
            this.maxHpGrowth = maxHpGrowth;
        }

        public int getPhysicalAttackGrowth()
        {
            return physicalAttackGrowth;
        }

        public void setPhysicalAttackGrowth(int physicalAttackGrowth)
        {
            this.physicalAttackGrowth = physicalAttackGrowth;
        }

        public int getMagicAttackGrowth()
        {
            return magicAttackGrowth;
        }

        public void setMagicAttackGrowth(int magicAttackGrowth)
        {
            this.magicAttackGrowth = magicAttackGrowth;
        }

        public int getManaGrowth()
        {
            return manaGrowth;
        }

        public void setManaGrowth(int manaGrowth)
        {
            this.manaGrowth = manaGrowth;
        }

        public int getEvadeGrowth()
        {
            return evadeGrowth;
        }

        public void setEvadeGrowth(int evadeGrowth)
        {
            this.evadeGrowth = evadeGrowth;
        }
    }

    /**
     * 用于此单位的buff模块交互.
     *
     * @throws NullPointerException 如果{@code buff}为null
     * @see BuffModule
     * @return 此单位的buff对象
     */
    public BuffModule buff()
    {
        return requireNonNull(buff);
    }

    public UnitGrowth growth()
    {
        return growth;
    }

    public final String getName()
    {
        return name;
    }

    public final int getHp()
    {
        return hp;
    }

    public final int getPhysicalAttack()
    {
        return physicalAttack;
    }

    public final int getCrit()
    {
        return crit;
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

    public int getHit()
    {
        return hit;
    }

    public void setHit(int hit)
    {
        this.hit = hit;
    }

    public int getArmor()
    {
        return armor;
    }

    public void setArmor(int armor)
    {
        this.armor = armor;
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

    public int getEvade()
    {
        return evade;
    }

    public int getMaxHp()
    {
        return maxHp;
    }

    public int getCritResistance()
    {
        return critResistance;
    }

    public void setCritResistance(int critResistance)
    {
        this.critResistance = critResistance;
    }

    public void setMaxHp(int maxHp)
    {
        this.maxHp = maxHp;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    public void setPhysicalAttack(int physicalAttack)
    {
        this.physicalAttack = physicalAttack;
    }

    public void setCrit(int crit)
    {
        this.crit = crit;
    }

    public void setCritsEffect(double critsEffect)
    {
        this.critsEffect = critsEffect;
    }

    public void setPhysicalResistance(double physicalResistance)
    {
        this.physicalResistance = physicalResistance;
    }

    public void setEvade(int evade)
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

    public int getMagicAttack()
    {
        return magicAttack;
    }

    public void setMagicAttack(int magicAttack)
    {
        this.magicAttack = magicAttack;
    }

    public int getManaRecovery()
    {
        return manaRecovery;
    }

    public void setManaRecovery(int manaRecovery)
    {
        this.manaRecovery = manaRecovery;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
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

    /**
     * 用来判断两个对象是否相同.
     *
     * @return 如果两个对象相同, 返回{@code true}
     * @since 15
     */
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

        return id == that.id && maxHp == that.maxHp && hp == that.hp && mana == that.mana && physicalAttack == that.physicalAttack
                && Double.compare(that.crit, crit) == 0 && Double.compare(that.critsEffect, critsEffect) == 0
                && Double.compare(that.physicalResistance, physicalResistance) == 0
                && Double.compare(that.evade, evade) == 0 && lifeRegeneration == that.lifeRegeneration
                && level == that.level && name.equals(that.name) && buff.equals(that.buff);
    }

    @Override
    public int hashCode()
    {
        return id * 31;
    }

    /**
     * @return 字符串表示的对象
     */
    @Override
    public String toString()
    {
        return "BasicUnitAttribute"
                + "[id:" + id
                + ", 名称:" + name
                + ", 最大生命值:" + maxHp
                + ", 生命值:" + hp
                + ", 等级:" + level
                + ", 速度:" + speed
                + ", 物理攻击:" + physicalAttack
                + ", 魔法攻击:" + magicAttack
                + ", 暴击:" + crit
                + ", 暴击抗性:" + critResistance
                + ", 暴击效果:" + critsEffect * 100 + "%"
                + ", 物理抗性:" + physicalResistance
                + ", 护甲:" + armor
                + ", 每回合生命回复:" +lifeRegeneration
                + ", 闪避:" + evade
                + "]";
    }

    /**
     * 用来克隆对象.
     *
     * @return 与this对象相同的对象
     * @throws CloneNotSupportedException 类不存在时
     */
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