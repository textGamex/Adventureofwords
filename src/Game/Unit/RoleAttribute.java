package Game.Unit;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 玩家角色属性
 * @version 0.13
 * @author Millennium
 */
public class RoleAttribute extends BasicUnitAttribute
{
    /**持有货币*/
    private int money;
    /**拥有经验*/
    private int EXP;
    /**升到下一级所需经验*/
    private int upgradeNeedXP;
    /**游戏得分*/
    private int totalGameScore;
    /**角色创建日期*/
    private final LocalDateTime CreatingDateTime;

    public static void main(String[] args)
    {
        LocalDateTime time = LocalDateTime.now();
        LocalDate date = LocalDate.now();
          System.out.println(time);
          System.out.println(date);
    }

    public RoleAttribute() //*默认数值
    {
        super();
        money = 100;
        totalGameScore = 0;
        EXP = 0;
        upgradeNeedXP = 20;
        CreatingDateTime = LocalDateTime.now();
    }
    public RoleAttribute(String name) //*构造玩家角色
    {
        super(name);
        money = 100;
        totalGameScore = 0;
        EXP = 0;
        upgradeNeedXP = 20;
        CreatingDateTime = LocalDateTime.now();
    }

    public final int getMoney()
    {
        return money;
    }
    public final int getTotalGameScore()
    {
        return totalGameScore;
    }
    public final LocalDateTime getCreatingDateTime()
    {
        return CreatingDateTime;
    }
    public final int getEXP()
    {
        return EXP;
    }
    public final int getUpgradeNeedXP()
    {
        return upgradeNeedXP;
    }
    public final void setUpgradeNeedXP(int addUpgradeXP)
    {
        upgradeNeedXP += addUpgradeXP;
    }
    public final void setEXP(int gainEXP)
    {
        EXP += gainEXP;
    }
    public final void setMoney(int addMoney)
    {
        money += addMoney;
    }
    public final void setTotalGameScore(int addGameScore)
    {
        totalGameScore += addGameScore;
    }
    @Override
    public String toString()
    {
        return  super.toString() +
                "[持有货币:" + money +
                ", 游戏得分:" + totalGameScore +
                ", 角色拥有经验:" + EXP +
                ", 升到下一级所需经验:" + upgradeNeedXP +
                ", 创建日期:" + CreatingDateTime +
                ']';
    }
}
