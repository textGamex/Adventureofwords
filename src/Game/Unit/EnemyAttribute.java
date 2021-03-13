package Game.Unit;
/**
 * 敌对单位属性
 * @version 0.11
 * @author Millennium
 */
public class EnemyAttribute extends BasicUnitAttribute
{
    /**所值分数 */
    private final int value;
    /**所值经验 */
    private final int xp;
    /**所值硬币 */
    private final int coin;
    public EnemyAttribute() //*默认数值
    {
        super();
        value = 10;
        xp = 10;
        coin = 10;
    }
    public EnemyAttribute(String name,int grade, int MAXHP, int ATK, float CRIT, float critsEffect,
                          int fixArmorPen, float perArmorPen, int armor, float physicalResistanc,
                          int lifeRegeneration, int id, int value, int xp, int coin)//*构造测试单位
    {
        super(name, grade, MAXHP, ATK, CRIT, critsEffect, fixArmorPen, perArmorPen, armor, physicalResistanc
        , lifeRegeneration, id);
        this.value = value;
        this.xp = xp;
        this.coin = coin;
    }
    public final int getValue()
    {
        return value;
    }
    public final int getXp()
    {
        return xp;
    }
    public final int getCoin()
    {
        return coin;
    }

    @Override
    public String toString()
    {
        return  super.toString()
                + "[所值分数:" + value +
                ", 所值经验:" + xp +
                ", 所值硬币:" + coin +
                ']';
    }
}
