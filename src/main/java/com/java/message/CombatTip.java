package com.java.message;

import static java.util.Objects.requireNonNull;
/**
 * 战斗提示信息.
 *
 * @author 千年
 * @version 1.0.2
 * @since 16
*/
public final class CombatTip
{
    public static void main(String[] args)
    {
        combatTip(new CombatMessage("玩家", 7, AttackType.COMMON_ATTACK, "哥布林", 70));
    }
    
    /**
     * 用于输出战场信息.
     *
     * @see CombatMessage
     * @throws NullPointerException 如果m{@code message}为null
     * @throws IllegalArgumentException 如果{@code message.getHarm()}方法的返回值小于0
     * @param message 战场信息包
     */
    public static void combatTip(CombatMessage message)
    {
        if (message == null)
            throw new NullPointerException();

        //不直接写成 message.harm是因为以后可能要做分离处理
        var victimName = message.getVictimName();
        var attackerName = message.getAttackerName();
        var harm = message.getHarm();

        if (message.getHarm() == 0)
            System.out.printf("%s使用%s对%s发起了攻击, 但未造成伤害", attackerName, message.getAttackType(), victimName);
        else if (message.getHarm() > 0)
            System.out.printf("[系统]%s使用%s对%s造成了%d点伤害,%s还剩%d点生命值%n",attackerName, message.getHarmTypeName(),
                victimName, message.getHarm(), victimName, message.getVictimSurplusHp());
        else throw new IllegalArgumentException("异常伤害:" + harm);
    }

    /**
     * 战场信息包, 用来辅助{@code CombatTip}类, 以便实现战斗信息提示
     *
     * <p>一个保存战斗数据的辅助类, 用于传输以下信息</p>
     * <em>
     *     <li>攻击者名称</li>
     *     <li>攻击者造成的伤害量</li>
     *     <li>攻击类型</li>
     *     <li>被攻击者名称</li>
     *     <li>被攻击者剩余血量</li>
     * </em>
     * @version 1.0.1
     * @since 16
     * @author 千年
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
         * @param attackerName 攻击者的名字
         * @param victimName 被攻击者的名字
         * @param harm 攻击者造成的伤害
         * @param attackType 伤害类型
         * @param victimSurplusHp 被攻击后剩余血量
         * @author Millennium
         * @throws NullPointerException 如果{@code attackerName}或{@code victimName}为null
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
