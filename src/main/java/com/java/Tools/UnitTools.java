package com.java.Tools;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @version 1.0.1
 * @author 千年
 */
public final class UnitTools
{
    private static final String[] extraAttribute =
    {
        "百发百中的",
        "带附加装甲的",
        "幸运的"
    };

    /**
     * @throws NullPointerException 如果{@code name}为null
     * @param name 要加前缀的单位名称
     * @return 一个添加了随机前缀的单位名称
     */
    public static String randomExtraAttribute(String name)
    {
        if (name == null)
            throw new NullPointerException();

        var index = current().nextInt(extraAttribute.length);
        return extraAttribute[index] + name;
    }
}
