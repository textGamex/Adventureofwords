package com.java.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UiToolTest
{
    @Test
    void testGenerateUi()
    {
        String[] array = {"攻击", "防御"};
        var string = UiTool.generateUi(array);

        assertEquals("1.攻击   2.防御", string);
    }
}