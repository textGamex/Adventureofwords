package com.java.unit;

import com.java.battleSystem.BuffModule.BuffModule;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;

import java.io.*;
import static java.util.Objects.requireNonNull;
import static com.java.unit.BasicUnit.UnitGrowth.calculationLevelGrowth;

/**
 * ��Ϸ�ĵ�λ, ����ʵ�ֻ�������Ϸ��ս.
 *
 * <p>����ʵ����������</p>
 * <ul>
 *     <li>��λ����, ��Ĭ��ֵ</li>
 *     <li>��λ�ȼ�, Ĭ��ֵΪ1</li>
 *     <li>�������ֵ, Ĭ��ֵΪ100</li>
 *     <li>��λ���Գɳ�ֵ, Ĭ��ֵ�μ�{@link UnitGrowth}</li>
 *     <li>�ٶ�, Ĭ��ֵΪ50</li>
 *     <li>������, Ĭ��ֵΪ0</li>
 *     <li>ħ������, Ĭ��ֵΪ0</li>
 *     <li>ħ��ֵ, Ĭ��ֵΪ0</li>
 *     <li>����, Ĭ��ֵΪ0</li>
 *     <li>��������, Ĭ��ֵΪ0</li>
 *     <li>����Ч��, Ĭ��ֵΪ2.0</li>
 *     <li>����, Ĭ��ֵΪ50</li>
 *     <li>����, Ĭ��ֵΪ5</li>
 *     <li>������, Ĭ��ֵΪ0</li>
 *     <li>����, Ĭ��ֵΪ0</li>
 *     <li>ħ������, Ĭ��ֵΪ0</li>
 *     <li>ÿ�غ������ظ�, Ĭ��ֵΪ0</li>
 *     <li>ÿ�غϷ���ֵ�ָ�, Ĭ��ֵΪ0</li>
 * </ul>
 * @version 2.0.0
 * @author ����ǧ��
 * @see BuffModule
 * @see Role
 * @see Enemy
 */
public class BasicUnit implements Comparable<BasicUnit>, Serializable
{
    @Serial
    private static final long serialVersionUID = 7938388190739071271L;
    private static int nextTestId = 50000;//���Ե�λid
    private final int id = ++nextTestId;

    private final BuffModule buff = new BuffModule();
    private final UnitAttack attackModule = new UnitAttack();
    private final UnitDefense defenseModule = new UnitDefense();
    private final UnitGrowth growth;

    private final String name;
    private int speed;
    /**��λ�ȼ�*/
    private int level;

    /**
     * ����{@link BasicUnit}����.
     *
     * <p>����ʵ����������</p>
     *
     * <li>��λ����, ��Ĭ��ֵ</li>
     * <li>��λ�ȼ�, Ĭ��ֵΪ1</li>
     * <li>�������ֵ, Ĭ��ֵΪ100</li>
     * <li>�ٶ�, Ĭ��ֵΪ50</li>
     * <li>������, Ĭ��ֵΪ0</li>
     * <li>ħ������, Ĭ��ֵΪ0</li>
     * <li>ħ��ֵ, Ĭ��ֵΪ0</li>
     * <li>����, Ĭ��ֵΪ0</li>
     * <li>��������, Ĭ��ֵΪ0</li>
     * <li>����Ч��, Ĭ��ֵΪ2.0</li>
     * <li>����, Ĭ��ֵΪ50</li>
     * <li>����, Ĭ��ֵΪ5</li>
     * <li>������, Ĭ��ֵΪ0</li>
     * <li>����, Ĭ��ֵΪ0</li>
     * <li>ħ������, Ĭ��ֵΪ0</li>
     * <li>ÿ�غ������ظ�, Ĭ��ֵΪ0</li>
     * <li>ÿ�غϷ���ֵ�ָ�, Ĭ��ֵΪ0</li>
     *
     * <strong>�ο�Effective Java(������)�ڶ���</strong>
     * <p>���÷�����ʹ��������������</p>
     * <strong>ע��:�˹������޷�ֱ�Ӳ���{@link UnitGrowth}����, �봴��������ٶ�{@link UnitGrowth}������в���</strong>
     * @see BasicUnit
     * @see Role.Builder
     * @see Enemy.Builder
     * @since 15
     * @author ����ǧ��
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
        private int crit                  = 0;//����
        private int critResistance        = 0;
        private double critsEffect        = 2.0;//����Ч��
        private double physicalResistance = 0.0;//������
        private int evade                 = 5;//����
        private int lifeRegeneration      = 0;//ÿ�غ������ظ�
        private double magicResistance    = 0.0;
        private int manaRecovery          = 0;
        private int level                 = 1;
        private int mana                  = 0;//����ֵ
        private int hit                   = 50;
        private int speed                 = 50;

        /**
         * @param name ��λ����
         * @throws NullPointerException ���{@code name}��null
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
         * @throws IllegalArgumentException ���{@code maxHp}С�ڵ���0
         */
        public T maxHp(int maxHp)
        {
            if (maxHp <= 0)
            {
                throw new IllegalArgumentException("�쳣����: " + maxHp);
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
         * @throws IllegalArgumentException ���{@code level}С�ڵ���0
         */
        public T level(int level)
        {
            if (level < 0)
            {
                throw new IllegalArgumentException("�쳣����:" + level);
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
     * ����{@link BasicUnit}����.
     *
     * <p>Ҫ�����¸�ʽ</p>
     * <pre>{@code var Object = new BasicUnit.Builder("unitName").build();}</pre>
     * <p>������</p>
     * <pre>{@code var Object = new BasicUnit.Builder();}</pre>
     * @throws NullPointerException ���{@code builder}��null
     * @see Builder
     */
    protected BasicUnit(Builder builder)
    {
        //TODO:�Ժ���ʱ����԰ѹ����ͷ���ģ��Ĺ�����д�ɽ���Builder
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
     * ��λ�ɳ�ֵ, ����ʵ�ֵ�λ����ʱ�����Գɳ�.
     *
     * <p>��������{@link BasicUnit}��������</p>
     * <p>�Ѿ�ʵ�ֵ����Գɳ�ֵ</p>
     * <li>�������ֵ, Ĭ��ֵΪ0.07</li>
     * <li>������, Ĭ��ֵΪ0.07</li>
     * <li>ħ������, Ĭ��ֵΪ0.07</li>
     * <li>����ֵ, Ĭ��ֵΪ0.07</li>
     * <li>����, Ĭ��ֵΪ0.07</li>
     * <li>����, Ĭ��ֵΪ0.07</li>
     * @author ����ǧ��
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
         * @param attribute ԭ��������
         * @param growthRatio ���Գɳ���
         * @param increaseLevel �����ĵȼ�
         * @return ����������ֵ
         * @throws IllegalArgumentException ���{@code attribute}��{@code increaseLevel < 0}
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

            LOGGER.trace("��ʼֵ:{}, ÿ����������{}%, ��������Ϊ{}", attribute, String.format("%.3f", growthRatio * 100),
                    increaseLevel);
            for (int i = 0; i < increaseLevel; i++)
            {
                attribute += attribute * growthRatio;
                LOGGER.trace("��{}��ѭ��, ֵΪ{}", i + 1, attribute);
            }
            return attribute;
        }

        /**
         * ��λ��������������.
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
     * ���ڴ˵�λ��buffģ�齻��.
     *
     * @throws NullPointerException ���buffģ�鲻����
     * @see BuffModule
     * @return �˵�λ��buff����
     */
    public BuffModule buff()
    {
        return requireNonNull(buff);
    }

    /**
     *
     * @return ���ص�λ������ģ��
     */
    public UnitGrowth growth()
    {
        return requireNonNull(growth);
    }

    /**
     *
     * @return ���ص�λ�Ĺ���ģ��
     */
    public UnitAttack attack()
    {
        return requireNonNull(attackModule);
    }

    /**
     *
     * @return ���ص�λ�ķ���ģ��
     */
    public UnitDefense defense()
    {
        return requireNonNull(defenseModule);
    }

    /**
     *
     * @return ���ص�λ����
     */
    public final String getName()
    {
        return name;
    }
    /**
     *
     * @return ���ص�λ��ΨһID
     */
    public int getId()
    {
        return id;
    }

    /**
     *
     * @return ���ص�λ�ĵȼ�
     */
    public final int getLevel()
    {
        return level;
    }

    /**
     *
     * @return ���ص�λ���ٶ�
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
     * @throws IllegalArgumentException ���{@code level}С�ڵ���0
     */
    public void setLevel(int level)
    {
        if (level <= 0)
        {
            throw new IllegalArgumentException("�쳣����:" + level);
        }
        this.level = level;
    }

    /**
     * �����ж����������Ƿ���ͬ.
     *
     * @return �������������ͬ, ����{@code true}
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
     * @return �ַ�����ʾ�Ķ���
     */
    @Override
    public String toString()
    {
        return "BasicUnitAttribute"
                + "[id:" + id
                + ", ����:" + name
                + ", �������ֵ:" + defenseModule.getMaxHp()
                + ", ����ֵ:" + defenseModule.getHp()
                + ", �ȼ�:" + level
                + ", �ٶ�:" + speed
                + ", ������:" + attackModule.getPhysicalAttack()
                + ", ħ������:" + attackModule.getPhysicalAttack()
                + ", ����:" + attackModule.getCrit()
                + ", ��������:" + defenseModule.getCritResistance()
                + ", ����Ч��:" + attackModule.getCritsEffect() * 100 + "%"
                + ", ������:" + defenseModule.getPhysicalResistance()
                + ", ����:" + defenseModule.getArmor()
                + ", ÿ�غ������ظ�:" + defenseModule.getLifeRegeneration()
                + ", ÿ�غ�ħ��ֵ�ظ�:" + attackModule.getManaRecovery()
                + ", ����:" + defenseModule.getEvade()
                + "]";
    }

    /**
     * ������¡����.
     *
     * @return ��this������ͬ�Ķ���
     * @throws CloneNotSupportedException �಻����ʱ
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
     * @throws NullPointerException ���{@code other}Ϊnull
     */
    @Override
    public int compareTo(@NotNull BasicUnit other)
    {
        return Integer.compare(id, requireNonNull(other).getId());
    }
}