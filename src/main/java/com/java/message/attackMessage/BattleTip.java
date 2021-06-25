package com.java.message.attackMessage;

import com.java.battleSystem.BattleSystem;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.Objects.requireNonNull;
/**
 * 战斗提示信息.
 *
 * @author 留恋千年
 * @version 1.0.2
 * @since 16
*/
public final class BattleTip
{
    /**
     * @author 留恋千年
     */
    public enum CauseOfNoInjury
    {
        MISS("未命中");
        private final String typeName;

        CauseOfNoInjury(String type)
        {
            this.typeName = type;
        }

        public String getTypeName()
        {
            return typeName;
        }
    }

    private BattleTip()
    {
        throw new AssertionError();
    }

    public static String returnAttackMessage(final AttackMessage message)
    {
        return returnAttackMessage(message, Locale.getDefault());
    }

    /**
     * 用于输出战场信息.
     *
     * @see AttackMessage
     * @throws NullPointerException 如果{@code message}或{@code locale}为null
     * @throws IllegalArgumentException 如果{@code message.getHarm()}方法的返回值小于0
     * @param message 战场信息包
     * @param locale 环境语言
     */
    public static String returnAttackMessage(final AttackMessage message, Locale locale)
    {
        requireNonNull(message);
        final var language = ResourceBundle.getBundle("language/BattleTip_InformationDisplayed",
                requireNonNull(locale));

        //不直接写成 message.harm是因为以后可能要做分离处理
        var victimName = message.getVictimName();
        var attackerName = message.getAttackerName();
        var harm = message.getHarm();

        if (message.getHarm() == 0)
        {
            return MessageFormat.format(language.getString("attackButNoHarmWasDone"), attackerName,
                    message.getHarmTypeName(locale), victimName);
        }
        else if (message.getHarm() > 0)
        {
            return MessageFormat.format(language.getString("successfulAttack"), attackerName,
                    message.getHarmTypeName(locale), victimName, message.getHarm(), victimName, message.getVictimSurplusHp());
        }
        else
        {
            throw new IllegalArgumentException("异常伤害:" + harm);
        }
    }

    /**
     * 战场信息包, 用来辅助{@code BattleTip}类, 以便实现战斗信息提示.
     *
     * <p>一个保存战斗数据的辅助类, 用于传输以下信息</p>
     * <li>攻击者名称</li>
     * <li>攻击者造成的伤害量</li>
     * <li>攻击类型</li>
     * <li>被攻击者名称</li>
     * <li>被攻击者剩余血量</li>
     *
     * @version 1.0.1
     * @since 16
     * @author 留恋千年
     * @see BattleSystem
     * @see AttackMessage
     */
    public static final class AttackMessage
    {
        private final String attackerName;
        private int harm;
        private AttackType attackType;

        private final String victimName;
        private final int victimSurplusHp;

        /**
         * @param attackerName 攻击者的名字
         * @param victimName 被攻击者的名字
         * @param harm 攻击者造成的伤害
         * @param attackType 伤害类型
         * @param victimSurplusHp 被攻击后剩余血量
         * @author 留恋千年
         * @throws NullPointerException 如果{@code attackerName}或{@code victimName}为null
         * @since 16
         */
        public AttackMessage(String attackerName, int harm, AttackType attackType, String victimName, int victimSurplusHp)
        {
            this.attackerName = requireNonNull(attackerName);
            this.victimName = requireNonNull(victimName);
            this.harm = harm;
            this.attackType = attackType;
            this.victimSurplusHp = victimSurplusHp;
        }

        public String getAttackerName()
        {
            return attackerName;
        }

        public String getVictimName()
        {
            return victimName;
        }

        public int getHarm()
        {
            return harm;
        }

        public AttackType getAttackType()
        {
            return attackType;
        }

        public String getHarmTypeName(final Locale locale)
        {
            return attackType.getTypeName(requireNonNull(locale));
        }

        public int getVictimSurplusHp()
        {
            return victimSurplusHp;
        }

        public void setAttackType(final AttackType attackType)
        {
            this.attackType = attackType;
        }

        public void setHarm(final int harm)
        {
            this.harm = harm;
        }
    }

    public static class NoHarmWasCaused
    {
        public static void normalAttackMessage(CauseOfNoInjury cause)
        {
            switch (cause)
            {
                case MISS:
                    System.out.println();
                    break;
                default: throw new NullPointerException();
            }
        }
    }
}
