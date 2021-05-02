package com.java.CombatSystem;

import com.java.Message.CombatTip;
import com.java.Unit.Enemy;
import com.java.Unit.Role;

/**
 * 战斗系统
 * @version 0.2.1
 * @author Millennium
 */
public final class BattleSystem
{
    public static void main(String[] args)
    {
    }
    private double expRewardsFactor;
    private double cashRewardsFactor;
    private double valueFactor;

    private double roleAttackFactor;
    private double roleCritRateFactor;
    private double roleCritsEffectFactor ;
    private double roleDamageCoefficient;//承伤系数

    private double enemyAttackFactor;
    private double enemyCritRateFactor;
    private double enemyCritsEffectFactor;
    private double enemyDamageCoefficient;//承伤系数

    public final static class Builder
    {
        private double expRewardsFactor       = 1.0;
        private double cashRewardsFactor      = 1.0;
        private double valueFactor            = 1.0;
        private double roleAttackFactor       = 1.0;
        private double roleCritRateFactor     = 1.0;
        private double roleCritsEffectFactor  = 1.0;
        private double roleDamageCoefficient  = 1.0;//承伤系数

        private double enemyAttackFactor      = 1.0;
        private double enemyCritRateFactor    = 1.0;
        private double enemyCritsEffectFactor = 1.0;
        private double enemyDamageCoefficient = 1.0;//承伤系数

        public Builder expRewardsFactor(double expRewardsFactor)
        {
            this.expRewardsFactor = expRewardsFactor;
            return this;
        }

        public Builder valueFactor(double valueFactor)
        {
            this.valueFactor = valueFactor;
            return this;
        }

        public Builder cashRewardsFactor(double cashRewardsFactor)
        {
            this.cashRewardsFactor = cashRewardsFactor;
            return this;
        }

        public Builder roleAttackFactor(double roleAttackFactor)
        {
            this.roleAttackFactor = roleAttackFactor;
            return this;
        }

        public Builder roleCritRateFactor(double roleCritRateFactor)
        {
            this.roleCritRateFactor = roleCritRateFactor;
            return this;
        }

        public Builder roleCritsEffectFactor(double roleCritsEffectFactor)
        {
            this.roleCritsEffectFactor = roleCritsEffectFactor;
            return this;
        }

        public Builder roleDamageCoefficient(double roleDamageCoefficient)
        {
            this.roleDamageCoefficient = roleDamageCoefficient;
            return this;
        }

        public Builder enemyAttackFactor(double enemyAttackFactor)
        {
            this.enemyAttackFactor = enemyAttackFactor;
            return this;
        }

        public Builder enemyCritRateFactor(double enemyCritRateFactor)
        {
            this.enemyCritRateFactor = enemyCritRateFactor;
            return this;
        }

        public Builder enemyCritsEffectFactor(double enemyCritsEffectFactor)
        {
            this.enemyCritsEffectFactor = enemyCritsEffectFactor;
            return this;
        }

        public Builder enemyDamageCoefficient(double enemyDamageCoefficient)
        {
            this.enemyDamageCoefficient = enemyDamageCoefficient;
            return this;
        }

        public BattleSystem build()
        {
            return new BattleSystem(this);
        }
    }

    private BattleSystem(Builder builder)
    {
        assert builder != null;
        expRewardsFactor       = builder.expRewardsFactor;
        cashRewardsFactor      = builder.cashRewardsFactor;
        valueFactor            = builder.valueFactor;
        roleAttackFactor       = builder.roleAttackFactor;
        roleCritRateFactor     = builder.roleCritRateFactor;
        roleCritsEffectFactor  = builder.roleCritsEffectFactor;
        roleDamageCoefficient  = builder.roleDamageCoefficient;
        enemyAttackFactor      = builder.enemyAttackFactor;
        enemyCritRateFactor    = builder.enemyCritRateFactor;
        enemyCritsEffectFactor = builder.enemyCritsEffectFactor;
        enemyDamageCoefficient = builder.enemyDamageCoefficient;
    }

    public void fight(Role role, Enemy enemy)
    {

    }

    public double getExpRewardsFactor()
    {
        return expRewardsFactor;
    }

    public double getValueFactor()
    {
        return valueFactor;
    }

    public double getCashRewardsFactor()
    {
        return cashRewardsFactor;
    }

    public double getRoleAttackFactor()
    {
        return roleAttackFactor;
    }

    public double getRoleCritRateFactor()
    {
        return roleCritRateFactor;
    }

    public double getRoleCritsEffectFactor()
    {
        return roleCritsEffectFactor;
    }

    public double getRoleDamageCoefficient()
    {
        return roleDamageCoefficient;
    }

    public double getEnemyAttackFactor()
    {
        return enemyAttackFactor;
    }

    public double getEnemyCritRateFactor()
    {
        return enemyCritRateFactor;
    }

    public double getEnemyCritsEffectFactor()
    {
        return enemyCritsEffectFactor;
    }

    public double getEnemyDamageCoefficient()
    {
        return enemyDamageCoefficient;
    }

    @Override
    public String toString()
    {
        return "BattleSystem[" +
                "经验奖励系数:" + expRewardsFactor +
                ", 货币奖励系数:" + cashRewardsFactor +
                ", 得分奖励系数:" + valueFactor +
                ", 玩家物理攻击系数:" + roleAttackFactor +
                ", 角色暴击率系数:" + roleCritRateFactor +
                ", 角色暴击效果系数:" + roleCritsEffectFactor +
                ", 角色所受伤害系数:" + roleDamageCoefficient +
                ", 敌人物理攻击系数:" + enemyAttackFactor +
                ", 敌人暴击率系数:" + enemyCritRateFactor +
                ", 敌人暴击效果系数:" + enemyCritsEffectFactor +
                ", 敌人所受伤害系数:" + enemyDamageCoefficient +
                ']';
    }
}