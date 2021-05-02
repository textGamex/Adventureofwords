package com.java.CombatSystem.BuffModule;

import java.io.Serial;
import java.io.Serializable;

/**
 * ��������buff�ľ���Ч��
 *
 * @see BuffModule
 * @see BuffType
 * @version 1.3.5
 * @since 15
 */
public final class BuffEffect implements Serializable
{
    @Serial
    private static final long serialVersionUID = 6938381291107023911L;
    /**�����غ�*/
    private int timeLimit;
    /**Ч������*/
    private int layers;
    /**�Ƿ���Debuff*/
    private final boolean debuff;
    /**�Ƿ��Ǳ���*/
    private final boolean timeLess;

    /**
     * @param timeLimit Ч�������غ�
     * @param layers Ч������
     * @param debuff ��debuff
     * @param timeLess ������
     * @throws IllegalArgumentException ���{@code timeLimit}��{@code layers}С�ڵ���0
     */
    public BuffEffect(int timeLimit, int layers, boolean debuff, boolean timeLess)
    {
        if (timeLimit <= 0)
            throw new IllegalArgumentException("�Ƿ�����,timeLimit:" + timeLimit);
        if (layers <= 0)
            throw new IllegalArgumentException("�Ƿ�����,layers:" + layers);
        this.timeLimit = timeLimit;
        this.layers = layers;
        this.debuff = debuff;
        this.timeLess = timeLess;
    }

    /**
     * {@code timeLess}Ĭ��Ϊ{@code false}
     *
     * @param timeLimit Ч�������غ�
     * @param layers Ч������
     * @param isDebuff ��debuff
     */
    public BuffEffect(int timeLimit, int layers, boolean isDebuff)
    {
        this(timeLimit, layers, isDebuff, false);
    }

    /**
     * {@code timeLess}��{@code debug}Ĭ��Ϊ{@code false}
     *
     * @param timeLimit Ч�������غ�
     * @param layers Ч������
     */
    public BuffEffect(int timeLimit, int layers)
    {
        this(timeLimit, layers, false, false);
    }

    public int getTimeLimit()
    {
        return timeLimit;
    }

    public int getLayers()
    {
        return layers;
    }

    public boolean isDebuff()
    {
        return debuff;
    }

    public boolean isTimeLess()
    {
        return timeLess;
    }

    public void setTimeLimit(int timeLimit)
    {
        this.timeLimit = timeLimit;
    }

    public void setLayers(int layers)
    {
        this.layers = layers;
    }

    @Override
    public String toString()
    {
        return "BuffMessage[" +
                "�����غ�:" + timeLimit +
                ", Ч������:" + layers +
                ", Debuff:" + debuff +
                ", ����:" + timeLess +
                ']';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuffEffect that = (BuffEffect) o;
        return timeLimit == that.timeLimit && layers == that.layers && debuff == that.debuff && timeLess == that.timeLess;
    }
}
