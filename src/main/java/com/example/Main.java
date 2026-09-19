package com.example;

public class Main {

    public static void main(String[] args) {

        int[] a = {8, 3, 5, 1, 7, 2};

        Metrics metrics = new Metrics();

        int result = QuickSelect.select(a, 2, metrics);

        System.out.println("Result: " + result);
        System.out.println("Comparisons: " + metrics.getComparisons());
        System.out.println("Max depth: " + metrics.getMaxDepth());
    }
}