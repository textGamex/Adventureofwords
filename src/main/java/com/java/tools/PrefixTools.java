package com.java.tools;

import org.slf4j.LoggerFactory;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * 此类的每个对象维护着一个不同的前缀池, 默认为不允许有重复前缀.
 *
 * @version 1.3.1
 * @author 千年
 * @since 2021-5-9
 */
public final class PrefixTools
{
    public static void main(String[] args)
    {
        final var attribute = new PrefixTools();
        attribute.setRepeatAllowed(true);
        attribute.add("sss");
        attribute.add("sss");
        attribute.add("sss");
        attribute.add("aaa");
        attribute.add("aaa");
        attribute.add("mmm");

        System.out.println(attribute.toRandomToProbabilityString());
    }
    private final List<String> extraAttributes = new ArrayList<>(16);
    private boolean repeatAllowed = false;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PrefixTools.class);

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
     *<p>可以通过{@link PrefixTools#setRepeatAllowed(boolean)}来更改是否可以重复</p>
     * @param extraAttribute 要添加的新前缀
     * @throws NullPointerException 如果{@code extraAttribute}为null
     * @throws IllegalStateException 如果此对象池不允许存在重复前缀, 但前缀池中已经存在{@code extraAttribute}
     */
    public void add(final String extraAttribute)
    {
        requireNonNull(extraAttribute);

        if (notAllowedRepetition())
        {
            if (extraAttributes.contains(extraAttribute))
            {
                throw new IllegalStateException("此前缀池不允许存在重复前缀");
            }
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

    public double calculateReturnProbability(final String prefix)
    {
        requireNonNull(prefix);
        if (extraAttributes.size() == 1 && extraAttributes.contains(prefix))
        {
            return 1.0;
        }
        if (extraAttributes.size() == 0 || !extraAttributes.contains(prefix))
        {
            return 0.0;
        }

        return (double) this.sum(prefix) / extraAttributes.size();
    }

    /**
     * 此对象的元素池中 {@code prefix}的总数.
     *
     * @param prefix 要计算总数的元素
     * @return 返回在此对象的元素池中 {@code prefix}的总数
     * @throws NullPointerException 如果{@code prefix}为null
     * @since 2021-5-28
     */
    public int sum(final String prefix)
    {
        requireNonNull(prefix);

        int count = 0;
        for (final var e : extraAttributes)
        {
            if (e.equals(prefix))
            {
                ++count;
            }
        }
        LOGGER.trace("查找元素:{}, 共找到{}个", prefix, count);
        return count;
    }

    public String[] toUsageProbabilityStringArray()
    {
        return null;
    }

    /**
     *
     * @return
     */
    public String toRandomToProbabilityString()
    {
        final int end = extraAttributes.size();
        int count = 0;

        final var string = new StringBuilder("[");
        for (final var s : toNumberOfOccurrencesArray())
        {
            count++;
            string.append("元素:").append(s.getKey()).append(", 所占比例:").append(
                    String.format("%.3f", (double) s.getValue() / end));
            if (count != end)
            {
                string.append(", ");
            }
        }
        return string.append("]").toString();
    }

    /**
     * 此前缀池按出现次数倒序排序的{@code List}.
     *
     * @since 2021-5-29
     * @return 返回此前缀池按出现次数倒序排序的 {@code List}, {@code Map}的键为元素池中的元素, 值为对应的出现次数
     */
    protected List<Map.Entry<String, Integer>> toNumberOfOccurrencesArray()
    {
        final Map<String, Integer> countMap = new HashMap<>(16);
        for (final String s : extraAttributes)
        {
            countMap.merge(s, 1, Integer::sum);
            LOGGER.trace("元素池值:{}", s);
        }

        final List<Map.Entry<String, Integer>> list = new ArrayList<>(countMap.entrySet());
        //降序排序
        list.sort(Map.Entry.comparingByValue((num1, num2) -> num2.compareTo(num1)));
        for (final var e : list)
        {
            LOGGER.trace("键:{}, 值:{}", e.getKey(),  e.getValue());
        }

        return list;
    }

    @Override
    public String toString()
    {
        return "PrefixTools[" +
                "前缀:" + extraAttributes +
                ", 允许重复元素:" + repeatAllowed +
                ']';
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        final PrefixTools that = (PrefixTools) o;
        return repeatAllowed == that.repeatAllowed && extraAttributes.equals(that.extraAttributes);
    }

    @Override
    public int hashCode()
    {
        int result = extraAttributes.hashCode();
        result = 31 * result + Boolean.hashCode(repeatAllowed);
        return result;
    }
}