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
 * Buffģ��, ���ں�{@link com.java.unit.BasicUnit}�ཻ��.
 *
 * <p>����ʵ����Ϸ��buff����, ����һ��غ�����Ϸbuff��صĴ󲿷ֹ���</p>
 * @see com.java.unit.BasicUnit
 * @see BuffEffect
 * @see BuffType
 * @version 1.3.3
 * @since 15
 * @author ����ǧ��
 */
public class BuffModule implements Serializable
{
    public static final Logger LOGGER = LoggerFactory.getLogger(BuffModule.class);

    @Serial
    private static final long serialVersionUID = 6182039129119023911L;

    private final Map<BuffType, BuffEffect> haveBuffs = new EnumMap<>(BuffType.class);

    /**
     * ����λ���{@link BuffType}.
     *
     * <p>����λ���buff, ���Ҫ��ӵ�Ч���Ѵ���, ������غ�����Ч����������2Ȼ����Ѵ��ڵ�{@code buffEffect}��time��layer���</p>
     * <p>���{@link BuffEffect#isTimeLess()}�����ķ���ֵΪ{@code true}, ��ֱ���滻</p>
     * @param type Ҫ��ӵ�buff����
     * @param buffEffect ��ӵ�buff�ľ���Ч��
     * @since 15
     * @throws NullPointerException ���{@code buffEffect}��{@code type}Ϊnull
     * @see BuffEffect
     */
    public final void add(final BuffType type, final BuffEffect buffEffect)
    {
        requireNonNull(type);
        requireNonNull(buffEffect);

        if (haveBuffs.containsKey(type))
        {
            LOGGER.debug("{}({})����", type, type.getType());
            if (buffEffect.isTimeLess())
            {
                haveBuffs.put(type, buffEffect);
                LOGGER.debug("{}({})������", type, type.getType());
            }
            else
            {
                var existentBuff = haveBuffs.get(type);
                var addTimeLimit = buffEffect.getTimeLimit();
                var addLayers = buffEffect.getLayers();

                existentBuff.setTimeLimit(existentBuff.getTimeLimit() + (addTimeLimit == 1 ? 1 : addTimeLimit / 2));
                existentBuff.setLayers(existentBuff.getLayers() + (addLayers == 1 ? 1 : addLayers / 2));
                LOGGER.debug("{}({})��������", type, type.getType());
            }
        }
        else
        {
            haveBuffs.put(type, buffEffect);
            LOGGER.debug("{}({})������, ֱ�����", type, type.getType());
        }
    }

    /**
     * ���ָ��{@link BuffType}��Ч����Ϣ.
     *
     * @throws NullPointerException ���{@code type}Ϊnull��{@code type}������
     * @return ��buff��Ч����Ϣ
     * @param type buff������
     * @see BuffType
     */
    public final BuffEffect getMessage(final BuffType type)
    {
        if (buffNotExist(requireNonNull(type)))
        {
            throw new NullPointerException("buff������:" + type);
        }

        return haveBuffs.get(type);
    }

    /**
     * ���{@link BuffType}����, ����{@code true}.
     *
     * @param aBuffType ���{@link BuffType}�Ƿ����
     * @throws NullPointerException ���{@code aBuffType}Ϊnull
     * @return �������, ���� {@code true}, ���򷵻�{@code false}
     */
    public final boolean have(final BuffType aBuffType)
    {
        return haveBuffs.containsKey(requireNonNull(aBuffType));
    }

    /**
     * @return ����ǿյ�, ����{@code true}
     */
    public final boolean isEmpty()
    {
        return haveBuffs.isEmpty();
    }

    /**
     * ���ش˵�λ���е�buff����.
     *
     * @return �˵�λ���е�buff����
     */
    public int size()
    {
        return haveBuffs.size();
    }

    /**
     * �Ӿ��е�{@link BuffType}���Ƴ���һ��ָ����buff.
     *
     * @param type Ҫ�Ƴ���buff����
     * @throws NullPointerException ���{@code type}Ϊnull����Ҫ�Ƴ�buff�Ĳ�����
     */
    public void remove(final BuffType type)
    {
        if (buffNotExist(requireNonNull(type)))
        {
            throw new NullPointerException("Buff������:" + type);
        }
        haveBuffs.remove(type);
    }

    /**
     * �Ƴ�ָ��{@link BuffType}�ĳ����غ���, ����Ƴ��Ĵ��ڵ������еĻغ���, ��ֱ���Ƴ�buff.
     *
     * @param type Ҫ�Ƴ���{@link BuffType}
     * @param reduceTime Ҫ�Ƴ���buff�Ļغ���
     * @throws NullPointerException ���{@code type}Ϊnull
     * @throws IllegalArgumentException rg{@code reduceTime}С�ڵ���0
     * @see BuffType
     * @see BuffEffect
     */
    public void remove(final BuffType type, final int reduceTime)
    {
        requireNonNull(type);
        if (reduceTime <= 0)
        {
            throw new IllegalArgumentException("�Ƿ�����:" + reduceTime);
        }

        if (buffNotExist(type))
        {
            throw new NullPointerException("Buff������:" + type);
        }

        var buff = haveBuffs.get(type);
        var originalTime = buff.getTimeLimit();

        //����Ƴ��Ļغϴ������еĻغ�, ��ֱ���Ƴ�
        if (originalTime <= reduceTime)
        {
            haveBuffs.remove(type);
            LOGGER.debug("�����غ�:{} С�ڵ��� �Ƴ�ʱ��:{}, ֱ���Ƴ�{}", originalTime, reduceTime, type);
        }
        else
        {
            LOGGER.debug("�����غ�:{} ���� �Ƴ�ʱ��:{}, �غ������", originalTime, reduceTime);
            buff.setTimeLimit(originalTime - reduceTime);
        }
    }
    private boolean buffNotExist(final BuffType type)
    {
        assert type != null;
        return !haveBuffs.containsKey(type);
    }

    /**
     * ��յ�λ��Я��������buffЧ��.
     *
     * @since 15
     */
    public final void clear()
    {
        haveBuffs.clear();
    }

    /**
     * ��յ�λ��Я�������и���Ч��
     *
     * <p>
     *     <font color="#FF0000">ע��:</font>
     * <strong>ֻ��{@link BuffEffect#isDebuff()}�����ķ���ֵΪ{@code true}��buff�Żᱻ���</strong>
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
     * @param o Ҫ����Ƿ���ȵĶ���
     * @return ��� {@code o}��this���, ����{@code true}
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
     * @return �ַ�����ʾ�Ķ���
     */
    @Override
    public String toString()
    {
        return "BasicBuffModule[" +
                "hasBuff:" + haveBuffs +
                ']';
    }
}
