package com.java.tools;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * 此类的每个对象维护着一个不同的前缀池, 默认为不允许有重复前缀.
 *
 * @version 1.1.1
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

    /**
     * 用来设置此前缀池是否允许有重复的前缀, 默认为不允许有重复前缀.
     *
     * @param repeatAllowed 为{@code true}时允许有重复前缀, 为{@code false}时不允许存在重复前缀
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
     * 往前缀池中添加新的前缀, 默认不允许添加重复的前缀.
     *
     *<p>可以通过{@link UnitTools#setRepeatAllowed(boolean)}来更改是否可以重复</p>
     * @param extraAttribute 要添加的新前缀
     * @throws NullPointerException 如果{@code extraAttribute}为null
     * @throws IllegalStateException 如果此对象池不允许存在重复前缀, 但前缀池中已经存在{@code extraAttribute}
     */
    public void add(String extraAttribute)
    {
        requireNonNull(extraAttribute);

        if (notAllowedRepetition())
        {
            if (extraAttributes.contains(extraAttribute))
                throw new IllegalStateException("此前缀池不允许存在重复前缀");
        }
        extraAttributes.add(extraAttribute);
    }
    private boolean notAllowedRepetition()
    {
        return !repeatAllowed;
    }

    /**
     * 返回一个包含此前缀池中所有前缀的数组.
     *
     * @return 返回一个字符串数组
     */
    public String[] toArrays()
    {
        return extraAttributes.toArray(new String[0]);
    }

    /**
     * 从此前缀池中删除所有前缀.
     */
    public void clear()
    {
        extraAttributes.clear();
    }

    /**
     * @param extraAttribute 要判断是否已经存在的前缀
     * @return 如果前缀池中存在 {@code extraAttribute}返回{@code true}, 否则返回{@code false}
     * @throws NullPointerException 如果{@code extraAttribute}为null
     */
    public Boolean have(String extraAttribute)
    {
        return extraAttributes.contains(requireNonNull(extraAttribute));
    }
}