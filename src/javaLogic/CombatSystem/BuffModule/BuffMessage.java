package javaLogic.CombatSystem.BuffModule;

public final class BuffMessage
{
    /**持续回合*/
    private int time;
    /**效果层数*/
    private int layers;
    /**是否是Debuff*/
    private final boolean Debuff;
    /**是否是被动*/
    private final boolean Passive;

    public BuffMessage(int time, int layers, boolean Debuff, boolean Passive)
    {
        this.time = time;
        this.layers = layers;
        this.Debuff = Debuff;
        this.Passive = Passive;
    }
    public BuffMessage(int time, int layers, boolean isDebuff)
    {
        this(time, layers, isDebuff, false);
    }

    public int getTime()
    {
        return time;
    }
    public int getLayers()
    {
        return layers;
    }
    public boolean isDebuff()
    {
        return Debuff;
    }
    public boolean isPassive()
    {
        return Passive;
    }
    public void setTime(int time)
    {
        this.time = time;
    }
    public void setLayers(int layers)
    {
        this.layers = layers;
    }

    @Override
    public String toString()
    {
        return "BuffMessage[" +
                "持续回合:" + time +
                ", 效果层数:" + layers +
                ", Debuff:" + Debuff +
                ", 特性:" + Passive +
                ']';
    }
}
