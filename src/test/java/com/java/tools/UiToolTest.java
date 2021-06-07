package com.java.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UiToolTest
{
    @Test
    void testGenerateUi()
    {
        String[] array = {"����", "����"};
        var string = UiTool.generateUi(array);

        assertEquals("1.����   2.����", string);
    }
}