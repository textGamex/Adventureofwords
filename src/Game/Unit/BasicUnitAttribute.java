package Game.Unit;
/**
 * 基本单位属性
 * @version 0.15
 * @author Millennium
 */
public class BasicUnitAttribute implements Cloneable, Comparable<BasicUnitAttribute>
{
    public static void main(String[] args)
    {
        var f = new BasicUnitAttribute();
        System.out.println(f.hashCode());
        System.out.println(f.toString());
    }
    private final int id;
    private final String name;
    private int MAXHP; //*最大生命值
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

    //@    防御类    */
    /**护甲值*/
    private int armor;
    /**物理抗性 */
    private float physicalResistanc;
    /**闪避*/
    //private float evade;

    public BasicUnitAttribute(String name, int MAXHP, int ATK, float CRIT, float critsEffect,
                              int fixArmorPen, float perArmorPen, int armor, float physicalResistanc)//*构造测试单位
    {
        this.name = name;
        this.MAXHP = MAXHP;
        this.HP = MAXHP;
        this.ATK = ATK;
        this.CRIT = CRIT;
        this.critsEffect = critsEffect;
        this.fixArmorPen = fixArmorPen;
        this.perArmorPen = perArmorPen;
        this.armor = armor;
        this.physicalResistanc = physicalResistanc;
        id = 60001;
    }
    public BasicUnitAttribute()//*构造测试单位
    {
        name = "测试单位";
        MAXHP = 100;
        HP = MAXHP;
        ATK = 20;
        CRIT = 0.1f;//*10%暴击率
        critsEffect = 2.0f;
        fixArmorPen = 5;
        perArmorPen = 0.0f;
        armor = 10;
        physicalResistanc = 0.0f;
        id = 60001;
    }
    public BasicUnitAttribute(String name)//*构造有名字单位
    {
        this.name = name;
        MAXHP = 100;
        HP = MAXHP;
        ATK = 20;
        CRIT = 0.1f;//*10%暴击率
        critsEffect = 2.0f;
        fixArmorPen = 5;
        perArmorPen = 0.0f;
        armor = 10;
        physicalResistanc = 0.0f;
        id = 60001;
    }
    public String getName()
    {
        return name;
    }
    public int getMAXHP()
    {
        return MAXHP;
    }
    public int getHP()
    {
        return HP;
    }
    public int getATK()
    {
        return ATK;
    }
    public int getfixArmorPen()
    {
        return fixArmorPen;
    }
    public float getperArmorPen()
    {
        return perArmorPen;
    }
    public int getarmor()
    {
        return armor;
    }
    public float getCRIT()
    {
        return CRIT;
    }
    public float getcritsEffect()
    {
        return critsEffect;
    }
    public float getPhysicalResistanc()
    {
        return physicalResistanc;
    }
    public int getId()
    {
        return id;
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
                + "[id:" +id
                + ", name:" + name
                + ", 最大生命值:" + MAXHP
                + ", 物理攻击:" + ATK
                + ", 暴击率:" + CRIT * 100 + "%"
                + ", 暴击效果:" + critsEffect * 100 + "%"
                + ", 固定护甲穿透:" + fixArmorPen
                + ", 百分比护甲穿透:" + perArmorPen
                + ", 护甲值:" + armor
                + ", 物理抗性:" + physicalResistanc
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
