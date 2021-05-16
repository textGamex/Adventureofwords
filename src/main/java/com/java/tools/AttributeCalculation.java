package com.java.tools;

import com.java.unit.BasicUnit;
import static java.util.Objects.requireNonNull;

/**
 * @author ǧ��
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
     *
     * @param attackerHit �����ߵ�����
     * @param victimEvade �������ߵ�����
     * @return �����ߵĹ���δ���еļ���
     * @since 2021-5-15
     */
    public static double victimEvade(int attackerHit, int victimEvade)
    {
        //Ϊ�˷�ֹ����NaN����
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
     * @param attackerCrit �����ߵı���
     * @param victimResistance �������ߵı�������
     * @return �����ߵĹ��������ĸ���
     * @since 2021-5-16
     */
    public static double attackerCritChance(int attackerCrit, int victimResistance)
    {
        //Ϊ�˷�ֹ����NaN����
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
     * @throws NullPointerException ���{@code attacker}��{@code victim}Ϊnull
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
}