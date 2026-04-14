package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
 

/**
 * JUnit 5 test suite for {@link IntegerSet}.
 * Covers every public method with at least one normal case and one edge case.
 */
@DisplayName("IntegerSet Test Suite")
public class IntegerSetTest {

    private IntegerSet setA;
    private IntegerSet setB;

    /** Fresh sets before every test. */
    @BeforeEach
    public void setUp() {
        setA = new IntegerSet();
        setB = new IntegerSet();
    }

    // -----------------------------------------------------------------------
    // clear()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("clear() - normal: removes all elements from non-empty set")
    public void testClearNormal() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setA.clear();
        assertEquals(0, setA.length(), "After clear(), length should be 0");
        assertTrue(setA.isEmpty(), "After clear(), set should be empty");
    }

    @Test
    @DisplayName("clear() - edge: clearing an already-empty set is a no-op")
    public void testClearAlreadyEmpty() {
        setA.clear(); // should not throw
        assertTrue(setA.isEmpty());
        assertEquals(0, setA.length());
    }

    // -----------------------------------------------------------------------
    // length()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("length() - normal: returns correct count for non-empty set")
    public void testLengthNormal() {
        setA.add(10);
        setA.add(20);
        setA.add(30);
        assertEquals(3, setA.length());
    }

    @Test
    @DisplayName("length() - edge: returns 0 for empty set")
    public void testLengthEmpty() {
        assertEquals(0, setA.length());
    }

    // -----------------------------------------------------------------------
    // isEmpty()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("isEmpty() - edge: returns true for empty set")
    public void testIsEmptyTrue() {
        assertTrue(setA.isEmpty());
    }

    @Test
    @DisplayName("isEmpty() - normal: returns false for non-empty set")
    public void testIsEmptyFalse() {
        setA.add(5);
        assertFalse(setA.isEmpty());
    }

    // -----------------------------------------------------------------------
    // contains()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("contains() - normal: returns true when value is present")
    public void testContainsPresent() {
        setA.add(7);
        assertTrue(setA.contains(7));
    }

    @Test
    @DisplayName("contains() - edge: returns false when value is not present")
    public void testContainsAbsent() {
        setA.add(7);
        assertFalse(setA.contains(99));
    }

    // -----------------------------------------------------------------------
    // add()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("add() - normal: adds a new element, size increases")
    public void testAddNormal() {
        setA.add(42);
        assertTrue(setA.contains(42));
        assertEquals(1, setA.length());
    }

    @Test
    @DisplayName("add() - edge: duplicate value is not added twice")
    public void testAddDuplicate() {
        setA.add(5);
        setA.add(5);
        assertEquals(1, setA.length(), "Duplicate should not increase size");
        assertTrue(setA.contains(5));
    }

    // -----------------------------------------------------------------------
    // remove()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("remove() - normal: removes an existing element")
    public void testRemoveNormal() {
        setA.add(3);
        setA.add(6);
        setA.remove(3);
        assertFalse(setA.contains(3));
        assertEquals(1, setA.length());
    }

    @Test
    @DisplayName("remove() - edge: removing a value not in the set is a no-op")
    public void testRemoveAbsent() {
        setA.add(3);
        setA.remove(99); // not present — should not throw
        assertEquals(1, setA.length());
        assertTrue(setA.contains(3));
    }

    // -----------------------------------------------------------------------
    // largest()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("largest() - normal: returns the maximum element")
    public void testLargestNormal() {
        setA.add(4);
        setA.add(9);
        setA.add(2);
        assertEquals(9, setA.largest());
    }

    @Test
    @DisplayName("largest() - edge: single-element set returns that element")
    public void testLargestSingleElement() {
        setA.add(42);
        assertEquals(42, setA.largest());
    }

    @Test
    @DisplayName("largest() - edge: throws RuntimeException on empty set")
    public void testLargestEmptyThrows() {
        assertThrows(RuntimeException.class, () -> setA.largest(),
                "largest() on empty set must throw RuntimeException");
    }

    // -----------------------------------------------------------------------
    // smallest()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("smallest() - normal: returns the minimum element")
    public void testSmallestNormal() {
        setA.add(4);
        setA.add(9);
        setA.add(2);
        assertEquals(2, setA.smallest());
    }

    @Test
    @DisplayName("smallest() - edge: single-element set returns that element")
    public void testSmallestSingleElement() {
        setA.add(7);
        assertEquals(7, setA.smallest());
    }

    @Test
    @DisplayName("smallest() - edge: throws RuntimeException on empty set")
    public void testSmallestEmptyThrows() {
        assertThrows(RuntimeException.class, () -> setA.smallest(),
                "smallest() on empty set must throw RuntimeException");
    }

    // -----------------------------------------------------------------------
    // equals()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("equals() - normal: two sets with the same elements are equal")
    public void testEqualsNormal() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setB.add(1);
        setB.add(2);
        setB.add(3);
        assertTrue(setA.equals(setB));
    }

    @Test
    @DisplayName("equals() - edge: same elements in different insertion order are equal")
    public void testEqualsDifferentOrder() {
        setA.add(3);
        setA.add(1);
        setA.add(2);
        setB.add(1);
        setB.add(2);
        setB.add(3);
        assertTrue(setA.equals(setB));
    }

    @Test
    @DisplayName("equals() - edge: sets with different elements are not equal")
    public void testEqualsMismatch() {
        setA.add(1);
        setA.add(2);
        setB.add(1);
        setB.add(99);
        assertFalse(setA.equals(setB));
    }

    @Test
    @DisplayName("equals() - edge: sets of different sizes are not equal")
    public void testEqualsDifferentSize() {
        setA.add(1);
        setA.add(2);
        setB.add(1);
        assertFalse(setA.equals(setB));
    }

    // -----------------------------------------------------------------------
    // union()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("union() - normal: result contains all elements from both sets")
    public void testUnionNormal() {
        setA.add(1);
        setA.add(2);
        setB.add(3);
        setB.add(4);
        IntegerSet result = setA.union(setB);
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertEquals(4, result.length());
    }

    @Test
    @DisplayName("union() - edge: union with empty set returns copy of original")
    public void testUnionWithEmptySet() {
        setA.add(1);
        setA.add(2);
        IntegerSet result = setA.union(setB); // setB is empty
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertEquals(2, result.length());
    }

    @Test
    @DisplayName("union() - edge: overlapping elements are not duplicated")
    public void testUnionOverlap() {
        setA.add(1);
        setA.add(2);
        setB.add(2);
        setB.add(3);
        IntegerSet result = setA.union(setB);
        assertEquals(3, result.length());
    }

    // -----------------------------------------------------------------------
    // intersect()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("intersect() - normal: returns common elements only")
    public void testIntersectNormal() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setB.add(2);
        setB.add(3);
        setB.add(4);
        IntegerSet result = setA.intersect(setB);
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertFalse(result.contains(1));
        assertFalse(result.contains(4));
        assertEquals(2, result.length());
    }

    @Test
    @DisplayName("intersect() - edge: no common elements returns empty set")
    public void testIntersectNoOverlap() {
        setA.add(1);
        setA.add(2);
        setB.add(3);
        setB.add(4);
        IntegerSet result = setA.intersect(setB);
        assertTrue(result.isEmpty());
    }

    // -----------------------------------------------------------------------
    // diff()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("diff() - normal: returns elements in A but not in B")
    public void testDiffNormal() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setB.add(2);
        setB.add(3);
        IntegerSet result = setA.diff(setB);
        assertTrue(result.contains(1));
        assertFalse(result.contains(2));
        assertFalse(result.contains(3));
        assertEquals(1, result.length());
    }

    @Test
    @DisplayName("diff() - edge: identical sets produce an empty diff")
    public void testDiffIdenticalSets() {
        setA.add(1);
        setA.add(2);
        setB.add(1);
        setB.add(2);
        IntegerSet result = setA.diff(setB);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("diff() - edge: diff with empty set returns full copy of A")
    public void testDiffWithEmptySet() {
        setA.add(1);
        setA.add(2);
        IntegerSet result = setA.diff(setB); // setB empty
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertEquals(2, result.length());
    }

    // -----------------------------------------------------------------------
    // complement()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("complement() - normal: returns elements in B not in A")
    public void testComplementNormal() {
        setA.add(1);
        setA.add(2);
        setB.add(2);
        setB.add(3);
        setB.add(4);
        IntegerSet result = setA.complement(setB);
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertFalse(result.contains(1));
        assertFalse(result.contains(2));
        assertEquals(2, result.length());
    }

    @Test
    @DisplayName("complement() - edge: disjoint sets — complement equals all of B")
    public void testComplementDisjointSets() {
        setA.add(1);
        setA.add(2);
        setB.add(3);
        setB.add(4);
        IntegerSet result = setA.complement(setB);
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertEquals(2, result.length());
    }

    // -----------------------------------------------------------------------
    // toString()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("toString() - normal: elements appear in ascending order")
    public void testToStringNormal() {
        setA.add(3);
        setA.add(1);
        setA.add(2);
        assertEquals("[1, 2, 3]", setA.toString());
    }

    @Test
    @DisplayName("toString() - edge: empty set prints as []")
    public void testToStringEmpty() {
        assertEquals("[]", setA.toString());
    }

    @Test
    @DisplayName("toString() - edge: single element prints without trailing comma")
    public void testToStringSingleElement() {
        setA.add(5);
        assertEquals("[5]", setA.toString());
    }
}