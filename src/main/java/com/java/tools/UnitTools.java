package com.java.tools;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * �����ÿ������ά����һ����ͬ��ǰ׺��, Ĭ��Ϊ���������ظ�ǰ׺.
 *
 * @version 1.1.1
 * @author ǧ��
 * @since 2021-5-9
 */
public final class UnitTools
{
    public static void main(String[] args)
    {
    }
    private final List<String> extraAttributes = new ArrayList<>(16);
    private boolean repeatAllowed = false;

    {
        extraAttributes.add("�ٷ����е�");
        extraAttributes.add("������װ�׵�");
        extraAttributes.add("���˵�");
    }

    /**
     * ��ǰ׺�������ѡ��һ��ǰ׺�����{@code name}ĩβ������.
     *
     * @throws NullPointerException ���{@code name}Ϊnull
     * @param name Ҫ��ǰ׺�ĵ�λ����
     * @return һ����������ǰ׺�ĵ�λ����
     */
    public String randomExtraAttribute(String name)
    {
        requireNonNull(name);

        var index = current().nextInt(extraAttributes.size());
        return extraAttributes.get(index) + name;
    }

    /**
     * ���ش�ǰ׺�ص�ǰ׺��, �����ǰ׺�ذ������Integer.MAX_VALUEԪ�أ��򷵻�Integer.MAX_VALUE.
     *
     * <p>���ش�ǰ׺�ص�ǰ׺��, �����ǰ׺�ذ������Integer.MAX_VALUEԪ�أ��򷵻�Integer.MAX_VALUE.</p>
     * @return ���ش�ǰ׺�ص�ǰ׺��
     */
    public int size()
    {
        return extraAttributes.size();
    }

    /**
     * �������ô�ǰ׺���Ƿ��������ظ���ǰ׺, Ĭ��Ϊ���������ظ�ǰ׺.
     *
     * @param repeatAllowed Ϊ{@code true}ʱ�������ظ�ǰ׺, Ϊ{@code false}ʱ����������ظ�ǰ׺
     */
    public void setRepeatAllowed(boolean repeatAllowed)
    {
        this.repeatAllowed = repeatAllowed;
    }

    public boolean isRepeatAllowed()
    {
        return repeatAllowed;
    }

    /**
     * ��ǰ׺��������µ�ǰ׺, Ĭ�ϲ���������ظ���ǰ׺.
     *
     *<p>����ͨ��{@link UnitTools#setRepeatAllowed(boolean)}�������Ƿ�����ظ�</p>
     * @param extraAttribute Ҫ��ӵ���ǰ׺
     * @throws NullPointerException ���{@code extraAttribute}Ϊnull
     * @throws IllegalStateException ����˶���ز���������ظ�ǰ׺, ��ǰ׺�����Ѿ�����{@code extraAttribute}
     */
    public void add(String extraAttribute)
    {
        requireNonNull(extraAttribute);

        if (notAllowedRepetition())
        {
            if (extraAttributes.contains(extraAttribute))
                throw new IllegalStateException("��ǰ׺�ز���������ظ�ǰ׺");
        }
        extraAttributes.add(extraAttribute);
    }
    private boolean notAllowedRepetition()
    {
        return !repeatAllowed;
    }

    /**
     * ����һ��������ǰ׺��������ǰ׺������.
     *
     * @return ����һ���ַ�������
     */
    public String[] toArrays()
    {
        return extraAttributes.toArray(new String[0]);
    }

    /**
     * �Ӵ�ǰ׺����ɾ������ǰ׺.
     */
    public void clear()
    {
        extraAttributes.clear();
    }

    /**
     * @param extraAttribute Ҫ�ж��Ƿ��Ѿ����ڵ�ǰ׺
     * @return ���ǰ׺���д��� {@code extraAttribute}����{@code true}, ���򷵻�{@code false}
     * @throws NullPointerException ���{@code extraAttribute}Ϊnull
     */
    public Boolean have(String extraAttribute)
    {
        return extraAttributes.contains(requireNonNull(extraAttribute));
    }
}