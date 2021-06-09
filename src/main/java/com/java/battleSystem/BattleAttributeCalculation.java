package com.java.battleSystem;

import com.java.unit.BasicUnit;
import org.slf4j.LoggerFactory;

import static java.util.Objects.requireNonNull;

/**
 * ���ڼ�����ս����ص���ֵ.
 *
 * @author ����ǧ��
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
     * ���㹥���ߵĹ������еļ���.
     *
     * @param attacker ������
     * @param victim ��������
     * @throws NullPointerException ���{@code attacker}��{@code victim}Ϊnull
     * @return �����ߵĹ������еļ���
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
     * ���ݸ�������ֵ���㹥���ߵĹ������еļ���.
     *
     * @param attackerHit �����ߵ�����
     * @param victimEvade �������ߵ�����
     * @return �����ߵĹ������еļ���
     * @since 2021-5-15
     */
    public static double attackHitRate(final int attackerHit, final int victimEvade)
    {
        if (attackerHit <= 0)
        {
            LOGGER.debug("attackerHitС�ڵ���0, attackerHit:{}", attackerHit);
            return 1.0;
        }
        if (victimEvade <= 0)
        {
            LOGGER.debug("victimEvadeС�ڵ���0, attackHitRate:{}", victimEvade);
            return 0.0;
        }
        return (double) attackerHit / (attackerHit + victimEvade);
    }

    /**
     * ���ع����߶Ա������ߵĹ��������ĸ���.
     *
     * @param attacker ������
     * @param victim ��������
     * @return �����ߵĹ��������ĸ���
     * @throws NullPointerException ���{@code attacker}��{@code victim}Ϊnull
     * @since 2021-5-16
     */
    public static double attackerCritChance(final BasicUnit attacker, final BasicUnit victim)
    {
        return attackerCritChance(requireNonNull(attacker).attack().getCrit(),
                requireNonNull(victim).defense().getCritResistance());
    }

    /**
     * ���㹥���ߵĹ��������ĸ���.
     *
     * @param attackerCrit �����ߵı���
     * @param victimResistance �������ߵı�������
     * @return �����ߵĹ��������ĸ���
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
     * ���㹥�����ܶԱ������ߴ�����˺�.
     *
     * @param attacker ������
     * @param victim ��������
     * @throws NullPointerException ���{@code attacker}��{@code victim}Ϊnull
     * @return �����ߵ��˺�
     */
    public static double attackerPhysicalDamage(final BasicUnit attacker, final BasicUnit victim)
    {
        return attackerPhysicalDamage(requireNonNull(attacker).attack().getPhysicalAttack(),
                requireNonNull(victim).defense().getArmor());
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
     * ����ÿ�غ��˺�.
     *
     * @param attacker ������
     * @param victim ��������
     * @throws NullPointerException ���{@code attacker}��{@code victim}Ϊnull
     */
    public double DPR(final BasicUnit attacker, final BasicUnit victim)
    {
        var damage = attackerPhysicalDamage(attacker, victim);
        var critChance = attackerCritChance(attacker, victim);
        return damage * (1 - critChance) + damage * critChance * attacker.attack().getCritsEffect();
    }

    /**
     * ���㱻�����ߵ���Ч����ֵ, ����������ߵ������ʻ��˺�������ڵ���100%, ����{@code Integer.MAX_VALUE}.
     *
     * @param attacker ������
     * @param victim ��������
     * @return ���ر������ߵ���Ч����ֵ
     * @throws NullPointerException ���{@code attacker}��{@code victim}Ϊnull
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
     * ���㱻��������ЧHP.
     *
     * @param victimHp �������ߵ�HP
     * @param damageReduction �˺�������
     * @param evadeChance ���ܸ���
     * @return ���ر���������ЧHP
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
     *  ���㹥���߶Ա������ߵı����˺�.
     *
     * @param attacker ������
     * @param victim ��������
     * @return �����߶Ա������ߵı����˺�
     * @throws NullPointerException ���{@code attacker}��{@code victim}Ϊnull
     */
    public static int criticalDamage(final BasicUnit attacker, final BasicUnit victim)
    {
       final var hurt = attackerPhysicalDamage(requireNonNull(attacker), requireNonNull(victim));
       return criticalDamage(hurt, attacker.attack().getCritsEffect());
    }

    /**
     *  ���㹥���߶Ա������ߵı����˺�.
     *
     * @param hurt �����߶Ա������߿�����ɵĵ��˺�
     * @param critsEffect �����ߵı���Ч��
     * @return �����߶Ա������ߵı����˺�
     * @throws NullPointerException ���{@code attacker}��{@code victim}Ϊnull
     */
    public static int criticalDamage(final double hurt, final double critsEffect)
    {
        return Math.round((float) (hurt * critsEffect));
    }
}