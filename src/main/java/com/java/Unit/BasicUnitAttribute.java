package com.java.Unit;

/**
 * 基本单位属性
 * @version 0.15
 * @author Millennium
 */
public class BasicUnitAttribute extends BasicBuffModule implements Cloneable, Comparable<BasicUnitAttribute>
{
    public static void main(String[] args)
    {
        var f = new BasicUnitAttribute();
        System.out.println(f.hashCode());
        System.out.println(f.toString());
    }
    private final int id;
//    private static int nextTextId = 0;//测试单位id TODO:未完成
//    private static int nextSpecialId = 0;//特殊单位id
    private final String name;
    /**最大生命值*/
    private int maxHp;
    /**生命值*/
    private int hp;
    /**物理攻击*/
    private int atk;
    /**暴击率 */
    private float CRIT;
    /**暴击效果*/
    private float critsEffect;
    /*    防御类    */

    /**物理抗性 */
    private float physicalResistanc;
    /**闪避*/
    //private float evade;
    /**每回合生命回复*/
    private int lifeRegeneration;
    /**单位等级*/
    private int grade;

    public static class Builder
    {
        private final String name;
        private int maxHp;
        private int hp;

        /**物理攻击*/
        private int atk           = 0;
        /**暴击率 */
        private float critRate    = 0.0f;
        /**暴击效果*/
        private float critsEffect = 0.0f;

        /*    防御类    */

        /**物理抗性 */
        private float physicalResistanc = 0.0f;
        /**闪避*/
        //private float evade;

        /**每回合生命回复*/
        private int lifeRegeneration = 0;
        /**单位等级*/
        private int grade = 1;

        public Builder(String name, int maxHp)
        {
            this.name = name;
            this.maxHp = maxHp;
            hp = maxHp;
        }

        public Builder maxHp(int maxHp)
        {
            this.maxHp = maxHp;
            return this;
        }
        public Builder atk(int atk)
        {
            this.atk = atk;
            return this;
        }
        public Builder critRate(int critRate)
        {
            this.critRate = critRate;
            return this;
        }
        public Builder critsEffect(int critsEffect)
        {
            this.critsEffect = critsEffect;
            return this;
        }
        public Builder physicalResistanc(int physicalResistanc)
        {
            this.physicalResistanc = physicalResistanc;
            return this;
        }
        public Builder lifeRegeneration(int lifeRegeneration)
        {
            this.lifeRegeneration = lifeRegeneration;
            return this;
        }
        public Builder grade(int grade)
        {
            this.grade = grade;
            return this;
        }
    }
    public BasicUnitAttribute(String name, int grade, int maxHp, int atk, float CRIT, float critsEffect,
                              float physicalResistanc,
                              int lifeRegeneration, int id)//*构造测试单位
    {
        this.name = name;
        this.grade = grade;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.atk = atk;
        this.CRIT = CRIT;
        this.critsEffect = critsEffect;
        this.physicalResistanc = physicalResistanc;
        this.lifeRegeneration = lifeRegeneration;
        this.id = id;
    }
    public BasicUnitAttribute()//*构造测试单位
    {
        this("测试单位");
    }
    public BasicUnitAttribute(String name)//*构造有名字单位
    {
        this.name = name;
        maxHp = 100;
        hp = maxHp;
        grade = 1;
        atk = 20;
        CRIT = 0.1f;//*10%暴击率
        critsEffect = 2.0f;
        physicalResistanc = 0.0f;
        lifeRegeneration = 0;
        id = 60001;

    }
    public final String getName()
    {
        return name;
    }
    public final int getMaxXP()
    {
        return maxHp;
    }
    public final int getHp()
    {
        return hp;
    }
    public final int getAtk()
    {
        return atk;
    }
    public final float getCRIT()
    {
        return CRIT;
    }
    public final float getCritsEffect()
    {
        return critsEffect;
    }
    public final float getPhysicalResistanc()
    {
        return physicalResistanc;
    }
    public int getId()
    {
        return id;
    }
    public final int getLifeRegeneration()
    {
        return lifeRegeneration;
    }
    public final int getGrade()
    {
        return grade;
    }
//    public int directHP(int reducedHP)//*无视物抗直接扣血并返回扣除的HP */
//    {
//        HP -= reducedHP;
//        return reducedHP;
//    }

//    public int subtractHP(int ATK, int fixArmorPen, float perArmorPen)//*计算护甲并扣血
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
                + ", 等级:" + grade
                + ", 物理攻击:" + atk
                + ", 暴击率:" + CRIT * 100 + "%"
                + ", 暴击效果:" + critsEffect * 100 + "%"
                + ", 物理抗性:" + physicalResistanc
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
