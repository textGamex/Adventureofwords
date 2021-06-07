package com.java.message.attackMessage;

import com.java.battleSystem.BattleSystem;

import static java.util.Objects.requireNonNull;
/**
 * ս����ʾ��Ϣ.
 *
 * @author ����ǧ��
 * @version 1.0.2
 * @since 16
*/
public final class BattleTip
{
    /**
     * @author ����ǧ��
     */
    public enum CauseOfNoInjury
    {
        MISS("δ����");
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

    /**
     * �������ս����Ϣ.
     *
     * @see AttackMessage
     * @throws NullPointerException ���m{@code message}Ϊnull
     * @throws IllegalArgumentException ���{@code message.getHarm()}�����ķ���ֵС��0
     * @param message ս����Ϣ��
     */
    public static String returnAttackMessage(AttackMessage message)
    {
        requireNonNull(message);

        //��ֱ��д�� message.harm����Ϊ�Ժ����Ҫ�����봦��
        var victimName = message.getVictimName();
        var attackerName = message.getAttackerName();
        var harm = message.getHarm();

        if (message.getHarm() == 0)
        {
            return String.format("%sʹ��[%s]��%s�����˹���, ��δ����˺�", attackerName, message.getHarmTypeName(), victimName);
        }
        else if (message.getHarm() > 0)
        {
            return String.format("%sʹ��[%s]��%s�����%d���˺�,%s��ʣ%d������ֵ",attackerName, message.getHarmTypeName(),
                victimName, message.getHarm(), victimName, message.getVictimSurplusHp());
        }
        else
        {
            throw new IllegalArgumentException("�쳣�˺�:" + harm);
        }
    }

    /**
     * ս����Ϣ��, ��������{@code BattleTip}��, �Ա�ʵ��ս����Ϣ��ʾ.
     *
     * <p>һ������ս�����ݵĸ�����, ���ڴ���������Ϣ</p>
     * <li>����������</li>
     * <li>��������ɵ��˺���</li>
     * <li>��������</li>
     * <li>������������</li>
     * <li>��������ʣ��Ѫ��</li>
     *
     * @version 1.0.1
     * @since 16
     * @author ����ǧ��
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
         * @param attackerName �����ߵ�����
         * @param victimName �������ߵ�����
         * @param harm ��������ɵ��˺�
         * @param attackType �˺�����
         * @param victimSurplusHp ��������ʣ��Ѫ��
         * @author ����ǧ��
         * @throws NullPointerException ���{@code attackerName}��{@code victimName}Ϊnull
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
        public String getHarmTypeName()
        {
            return attackType.getTypeName();
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
                    System.out.println("");
                    break;
                default: throw new NullPointerException();
            }
        }
    }
}
