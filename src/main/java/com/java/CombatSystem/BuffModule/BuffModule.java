package com.java.CombatSystem.BuffModule;

import java.io.Serial;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Objects;
import static java.util.Objects.requireNonNull;

/**
 * Buff模块, 用于和{@code BasicUnit}类交互
 *
 * <p>用于实现游戏的buff机制</p>
 * @see com.java.Unit.BasicUnit
 * @see BuffEffect
 * @see BuffType
 * @version 1.3.1
 * @since 15
 */
public class BuffModule implements Serializable
{
    public static void main(String[] args)
    {
    }
    @Serial
    private static final long serialVersionUID = 6182039129119023911L;

    private final EnumMap<BuffType, BuffEffect> haveBuffs = new EnumMap<>(BuffType.class);

    /**
     * 添加buff, 如果要添加的已存在, 则time和layer除于2然后和已存在的buffEffect的time和layer相加
     * @param type buff类型
     * @param buffEffect buff的具体效果
     * @since 15
     * @throws NullPointerException 如果{@code buffEffect}或{@code type}为null
     * @see BuffEffect
     */
    public final void add(BuffType type, BuffEffect buffEffect)
    {
        if (type == null)
            throw new NullPointerException();
        if (buffEffect == null)
            throw new NullPointerException();

        if (haveBuffs.containsKey(type))
        {
            if (buffEffect.isTimeLess())
                haveBuffs.put(type, buffEffect);
            else
            {
                var existentBuff = haveBuffs.get(type);
                var addTimeLimit = buffEffect.getTimeLimit();
                var addLayers = buffEffect.getLayers();

                existentBuff.setTimeLimit(existentBuff.getTimeLimit() + (addTimeLimit == 1 ? 1 : addTimeLimit / 2));
                existentBuff.setLayers(existentBuff.getLayers() + (addLayers == 1 ? 1 : addLayers / 2));
            }
        }
        else
            haveBuffs.put(type, buffEffect);
    }

    /**
     * @throws NullPointerException 如果{@code type}为null或{@code type}不存在
     * @return 此buff的效果信息
     * @param type buff的类型
     */
    public final BuffEffect getMessage(BuffType type)
    {
        if (buffNotExist(requireNonNull(type)))
            throw new NullPointerException("buff不存在:" + type);

        return haveBuffs.get(type);
    }

    /**
     * @param aBuffType buff类型
     * @throws NullPointerException 如果{@code aBuffType}为null
     * @return 如果存在, 返回 {@code true}, 否则返回{@code false}
     */
    public final boolean have(BuffType aBuffType)
    {
        return haveBuffs.containsKey(requireNonNull(aBuffType));
    }

    public final boolean isEmpty()
    {
        return haveBuffs.isEmpty();
    }

    /**
     * 此方法是对{@link EnumMap#size()}的包装
     * @return 此单位具有的buff数量
     */
    public int size()
    {
        return haveBuffs.size();
    }

    /**
     * 从具有的buff中移除走一个特定的buff
     *
     * @param type 要移除的buff类型
     * @throws NullPointerException 如果{@code type}为null或者要移除buff不存在
     */
    public void remove(BuffType type)
    {
        if (buffNotExist(requireNonNull(type)))
            throw new NullPointerException("Buff不存在:" + type);
        haveBuffs.remove(type);
    }

    /**
     * 移除特定buff的持续回合数, 如果移除的大于等于现有的回合数, 则直接移除buff
     *
     * @param type 要移除的buff类型
     * @param reduceTime 要移除的buff的回合数
     * @throws NullPointerException 如果{@code type}为null
     */
    public void remove(BuffType type, int reduceTime)
    {
        requireNonNull(type);
        if (reduceTime < 0)
            throw new IllegalArgumentException("非法参数:" + reduceTime);

        if (buffNotExist(type))
            throw new NullPointerException("Buff不存在:" + type);

        var buff = haveBuffs.get(type);
        var originalTime = buff.getTimeLimit();

        //如果移除的回合大于现有的回合, 则直接移除
        if (originalTime <= reduceTime)
            haveBuffs.remove(type);
        else
            buff.setTimeLimit(originalTime - reduceTime);
    }
    private boolean buffNotExist(BuffType type)
    {
        assert type != null;
        return !haveBuffs.containsKey(type);
    }

    /**
     * 此方法是对{@link EnumMap#clear()}方法的包装
     * @see 15
     */
    public final void clear()
    {
        haveBuffs.clear();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuffModule that = (BuffModule) o;
        return haveBuffs.equals(that.haveBuffs);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(haveBuffs);
    }

    @Override
    public String toString()
    {
        return "BasicBuffModule[" +
                "hasBuff:" + haveBuffs +
                ']';
    }
}
