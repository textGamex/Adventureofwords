package com.java.battleSystem.BuffModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import static java.util.Objects.requireNonNull;

/**
 * Buff模块, 用于和{@link com.java.unit.BasicUnit}类交互.
 *
 * <p>用于实现游戏的buff机制, 具有一般回合制游戏buff相关的大部分功能</p>
 * @see com.java.unit.BasicUnit
 * @see BuffEffect
 * @see BuffType
 * @version 1.3.3
 * @since 15
 * @author 留恋千年
 */
public class BuffModule implements Serializable
{
    public static final Logger LOGGER = LoggerFactory.getLogger(BuffModule.class);

    @Serial
    private static final long serialVersionUID = 6182039129119023911L;

    private final Map<BuffType, BuffEffect> haveBuffs = new EnumMap<>(BuffType.class);

    /**
     * 给单位添加{@link BuffType}.
     *
     * <p>给单位添加buff, 如果要添加的效果已存在, 则持续回合数和效果层数除于2然后和已存在的{@code buffEffect}的time和layer相加</p>
     * <p>如果{@link BuffEffect#isTimeLess()}方法的返回值为{@code true}, 则直接替换</p>
     * @param type 要添加的buff类型
     * @param buffEffect 添加的buff的具体效果
     * @since 15
     * @throws NullPointerException 如果{@code buffEffect}或{@code type}为null
     * @see BuffEffect
     */
    public final void add(final BuffType type, final BuffEffect buffEffect)
    {
        requireNonNull(type);
        requireNonNull(buffEffect);

        if (haveBuffs.containsKey(type))
        {
            LOGGER.debug("{}({})存在", type, type.getType());
            if (buffEffect.isTimeLess())
            {
                haveBuffs.put(type, buffEffect);
                LOGGER.debug("{}({})是特性", type, type.getType());
            }
            else
            {
                var existentBuff = haveBuffs.get(type);
                var addTimeLimit = buffEffect.getTimeLimit();
                var addLayers = buffEffect.getLayers();

                existentBuff.setTimeLimit(existentBuff.getTimeLimit() + (addTimeLimit == 1 ? 1 : addTimeLimit / 2));
                existentBuff.setLayers(existentBuff.getLayers() + (addLayers == 1 ? 1 : addLayers / 2));
                LOGGER.debug("{}({})不是特性", type, type.getType());
            }
        }
        else
        {
            haveBuffs.put(type, buffEffect);
            LOGGER.debug("{}({})不存在, 直接添加", type, type.getType());
        }
    }

    /**
     * 获得指定{@link BuffType}的效果信息.
     *
     * @throws NullPointerException 如果{@code type}为null或{@code type}不存在
     * @return 此buff的效果信息
     * @param type buff的类型
     * @see BuffType
     */
    public final BuffEffect getMessage(final BuffType type)
    {
        if (buffNotExist(requireNonNull(type)))
        {
            throw new NullPointerException("buff不存在:" + type);
        }

        return haveBuffs.get(type);
    }

    /**
     * 如果{@link BuffType}存在, 返回{@code true}.
     *
     * @param aBuffType 检测{@link BuffType}是否存在
     * @throws NullPointerException 如果{@code aBuffType}为null
     * @return 如果存在, 返回 {@code true}, 否则返回{@code false}
     */
    public final boolean have(final BuffType aBuffType)
    {
        return haveBuffs.containsKey(requireNonNull(aBuffType));
    }

    /**
     * @return 如果是空的, 返回{@code true}
     */
    public final boolean isEmpty()
    {
        return haveBuffs.isEmpty();
    }

    /**
     * 返回此单位具有的buff数量.
     *
     * @return 此单位具有的buff数量
     */
    public int size()
    {
        return haveBuffs.size();
    }

    /**
     * 从具有的{@link BuffType}中移除走一个指定的buff.
     *
     * @param type 要移除的buff类型
     * @throws NullPointerException 如果{@code type}为null或者要移除buff的不存在
     */
    public void remove(final BuffType type)
    {
        if (buffNotExist(requireNonNull(type)))
        {
            throw new NullPointerException("Buff不存在:" + type);
        }
        haveBuffs.remove(type);
    }

    /**
     * 移除指定{@link BuffType}的持续回合数, 如果移除的大于等于现有的回合数, 则直接移除buff.
     *
     * @param type 要移除的{@link BuffType}
     * @param reduceTime 要移除的buff的回合数
     * @throws NullPointerException 如果{@code type}为null
     * @throws IllegalArgumentException rg{@code reduceTime}小于等于0
     * @see BuffType
     * @see BuffEffect
     */
    public void remove(final BuffType type, final int reduceTime)
    {
        requireNonNull(type);
        if (reduceTime <= 0)
        {
            throw new IllegalArgumentException("非法参数:" + reduceTime);
        }

        if (buffNotExist(type))
        {
            throw new NullPointerException("Buff不存在:" + type);
        }

        var buff = haveBuffs.get(type);
        var originalTime = buff.getTimeLimit();

        //如果移除的回合大于现有的回合, 则直接移除
        if (originalTime <= reduceTime)
        {
            haveBuffs.remove(type);
            LOGGER.debug("持续回合:{} 小于等于 移除时间:{}, 直接移除{}", originalTime, reduceTime, type);
        }
        else
        {
            LOGGER.debug("持续回合:{} 大于 移除时间:{}, 回合数相减", originalTime, reduceTime);
            buff.setTimeLimit(originalTime - reduceTime);
        }
    }
    private boolean buffNotExist(final BuffType type)
    {
        assert type != null;
        return !haveBuffs.containsKey(type);
    }

    /**
     * 清空单位所携带的所有buff效果.
     *
     * @since 15
     */
    public final void clear()
    {
        haveBuffs.clear();
    }

    /**
     * 清空单位所携带的所有负面效果
     *
     * <p>
     *     <font color="#FF0000">注意:</font>
     * <strong>只有{@link BuffEffect#isDebuff()}方法的返回值为{@code true}的buff才会被清除</strong>
     * </p>
     * @since 2021-5-3
     */
    public void clearDebuff()
    {
        var allBuff = haveBuffs.entrySet();
        for (var buff : allBuff)
        {
            if (buff.getValue().isDebuff())
            {
                remove(buff.getKey());
            }
        }
    }

    /**
     *
     * @param o 要检测是否相等的对象
     * @return 如果 {@code o}与this相等, 返回{@code true}
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        BuffModule that = (BuffModule) o;
        return haveBuffs.equals(that.haveBuffs);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(haveBuffs);
    }

    /**
     * @return 字符串表示的对象
     */
    @Override
    public String toString()
    {
        return "BasicBuffModule[" +
                "hasBuff:" + haveBuffs +
                ']';
    }
}
