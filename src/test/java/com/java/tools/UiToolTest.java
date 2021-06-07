package com.java.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UiToolTest
{
    @Test
    void testGenerateUi()
    {
        String[] array = {"¹¥»÷", "·ÀÓù"};
        var string = UiTool.generateUi(array);

        assertEquals("1.¹¥»÷   2.·ÀÓù", string);
    }
}