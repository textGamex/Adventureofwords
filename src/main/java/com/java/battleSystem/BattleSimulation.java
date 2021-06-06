package com.java.battleSystem;

import com.java.message.PlayerStatistics;
import com.java.unit.BasicUnit;
import com.java.unit.Enemy;
import com.java.unit.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.java.battleSystem.BattleSystem.*;
import static com.java.battleSystem.BattleAttributeCalculation.*;

/**
 * @author 留恋千年
 */
public final class BattleSimulation
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BattleSimulation.class);
    public static final int MAX = 100000;

    public static void main(String[] args) throws Exception
    {
//        final var account1 = new AccountMessage(ACCOUNT1);
//        final var account2 = new AccountMessage(ACCOUNT2);
//        final var roleStatistics = PlayerStatistics.loadStatistics(account1);
//        final var enemyStatistics = PlayerStatistics.loadStatistics(account2);

        final var roleStatistics = new PlayerStatistics();
        final var enemyStatistics = new PlayerStatistics();

        var role = new Role.Builder("玩家").maxHp(100).physicalAttack(20).armor(1).crit(10).critsEffect(2.0)
                .critResistance(50).hit(50).evade(5).build();
        var enemy = new Enemy.Builder("哥布林").maxHp(155).physicalAttack(10).armor(5).critResistance(90)
                .crit(3).hit(58).evade(9).critsEffect(2.0).build();


        for (var i = 0; i < MAX;)
        {
            roleStatistics.setTotalRound(roleStatistics.getTotalRound() + 1);
            enemyStatistics.setTotalRound(enemyStatistics.getTotalRound() + 1);
            if (canAct(role))
            {
                if (canHit(role, enemy))
                {
                    if (attackCanCrit(role, enemy))
                    {
                        final var hurt = normalAttackDamage(role, enemy);
                        final var critHurt = criticalDamage(hurt, role.attack().getCritsEffect());
                        enemy.subtractHp(critHurt);

                        roleStatistics.addTotalHarm(critHurt);

//                        BattleTip.printAttackMessage(new BattleTip.AttackMessage(role.getName(), critHurt,
//                                AttackType.CRIT, enemy.getName(), enemy.defense().getHp()));
                    }
                    else
                    {
                        final var hurt = normalAttackDamage(role, enemy);
                        enemy.subtractHp(hurt);

                        roleStatistics.addTotalHarm(hurt);

//                        BattleTip.printAttackMessage(new BattleTip.AttackMessage(role.getName(), hurt,
//                                AttackType.COMMON_ATTACK, enemy.getName(), enemy.defense().getHp()));
                    }
                    roleStatistics.setTotalAttack(roleStatistics.getTotalAttack() + 1);
                }
                else
                {
                    LOGGER.debug(role.getName() + "未击中" + enemy.getName());
                }
            }
            else
            {
                LOGGER.debug("不能行动");
            }
            if (enemy.defense().getHp() <= 0)
            {
                LOGGER.trace("{}仅剩{}点血",role.getName(), role.defense().getHp());
                LOGGER.debug("玩家胜利!");
                stateRecovery(role);
                stateRecovery(enemy);
                roleStatistics.setTotalKill(roleStatistics.getTotalKill() + 1);
                roleStatistics.setTotalVictory(roleStatistics.getTotalVictory() + 1);

                i++;
                continue;
            }
            if (canAct(enemy))
            {
                if (canHit(enemy, role))
                {
                    if (attackCanCrit(enemy, role))
                    {
                        final var hurt = normalAttackDamage(enemy, role);
                        final var critHurt = criticalDamage(hurt, enemy.attack().getCritsEffect());
                        role.subtractHp(critHurt);

                        enemyStatistics.addTotalHarm(critHurt);

//                        BattleTip.printAttackMessage(new BattleTip.AttackMessage(enemy.getName(), critHurt,
//                                AttackType.CRIT, role.getName(), role.defense().getHp()));
                    }
                    else
                    {
                        final var hurt = normalAttackDamage(enemy, role);
                        role.subtractHp(hurt);

                        enemyStatistics.addTotalHarm(hurt);

//                        BattleTip.printAttackMessage(new BattleTip.AttackMessage(enemy.getName(), hurt,
//                                AttackType.COMMON_ATTACK, role.getName(), role.defense().getHp()));
                    }
                    enemyStatistics.setTotalAttack(enemyStatistics.getTotalAttack() + 1);
                }
                else
                {
                    LOGGER.debug(enemy.getName() + "未击中" + role.getName());
                }
            }
            else
            {
                LOGGER.debug("不能行动");
            }
            if (role.defense().getHp() <= 0)
            {
                LOGGER.debug("敌对单位胜利!");
                LOGGER.trace("{}仅剩{}点血",enemy.getName(), enemy.defense().getHp());
                enemyStatistics.setTotalVictory(enemyStatistics.getTotalVictory() + 1);
                enemyStatistics.setTotalKill(enemyStatistics.getTotalKill() + 1);
                stateRecovery(role);
                stateRecovery(enemy);

                i++;
                continue;
            }
        }
        System.out.println("游戏结束");
//        roleStatistics.saveStatistics(account1);
//        enemyStatistics.saveStatistics(account2);
        System.out.println(roleStatistics);
        System.out.println("玩家胜率为" + (double) (roleStatistics.getTotalVictory() / MAX));
        System.out.println(enemyStatistics);
    }

    private static void stateRecovery(final BasicUnit unit)
    {
        assert unit != null;
        unit.defense().setHp(unit.defense().getMaxHp());
    }

}