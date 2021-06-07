package com.java.unit;

import com.java.battleSystem.BuffModule.BuffModule;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import java.io.*;
import static java.util.Objects.requireNonNull;
import static com.java.unit.BasicUnit.UnitGrowth.calculationLevelGrowth;

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
 * @version 2.0.0
 * @author 留恋千年
 * @see BuffModule
 * @see Role
 * @see Enemy
 */
public class BasicUnit implements Comparable<BasicUnit>, Serializable
{
    @Serial
    private static final long serialVersionUID = 7938388190739071271L;
    private static int nextTestId = 50000;//测试单位id
    private final int id = ++nextTestId;

    private final BuffModule buff = new BuffModule();
    private final UnitAttack attackModule = new UnitAttack();
    private final UnitDefense defenseModule = new UnitDefense();
    private final UnitGrowth growth;

    private final String name;
    private int speed;
    /**单位等级*/
    private int level;

    /**
     * 构建{@link BasicUnit}对象.
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
     * @author 留恋千年
     * @version 1.4.1
     */
    public static class Builder<T extends Builder<T>>
    {
        private final String name;

        private final UnitGrowth growth = new UnitGrowth();
        private final UnitAttack unitAttack = new UnitAttack();
        private final UnitDefense unitDefense = new UnitDefense();
        private boolean attributesIsGrowWithLevel = false;
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

        public T attributesIsGrowWithLevel(boolean attributesIsGrowWithLevel)
        {
            this.attributesIsGrowWithLevel = attributesIsGrowWithLevel;
            return (T) this;
        }
        /**
         * @throws IllegalArgumentException 如果{@code maxHp}小于等于0
         */
        public T maxHp(int maxHp)
        {
            if (maxHp <= 0)
            {
                throw new IllegalArgumentException("异常参数: " + maxHp);
            }
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
            {
                throw new IllegalArgumentException("异常参数:" + level);
            }
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

        public T maxHpGrowth(double maxHpGrowth)
        {
            growth.setMaxHpGrowth(maxHpGrowth);
            return (T) this;
        }

        public T physicalAttackGrowth(double physicalAttackGrowth)
        {
            growth.setPhysicalAttackGrowth(physicalAttackGrowth);
            return (T) this;
        }

        public T magicAttackGrowth(double magicAttackGrowth)
        {
            growth.setMagicAttackGrowth(magicAttackGrowth);
            return (T) this;
        }

        public T manaGrowth(double manaGrowth)
        {
            growth.setManaGrowth(manaGrowth);
            return (T) this;
        }

        public T evadeGrowth(double evadeGrowth)
        {
            growth.setEvadeGrowth(evadeGrowth);
            return (T) this;
        }

        public T critGrowth(double critGrowth)
        {
            growth.setCritGrowth(critGrowth);
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
     * <p>而不是</p>
     * <pre>{@code var Object = new BasicUnit.Builder();}</pre>
     * @throws NullPointerException 如果{@code builder}是null
     * @see Builder
     */
    protected BasicUnit(Builder builder)
    {
        //TODO:以后有时间可以把攻击和防御模块的构造器写成接收Builder
        requireNonNull(builder);
        growth             = requireNonNull(builder.growth);
        int increaseLevel = 0;
        if (builder.attributesIsGrowWithLevel)
        {
            increaseLevel = builder.level - 1;
        }
        name               = builder.name;
        level              = builder.level;
        speed              = builder.speed;

        attackModule.setPhysicalAttack(calculationLevelGrowth(builder.physicalAttack, builder.growth.physicalAttackGrowth,
                increaseLevel));
        attackModule.setMagicAttack(calculationLevelGrowth(builder.magicAttack, builder.growth.magicAttackGrowth,
                increaseLevel));
        attackModule.setCrit(calculationLevelGrowth(builder.crit, builder.growth.critGrowth,
                increaseLevel));
        attackModule.setMana(calculationLevelGrowth(builder.mana, builder.growth.manaGrowth,
                increaseLevel));
        attackModule.setHit(builder.hit);
        attackModule.setManaRecovery(builder.manaRecovery);

        defenseModule.setCritResistance(builder.critResistance);
        attackModule.setCritsEffect(builder.critsEffect);
        defenseModule.setPhysicalResistance(builder.physicalResistance);
        defenseModule.setArmor(builder.armor);
        defenseModule.setLifeRegeneration(builder.lifeRegeneration);
        defenseModule.setEvade(builder.evade);
        defenseModule.setMaxHp(calculationLevelGrowth(builder.maxHp, builder.growth.maxHpGrowth,
                increaseLevel));
        defenseModule.setHp(defenseModule.getMaxHp());
        defenseModule.setMagicResistance(builder.magicResistance);
    }

    /**
     * 单位成长值, 用来实现单位升级时的属性成长.
     *
     * <p>用来服务{@link BasicUnit}及其子类</p>
     * <p>已经实现的属性成长值</p>
     * <li>最大生命值, 默认值为0.07</li>
     * <li>物理攻击, 默认值为0.07</li>
     * <li>魔法攻击, 默认值为0.07</li>
     * <li>法力值, 默认值为0.07</li>
     * <li>闪避, 默认值为0.07</li>
     * <li>暴击, 默认值为0.07</li>
     * @author 留恋千年
     * @version 1.1.2
     * @since 2021-5-3
     * @see BasicUnit
     * @see Role
     * @see Enemy
     */
    protected static class UnitGrowth implements Serializable
    {
        private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UnitGrowth.class);
        private double maxHpGrowth = 0.07;
        private double physicalAttackGrowth = 0.07;
        private double magicAttackGrowth = 0.07;
        private double manaGrowth = 0.07;
        private double evadeGrowth = 0.07;
        private double critGrowth = 0.07;

        /**
         *
         * @param attribute 原来的属性
         * @param growthRatio 属性成长比
         * @param increaseLevel 提升的等级
         * @return 计算后的属性值
         * @throws IllegalArgumentException 如果{@code attribute}或{@code increaseLevel < 0}
         * @since 2021-5-28
         */
        public static int calculationLevelGrowth(int attribute, final double growthRatio, final int increaseLevel)
        {
            if (increaseLevel < 0)
            {
                throw new IllegalArgumentException("increaseLevel < 0, increaseLevel:" + increaseLevel);
            }
            if (attribute < 0)
            {
                throw new IllegalArgumentException("attribute < 0, attribute:" + attribute);
            }
            if (increaseLevel == 0 || growthRatio == 0.0 || attribute == 0)
            {
                return attribute;
            }

            LOGGER.trace("初始值:{}, 每次升级提升{}%, 升级次数为{}", attribute, String.format("%.3f", growthRatio * 100),
                    increaseLevel);
            for (int i = 0; i < increaseLevel; i++)
            {
                attribute += attribute * growthRatio;
                LOGGER.trace("第{}次循环, 值为{}", i + 1, attribute);
            }
            return attribute;
        }

        /**
         * 单位升级后提升属性.
         *
         * @see Role
         * @see Enemy
         * @since 2021-5-3
         */
        public void levelUpgrade()
        {

        }

        public double getMaxHpGrowth()
        {
            return maxHpGrowth;
        }

        public void setMaxHpGrowth(double maxHpGrowth)
        {
            this.maxHpGrowth = maxHpGrowth;
        }

        public double getPhysicalAttackGrowth()
        {
            return physicalAttackGrowth;
        }

        public void setPhysicalAttackGrowth(double physicalAttackGrowth)
        {
            this.physicalAttackGrowth = physicalAttackGrowth;
        }

        public double getMagicAttackGrowth()
        {
            return magicAttackGrowth;
        }

        public void setMagicAttackGrowth(double magicAttackGrowth)
        {
            this.magicAttackGrowth = magicAttackGrowth;
        }

        public double getManaGrowth()
        {
            return manaGrowth;
        }

        public void setManaGrowth(double manaGrowth)
        {
            this.manaGrowth = manaGrowth;
        }

        public double getEvadeGrowth()
        {
            return evadeGrowth;
        }

        public void setEvadeGrowth(double evadeGrowth)
        {
            this.evadeGrowth = evadeGrowth;
        }

        public double getCritGrowth()
        {
            return critGrowth;
        }

        public void setCritGrowth(final double critGrowth)
        {
            this.critGrowth = critGrowth;
        }
    }

    /**
     * 用于此单位的buff模块交互.
     *
     * @throws NullPointerException 如果buff模块不存在
     * @see BuffModule
     * @return 此单位的buff对象
     */
    public BuffModule buff()
    {
        return requireNonNull(buff);
    }

    /**
     *
     * @return 返回单位的升级模块
     */
    public UnitGrowth growth()
    {
        return requireNonNull(growth);
    }

    /**
     *
     * @return 返回单位的攻击模块
     */
    public UnitAttack attack()
    {
        return requireNonNull(attackModule);
    }

    /**
     *
     * @return 返回单位的防御模块
     */
    public UnitDefense defense()
    {
        return requireNonNull(defenseModule);
    }

    /**
     *
     * @return 返回单位名称
     */
    public final String getName()
    {
        return name;
    }
    /**
     *
     * @return 返回单位的唯一ID
     */
    public int getId()
    {
        return id;
    }

    /**
     *
     * @return 返回单位的等级
     */
    public final int getLevel()
    {
        return level;
    }

    /**
     *
     * @return 返回单位的速度
     */
    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

//    public void
    /**
     * @throws IllegalArgumentException 如果{@code level}小于等于0
     */
    public void setLevel(int level)
    {
        if (level <= 0)
        {
            throw new IllegalArgumentException("异常参数:" + level);
        }
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
        if (this == otherObject)
        {
            return true;
        }
        if (otherObject == null)
        {
            return false;
        }
        if (!(otherObject instanceof BasicUnit other))
        {
            return false;
        }
        return this.id == other.getId();
    }

    public void subtractHp(final int subtractHp)
    {
        defense().setHp(defense().getHp() - subtractHp);
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
                + ", 最大生命值:" + defenseModule.getMaxHp()
                + ", 生命值:" + defenseModule.getHp()
                + ", 等级:" + level
                + ", 速度:" + speed
                + ", 物理攻击:" + attackModule.getPhysicalAttack()
                + ", 魔法攻击:" + attackModule.getPhysicalAttack()
                + ", 暴击:" + attackModule.getCrit()
                + ", 暴击抗性:" + defenseModule.getCritResistance()
                + ", 暴击效果:" + attackModule.getCritsEffect() * 100 + "%"
                + ", 物理抗性:" + defenseModule.getPhysicalResistance()
                + ", 护甲:" + defenseModule.getArmor()
                + ", 每回合生命回复:" + defenseModule.getLifeRegeneration()
                + ", 每回合魔法值回复:" + attackModule.getManaRecovery()
                + ", 闪避:" + defenseModule.getEvade()
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
    @Override
    public int compareTo(@NotNull BasicUnit other)
    {
        return Integer.compare(id, requireNonNull(other).getId());
    }
}