package com.java.message.attackMessage;

/**
 * ��������
 *
 * @author Millennium
 * @version 1.0.0
 * @see BattleTip
 * @see BattleTip.AttackMessage
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
