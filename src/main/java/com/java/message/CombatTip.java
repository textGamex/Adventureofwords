package com.java.message;

import static java.util.Objects.requireNonNull;
/**
 * ս����ʾ��Ϣ.
 *
 * @author ǧ��
 * @version 1.0.2
 * @since 16
*/
public final class CombatTip
{
    public static void main(String[] args)
    {
        combatTip(new CombatMessage("���", 7, AttackType.COMMON_ATTACK, "�粼��", 70));
    }
    
    /**
     * �������ս����Ϣ.
     *
     * @see CombatMessage
     * @throws NullPointerException ���m{@code message}Ϊnull
     * @throws IllegalArgumentException ���{@code message.getHarm()}�����ķ���ֵС��0
     * @param message ս����Ϣ��
     */
    public static void combatTip(CombatMessage message)
    {
        if (message == null)
            throw new NullPointerException();

        //��ֱ��д�� message.harm����Ϊ�Ժ����Ҫ�����봦��
        var victimName = message.getVictimName();
        var attackerName = message.getAttackerName();
        var harm = message.getHarm();

        if (message.getHarm() == 0)
            System.out.printf("%sʹ��%s��%s�����˹���, ��δ����˺�", attackerName, message.getAttackType(), victimName);
        else if (message.getHarm() > 0)
            System.out.printf("[ϵͳ]%sʹ��%s��%s�����%d���˺�,%s��ʣ%d������ֵ%n",attackerName, message.getHarmTypeName(),
                victimName, message.getHarm(), victimName, message.getVictimSurplusHp());
        else throw new IllegalArgumentException("�쳣�˺�:" + harm);
    }

    /**
     * ս����Ϣ��, ��������{@code CombatTip}��, �Ա�ʵ��ս����Ϣ��ʾ
     *
     * <p>һ������ս�����ݵĸ�����, ���ڴ���������Ϣ</p>
     * <em>
     *     <li>����������</li>
     *     <li>��������ɵ��˺���</li>
     *     <li>��������</li>
     *     <li>������������</li>
     *     <li>��������ʣ��Ѫ��</li>
     * </em>
     * @version 1.0.1
     * @since 16
     * @author ǧ��
     * @see com.java.combatSystem.BattleSystem
     * @see CombatMessage
     */
    public static final class CombatMessage
    {
        private final String attackerName;
        private final int harm;
        private final AttackType attackType;

        private final String victimName;
        private final int victimSurplusHp;

        /**
         * @param attackerName �����ߵ�����
         * @param victimName �������ߵ�����
         * @param harm ��������ɵ��˺�
         * @param attackType �˺�����
         * @param victimSurplusHp ��������ʣ��Ѫ��
         * @author Millennium
         * @throws NullPointerException ���{@code attackerName}��{@code victimName}Ϊnull
         * @since 16
         */
        public CombatMessage(String attackerName, int harm, AttackType attackType, String victimName, int victimSurplusHp)
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
    }
}
