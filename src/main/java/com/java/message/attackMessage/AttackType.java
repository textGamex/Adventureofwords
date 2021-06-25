package com.java.message.attackMessage;

import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.Objects.requireNonNull;

/**
 * 攻击类型
 *
 * @author Millennium
 * @version 1.0.0
 * @see BattleTip
 * @see BattleTip.AttackMessage
 * @since 15
*/
public enum AttackType
{
    COMMON_ATTACK("commonAttack"),
    CRIT("criticalHit");

    private final String typeName;

    AttackType(String typeName)
    {
        this.typeName = typeName;
    }

    public String getTypeName(final Locale locale)
    {
        final var language = ResourceBundle.getBundle("language/AttackType_TypeName",
                requireNonNull(locale));
        return language.getString(typeName);
    }
}
