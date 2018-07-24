package org.usfirst.frc.team1764.robot.custom.util;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSelector
{
    private static Selector<MockSelectable> selector;

    @BeforeClass
    public static void initSelector()
    {
        selector = new Selector<>();
    }

    @Before
    public void clearSelector()
    {
        selector.selectNone();
        selector.clear();
    }

    @Test
    public void testClear()
    {
        selector.clear();
        assertEquals(0, selector.size());
    }

    @Test
    public void testSelectNone()
    {
        selector.selectNone();
        assertNull(selector.getSelected());
    }

    @Test
    public void testAdd()
    {
        selector.addAll(new MockSelectable());
        assertEquals(1, selector.size());
    }

    @Test
    public void testHasSelected()
    {
        selector.addAll(new MockSelectable());
        assertFalse(selector.hasSelected());
        selector.select("mock");
        assertTrue(selector.hasSelected());
    }

    @Test
    public void testIsSelected()
    {
        selector.addAll(new MockSelectable());
        assertFalse(selector.isSelected("mock"));
        selector.select("mock");
        assertTrue(selector.isSelected("mock"));
    }
}
