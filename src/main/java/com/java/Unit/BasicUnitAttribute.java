package com.java.Unit;

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
//    private static int nextTextId = 0;//���Ե�λid TODO:δ���
//    private static int nextSpecialId = 0;//���ⵥλid
    private final String name;
    /**�������ֵ*/
    private int maxHp;
    /**����ֵ*/
    private int hp;
    /**������*/
    private int atk;
    /**������ */
    private float CRIT;
    /**����Ч��*/
    private float critsEffect;
    /*    ������    */

    /**������ */
    private float physicalResistanc;
    /**����*/
    //private float evade;
    /**ÿ�غ������ظ�*/
    private int lifeRegeneration;
    /**��λ�ȼ�*/
    private int grade;

    public static class Builder
    {
        private final String name;
        private int maxHp;
        private int hp;

        /**������*/
        private int atk           = 0;
        /**������ */
        private float critRate    = 0.0f;
        /**����Ч��*/
        private float critsEffect = 0.0f;

        /*    ������    */

        /**������ */
        private float physicalResistanc = 0.0f;
        /**����*/
        //private float evade;

        /**ÿ�غ������ظ�*/
        private int lifeRegeneration = 0;
        /**��λ�ȼ�*/
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
                              int lifeRegeneration, int id)//*������Ե�λ
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
    public BasicUnitAttribute()//*������Ե�λ
    {
        this("���Ե�λ");
    }
    public BasicUnitAttribute(String name)//*���������ֵ�λ
    {
        this.name = name;
        maxHp = 100;
        hp = maxHp;
        grade = 1;
        atk = 20;
        CRIT = 0.1f;//*10%������
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
//    public int directHP(int reducedHP)//*�����￹ֱ�ӿ�Ѫ�����ؿ۳���HP */
//    {
//        HP -= reducedHP;
//        return reducedHP;
//    }

//    public int subtractHP(int ATK, int fixArmorPen, float perArmorPen)//*���㻤�ײ���Ѫ
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
                + ", �ȼ�:" + grade
                + ", ������:" + atk
                + ", ������:" + CRIT * 100 + "%"
                + ", ����Ч��:" + critsEffect * 100 + "%"
                + ", ������:" + physicalResistanc
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
