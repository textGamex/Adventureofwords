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
    /**角色等级*/
    private int grade;
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
        grade = 1;
        money = 100;
        totalGameScore = 0;
        EXP = 0;
        upgradeNeedXP = 20;
        CreatingDateTime = LocalDateTime.now();
    }
    public RoleAttribute(String name) //*构造玩家角色
    {
        super(name);
        grade = 1;
        money = 100;
        totalGameScore = 0;
        EXP = 0;
        upgradeNeedXP = 20;
        CreatingDateTime = LocalDateTime.now();
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
    public LocalDateTime getCreatingDateTime()
    {
        return CreatingDateTime;
    }
    public int getEXP()
    {
        return EXP;
    }
    public int getUpgradeNeedXP()
    {
        return upgradeNeedXP;
    }
    public void setUpgradeNeedXP(int addUpgradeXP)
    {
        upgradeNeedXP += addUpgradeXP;
    }
    @Override
    public String toString()
    {
        return  super.toString() +
                "[角色等级:" + grade +
                ", 持有货币:" + money +
                ", 游戏得分:" + totalGameScore +
                ", 角色拥有经验:" + EXP +
                ", 升到下一级所需经验:" + upgradeNeedXP +
                ", 创建日期:" + CreatingDateTime +
                ']';
    }
}
