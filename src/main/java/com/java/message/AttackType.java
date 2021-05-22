package com.java.message;

/**
 * 攻击类型
 *
 * @author Millennium
 * @version 1.0.0
 * @see CombatTip
 * @see com.java.message.CombatTip.CombatMessage
 * @since 15
*/
public enum AttackType
{
    COMMON_ATTACK("普通攻击"),
    CRIT("会心一击"),
    MISS("MISS");

    private final String typeName;

    AttackType(String type)
    {
        this.typeName = type;
    }

    public String getTypeName()
    {
        return typeName;
    }
}
