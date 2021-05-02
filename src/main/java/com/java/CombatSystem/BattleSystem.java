package com.java.CombatSystem;

import com.java.Message.CombatTip;
import com.java.Unit.Enemy;
import com.java.Unit.Role;

/**
 * ս��ϵͳ
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
    private double roleDamageCoefficient;//����ϵ��

    private double enemyAttackFactor;
    private double enemyCritRateFactor;
    private double enemyCritsEffectFactor;
    private double enemyDamageCoefficient;//����ϵ��

    public final static class Builder
    {
        private double expRewardsFactor       = 1.0;
        private double cashRewardsFactor      = 1.0;
        private double valueFactor            = 1.0;
        private double roleAttackFactor       = 1.0;
        private double roleCritRateFactor     = 1.0;
        private double roleCritsEffectFactor  = 1.0;
        private double roleDamageCoefficient  = 1.0;//����ϵ��

        private double enemyAttackFactor      = 1.0;
        private double enemyCritRateFactor    = 1.0;
        private double enemyCritsEffectFactor = 1.0;
        private double enemyDamageCoefficient = 1.0;//����ϵ��

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
                "���齱��ϵ��:" + expRewardsFactor +
                ", ���ҽ���ϵ��:" + cashRewardsFactor +
                ", �÷ֽ���ϵ��:" + valueFactor +
                ", ���������ϵ��:" + roleAttackFactor +
                ", ��ɫ������ϵ��:" + roleCritRateFactor +
                ", ��ɫ����Ч��ϵ��:" + roleCritsEffectFactor +
                ", ��ɫ�����˺�ϵ��:" + roleDamageCoefficient +
                ", ����������ϵ��:" + enemyAttackFactor +
                ", ���˱�����ϵ��:" + enemyCritRateFactor +
                ", ���˱���Ч��ϵ��:" + enemyCritsEffectFactor +
                ", ���������˺�ϵ��:" + enemyDamageCoefficient +
                ']';
    }
}