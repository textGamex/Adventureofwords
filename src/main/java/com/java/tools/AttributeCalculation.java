package com.java.tools;

import com.java.unit.BasicUnit;
import org.slf4j.LoggerFactory;

import static java.util.Objects.requireNonNull;

/**
 * @author 千年
 * @version 1.0.2
 * @since 20221-5-15
*/
public final class AttributeCalculation
{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AttributeCalculation.class);
    public static void main(String[] args)
    {
        System.out.println(victimEffectiveHp(100, 1.9, 0.98));
    }

    /**
     * 计算攻击者的攻击未命中的几率.
     *
     * @param attacker 攻击者
     * @param victim 被攻击者
     * @throws NullPointerException 如果{@code attacker}或{@code victim}为null
     * @return 攻击者的攻击未命中的几率
     * @see BasicUnit
     * @since 2021-5-15
     */
    public static double victimEvade(BasicUnit attacker, BasicUnit victim)
    {
        requireNonNull(attacker);
        requireNonNull(victim);

        return victimEvade(attacker.getHit(), attacker.getEvade());
    }

    /**
     * 根据给定的数值计算攻击者的攻击未命中的几率.
     *
     * @param attackerHit 攻击者的命中
     * @param victimEvade 被攻击者的闪避
     * @return 攻击者的攻击未命中的几率
     * @since 2021-5-15
     */
    public static double victimEvade(int attackerHit, int victimEvade)
    {
        if (attackerHit <= 0)
        {
            LOGGER.debug("attackerHit小于等于0:{}", attackerHit);
            return 1.0;
        }
        if (victimEvade <= 0)
        {
            LOGGER.debug("victimEvade小于等于0:{}", victimEvade);
            return 0.0;
        }
        return (double) attackerHit / (attackerHit + victimEvade);
    }

    /**
     * @param attacker 攻击者
     * @param victim 被攻击者
     * @return 攻击者的攻击暴击的概率
     * @throws NullPointerException 如果{@code attacker}或{@code victim}为null
     * @since 2021-5-16
     */
    public static double attackerCritChance(BasicUnit attacker, BasicUnit victim)
    {
        return attackerCritChance(requireNonNull(attacker).getCrit(), requireNonNull(victim).getCritResistance());
    }

    /**
     * 计算攻击者的攻击暴击的概率.
     * @param attackerCrit 攻击者的暴击
     * @param victimResistance 被攻击者的暴击抗性
     * @return 攻击者的攻击暴击的概率
     * @since 2021-5-16
     */
    public static double attackerCritChance(int attackerCrit, int victimResistance)
    {
        if (attackerCrit <= 0)
            return 0.0;
        if (victimResistance <= 0)
            return 1.0;
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
    public static double attackerPhysicalDamage(BasicUnit attacker, BasicUnit victim)
    {
        return attackerPhysicalDamage(requireNonNull(attacker).getPhysicalAttack(),
                requireNonNull(victim).getArmor());
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
                attack = ++attackerPhysicalAttack;
            if (attackerPhysicalAttack <= 0)
                armor = ++victimArmor;
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
    public double DPR(BasicUnit attacker, BasicUnit victim)
    {
        var damage = attackerPhysicalDamage(attacker, victim);
        var critChance = attackerCritChance(attacker, victim);
        return damage * (1 - critChance) + damage * critChance * attacker.getCritsEffect();
    }

    /**
     * 计算被攻击者的有效生命值, 如果被攻击者的闪避率或伤害减免大于等于100%, 返回{@code Integer.MAX_VALUE}.
     *
     * @param attacker 攻击者
     * @param victim 被攻击者
     * @return 返回被攻击者的有效生命值
     * @throws NullPointerException 如果{@code attacker}或{@code victim}为null
     */
    public static double victimEffectiveHp(BasicUnit attacker, BasicUnit victim)
    {
        var physicalAttack = requireNonNull(attacker).getPhysicalAttack();
        if (physicalAttack == 0)
            ++physicalAttack;

        var damage = attackerPhysicalDamage(attacker, victim);
        var damageReduction = (physicalAttack - damage) / physicalAttack;
        var evadeChance = victimEvade(attacker, victim);
        if (evadeChance >= 1.0 || damageReduction >= 1.0)
            return Integer.MAX_VALUE;
        return victim.getMaxHp() / (1.0 - damageReduction) / (1.0 - evadeChance);
    }

    /**
     *
     * @param victimHp
     * @param damageReduction
     * @param evadeChance
     * @return
     */
    public static double victimEffectiveHp(int victimHp, double damageReduction, double evadeChance)
    {
        if (evadeChance >= 1.0 || damageReduction >= 1.0)
            return Integer.MAX_VALUE;
        return victimHp / (1.0 - damageReduction) / (1.0 - evadeChance);
    }
}