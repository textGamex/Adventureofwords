package com.java.tools;

import static java.util.Objects.requireNonNull;

/**
 * ���ڹ���UI�Ĺ���.
 *
 * @author ����ǧ��
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

    /**
     * ���ݴ�����ַ��������Զ���ӡ�ָ���.
     *
     * @param separatorCharacter Ҫ��ӡ�ķָ���
     * @param count Ҫ��ӡ������
     * @throws IllegalArgumentException ���{@code length}Ϊ��
     * @throws NullPointerException ���{@code separatorCharacter}Ϊnull
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
