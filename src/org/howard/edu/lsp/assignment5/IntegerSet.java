package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Models a mathematical set of integers with no duplicate elements.
 * Backed internally by an {@link ArrayList}.
 * Supports standard set operations: union, intersection, difference, and complement.

 */
public class IntegerSet {

    /** Internal storage; no duplicates are ever inserted. */
    private ArrayList<Integer> set = new ArrayList<>();

    // -----------------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------------

    /** Creates an empty IntegerSet. */
    public IntegerSet() {}

    /**
     * Creates an IntegerSet pre-loaded with the given list.
     * Duplicates in the list are silently ignored.
     *
     * @param set ArrayList of integers to seed the set with
     */
    public IntegerSet(ArrayList<Integer> set) {
        for (int item : set) {
            add(item);
        }
    }

    // -----------------------------------------------------------------------
    // Basic operations
    // -----------------------------------------------------------------------

    /**
     * Removes all elements from this set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in this set.
     *
     * @return the size of the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns {@code true} if this set contains no elements.
     *
     * @return {@code true} if empty
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns {@code true} if this set contains the specified value.
     *
     * @param value the integer to look up
     * @return {@code true} if found
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Adds an integer to this set. If the value is already present,
     * the set is unchanged (no duplicates allowed).
     *
     * @param item the integer to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an integer from this set.
     * If the value is not present, the set is unchanged.
     *
     * @param item the integer to remove
     */
    public void remove(int item) {
        // Integer.valueOf avoids accidentally calling remove(int index)
        set.remove(Integer.valueOf(item));
    }

    // -----------------------------------------------------------------------
    // Min / Max
    // -----------------------------------------------------------------------

    /**
     * Returns the largest element in this set.
     *
     * @return the largest integer
     * @throws RuntimeException if the set is empty
     */
    public int largest() {
        if (isEmpty()) {
            throw new RuntimeException("Set is empty — cannot call largest()");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest element in this set.
     *
     * @return the smallest integer
     * @throws RuntimeException if the set is empty
     */
    public int smallest() {
        if (isEmpty()) {
            throw new RuntimeException("Set is empty — cannot call smallest()");
        }
        return Collections.min(set);
    }

    // -----------------------------------------------------------------------
    // Set operations — all return NEW IntegerSets; originals are untouched
    // -----------------------------------------------------------------------

    /**
     * Returns a new set containing every element that appears in either
     * this set or {@code intSetb} (A ∪ B).
     *
     * @param intSetb the other IntegerSet
     * @return new IntegerSet representing the union
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet(this.set);   // copy of this
        for (int item : intSetb.set) {
            result.add(item);                           // add() ignores duplicates
        }
        return result;
    }

    /**
     * Returns a new set containing only elements common to both sets (A ∩ B).
     *
     * @param intSetb the other IntegerSet
     * @return new IntegerSet representing the intersection
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : this.set) {
            if (intSetb.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Returns a new set with elements in this set but <em>not</em> in
     * {@code intSetb} (A − B).
     *
     * @param intSetb the other IntegerSet
     * @return new IntegerSet representing the difference
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : this.set) {
            if (!intSetb.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Returns a new set with elements in {@code intSetb} but <em>not</em>
     * in this set (B − A).
     *
     * @param intSetb the other IntegerSet
     * @return new IntegerSet representing the complement
     */
    public IntegerSet complement(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : intSetb.set) {
            if (!this.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    // -----------------------------------------------------------------------
    // Equality & string representation
    // -----------------------------------------------------------------------

    /**
     * Returns {@code true} if both sets contain exactly the same elements,
     * regardless of internal ordering.
     *
     * @param b the other IntegerSet to compare with
     * @return {@code true} if the sets are equal
     */
    public boolean equals(IntegerSet b) {
        if (this.length() != b.length()) {
            return false;
        }
        for (int item : this.set) {
            if (!b.contains(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a string representation of this set with values in ascending
     * order, formatted as {@code [1, 2, 3]}.
     * An empty set returns {@code []}.
     *
     * @return formatted string of the set contents
     */
    @Override
    public String toString() {
        ArrayList<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < sorted.size(); i++) {
            sb.append(sorted.get(i));
            if (i < sorted.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}