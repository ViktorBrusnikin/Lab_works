package ru.ssau.tk._viktor_._lab2_.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testToString() {
        double tempX = 1.5;
        double tempY = 3.2;

        Node test = new Node(tempX, tempY);
        assertEquals("(1.5 ; 3.2)", test.toString());
    }

    @Test
    void testEquals() {
        double tempX = 3.5;
        double tempY = 6.1;

        Node test1 = new Node(tempX, tempY);
        Node test2 = new Node(tempX, tempY);
        Node test3 = new Node(0, 0);
        assertEquals(true, test1.equals(test2));
        assertEquals(false, test1.equals(test3));
    }

    @Test
    void testHashCode() {
        double tempX = 1.5;
        double tempY = 3.2;

        Node test = new Node(tempX, tempY);
        assertEquals(2, test.hashCode());
    }

    @Test
    void testClone() {
        double tempX = 1.5;
        double tempY = 3.2;

        Node test = new Node(tempX, tempY);
        Node result = test.clone();
        assertEquals(result, test);
        assertNotSame(result, test);
    }

}