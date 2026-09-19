package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    private static final int[] SIZES = {
            1_000,
            10_000,
            100_000,
            1_000_000
    };

    private static final int RUNS = 5;

    private static final Random RANDOM = new Random(42);

    public static void main(String[] args) throws IOException {

        try (FileWriter writer = new FileWriter("results.csv")) {

            writer.write(
                    "algorithm,input,n,time_ms,comparisons,max_depth\n"
            );

            for (int n : SIZES) {

                runMergeSort(writer, "random", n);
                runMergeSort(writer, "sorted", n);
                runMergeSort(writer, "duplicates", n);

                runQuickSort(writer, "random", n);
                runQuickSort(writer, "sorted", n);
                runQuickSort(writer, "duplicates", n);

                runQuickSelect(writer, "random", n);
                runQuickSelect(writer, "sorted", n);
                runQuickSelect(writer, "duplicates", n);
            }
        }

        System.out.println("Benchmark finished.");
        System.out.println("CSV file: results.csv");
    }

    private static void runMergeSort(
            FileWriter writer,
            String type,
            int n) throws IOException {

        long[] times = new long[RUNS];
        long[] comparisons = new long[RUNS];
        int[] depths = new int[RUNS];

        for (int run = 0; run < RUNS; run++) {

            int[] a = createArray(n, type);

            Metrics metrics = new Metrics();

            long start = System.nanoTime();

            MergeSort.sort(a, metrics);

            long end = System.nanoTime();

            times[run] = end - start;
            comparisons[run] = metrics.getComparisons();
            depths[run] = metrics.getMaxDepth();

            checkSorted(a);
        }

        writeResult(
                writer,
                "MergeSort",
                type,
                n,
                times,
                comparisons,
                depths
        );
    }

    private static void runQuickSort(
            FileWriter writer,
            String type,
            int n) throws IOException {

        long[] times = new long[RUNS];
        long[] comparisons = new long[RUNS];
        int[] depths = new int[RUNS];

        for (int run = 0; run < RUNS; run++) {

            int[] a = createArray(n, type);

            Metrics metrics = new Metrics();

            long start = System.nanoTime();

            QuickSort.sort(a, metrics);

            long end = System.nanoTime();

            times[run] = end - start;
            comparisons[run] = metrics.getComparisons();
            depths[run] = metrics.getMaxDepth();

            checkSorted(a);
        }

        writeResult(
                writer,
                "QuickSort",
                type,
                n,
                times,
                comparisons,
                depths
        );
    }

    private static void runQuickSelect(
            FileWriter writer,
            String type,
            int n) throws IOException {

        long[] times = new long[RUNS];
        long[] comparisons = new long[RUNS];
        int[] depths = new int[RUNS];

        int k = n / 2;

        for (int run = 0; run < RUNS; run++) {

            int[] a = createArray(n, type);

            int[] expected = a.clone();
            Arrays.sort(expected);

            Metrics metrics = new Metrics();

            long start = System.nanoTime();

            int result = QuickSelect.select(a, k, metrics);

            long end = System.nanoTime();

            if (result != expected[k]) {
                throw new IllegalStateException(
                        "QuickSelect returned wrong result"
                );
            }

            times[run] = end - start;
            comparisons[run] = metrics.getComparisons();
            depths[run] = metrics.getMaxDepth();
        }

        writeResult(
                writer,
                "QuickSelect",
                type,
                n,
                times,
                comparisons,
                depths
        );
    }

    private static int[] createArray(int n, String type) {

        int[] a = new int[n];

        if (type.equals("random")) {

            for (int i = 0; i < n; i++) {
                a[i] = RANDOM.nextInt(1_000_000);
            }

        } else if (type.equals("sorted")) {

            for (int i = 0; i < n; i++) {
                a[i] = i;
            }

        } else if (type.equals("duplicates")) {

            for (int i = 0; i < n; i++) {
                a[i] = RANDOM.nextInt(10);
            }

        } else {
            throw new IllegalArgumentException(
                    "Unknown input type: " + type
            );
        }

        return a;
    }

    private static void writeResult(
            FileWriter writer,
            String algorithm,
            String input,
            int n,
            long[] times,
            long[] comparisons,
            int[] depths) throws IOException {

        long medianTime = median(times);
        long medianComparisons = median(comparisons);
        int medianDepth = median(depths);

        double timeMs = medianTime / 1_000_000.0;

        writer.write(
                algorithm + "," +
                        input + "," +
                        n + "," +
                        timeMs + "," +
                        medianComparisons + "," +
                        medianDepth + "\n"
        );

        System.out.println(
                algorithm + " | " +
                        input + " | n=" + n +
                        " | time=" + timeMs + " ms" +
                        " | comparisons=" + medianComparisons +
                        " | depth=" + medianDepth
        );
    }

    private static long median(long[] values) {
        long[] copy = values.clone();

        Arrays.sort(copy);

        return copy[copy.length / 2];
    }

    private static int median(int[] values) {
        int[] copy = values.clone();

        Arrays.sort(copy);

        return copy[copy.length / 2];
    }

    private static void checkSorted(int[] a) {

        for (int i = 1; i < a.length; i++) {

            if (a[i - 1] > a[i]) {
                throw new IllegalStateException(
                        "Array is not sorted"
                );
            }
        }
    }
}