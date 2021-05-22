package com.java.tools;

import com.java.unit.BasicUnit;
import org.slf4j.LoggerFactory;

import static java.util.Objects.requireNonNull;

/**
 * @author ǧ��
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
     * ���㹥���ߵĹ���δ���еļ���.
     *
     * @param attacker ������
     * @param victim ��������
     * @throws NullPointerException ���{@code attacker}��{@code victim}Ϊnull
     * @return �����ߵĹ���δ���еļ���
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
     * ���ݸ�������ֵ���㹥���ߵĹ���δ���еļ���.
     *
     * @param attackerHit �����ߵ�����
     * @param victimEvade �������ߵ�����
     * @return �����ߵĹ���δ���еļ���
     * @since 2021-5-15
     */
    public static double victimEvade(int attackerHit, int victimEvade)
    {
        if (attackerHit <= 0)
        {
            LOGGER.debug("attackerHitС�ڵ���0:{}", attackerHit);
            return 1.0;
        }
        if (victimEvade <= 0)
        {
            LOGGER.debug("victimEvadeС�ڵ���0:{}", victimEvade);
            return 0.0;
        }
        return (double) attackerHit / (attackerHit + victimEvade);
    }

    /**
     * @param attacker ������
     * @param victim ��������
     * @return �����ߵĹ��������ĸ���
     * @throws NullPointerException ���{@code attacker}��{@code victim}Ϊnull
     * @since 2021-5-16
     */
    public static double attackerCritChance(BasicUnit attacker, BasicUnit victim)
    {
        return attackerCritChance(requireNonNull(attacker).getCrit(), requireNonNull(victim).getCritResistance());
    }

    /**
     * ���㹥���ߵĹ��������ĸ���.
     * @param attackerCrit �����ߵı���
     * @param victimResistance �������ߵı�������
     * @return �����ߵĹ��������ĸ���
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
     * ���㹥�����ܶԱ������ߴ�����˺�.
     *
     * @param attacker ������
     * @param victim ��������
     * @throws NullPointerException ���{@code attacker}��{@code victim}Ϊnull
     * @return �����ߵ��˺�
     */
    public static double attackerPhysicalDamage(BasicUnit attacker, BasicUnit victim)
    {
        return attackerPhysicalDamage(requireNonNull(attacker).getPhysicalAttack(),
                requireNonNull(victim).getArmor());
    }

    /**
     * �ø�������ֵ���㹥�����ܶԱ������ߴ�����˺�.
     *
     * @param attackerPhysicalAttack �����ߵ�������
     * @param victimArmor �������ߵĻ���ֵ
     * @throws NullPointerException ���{@code attacker}��{@code victim}Ϊnull
     * @return �����ߵ��˺�
     */
    public static double attackerPhysicalDamage(double attackerPhysicalAttack, double victimArmor)
    {
        var attack = attackerPhysicalAttack;
        var armor = victimArmor;

        //Ϊ�˷�ֹ����NaN����
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
     * ����ÿ�غ��˺�.
     *
     * @param attacker ������
     * @param victim ��������
     * @throws NullPointerException ���{@code attacker}��{@code victim}Ϊnull
     */
    public double DPR(BasicUnit attacker, BasicUnit victim)
    {
        var damage = attackerPhysicalDamage(attacker, victim);
        var critChance = attackerCritChance(attacker, victim);
        return damage * (1 - critChance) + damage * critChance * attacker.getCritsEffect();
    }

    /**
     * ���㱻�����ߵ���Ч����ֵ, ����������ߵ������ʻ��˺�������ڵ���100%, ����{@code Integer.MAX_VALUE}.
     *
     * @param attacker ������
     * @param victim ��������
     * @return ���ر������ߵ���Ч����ֵ
     * @throws NullPointerException ���{@code attacker}��{@code victim}Ϊnull
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