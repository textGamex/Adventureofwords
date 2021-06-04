package com.java.battleSystem;

import com.java.unit.Enemy;
import com.java.unit.Role;

public class BattleCoefficient
{
    private double expRewardsFactor;
    private double cashRewardsFactor;
    private double valueFactor;

    private double roleAttackFactor;
    private double roleCritRateFactor;
    private double roleCritsEffectFactor;
    //����ϵ��
    private double roleDamageFactor;
    private double rolePhysicalResistanceFactor;
    private double roleMagicResistanceFactor;

    private double enemyAttackFactor;
    private double enemyCritRateFactor;
    private double enemyCritsEffectFactor;
    //����ϵ��
    private double enemyDamageFactor;
    private double enemyPhysicalResistanceFactor;
    private double enemyMagicResistanceFactor;

    /**
     * ��������{@code BattleSystem}����
     * @version 1.0.2
     * @since 16
     */
    public final static class Builder
    {
        private double expRewardsFactor             = 1.0;
        private double cashRewardsFactor            = 1.0;
        private double valueFactor                  = 1.0;
        private double roleAttackFactor             = 1.0;
        private double roleCritRateFactor           = 1.0;
        private double roleCritsEffectFactor        = 1.0;
        private double roleDamageCoefficient        = 1.0;//����ϵ��
        private double rolePhysicalResistanceFactor = 1.0;
        private double roleMagicResistanceFactor    = 1.0;

        private double enemyAttackFactor             = 1.0;
        private double enemyCritRateFactor           = 1.0;
        private double enemyCritsEffectFactor        = 1.0;
        private double enemyDamageCoefficient        = 1.0;//����ϵ��
        private double enemyPhysicalResistanceFactor = 1.0;
        private double enemyMagicResistanceFactor    = 1.0;

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

        public Builder rolePhysicalResistanceFactor(double rolePhysicalResistanceFactor)
        {
            this.rolePhysicalResistanceFactor = rolePhysicalResistanceFactor;
            return this;
        }

        public Builder enemyPhysicalResistanceFactor(double enemyPhysicalResistanceFactor)
        {
            this.enemyPhysicalResistanceFactor = enemyPhysicalResistanceFactor;
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

        public Builder roleMagicResistanceFactor(double roleMagicResistanceFactor)
        {
            this.roleMagicResistanceFactor = roleMagicResistanceFactor;
            return this;
        }

        public Builder enemyMagicResistanceFactor(double enemyMagicResistanceFactor)
        {
            this.enemyMagicResistanceFactor = enemyMagicResistanceFactor;
            return this;
        }

        public BattleCoefficient build()
        {
            return new BattleCoefficient(this);
        }
    }

    private BattleCoefficient(Builder builder)
    {
        assert builder != null;
        expRewardsFactor              = builder.expRewardsFactor;
        cashRewardsFactor             = builder.cashRewardsFactor;
        valueFactor                   = builder.valueFactor;
        roleAttackFactor              = builder.roleAttackFactor;
        roleCritRateFactor            = builder.roleCritRateFactor;
        roleCritsEffectFactor         = builder.roleCritsEffectFactor;
        roleDamageFactor              = builder.roleDamageCoefficient;
        rolePhysicalResistanceFactor  = builder.rolePhysicalResistanceFactor;
        roleMagicResistanceFactor     = builder.roleMagicResistanceFactor;
        enemyAttackFactor             = builder.enemyAttackFactor;
        enemyCritRateFactor           = builder.enemyCritRateFactor;
        enemyCritsEffectFactor        = builder.enemyCritsEffectFactor;
        enemyDamageFactor             = builder.enemyDamageCoefficient;
        enemyPhysicalResistanceFactor = builder.enemyPhysicalResistanceFactor;
        enemyMagicResistanceFactor    = builder.enemyMagicResistanceFactor;
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

    public double getRoleDamageFactor()
    {
        return roleDamageFactor;
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

    public double getEnemyDamageFactor()
    {
        return enemyDamageFactor;
    }

    public double getRolePhysicalResistanceFactor()
    {
        return rolePhysicalResistanceFactor;
    }

    public double getEnemyPhysicalResistanceFactor()
    {
        return enemyPhysicalResistanceFactor;
    }

    public double getRoleMagicResistanceFactor()
    {
        return roleMagicResistanceFactor;
    }

    public double getEnemyMagicResistanceFactor()
    {
        return enemyMagicResistanceFactor;
    }

    /**
     * @return �ַ�����ʾ�Ķ���
     */
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
                ", ��ɫ�����˺�ϵ��:" + roleDamageFactor +
                ", ���������ϵ��:" + rolePhysicalResistanceFactor +
                ", ��ҷ�������ϵ��:" + roleMagicResistanceFactor +
                ", ����������ϵ��:" + enemyAttackFactor +
                ", ���˱�����ϵ��:" + enemyCritRateFactor +
                ", ���˱���Ч��ϵ��:" + enemyCritsEffectFactor +
                ", ���������˺�ϵ��:" + enemyDamageFactor +
                ", ����������ϵ��:" + enemyPhysicalResistanceFactor +
                ", ���˷�������ϵ��:" + enemyMagicResistanceFactor +
                ']';
    }
}
