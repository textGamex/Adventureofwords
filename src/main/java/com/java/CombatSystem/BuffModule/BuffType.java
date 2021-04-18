package com.java.CombatSystem.BuffModule;

import java.io.Serializable;

public enum BuffType implements Serializable
{
    /**Á÷Ñª*/
    BLEED("Á÷Ñª"),
    /**ÖÐ¶¾*/
    POISON("ÖÐ¶¾"),
    /**ÐéÈõ*/
    DEBILITY("ÐéÈõ");

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
