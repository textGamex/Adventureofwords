package com.java.tools;

import static java.util.Objects.requireNonNull;

/**
 * @author ����ǧ��
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
     * ���ݴ�����ַ�����������������һ��UI����.
     *
     * @param test UI����
     * @return UI
     * @throws NullPointerException ���{@code test}�������е�Ԫ��Ϊnull
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
