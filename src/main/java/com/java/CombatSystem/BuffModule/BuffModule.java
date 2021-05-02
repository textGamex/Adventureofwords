package com.java.CombatSystem.BuffModule;

import java.io.Serial;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Objects;
import static java.util.Objects.requireNonNull;

/**
 * Buffģ��, ���ں�{@code BasicUnit}�ཻ��
 *
 * <p>����ʵ����Ϸ��buff����</p>
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
     * ���buff, ���Ҫ��ӵ��Ѵ���, ��time��layer����2Ȼ����Ѵ��ڵ�buffEffect��time��layer���
     * @param type buff����
     * @param buffEffect buff�ľ���Ч��
     * @since 15
     * @throws NullPointerException ���{@code buffEffect}��{@code type}Ϊnull
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
     * @throws NullPointerException ���{@code type}Ϊnull��{@code type}������
     * @return ��buff��Ч����Ϣ
     * @param type buff������
     */
    public final BuffEffect getMessage(BuffType type)
    {
        if (buffNotExist(requireNonNull(type)))
            throw new NullPointerException("buff������:" + type);

        return haveBuffs.get(type);
    }

    /**
     * @param aBuffType buff����
     * @throws NullPointerException ���{@code aBuffType}Ϊnull
     * @return �������, ���� {@code true}, ���򷵻�{@code false}
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
     * �˷����Ƕ�{@link EnumMap#size()}�İ�װ
     * @return �˵�λ���е�buff����
     */
    public int size()
    {
        return haveBuffs.size();
    }

    /**
     * �Ӿ��е�buff���Ƴ���һ���ض���buff
     *
     * @param type Ҫ�Ƴ���buff����
     * @throws NullPointerException ���{@code type}Ϊnull����Ҫ�Ƴ�buff������
     */
    public void remove(BuffType type)
    {
        if (buffNotExist(requireNonNull(type)))
            throw new NullPointerException("Buff������:" + type);
        haveBuffs.remove(type);
    }

    /**
     * �Ƴ��ض�buff�ĳ����غ���, ����Ƴ��Ĵ��ڵ������еĻغ���, ��ֱ���Ƴ�buff
     *
     * @param type Ҫ�Ƴ���buff����
     * @param reduceTime Ҫ�Ƴ���buff�Ļغ���
     * @throws NullPointerException ���{@code type}Ϊnull
     */
    public void remove(BuffType type, int reduceTime)
    {
        requireNonNull(type);
        if (reduceTime < 0)
            throw new IllegalArgumentException("�Ƿ�����:" + reduceTime);

        if (buffNotExist(type))
            throw new NullPointerException("Buff������:" + type);

        var buff = haveBuffs.get(type);
        var originalTime = buff.getTimeLimit();

        //����Ƴ��Ļغϴ������еĻغ�, ��ֱ���Ƴ�
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
     * �˷����Ƕ�{@link EnumMap#clear()}�����İ�װ
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
