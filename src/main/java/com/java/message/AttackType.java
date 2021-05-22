package com.java.message;

/**
 * ��������
 *
 * @author Millennium
 * @version 1.0.0
 * @see CombatTip
 * @see com.java.message.CombatTip.CombatMessage
 * @since 15
*/
public enum AttackType
{
    COMMON_ATTACK("��ͨ����"),
    CRIT("����һ��"),
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
