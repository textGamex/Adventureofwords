package com.java.Message;

/**
 * ��������
 * @author Millennium
 * @version 1.0.0
*/
public enum AttackType
{
    COMMON_ATTACK("��ͨ����"),
    CRIT("����һ��");

    private final String typeName;

    private AttackType(String type)
    {
        this.typeName = type;
    }

    public String getTypeName()
    {
        return typeName;
    }
}
