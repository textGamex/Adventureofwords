package com.java.Tools;

import static java.util.concurrent.ThreadLocalRandom.current;
public final class UnitTools
{
    private static final String[] extraAttribute =
    {
        "百发百中的",
        "带附加装甲的",
        "幸运的"
    };

    public static String randomExtraAttribute(String name)
    {
        if (name == null)
            throw new NullPointerException();

        var index = current().nextInt(extraAttribute.length);
        return extraAttribute[index] + name;
    }
}
