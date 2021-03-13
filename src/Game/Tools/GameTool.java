package Game.Tools;

import java.util.Random;

/**
 * 辅助类
 * @version 0.2
 * @author Millennium
 */
public final class GameTool
{
    public static void main(String[] args)
    {
        final int MAX = 10000000;
        int count = 0;
        for (int i = 0; i < MAX; i++)
        {
            if(randomRun(0.5f))
                count++;
        }
        System.out.println(count);
    }
    /**
     * 根据传入的概率随机返回真或假
     * @param runSize是返回真的概率
     * @return true或false
     */
    public static boolean randomRun (float runSize)
    {
        return runSize > Math.random();
    }
    /**
     *
     *@author Millennium
     *@Date 2021/3/13 11:45
    */
    public static int FloatingNumber(int number, int floatingRange)
    {
        var rand = new Random();
        int radnomNumber = rand.nextInt(floatingRange + 1);
    }
}
