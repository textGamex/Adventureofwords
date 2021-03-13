package Game.Message;
/**
 *统计玩家信息
 *@author Millennium
 *@version 0.1
 *@Date 2021/3/13 22:42
*/
public class UserMessage
{
    //玩家击杀数
    private int sumKill;
    //总场数
    private int sumRound;
    //普通攻击次数
    private int sumAttack;
    //总伤害
    private int sumHarm;
    //胜利场数
    private int sumVictory;
    //游玩总时间 TODO:未实现
//    private int
    public int getSumKill()
    {
        return sumKill;
    }
    public int getSumRound()
    {
        return sumRound;
    }
    public int getSumAttack()
    {
        return sumAttack;
    }
    public int getSumHarm()
    {
        return sumHarm;
    }
    public int getSumVictory()
    {
        return sumVictory;
    }
}
