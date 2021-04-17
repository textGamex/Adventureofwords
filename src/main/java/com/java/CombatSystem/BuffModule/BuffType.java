package com.java.CombatSystem.BuffModule;

public enum BuffType
{
    /**流血*/
    BLEED("流血"),
    /**中毒*/
    POISON("中毒"),
    /**虚弱*/
    DEBILITY("虚弱");

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
