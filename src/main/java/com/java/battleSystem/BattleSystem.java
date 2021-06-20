package com.java.battleSystem;

import com.java.battleSystem.BuffModule.BuffType;
import com.java.unit.BasicUnit;
import com.java.unit.Role;
import org.slf4j.LoggerFactory;

import static com.java.tools.GameTool.*;
import static java.util.Objects.requireNonNull;
import static com.java.battleSystem.BattleAttributeCalculation.*;

/**
 * 战斗系统, 用来处理玩家战斗和战斗相关的数值处理.
 *
 * <p>已经实现的系数</p>
 * <em>
 *     <li>玩家经验奖励系数</li>
 *     <li>玩家货币奖励系数</li>
 *     <li>玩家分数奖励系数</li>
 *     <li>玩家/敌对单位攻击系数</li>
 *     <li>玩家/敌对单位暴击率</li>
 *     <li>玩家/敌对单位暴击效果</li>
 *     <li>玩家/敌对单位承伤系数</li>
 *     <li>玩家/敌对单位物理抗性系数</li>
 *     <li>玩家/敌对单位法术抗性系数</li>
 * </em>
 * @version 1.0.0
 * @author 留恋千年
 * @since 15
 * @see BattleCoefficient
 */
public final class BattleSystem
{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BattleSystem.class);

    /**
     * 判断攻击者是否可以对被被攻击者造成暴击.
     *
     * @param attacker 攻击者
     * @param victim 被攻击者
     * @return 如果可以造成暴击, 返回{@code true}, 否则返回{@code false}
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
     * 根据单位的状态来判断本回合是否可以行动.
     *
     * @param unit 要进行判断的单位
     * @return 如果可以行动, 返回{@code true}, 否则返回{@code false}
     * @throws NullPointerException 如果{@code unit}为null
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
     * 计算普通攻击伤害.
     *
     * @return 返回普通攻击伤害
     */
    public static int normalAttackDamage(final BasicUnit attacker, final BasicUnit victim)
    {
        LOGGER.debug("攻击者攻击力:{}, 被攻击者护甲:{}", attacker.attack().getPhysicalAttack(), victim.defense().getArmor());
        final var attackPower = attackerPhysicalDamage(requireNonNull(attacker), requireNonNull(victim));
        LOGGER.debug("伤害:{}", attackPower);
        final var hurt = floatingNumber(attackPower, 0.2);
        LOGGER.debug("随机后的伤害:{}", hurt);

        return hurt;
    }
}