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
    public static void main(String[] args)
    {

    }
    private final List<String> extraAttributes = new ArrayList<>(16);
    private boolean repeatAllowed = false;

    {
        extraAttributes.add("百发百中的");
        extraAttributes.add("带附加装甲的");
        extraAttributes.add("幸运的");
    }

    /**
     * 从前缀池中随机选择一个前缀添加在{@code name}末尾并返回.
     *
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
     * 返回此前缀池的前缀数, 如果此前缀池包含多个Integer.MAX_VALUE元素，则返回Integer.MAX_VALUE.
     *
     * <p>返回此前缀池的前缀数, 如果此前缀池包含多个Integer.MAX_VALUE元素，则返回Integer.MAX_VALUE.</p>
     * @return 返回此前缀池的前缀数
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
     * 往前缀池中添加新的前缀.
     *
     * @param extraAttribute 要添加的新前缀
     * @throws NullPointerException 如果{@code extraAttribute}为null
     */
    public void add(String extraAttribute)
    {
        extraAttributes.add(requireNonNull(extraAttribute));
    }

    /**
     *
     * @return
     */
    public String[] toArrays()
    {
        return (String[]) extraAttributes.toArray();
    }

    /**
     * 从此前缀池中删除所有前缀.
     */
    public void clear()
    {
        extraAttributes.clear();
    }
}