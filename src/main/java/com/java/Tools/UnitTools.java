package com.java.Tools;

import static java.util.concurrent.ThreadLocalRandom.current;
public final class UnitTools
{
    private static final String[] extraAttribute =
    {
        "�ٷ����е�",
        "������װ�׵�",
        "���˵�"
    };

    public static String randomExtraAttribute(String name)
    {
        if (name == null)
            throw new NullPointerException();

        var index = current().nextInt(extraAttribute.length);
        return extraAttribute[index] + name;
    }
}
