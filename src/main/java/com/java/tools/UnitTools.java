package com.java.tools;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @version 1.1.0
 * @author ǧ��
 * @since 2021-5-9
 */
public final class UnitTools
{
    private final List<String> extraAttributes = new ArrayList<>(16);
    private boolean repeatAllowed = false;

    {
        extraAttributes.add("�ٷ����е�");
        extraAttributes.add("������װ�׵�");
        extraAttributes.add("���˵�");
    }

    /**
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
     * ���ش��б��е�Ԫ������ ������б�������Integer.MAX_VALUEԪ�أ��򷵻�Integer.MAX_VALUE.
     *
     * @return ���ش��б��е�Ԫ����
     */
    public int size()
    {
        return extraAttributes.size();
    }

    public void setRepeatAllowed(boolean repeatAllowed)
    {
        this.repeatAllowed = repeatAllowed;
    }

    public boolean isRepeatAllowed()
    {
        return repeatAllowed;
    }

    /**
     * ������ǰ׺��������µ�����ǰ׺.
     *
     * @param extraAttribute Ҫ��ӵ�������ǰ׺
     * @throws NullPointerException ���{@code extraAttribute}Ϊnull
     */
    public void add(String extraAttribute)
    {
        extraAttributes.add(requireNonNull(extraAttribute));
    }
}