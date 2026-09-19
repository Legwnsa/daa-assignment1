package com.example;

public class Metrics {
    private long comparisons;
    private int maxDepth;

    public void compare() {
        comparisons++;
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void updateDepth(int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }
    }

    public void reset() {
        comparisons = 0;
        maxDepth = 0;
    }
}
