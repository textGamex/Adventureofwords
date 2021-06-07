package com.java.battleSystem;

import com.java.battleSystem.BuffModule.BuffType;
import com.java.unit.BasicUnit;
import com.java.unit.Role;
import org.slf4j.LoggerFactory;

import static com.java.tools.GameTool.*;
import static java.util.Objects.requireNonNull;
import static com.java.battleSystem.BattleAttributeCalculation.*;

/**
 * ս��ϵͳ, �����������ս����ս����ص���ֵ����.
 *
 * <p>�Ѿ�ʵ�ֵ�ϵ��</p>
 * <em>
 *     <li>��Ҿ��齱��ϵ��</li>
 *     <li>��һ��ҽ���ϵ��</li>
 *     <li>��ҷ�������ϵ��</li>
 *     <li>���/�жԵ�λ����ϵ��</li>
 *     <li>���/�жԵ�λ������</li>
 *     <li>���/�жԵ�λ����Ч��</li>
 *     <li>���/�жԵ�λ����ϵ��</li>
 *     <li>���/�жԵ�λ������ϵ��</li>
 *     <li>���/�жԵ�λ��������ϵ��</li>
 * </em>
 * @version 1.0.0
 * @author ����ǧ��
 * @since 15
 * @see BattleCoefficient
 */
public final class BattleSystem
{
    public static void main(String[] args)
    {
        final var role = Role.newStandardPrimaryLevelRole("����");
        final var enemy = Role.newStandardPrimaryLevelRole("�жԵ�λ");
        System.out.println(normalAttackDamage(role, enemy));
    }

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BattleSystem.class);

    /**
     * �жϹ������Ƿ���ԶԱ�����������ɱ���.
     *
     * @param attacker ������
     * @param victim ��������
     * @return ���������ɱ���, ����{@code true}, ���򷵻�{@code false}
     */
    public static boolean attackCanCrit(final BasicUnit attacker, final BasicUnit victim)
    {
        return randomRun(attackerCritChance(requireNonNull(attacker), requireNonNull(victim)));
    }

    public static boolean canHit(final BasicUnit attacker, final BasicUnit victim)
    {
        return randomRun(attackHitRate(requireNonNull(attacker), requireNonNull(victim)));
    }

    /**
     * ���ݵ�λ��״̬���жϱ��غ��Ƿ�����ж�.
     *
     * @param unit Ҫ�����жϵĵ�λ
     * @return ��������ж�, ����{@code true}, ���򷵻�{@code false}
     * @throws NullPointerException ���{@code unit}Ϊnull
     */
    public static boolean canAct(final BasicUnit unit)
    {
        requireNonNull(unit);
        if (unit.buff().have(BuffType.VERTIGO))
        {
            return false;
        }
        return true;
    }

    /**
     * ������ͨ�����˺�.
     *
     * @return ������ͨ�����˺�
     */
    public static int normalAttackDamage(final BasicUnit attacker, final BasicUnit victim)
    {
        LOGGER.debug("�����߹�����:{}, �������߻���:{}", attacker.attack().getPhysicalAttack(), victim.defense().getArmor());
        final var attackPower = attackerPhysicalDamage(requireNonNull(attacker), requireNonNull(victim));
        LOGGER.debug("�˺�:{}", attackPower);
        final var hurt = floatingNumber(attackPower, 0.2);
        LOGGER.debug("�������˺�:{}", hurt);

        return hurt;
    }
}