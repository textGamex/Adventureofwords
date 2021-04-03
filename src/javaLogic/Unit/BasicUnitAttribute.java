package javaLogic.Unit;

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
    private static int nextTextId = 0;//测试单位id TODO:未完成
    private static int nextSpecialId = 0;//特殊单位id
    private final String name;
    /**最大生命值*/
    private int MAXHP;
    /**生命值*/
    private int HP;
    /**物理攻击*/
    private int ATK;
    /**暴击率 */
    private float CRIT;
    /**暴击效果*/
    private float critsEffect;
    /**固定护甲穿透*/
    private int fixArmorPen;
    /**百分比护甲穿透*/
    private float perArmorPen;

    /*    防御类    */

    /**护甲值*/
    private int armor;
    /**物理抗性 */
    private float physicalResistanc;
    /**闪避*/
    //private float evade;
    /**每回合生命回复*/
    private int lifeRegeneration;
    /**单位等级*/
    private int grade;


    public BasicUnitAttribute(String name,int grade, int MAXHP, int ATK, float CRIT, float critsEffect,
                              int fixArmorPen, float perArmorPen, int armor, float physicalResistanc,
                              int lifeRegeneration, int id)//*构造测试单位
    {
        this.name = name;
        this.grade = grade;
        this.MAXHP = MAXHP;
        this.HP = MAXHP;
        this.ATK = ATK;
        this.CRIT = CRIT;
        this.critsEffect = critsEffect;
        this.fixArmorPen = fixArmorPen;
        this.perArmorPen = perArmorPen;
        this.armor = armor;
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
        MAXHP = 100;
        HP = MAXHP;
        grade = 1;
        ATK = 20;
        CRIT = 0.1f;//*10%暴击率
        critsEffect = 2.0f;
        fixArmorPen = 5;
        perArmorPen = 0.0f;
        armor = 10;
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
        return MAXHP;
    }
    public final int getHP()
    {
        return HP;
    }
    public final int getATK()
    {
        return ATK;
    }
    public final int getFixArmorPen()
    {
        return fixArmorPen;
    }
    public final float getPerArmorPen()
    {
        return perArmorPen;
    }
    public final int getArmor()
    {
        return armor;
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
    public int directHP(int reducedHP)//*无视物抗直接扣血并返回扣除的HP */
    {
        HP -= reducedHP;
        return reducedHP;
    }

    public int subtractHP(int ATK, int fixArmorPen, float perArmorPen)//*计算护甲并扣血
    {
        int sumArmorPen = (int) ((this.armor * perArmorPen) + fixArmorPen);//*计算护甲穿透总和
        if (sumArmorPen >= this.armor)
            return directHP(ATK);
        else if (ATK >(this.armor - sumArmorPen))
            return directHP(ATK - (this.armor - sumArmorPen));
        else return 0;
    }
    public final boolean equals(Object otherObject)
    {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (!(otherObject instanceof BasicUnitAttribute)) return false;
        BasicUnitAttribute other = (BasicUnitAttribute) otherObject;
        return this.id == other.getId();
    }
    public int hashCode()
    {
        return Integer.hashCode(id) * 3;
    }
    public String toString()
    {
        return getClass().getName()
                + "[id:" + id
                + ", name:" + name
                + ", 最大生命值:" + MAXHP
                + ", 等级:" + grade
                + ", 物理攻击:" + ATK
                + ", 暴击率:" + CRIT * 100 + "%"
                + ", 暴击效果:" + critsEffect * 100 + "%"
                + ", 固定护甲穿透:" + fixArmorPen
                + ", 百分比护甲穿透:" + perArmorPen
                + ", 护甲值:" + armor
                + ", 物理抗性:" + physicalResistanc
                + ", 每回合生命回复:" +lifeRegeneration
                + "]";
    }
    /*克隆实现*/
    public BasicUnitAttribute clone() throws CloneNotSupportedException
    {
        return (BasicUnitAttribute) super.clone();
    }
    /*sort接口实现*/
    public int compareTo(BasicUnitAttribute other)
    {
        return Integer.compare(id, other.getId());
    }
}
