package javaLogic.Unit;

/**
 * ������λ����
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
    private static int nextTextId = 0;//���Ե�λid TODO:δ���
    private static int nextSpecialId = 0;//���ⵥλid
    private final String name;
    /**�������ֵ*/
    private int MAXHP;
    /**����ֵ*/
    private int HP;
    /**������*/
    private int ATK;
    /**������ */
    private float CRIT;
    /**����Ч��*/
    private float critsEffect;
    /**�̶����״�͸*/
    private int fixArmorPen;
    /**�ٷֱȻ��״�͸*/
    private float perArmorPen;

    /*    ������    */

    /**����ֵ*/
    private int armor;
    /**������ */
    private float physicalResistanc;
    /**����*/
    //private float evade;
    /**ÿ�غ������ظ�*/
    private int lifeRegeneration;
    /**��λ�ȼ�*/
    private int grade;


    public BasicUnitAttribute(String name,int grade, int MAXHP, int ATK, float CRIT, float critsEffect,
                              int fixArmorPen, float perArmorPen, int armor, float physicalResistanc,
                              int lifeRegeneration, int id)//*������Ե�λ
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
    public BasicUnitAttribute()//*������Ե�λ
    {
        this("���Ե�λ");
    }
    public BasicUnitAttribute(String name)//*���������ֵ�λ
    {
        this.name = name;
        MAXHP = 100;
        HP = MAXHP;
        grade = 1;
        ATK = 20;
        CRIT = 0.1f;//*10%������
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
    public int directHP(int reducedHP)//*�����￹ֱ�ӿ�Ѫ�����ؿ۳���HP */
    {
        HP -= reducedHP;
        return reducedHP;
    }

    public int subtractHP(int ATK, int fixArmorPen, float perArmorPen)//*���㻤�ײ���Ѫ
    {
        int sumArmorPen = (int) ((this.armor * perArmorPen) + fixArmorPen);//*���㻤�״�͸�ܺ�
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
                + ", �������ֵ:" + MAXHP
                + ", �ȼ�:" + grade
                + ", ������:" + ATK
                + ", ������:" + CRIT * 100 + "%"
                + ", ����Ч��:" + critsEffect * 100 + "%"
                + ", �̶����״�͸:" + fixArmorPen
                + ", �ٷֱȻ��״�͸:" + perArmorPen
                + ", ����ֵ:" + armor
                + ", ������:" + physicalResistanc
                + ", ÿ�غ������ظ�:" +lifeRegeneration
                + "]";
    }
    /*��¡ʵ��*/
    public BasicUnitAttribute clone() throws CloneNotSupportedException
    {
        return (BasicUnitAttribute) super.clone();
    }
    /*sort�ӿ�ʵ��*/
    public int compareTo(BasicUnitAttribute other)
    {
        return Integer.compare(id, other.getId());
    }
}
