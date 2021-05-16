package com.java.unit;

import com.java.combatSystem.BuffModule.BuffModule;

import java.io.*;
import static java.util.Objects.requireNonNull;

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
 * @version 1.4.0
 * @author ǧ��
 * @see BuffModule
 * @see Role
 * @see Enemy
 */
public class BasicUnit implements Comparable<BasicUnit>, Serializable
{
    @Serial
    private static final long serialVersionUID = 7938388190739071271L;

    private static int nextTextId = 50000;//���Ե�λid
    private final int id = ++nextTextId;

    private final BuffModule buff = new BuffModule();
    private final UnitGrowth growth = new UnitGrowth();

    private final String name;
    /**�������ֵ*/
    private int maxHp;
    /**����ֵ*/
    private int hp;
    private int speed;
    /**ħ��ֵ*/
    private int mana;
    /**������*/
    private int physicalAttack;
    /**�����˺�*/
    private int magicAttack;
    /**���� */
    private int crit;
    /**�������� */
    private int critResistance;
    /**����Ч��*/
    private double critsEffect;

    /*     ������    */

    /**������*/
    private double physicalResistance;
    private int armor;
    /**ħ������*/
    private double magicResistance;
    /**����*/
    private int hit;
    /**����*/
    private int evade;
    /**ÿ�غ������ظ�*/
    private int lifeRegeneration;
    /**ÿ�غϷ���ֵ�ָ�*/
    private int manaRecovery;
    /**��λ�ȼ�*/
    private int level;

    /**
     * ����{@link BasicUnit}����
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
     * @author ǧ��
     * @version 1.4.1
     */
    public static class Builder<T extends Builder<T>>
    {
        private final String name;

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

        /**
         * @throws IllegalArgumentException ���{@code maxHp}С�ڵ���0
         */
        public T maxHp(int maxHp)
        {
            if (maxHp <= 0)
                throw new IllegalArgumentException("�쳣����: " + maxHp);
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
                throw new IllegalArgumentException("�쳣����:" + level);
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
     * ����{@link BasicUnit}����.
     *
     * <p>Ҫ�����¸�ʽ</p>
     * <pre>{@code var Object = new BasicUnit.Builder("unitName").build();}</pre>
     * <p>������</p><pre>{@code var Object = new BasicUnit.Builder();}</pre>
     * @throws NullPointerException ���{@code builder}��null
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
     * ��λ�ɳ�ֵ, ����ʵ�ֵ�λ����ʱ�����Գɳ�
     *
     * <p>��������{@link BasicUnit}��������</p>
     * <p>�Ѿ�ʵ�ֵ����Գɳ�ֵ</p>
     * <em>
     *     <li>�������ֵ, Ĭ��ֵΪ10</li>
     *     <li>������, Ĭ��ֵΪ2</li>
     *     <li>ħ������, Ĭ��ֵΪ1</li>
     *     <li>����ֵ, Ĭ��ֵΪ5</li>
     *     <li>����, Ĭ��ֵΪ1</li>
     * </em>
     * @author ǧ��
     * @version 1.0.1
     * @since 2021-5-3
     * @see BasicUnit
     * @see Role
     * @see Enemy
     */
    protected static class UnitGrowth//TODO:��
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
         * ��λ��������������
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
     * ���ڴ˵�λ��buffģ�齻��.
     *
     * @throws NullPointerException ���{@code buff}Ϊnull
     * @see BuffModule
     * @return �˵�λ��buff����
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
     * @throws IllegalArgumentException ���{@code level}С�ڵ���0
     */
    public void setLevel(int level)
    {
        if (level <= 0)
            throw new IllegalArgumentException("�쳣����:" + level);
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
     * @return �ַ�����ʾ�Ķ���
     */
    @Override
    public String toString()
    {
        return "BasicUnitAttribute"
                + "[id:" + id
                + ", ����:" + name
                + ", �������ֵ:" + maxHp
                + ", ����ֵ:" + hp
                + ", �ȼ�:" + level
                + ", �ٶ�:" + speed
                + ", ������:" + physicalAttack
                + ", ħ������:" + magicAttack
                + ", ����:" + crit
                + ", ��������:" + critResistance
                + ", ����Ч��:" + critsEffect * 100 + "%"
                + ", ������:" + physicalResistance
                + ", ����:" + armor
                + ", ÿ�غ������ظ�:" +lifeRegeneration
                + ", ����:" + evade
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
    public int compareTo(BasicUnit other)
    {
        return Integer.compare(id, requireNonNull(other).getId());
    }
}