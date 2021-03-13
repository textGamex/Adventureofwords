package Game.Unit;
/**
 * 玩家角色属性
 * @version 0.13
 * @author Millennium
 */
public class RoleAttribute extends BasicUnitAttribute
{
    /**角色等级*/
    private int grade;
    /**持有货币*/
    private int money;
    /**游戏得分*/
    private int totalGameScore;

    public RoleAttribute() //*默认数值
    {
        super();
        grade = 1;
        money = 100;
        totalGameScore = 0;
    }
    public RoleAttribute(String name) //*构造玩家角色
    {
        super(name);
        grade = 1;
        money = 100;
        totalGameScore = 0;
    }
    public int getGrade()
    {
        return grade;
    }
    public int getMoney()
    {
        return money;
    }
    public int getTotalGameScore()
    {
        return totalGameScore;
    }

    @Override
    public String toString()
    {
        return  super.toString() +
                "[角色等级:" + grade +
                ", 持有货币:" + money +
                ", 游戏得分:" + totalGameScore +
                ']';
    }
}
