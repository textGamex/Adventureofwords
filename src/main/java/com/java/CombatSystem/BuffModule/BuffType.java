package com.java.CombatSystem.BuffModule;

import java.io.Serializable;

public enum BuffType implements Serializable
{
    /**��Ѫ*/
    BLEED("��Ѫ"),
    /**�ж�*/
    POISON("�ж�"),
    /**����*/
    DEBILITY("����");

    private final String type;
    BuffType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
}
