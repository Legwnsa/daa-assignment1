package com.example;

import java.util.Random;

public class QuickSelect {

    private static final Random RANDOM = new Random();

    public static int select(int[] a, int k, Metrics metrics) {

        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException(
                    "k must be between 0 and " + (a.length - 1)
            );
        }

        int left = 0;
        int right = a.length - 1;
        int depth = 1;

        while (left <= right) {
            metrics.updateDepth(depth);

            int pivotIndex =
                    left + RANDOM.nextInt(right - left + 1);

            int pivot = a[pivotIndex];

            int[] parts =
                    QuickSort.partition(
                            a, left, right, pivot, metrics
                    );

            int less = parts[0];
            int greater = parts[1];

            if (k < less) {
                right = less - 1;
            } else if (k > greater) {
                left = greater + 1;
            } else {
                return pivot;
            }

            depth++;
        }

        throw new IllegalStateException("QuickSelect failed");
    }
}