package com.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmsTest {

    private final Random random = new Random(42);

    @Test
    void mergeSortRandomArrays() {
        for (int t = 0; t < 100; t++) {
            int n = random.nextInt(100);
            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = random.nextInt(1000) - 500;
            }

            int[] expected = a.clone();
            Arrays.sort(expected);

            Metrics metrics = new Metrics();

            MergeSort.sort(a, metrics);

            assertArrayEquals(expected, a);
        }
    }

    @Test
    void quickSortRandomArrays() {
        for (int t = 0; t < 100; t++) {
            int n = random.nextInt(100);
            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = random.nextInt(1000) - 500;
            }

            int[] expected = a.clone();
            Arrays.sort(expected);

            Metrics metrics = new Metrics();

            QuickSort.sort(a, metrics);

            assertArrayEquals(expected, a);
        }
    }

    @Test
    void quickSelectRandomArrays() {
        for (int t = 0; t < 100; t++) {
            int n = 1 + random.nextInt(100);
            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = random.nextInt(1000) - 500;
            }

            int[] expected = a.clone();
            Arrays.sort(expected);

            int k = random.nextInt(n);

            Metrics metrics = new Metrics();

            int result = QuickSelect.select(a, k, metrics);

            assertEquals(expected[k], result);
        }
    }

    @Test
    void emptyArray() {
        int[] a = {};

        Metrics metrics1 = new Metrics();
        MergeSort.sort(a, metrics1);
        assertArrayEquals(new int[]{}, a);

        Metrics metrics2 = new Metrics();
        QuickSort.sort(a, metrics2);
        assertArrayEquals(new int[]{}, a);

        Metrics metrics3 = new Metrics();

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(a, 0, metrics3)
        );
    }

    @Test
    void oneElementArray() {
        int[] a = {42};

        Metrics metrics1 = new Metrics();
        MergeSort.sort(a, metrics1);
        assertArrayEquals(new int[]{42}, a);

        Metrics metrics2 = new Metrics();
        QuickSort.sort(a, metrics2);
        assertArrayEquals(new int[]{42}, a);

        Metrics metrics3 = new Metrics();
        assertEquals(42, QuickSelect.select(a, 0, metrics3));
    }

    @Test
    void allEqualArray() {
        int[] a = {5, 5, 5, 5, 5, 5};

        int[] expected = a.clone();

        Metrics metrics1 = new Metrics();
        MergeSort.sort(a, metrics1);
        assertArrayEquals(expected, a);

        Metrics metrics2 = new Metrics();
        QuickSort.sort(a, metrics2);
        assertArrayEquals(expected, a);

        Metrics metrics3 = new Metrics();
        assertEquals(5, QuickSelect.select(a, 3, metrics3));
    }

    @Test
    void alreadySortedArray() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int[] expected = a.clone();

        Metrics metrics1 = new Metrics();
        MergeSort.sort(a, metrics1);
        assertArrayEquals(expected, a);

        Metrics metrics2 = new Metrics();
        QuickSort.sort(a, metrics2);
        assertArrayEquals(expected, a);

        Metrics metrics3 = new Metrics();
        assertEquals(6, QuickSelect.select(a, 5, metrics3));
    }

    @Test
    void quickSortDepthOnSorted100k() {
        int n = 100_000;
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = i;
        }

        Metrics metrics = new Metrics();

        QuickSort.sort(a, metrics);

        double limit = 2.0 * (Math.log(n) / Math.log(2));

        assertTrue(
                metrics.getMaxDepth() <= limit,
                "Max depth was " + metrics.getMaxDepth()
                        + ", limit was " + limit
        );
    }

    @Test
    void quickSelectInvalidK() {
        int[] a = {1, 2, 3, 4, 5};

        Metrics metrics1 = new Metrics();

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(a, -1, metrics1)
        );

        Metrics metrics2 = new Metrics();

        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(a, 5, metrics2)
        );
    }
}