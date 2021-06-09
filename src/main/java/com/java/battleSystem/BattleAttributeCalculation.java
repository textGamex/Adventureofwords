package com.java.battleSystem;

import com.java.unit.BasicUnit;
import org.slf4j.LoggerFactory;

import static java.util.Objects.requireNonNull;

/**
 * 用于计算与战斗相关的数值.
 *
 * @author 留恋千年
 * @version 1.0.2
 * @since 20221-5-15
*/
public final class BattleAttributeCalculation
{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BattleAttributeCalculation.class);
    public static void main(String[] args)
    {
        System.out.println(victimEffectiveHp(100, 1.9, 0.98));
    }

    private BattleAttributeCalculation()
    {
        throw new AssertionError();
    }

    /**
     * 计算攻击者的攻击命中的几率.
     *
     * @param attacker 攻击者
     * @param victim 被攻击者
     * @throws NullPointerException 如果{@code attacker}或{@code victim}为null
     * @return 攻击者的攻击命中的几率
     * @see BasicUnit
     * @since 2021-5-15
     */
    public static double attackHitRate(final BasicUnit attacker, final BasicUnit victim)
    {
        requireNonNull(attacker);
        requireNonNull(victim);

        return attackHitRate(attacker.attack().getHit(), victim.defense().getEvade());
    }

    /**
     * 根据给定的数值计算攻击者的攻击命中的几率.
     *
     * @param attackerHit 攻击者的命中
     * @param victimEvade 被攻击者的闪避
     * @return 攻击者的攻击命中的几率
     * @since 2021-5-15
     */
    public static double attackHitRate(final int attackerHit, final int victimEvade)
    {
        if (attackerHit <= 0)
        {
            LOGGER.debug("attackerHit小于等于0, attackerHit:{}", attackerHit);
            return 1.0;
        }
        if (victimEvade <= 0)
        {
            LOGGER.debug("victimEvade小于等于0, attackHitRate:{}", victimEvade);
            return 0.0;
        }
        return (double) attackerHit / (attackerHit + victimEvade);
    }

    /**
     * 返回攻击者对被攻击者的攻击暴击的概率.
     *
     * @param attacker 攻击者
     * @param victim 被攻击者
     * @return 攻击者的攻击暴击的概率
     * @throws NullPointerException 如果{@code attacker}或{@code victim}为null
     * @since 2021-5-16
     */
    public static double attackerCritChance(final BasicUnit attacker, final BasicUnit victim)
    {
        return attackerCritChance(requireNonNull(attacker).attack().getCrit(),
                requireNonNull(victim).defense().getCritResistance());
    }

    /**
     * 计算攻击者的攻击暴击的概率.
     *
     * @param attackerCrit 攻击者的暴击
     * @param victimResistance 被攻击者的暴击抗性
     * @return 攻击者的攻击暴击的概率
     * @since 2021-5-16
     */
    public static double attackerCritChance(final int attackerCrit, final int victimResistance)
    {
        if (attackerCrit <= 0)
        {
            return 0.0;
        }
        if (victimResistance <= 0)
        {
            return 1.0;
        }
        return (double) attackerCrit / (attackerCrit + victimResistance);
    }

    /**
     * 计算攻击者能对被攻击者打出的伤害.
     *
     * @param attacker 攻击者
     * @param victim 被攻击者
     * @throws NullPointerException 如果{@code attacker}或{@code victim}为null
     * @return 攻击者的伤害
     */
    public static double attackerPhysicalDamage(final BasicUnit attacker, final BasicUnit victim)
    {
        return attackerPhysicalDamage(requireNonNull(attacker).attack().getPhysicalAttack(),
                requireNonNull(victim).defense().getArmor());
    }

    /**
     * 用给定的数值计算攻击者能对被攻击者打出的伤害.
     *
     * @param attackerPhysicalAttack 攻击者的物理攻击
     * @param victimArmor 被攻击者的护甲值
     * @throws NullPointerException 如果{@code attacker}或{@code victim}为null
     * @return 攻击者的伤害
     */
    public static double attackerPhysicalDamage(double attackerPhysicalAttack, double victimArmor)
    {
        var attack = attackerPhysicalAttack;
        var armor = victimArmor;

        //为了防止出现NaN错误
        if (attackerPhysicalAttack + victimArmor == 0)
        {
            if (victimArmor <= 0)
            {
                attack = ++attackerPhysicalAttack;
            }
            if (attackerPhysicalAttack <= 0)
            {
                armor = ++victimArmor;
            }
        }
        return attack * attack / (attack + armor);
    }

    /**
     * 计算每回合伤害.
     *
     * @param attacker 攻击者
     * @param victim 被攻击者
     * @throws NullPointerException 如果{@code attacker}或{@code victim}为null
     */
    public double DPR(final BasicUnit attacker, final BasicUnit victim)
    {
        var damage = attackerPhysicalDamage(attacker, victim);
        var critChance = attackerCritChance(attacker, victim);
        return damage * (1 - critChance) + damage * critChance * attacker.attack().getCritsEffect();
    }

    /**
     * 计算被攻击者的有效生命值, 如果被攻击者的闪避率或伤害减免大于等于100%, 返回{@code Integer.MAX_VALUE}.
     *
     * @param attacker 攻击者
     * @param victim 被攻击者
     * @return 返回被攻击者的有效生命值
     * @throws NullPointerException 如果{@code attacker}或{@code victim}为null
     */
    public static double victimEffectiveHp(final BasicUnit attacker, final BasicUnit victim)
    {
        var physicalAttack = requireNonNull(attacker).attack().getPhysicalAttack();
        if (physicalAttack == 0)
        {
            ++physicalAttack;
        }

        var damage = attackerPhysicalDamage(attacker, victim);
        var damageReduction = (physicalAttack - damage) / physicalAttack;
        var evadeChance = attackHitRate(attacker, victim);
        if (evadeChance >= 1.0 || damageReduction >= 1.0)
        {
            return Integer.MAX_VALUE;
        }
        return victim.defense().getMaxHp() / (1.0 - damageReduction) / (1.0 - evadeChance);
    }

    /**
     * 计算被攻击者有效HP.
     *
     * @param victimHp 被攻击者的HP
     * @param damageReduction 伤害减免率
     * @param evadeChance 闪避概率
     * @return 返回被攻击者有效HP
     */
    public static double victimEffectiveHp(final int victimHp, final double damageReduction, final double evadeChance)
    {
        if (evadeChance >= 1.0 || damageReduction >= 1.0)
        {
            return Integer.MAX_VALUE;
        }
        return victimHp / (1.0 - damageReduction) / (1.0 - evadeChance);
    }

    /**
     *  计算攻击者对被攻击者的暴击伤害.
     *
     * @param attacker 攻击者
     * @param victim 被攻击者
     * @return 攻击者对被攻击者的暴击伤害
     * @throws NullPointerException 如果{@code attacker}或{@code victim}为null
     */
    public static int criticalDamage(final BasicUnit attacker, final BasicUnit victim)
    {
       final var hurt = attackerPhysicalDamage(requireNonNull(attacker), requireNonNull(victim));
       return criticalDamage(hurt, attacker.attack().getCritsEffect());
    }

    /**
     *  计算攻击者对被攻击者的暴击伤害.
     *
     * @param hurt 攻击者对被攻击者可以造成的的伤害
     * @param critsEffect 攻击者的暴击效果
     * @return 攻击者对被攻击者的暴击伤害
     * @throws NullPointerException 如果{@code attacker}或{@code victim}为null
     */
    public static int criticalDamage(final double hurt, final double critsEffect)
    {
        return Math.round((float) (hurt * critsEffect));
    }
}