package Game.Tools;
/**
 * 辅助类
 * @version 0.2
 * @author Millennium
 */
public class GameTool
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
}
