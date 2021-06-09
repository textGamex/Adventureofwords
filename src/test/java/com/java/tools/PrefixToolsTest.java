package com.java.tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrefixToolsTest
{
    private PrefixTools attribute;

    @BeforeEach
    void setUp()
    {
        attribute = new PrefixTools();
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

    @Test
    void CalculateReturnProbability()
    {
        attribute.add("000");

        assertEquals(1.0, attribute.calculateReturnProbability("000"));
        assertEquals(0.0, attribute.calculateReturnProbability("001"));
    }

    @Test
    void CalculateReturnProbabilityNoAddition()
    {
        assertEquals(0.0, attribute.calculateReturnProbability("000"));
    }

    @Test
    void testCalculateReturnProbability()
    {
        attribute.setRepeatAllowed(true);
        attribute.add("000");
        attribute.add("000");
        attribute.add("001");
        attribute.add("002");
        attribute.add("003");

        final var return003Probability  = attribute.calculateReturnProbability("003");
        final var return000Probability  = attribute.calculateReturnProbability("000");
        final var return005Probability  = attribute.calculateReturnProbability("005");

        assertEquals(0.2, return003Probability);
        assertEquals(0.4, return000Probability);
        assertEquals(0.0, return005Probability);
    }

    @Test
    void CalculateReturnProbabilityException()
    {
       assertThrows(NullPointerException.class, () -> attribute.calculateReturnProbability(null));
    }

    @Test
    void textSum()
    {
        attribute.setRepeatAllowed(true);
        attribute.add("000");
        attribute.add("000");

        final var sum1 = attribute.sum("000");
        final var sum2 = attribute.sum("002");

        assertEquals(2, sum1);
        assertEquals(0, sum2);
        assertThrows(NullPointerException.class, () -> attribute.sum(null));
    }

    @Test
    void testToNumberOfOccurrencesArray()
    {
        attribute.setRepeatAllowed(true);
        attribute.add("sss");
        attribute.add("sss");
        attribute.add("sss");
        attribute.add("aaa");
        attribute.add("aaa");
        attribute.add("mmm");

        var num = attribute.toNumberOfOccurrencesArray();

        assertEquals(3, num.get(0).getValue());
        assertEquals(2, num.get(1).getValue());
        assertEquals(1, num.get(2).getValue());
    }

    @Test
    void testRemoveIf()
    {
        attribute.setRepeatAllowed(true);
        attribute.add("sss");
        attribute.add("sss");
        attribute.add("sss");
        attribute.add("aaa");
        attribute.add("aaa");
        attribute.add("mmm");

        attribute.removeIf("sss");
        attribute.removeIf("mmm");

        assertFalse(attribute.have("sss"));
        assertFalse(attribute.have("mmm"));
        assertTrue(attribute.have("aaa"));
    }
}