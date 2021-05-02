package com.java.Unit;

import com.java.CombatSystem.BuffModule.BuffModule;

import java.io.*;
import static java.util.Objects.requireNonNull;
/**
 * ��Ϸ�ĵ�λ, ����ʵ�ֻ�������Ϸ��ս
 *
 * <p>����ʵ����������</p>
 * <ul>
 *     <li>����</li>
 *     <li>�ȼ�</li>
 *     <li>�������ֵ</li>
 *     <li>������</li>
 *     <li>ħ������</li>
 *     <li>������</li>
 *     <li>����Ч��</li>
 *     <li>������</li>
 *     <li>������</li>
 *     <li>������</li>
 *     <li>ħ������</li>
 *     <li>ÿ�غ������ظ�</li>
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

    private static int nextTextId = 50000;//���Ե�λid
    private final int id = ++nextTextId;

    private final BuffModule buff = new BuffModule();
    private final String name;
    /**�������ֵ*/
    private int maxHp;
    /**����ֵ*/
    private int hp;
    /**ħ��ֵ*/
    private int mana;
    /**������*/
    private int atk;
    /**������ */
    private double critRate;
    /**����Ч��*/
    private double critsEffect;

    /**    <������>    */

    /**������*/
    private double physicalResistance;
    /**ħ������*/
    private double magicResistance;
    /**������*/
    private double hitRate;
    /**����*/
    private double evade;
    /**ÿ�غ������ظ�*/
    private int lifeRegeneration;
    /**��λ�ȼ�*/
    private int level;

    /**
     * ����{@code BasicUnit}����
     *
     * <p>����ʵ����������</p>
     * <ul>
     *     <li>����</li>
     *     <li>�ȼ�</li>
     *     <li>�������ֵ</li>
     *     <li>������</li>
     *     <li>ħ������</li>
     *     <li>������</li>
     *     <li>����Ч��</li>
     *     <li>������</li>
     *     <li>������</li>
     *     <li>������</li>
     *     <li>ħ������</li>
     *     <li>ÿ�غ������ظ�</li>
     * </ul>
     * <strong>�ο�Effective Java�ڶ���</strong>
     * <p>���÷�����ʹ��������������</p>
     * @see BasicUnit
     * @since 15
     * @author ǧ��
     * @version 1.3.2
     */
    public static class Builder<T extends Builder>
    {
        private final String name;

        private int maxHp                 = 100;
        private int atk                   = 0;
        private double critRate           = 0.0;//������
        private double critsEffect        = 2.0;//����Ч��
        private double physicalResistance = 0.0;//������
        private double evade              = 0.0;//����
        private int lifeRegeneration      = 0;//ÿ�غ������ظ�
        private int level                 = 1;
        private int mana                  = 0;
        private double hitRate            = 0.7;
        private double magicResistance    = 0.0;
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
         * @throws IllegalArgumentException ���{@code level}С�ڵ���0
         */
        public T level(int level)
        {
            if (level < 0)
                throw new IllegalArgumentException("�쳣����:" + level);
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
     * @throws NullPointerException ���{@code builder}��null
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
     * @throws NullPointerException ���{@code buff}Ϊnull
     * @see BuffModule
     * @return �˵�λ��buff����
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
     * @throws IllegalArgumentException ���{@code level}С�ڵ���0
     */
    public void setLevel(int level)
    {
        if (level <= 0)
            throw new IllegalArgumentException("�쳣����:" + level);
        this.level = level;
    }

    /**�����￹ֱ�ӿ�Ѫ�����ؿ۳���HP */
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
                + ", ����:" + name
                + ", �������ֵ:" + maxHp
                + ", �ȼ�:" + level
                + ", ������:" + atk
                + ", ħ������:" + mana
                + ", ������:" + critRate * 100 + "%"
                + ", ����Ч��:" + critsEffect * 100 + "%"
                + ", ������:" + physicalResistance
                + ", ÿ�غ������ظ�:" +lifeRegeneration
                + ", ������:" + evade * 100 + "%"
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
     * @throws NullPointerException ���{@code other}Ϊnull
     */
    public int compareTo(BasicUnit other)
    {
        return Integer.compare(id, requireNonNull(other).getId());
    }
}