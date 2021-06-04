package com.java.battleSystem;

import com.java.account.AccountMessage;
import com.java.message.PlayerStatistics;
import com.java.message.attackMessage.AttackType;
import com.java.message.attackMessage.BattleTip;
import com.java.unit.Enemy;
import com.java.unit.Role;
import static com.java.battleSystem.BattleSystem.*;
import static com.java.battleSystem.BattleAttributeCalculation.*;
import static com.java.message.PrivateData.*;

/**
 * @author ����ǧ��
 */
public final class CombatSimulation
{
    public static void main(String[] args) throws Exception
    {
        final var account1 = new AccountMessage(ACCOUNT1);
        final var account2 = new AccountMessage(ACCOUNT2);
        final var roleStatistics = PlayerStatistics.loadStatistics(account1);
        final var enemyStatistics = PlayerStatistics.loadStatistics(account2);

        var role = new Role.Builder("���").maxHp(100).physicalAttack(20).armor(1).crit(10).critsEffect(2.0)
                .critResistance(50).hit(50).evade(5).build();
        var enemy = new Enemy.Builder("�粼��").maxHp(155).physicalAttack(10).armor(5).critResistance(90)
                .crit(3).hit(58).evade(9).critsEffect(2.0).build();

        while (true)
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

                        BattleTip.printAttackMessage(new BattleTip.AttackMessage(role.getName(), critHurt,
                                AttackType.CRIT, enemy.getName(), enemy.defense().getHp()));
                    }
                    else
                    {
                        final var hurt = normalAttackDamage(role, enemy);
                        enemy.subtractHp(hurt);

                        roleStatistics.addTotalHarm(hurt);

                        BattleTip.printAttackMessage(new BattleTip.AttackMessage(role.getName(), hurt,
                                AttackType.COMMON_ATTACK, enemy.getName(), enemy.defense().getHp()));
                    }
                    roleStatistics.setTotalAttack(roleStatistics.getTotalAttack() + 1);
                }
                else
                {
                    System.out.println(role.getName() + "δ����" + enemy.getName());
                }
            }
            else
            {
                System.out.println("�����ж�");
            }
            if (enemy.defense().getHp() <= 0)
            {
                System.out.println(role.getName() + "��ʣ" + role.defense().getHp() + "��Ѫ");
                System.out.println("���ʤ��!");

                roleStatistics.setTotalVictory(roleStatistics.getTotalVictory() + 1);

                break;
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

                        BattleTip.printAttackMessage(new BattleTip.AttackMessage(enemy.getName(), critHurt,
                                AttackType.CRIT, role.getName(), role.defense().getHp()));
                    }
                    else
                    {
                        final var hurt = normalAttackDamage(enemy, role);
                        role.subtractHp(hurt);

                        enemyStatistics.addTotalHarm(hurt);

                        BattleTip.printAttackMessage(new BattleTip.AttackMessage(enemy.getName(), hurt,
                                AttackType.COMMON_ATTACK, role.getName(), role.defense().getHp()));
                    }
                    enemyStatistics.setTotalAttack(enemyStatistics.getTotalAttack() + 1);
                }
                else
                {
                    System.out.println(enemy.getName() + "δ����" + role.getName());
                }
            }
            else
            {
                System.out.println("�����ж�");
            }
            if (role.defense().getHp() <= 0)
            {
                System.out.println("�жԵ�λʤ��!");
                System.out.println(enemy.getName() + "��ʣ" + enemy.defense().getHp() + "��Ѫ");
                enemyStatistics.setTotalVictory(enemyStatistics.getTotalVictory() + 1);
                break;
            }
        }
        System.out.println("��Ϸ����!");
        roleStatistics.saveStatistics(account1);
        enemyStatistics.saveStatistics(account2);
        System.out.println(roleStatistics);
        System.out.println(enemyStatistics);
    }
}
