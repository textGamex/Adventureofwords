package com.java.tools;

import static java.util.Objects.requireNonNull;

/**
 * 用于构建UI的工具.
 *
 * @author 留恋千年
 * @since 2021-6-7
 * @version 1.0.0
 */
public final class UiTool
{
    private UiTool()
    {
        throw new AssertionError();
    }

    /**
     * 根据传入的字符串数组来链接生成一个UI界面.
     *
     * @param test UI文字
     * @return UI
     * @throws NullPointerException 如果{@code test}或数组中的元素为null
     */
    public static String generateUi(final String[] test)
    {
        requireNonNull(test);
        final var uiText = new StringBuilder(10);

        for (int i = 0, testLength = test.length; i < testLength; i++)
        {
            uiText.append(i + 1).append(".").append(requireNonNull(test[i])).append("   ");
        }
        return uiText.toString().strip();
    }

    /**
     * 根据传入的字符和数量自动打印分隔符.
     *
     * @param separatorCharacter 要打印的分隔符
     * @param count 要打印的数量
     * @throws IllegalArgumentException 如果{@code length}为负
     * @throws NullPointerException 如果{@code separatorCharacter}为null
     */
    public static void separator(final String separatorCharacter, final int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException();
        }
        System.out.println(requireNonNull(separatorCharacter).repeat(count));
    }
}
