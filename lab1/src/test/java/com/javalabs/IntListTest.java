package com.javalabs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class IntListTest {

    @Test
    void add() {
        IntList list = new IntList();
        list.add(1);
        list.add(5);
        list.add(2);
        Assertions.assertEquals("[1, 5, 2]", list.toString());
    }

    @Test
    void get() {
        IntList list = new IntList();
        list.add(1);
        list.add(5);
        list.add(2);
        Assertions.assertEquals(5, list.get(1));
    }

    @Test
    void remove() {
        IntList list = new IntList();
        list.add(1);
        list.add(5);
        list.add(2);
        Assertions.assertEquals(5, list.remove(1));
        Assertions.assertEquals("[1, 2]", list.toString());
        Assertions.assertEquals(1, list.remove(0));
        Assertions.assertEquals("[2]", list.toString());
        Assertions.assertEquals(2, list.remove(0));
        Assertions.assertEquals("[]", list.toString());
    }

    @Test
    void size() {
        IntList list = new IntList();
        list.add(1);
        Assertions.assertEquals(1, list.size());
        list.add(5);
        Assertions.assertEquals(2, list.size());
        list.add(2);
        Assertions.assertEquals(3, list.size());
    }

    @Test
    void isEmpty() {
        IntList list = new IntList();
        Assertions.assertTrue(list.isEmpty());
        list.add(1);
        Assertions.assertFalse(list.isEmpty());
        list.remove(0);
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    void testToString() {
        IntList list = new IntList();
        Assertions.assertEquals("[]", list.toString());
        list.add(1);
        list.add(2);
        Assertions.assertEquals("[1, 2]", list.toString());
    }

    @Test
    void clear() {
        IntList list = new IntList();
        list.add(1);
        list.clear();
        Assertions.assertEquals("[]", list.toString());
        Assertions.assertEquals(0, list.size());
    }
}