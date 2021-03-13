package Game.Unit;
/**
 * 敌对单位属性
 * @version 0.11
 * @author Millennium
 */
public class EnemyAttribute extends BasicUnitAttribute
{
    /**所值分数 */
    private int value;
    /**所值经验 */
    private int xp;
    /**所值硬币 */
    private int coin;
    public EnemyAttribute() //*默认数值
    {
        super();
        value = 10;
        xp = 10;
        coin = 10;
    }
    public int getValue()
    {
        return value;
    }
    public int getxp()
    {
        return xp;
    }
    public int getCoin()
    {
        return coin;
    }
}
