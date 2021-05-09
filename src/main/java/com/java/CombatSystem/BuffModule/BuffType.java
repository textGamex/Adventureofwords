package com.java.CombatSystem.BuffModule;

import java.io.Serializable;

/**
 * ���ڸ���ʵ��{@link BuffModule}���ö����.
 *
 * <p>����ʵ�ֵĸ���Ч��</p>
 * <em>
 *     <li>��Ѫ</li>
 *     <li>�ж�</li>
 *     <li>����</li>
 *     <li>����</li>
 *     <li>����</li>
 *     <li>����</li>
 *     <li>��Ĭ</li>
 * </em>
 *
 * <p>����ʵ�ֵ�����Ч��</p>
 * <em>
 *     <li>�ָ�</li>
 *     <li>����</li>
 *     <li>����͸</li>
 *     <li>������͸</li>
 * </em>
 * @author ǧ��
 * @version 1.1.0
 * @since 15
 * @see BuffModule
 */
public enum BuffType implements Serializable
{
    BLEED("��Ѫ"),
    POISON("�ж�"),
    DEBILITY("����"),
    DELICATE("����"),
    SLOW_DOWN("����"),
    SILENT("��Ĭ"),
    RECOVER("�ָ�"),
    RELIEVE("����"),
    PHYSICS_PIERCE_THROUGH("����͸"),
    MAGIC_PIERCE_THROUGH("������͸");

    private final String type;
    BuffType(String type)
    {
        this.type = type;
    }

    /**
     * ������buff���͵�����
     *
     * @return ���buff���͵�����
     */
    public String getType()
    {
        return type;
    }
}
