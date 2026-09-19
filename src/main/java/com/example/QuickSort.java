package com.example;

import java.util.Random;

public class QuickSort {

    private static final Random RANDOM = new Random();

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length < 2) {
            return;
        }

        sort(a, 0, a.length - 1, metrics, 1);
    }

    private static void sort(
            int[] a,
            int left,
            int right,
            Metrics metrics,
            int depth) {

        while (left < right) {
            metrics.updateDepth(depth);

            int pivotIndex = left + RANDOM.nextInt(right - left + 1);
            int pivot = a[pivotIndex];

            int[] parts = partition(a, left, right, pivot, metrics);

            int less = parts[0];
            int greater = parts[1];

            int leftSize = less - left;
            int rightSize = right - greater;

            if (leftSize <= rightSize) {
                if (left < less - 1) {
                    sort(a, left, less - 1, metrics, depth + 1);
                }

                left = greater + 1;
            } else {
                if (greater + 1 < right) {
                    sort(a, greater + 1, right, metrics, depth + 1);
                }

                right = less - 1;
            }
        }
    }

    static int[] partition(
            int[] a,
            int left,
            int right,
            int pivot,
            Metrics metrics) {

        int lt = left;
        int i = left;
        int gt = right;

        while (i <= gt) {
            metrics.compare();

            if (a[i] < pivot) {
                swap(a, lt, i);
                lt++;
                i++;
            } else if (a[i] > pivot) {
                metrics.compare();

                swap(a, i, gt);
                gt--;
            } else {
                metrics.compare();

                i++;
            }
        }

        return new int[]{lt, gt};
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}