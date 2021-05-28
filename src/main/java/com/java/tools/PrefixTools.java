package com.java.tools;

import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * �����ÿ������ά����һ����ͬ��ǰ׺��, Ĭ��Ϊ���������ظ�ǰ׺.
 *
 * @version 1.1.2
 * @author ǧ��
 * @since 2021-5-9
 */
public final class PrefixTools
{
    public static void main(String[] args)
    {
        var a = new PrefixTools();
        a.setRepeatAllowed(true);
        a.add("1");
        a.add("1");
        a.add("3");
        System.out.println(a.toUsageProbabilityString());
    }
    private final List<String> extraAttributes = new ArrayList<>(16);
    private boolean repeatAllowed = false;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PrefixTools.class);

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
     *<p>����ͨ��{@link PrefixTools#setRepeatAllowed(boolean)}�������Ƿ�����ظ�</p>
     * @param extraAttribute Ҫ��ӵ���ǰ׺
     * @throws NullPointerException ���{@code extraAttribute}Ϊnull
     * @throws IllegalStateException ����˶���ز���������ظ�ǰ׺, ��ǰ׺�����Ѿ�����{@code extraAttribute}
     */
    public void add(final String extraAttribute)
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

    public double calculateReturnProbability(final String prefix)
    {
        requireNonNull(prefix);
        if (extraAttributes.size() == 1 && extraAttributes.contains(prefix))
            return 1.0;
        if (extraAttributes.size() == 0 || !extraAttributes.contains(prefix))
            return 0.0;

        return (double) this.sum(prefix) / extraAttributes.size();
    }

    /**
     * �˶����Ԫ�س��� {@code prefix}������.
     *
     * @param prefix Ҫ����������Ԫ��
     * @return �����ڴ˶����Ԫ�س��� {@code prefix}������
     * @throws NullPointerException ���{@code prefix}Ϊnull
     */
    public int sum(final String prefix)
    {
        requireNonNull(prefix);

        int count = 0;
        for (final var e : extraAttributes)
            if (e.equals(prefix))
                ++count;
        LOGGER.trace("����Ԫ��:{}, ���ҵ�{}��", prefix, count);
        return count;
    }

    public String[] toUsageProbabilityStringArray()
    {
        return null;
    }

    public String toUsageProbabilityString()
    {
        final int END = extraAttributes.size();
        int count = 0;
        final var string = new StringBuilder("[");
        for (final String s : extraAttributes)
        {
            count++;
            string.append("Ԫ��:").append(s).append(", ��ռ����:").append(String.format("%.3f", calculateReturnProbability(s)));
            if (count != END)
                string.append(", ");
        }
        return string.append("]").toString();
    }

    @Override
    public String toString()
    {
        return "PrefixTools[" +
                "ǰ׺:" + extraAttributes +
                ", �����ظ�Ԫ��:" + repeatAllowed +
                ']';
    }
}