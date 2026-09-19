package com.example;

public class MergeSort {
    private static final int CUTOFF = 15;

    public static void sort(int[] a,Metrics metrics) {
        if (a == null || a.length < 2) {
            return;
        }

        int[] buffer = new int[a.length];

        sort(a, buffer, 0, a.length - 1, metrics, 1);
    }

    private static void sort(int[] a, int[] buffer, int left, int right, Metrics metrics, int depth) {
        metrics.updateDepth(depth);

        if (right - left + 1 <= CUTOFF) {
            InsertionSort(a, left, right, metrics);
            return;
        }

        int mid = left + (right - left)/2;

        sort(a, buffer, left, mid, metrics, depth +1);
        sort(a, buffer, mid +1, right, metrics, depth +1);

        merge(a, buffer, left, mid, right, metrics);
    }

    private static void InsertionSort(int[] a, int left, int right, Metrics metrics) {
        for (int i = left +1; i <= right; i++) {
            int key = a[i];
            int j = i - 1;

            while (j >= left) {
                metrics.compare();

                if (a[j] <= key) {
                    break;
                }

                a[j + 1] = a[j];
                j--;
            }

            a[j+1] = key;
        }
    }

    private static void merge(int[] a, int[] buffer, int left, int mid, int right, Metrics metrics) {
        int i = left;
        int j = mid +1;
        int k = left;

        while (i <= mid && j <= right) {
            metrics.compare();

            if (a[i] <= a[j]) {
                buffer[k++] = a[i++];
            } else {
                buffer[k++] = a[j++];
            }
        }

        while (i <= mid) {
            buffer[k++] = a[i++];
        }
        while (j <= right) {
            buffer[k++] = a[j++];
        }

        for (int x = left; x <= right; x++) {
            a[x] = buffer[x];
        }
    }
}
