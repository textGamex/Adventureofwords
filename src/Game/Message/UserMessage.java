package Game.Message;
/**
 *统计玩家信息
 *@author Millennium
 *@version 0.1
 *@Date 2021/3/13 22:42
*/
public final class UserMessage
{
    //玩家ID
    private final int id;
    //玩家击杀数
    private int totalKill;
    //总场数
    private int totalRound;
    //普通攻击次数
    private int totalAttack;
    //总伤害
    private int totalHarm;
    //胜利场数
    private int totalVictory;
    //游玩总时间 TODO:未实现
//    private int
    public UserMessage(int id)
    {
        this.id = id;
        totalKill = 0;
        totalRound = 0;
        totalAttack = 0;
        totalHarm = 0;
        totalVictory = 0;
    }
    public int getTotalKill()
    {
        return totalKill;
    }
    public int getTotalRound()
    {
        return totalRound;
    }
    public int getTotalAttack()
    {
        return totalAttack;
    }
    public int getTotalHarm()
    {
        return totalHarm;
    }
    public int getTotalVictory()
    {
        return totalVictory;
    }
    public int getId()
    {
        return id;
    }
    public void addTotalKill(int addNumber)
    {
        totalKill += addNumber;
    }
    public void addTotalRound(int addNumber)
    {
        totalRound += addNumber;
    }
    public String toString()
    {
        return getClass().getName() +
                "[Id:" + id +
                ", 玩家击杀数: " + totalKill +
                ", 总场数:" + totalRound +
                ", 普通攻击次数:" + totalAttack +
                ", 总伤害:" + totalHarm +
                ", 胜利场数:" + totalVictory +
                "]";
    }
}
