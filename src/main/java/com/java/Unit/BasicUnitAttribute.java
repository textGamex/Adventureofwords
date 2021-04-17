package com.java.Unit;

/**
 * 基本单位属性
 * @version 0.30
 * @author Millennium
 */
public class BasicUnitAttribute extends BasicBuffModule implements Cloneable, Comparable<BasicUnitAttribute>
{
    public static void main(String[] args)
    {

    }
    private int id = 0;
    private static int nextTextId = 50000;//测试单位id TODO:未完成

    {
        id = ++nextTextId;
    }
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
    public BasicUnitAttribute()//TODO:要删除的
    {
        name = "";
    }
    /**参考<Effective Java第二章>*/
    public static class Builder
    {
        private String name;

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
        public Builder()
        {
            /*为了能被继承*/
        }
        public Builder maxHp(int maxHp)
        {
            if (maxHp <= 0)
                throw new IllegalArgumentException("异常参数: " + maxHp);
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
                throw new IllegalArgumentException("异常参数: " + level);
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
                + ", 最大生命值:" + maxHp
                + ", 等级:" + level
                + ", 物理攻击:" + atk
                + ", 暴击率:" + critRate * 100 + "%"
                + ", 暴击效果:" + critsEffect * 100 + "%"
                + ", 物理抗性:" + physicalResistance
                + ", 每回合生命回复:" +lifeRegeneration
                + "]";
    }
    /*克隆实现*/
    public BasicUnitAttribute clone() throws CloneNotSupportedException//TODO:现在有了EnumMap,可能要重写
    {
        return (BasicUnitAttribute) super.clone();
    }
    /*sort接口实现*/
    public int compareTo(BasicUnitAttribute other)
    {
        return Integer.compare(id, other.getId());
    }
}
