package com.java.tools;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @version 1.1.0
 * @author 千年
 * @since 2021-5-9
 */
public final class UnitTools
{
    private final List<String> extraAttributes = new ArrayList<>(16);
    private boolean repeatAllowed = false;

    {
        extraAttributes.add("百发百中的");
        extraAttributes.add("带附加装甲的");
        extraAttributes.add("幸运的");
    }

    /**
     * @throws NullPointerException 如果{@code name}为null
     * @param name 要加前缀的单位名称
     * @return 一个添加了随机前缀的单位名称
     */
    public String randomExtraAttribute(String name)
    {
        requireNonNull(name);

        var index = current().nextInt(extraAttributes.size());
        return extraAttributes.get(index) + name;
    }

    /**
     * 返回此列表中的元素数。 如果此列表包含多个Integer.MAX_VALUE元素，则返回Integer.MAX_VALUE.
     *
     * @return 返回此列表中的元素数
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
     * 往属性前缀池中添加新的属性前缀.
     *
     * @param extraAttribute 要添加的新属性前缀
     * @throws NullPointerException 如果{@code extraAttribute}为null
     */
    public void add(String extraAttribute)
    {
        extraAttributes.add(requireNonNull(extraAttribute));
    }
}