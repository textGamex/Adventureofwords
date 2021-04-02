package Game.Unit;

import Game.CombatSystem.BuffModule.BuffMessage;
import Game.CombatSystem.BuffModule.BuffType;

import java.util.EnumMap;

public class BasicBuffModule
{
    public static void main(String[] args)
    {
        var buff = new BasicBuffModule();
        buff.addBuff(BuffType.POISON, new BuffMessage(1,1,true));
        System.out.println(buff.hasBuff(BuffType.POISON));

    }
    private final EnumMap<BuffType, BuffMessage> hasBuff = new EnumMap<BuffType, BuffMessage>(BuffType.class);
    public final void addBuff(BuffType aBuffType, BuffMessage aBuffMessage)
    {
        hasBuff.put(aBuffType, aBuffMessage);
    }
    public final boolean hasBuff(BuffType aBuffType)
    {
        return hasBuff.containsKey(aBuffType);
    }
    public final boolean isEmptyBuff()
    {
        return hasBuff.isEmpty();
    }
    public final int buffSize()
    {
        return hasBuff.size();
    }
}
