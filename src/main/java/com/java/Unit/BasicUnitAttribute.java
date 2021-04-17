package com.java.Unit;

/**
 * ������λ����
 * @version 0.30
 * @author Millennium
 */
public class BasicUnitAttribute extends BasicBuffModule implements Cloneable, Comparable<BasicUnitAttribute>
{
    public static void main(String[] args)
    {

    }
    private int id = 0;
    private static int nextTextId = 50000;//���Ե�λid TODO:δ���

    {
        id = ++nextTextId;
    }
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

    /**������ */
    private double physicalResistance;
    /**����*/
    private double evade;
    /**ÿ�غ������ظ�*/
    private int lifeRegeneration;
    /**��λ�ȼ�*/
    private int level;
    public BasicUnitAttribute()//TODO:Ҫɾ����
    {
        name = "";
    }
    /**�ο�<Effective Java�ڶ���>*/
    public static class Builder
    {
        private String name;

        private int maxHp                 = 100;
        private int atk                   = 0;
        private double critRate           = 0.0;//������
        private double critsEffect        = 2.0;//����Ч��
        private double physicalResistance = 0.0;//������
        private double evade              = 0.0;//����
        private int lifeRegeneration      = 0;//ÿ�غ������ظ�
        private int level                 = 1;
        private int mana                  = 0;

        public Builder(String name)
        {
            if (name == null)
                throw new NullPointerException();
            this.name = name;
        }
        public Builder()
        {
            /*Ϊ���ܱ��̳�*/
        }
        public Builder maxHp(int maxHp)
        {
            if (maxHp <= 0)
                throw new IllegalArgumentException("�쳣����: " + maxHp);
            this.maxHp = maxHp;
            return this;
        }
        public Builder atk(int atk)
        {
            this.atk = atk;
            return this;
        }
        public Builder critRate(double critRate)
        {
            this.critRate = critRate;
            return this;
        }
        public Builder critsEffect(double critsEffect)
        {
            this.critsEffect = critsEffect;
            return this;
        }
        public Builder physicalResistance(double physicalResistance)
        {
            this.physicalResistance = physicalResistance;
            return this;
        }
        public Builder lifeRegeneration(int lifeRegeneration)
        {
            this.lifeRegeneration = lifeRegeneration;
            return this;
        }
        public Builder level(int level)
        {
            if (level < 0)
                throw new IllegalArgumentException("�쳣����: " + level);
            this.level = level;
            return this;
        }
        public Builder evade(double evade)
        {
            this.evade = evade;
            return this;
        }
        public Builder mana(int mana)
        {
            this.mana = mana;
            return this;
        }
        public BasicUnitAttribute build()
        {
            return new BasicUnitAttribute(this);
        }

    }

    private BasicUnitAttribute(Builder builder)
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
    //    public int directHP(int reducedHP)//*�����￹ֱ�ӿ�Ѫ�����ؿ۳���HP */
//    {
//        HP -= reducedHP;
//        return reducedHP;
//    }

//    public int subtractHP(int ATK, int fixArmorPen, double perArmorPen)//*���㻤�ײ���Ѫ
//    {
//        int sumArmorPen = (int) ((this.armor * perArmorPen) + fixArmorPen);//*���㻤�״�͸�ܺ�
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
        if (!(otherObject instanceof BasicUnitAttribute)) return false;
        BasicUnitAttribute other = (BasicUnitAttribute) otherObject;
        return this.id == other.getId();
    }
    @Override
    public int hashCode()
    {
        return Integer.hashCode(id) * 31;
    }
    @Override
    public String toString()
    {
        return super.toString()
                + "BasicUnitAttribute"
                + "[id:" + id
                + ", name:" + name
                + ", �������ֵ:" + maxHp
                + ", �ȼ�:" + level
                + ", ������:" + atk
                + ", ������:" + critRate * 100 + "%"
                + ", ����Ч��:" + critsEffect * 100 + "%"
                + ", ������:" + physicalResistance
                + ", ÿ�غ������ظ�:" +lifeRegeneration
                + "]";
    }
    /*��¡ʵ��*/
    public BasicUnitAttribute clone() throws CloneNotSupportedException//TODO:��������EnumMap,����Ҫ��д
    {
        return (BasicUnitAttribute) super.clone();
    }
    /*sort�ӿ�ʵ��*/
    public int compareTo(BasicUnitAttribute other)
    {
        return Integer.compare(id, other.getId());
    }
}
