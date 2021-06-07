package com.java.tools;

import static java.util.Objects.requireNonNull;

/**
 * @author 留恋千年
 * @since 2021-6-7
 * @version 1.0.0
 */
public final class UiTool
{
    private UiTool()
    {
        throw new NullPointerException();
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
}
