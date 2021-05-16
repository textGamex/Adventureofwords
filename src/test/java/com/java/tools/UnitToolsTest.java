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

        attribute.add("不允许重复的测试");

        assertThrows(NullPointerException.class, () -> attribute.add(null));
        assertThrows(IllegalStateException.class, () -> attribute.add("不允许重复的测试"));

        attribute.setRepeatAllowed(true);
        attribute.add("允许重复的测试");

        assertDoesNotThrow(() ->attribute.add("允许重复的测试"));
    }
}