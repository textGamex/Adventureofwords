package com.java.tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitToolsTest
{
    private UnitTools attribute;

    @BeforeEach
    void setUp()
    {
        attribute = new UnitTools();
    }

    @Test
    void randomExtraAttribute()
    {
        assertThrows(NullPointerException.class, () -> attribute.randomExtraAttribute(null));
    }

    @Test
    void add()
    {

        attribute.add("�������ظ��Ĳ���");

        assertThrows(NullPointerException.class, () -> attribute.add(null));
        assertThrows(IllegalStateException.class, () -> attribute.add("�������ظ��Ĳ���"));

        attribute.setRepeatAllowed(true);
        attribute.add("�����ظ��Ĳ���");

        assertDoesNotThrow(() ->attribute.add("�����ظ��Ĳ���"));
    }
}