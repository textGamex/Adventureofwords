package com.java.tools;

import com.java.unit.BasicUnit;
import static java.util.Objects.requireNonNull;

/**
 * @author 千年
 * @version 1.0.1
*/
public final class AttributeCalculation
{
    public static void main(String[] args)
    {
//        System.out.println(victimEvade(1, -1));
//        System.out.println(attackerCritChance(1, 0));
    }

    /**
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
     *
     * @param attackerHit 攻击者的命中
     * @param victimEvade 被攻击者的闪避
     * @return 攻击者的攻击未命中的几率
     * @since 2021-5-15
     */
    public static double victimEvade(int attackerHit, int victimEvade)
    {
        //为了防止出现NaN错误
        if (attackerHit + victimEvade == 0)
        {
            if (attackerHit <= 0)
                return 1.0;
            if (victimEvade <= 0)
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
     * @param attackerCrit 攻击者的暴击
     * @param victimResistance 被攻击者的暴击抗性
     * @return 攻击者的攻击暴击的概率
     * @since 2021-5-16
     */
    public static double attackerCritChance(int attackerCrit, int victimResistance)
    {
        //为了防止出现NaN错误
        if (attackerCrit + victimResistance == 0)
        {
            if (victimResistance <= 0)
                return 1.0;
            if (attackerCrit <= 0)
                return 0.0;
        }
        return (double) attackerCrit / (attackerCrit + victimResistance);
    }

    /**
     *
     * @param attacker
     * @param victim
     * @throws NullPointerException 如果{@code attacker}或{@code victim}为null
     * @return
     */
    public static int attackerPhysicalDamage(BasicUnit attacker, BasicUnit victim)
    {
        return attackerPhysicalDamage(requireNonNull(attacker).getPhysicalAttack(),
                requireNonNull(victim).getArmor());
    }

    public static int attackerPhysicalDamage(int attackerPhysicalAttack, int victimArmor)
    {
        var attack = attackerPhysicalAttack;
        var armor = attackerPhysicalAttack;

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
}