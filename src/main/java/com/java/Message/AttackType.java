package com.java.Message;

/**
 * ��������
 *
 * @author Millennium
 * @version 1.0.0
 * @see CombatTip
 * @see com.java.Message.CombatTip.CombatMessage
 * @since 15
*/
public enum AttackType
{
    COMMON_ATTACK("��ͨ����"),
    CRIT("����һ��");

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
