package com.java.Tools;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @version 1.0.1
 * @author ǧ��
 */
public final class UnitTools
{
    private static final String[] extraAttribute =
    {
        "�ٷ����е�",
        "������װ�׵�",
        "���˵�"
    };

    /**
     * @throws NullPointerException ���{@code name}Ϊnull
     * @param name Ҫ��ǰ׺�ĵ�λ����
     * @return һ����������ǰ׺�ĵ�λ����
     */
    public static String randomExtraAttribute(String name)
    {
        if (name == null)
            throw new NullPointerException();

        var index = current().nextInt(extraAttribute.length);
        return extraAttribute[index] + name;
    }
}
