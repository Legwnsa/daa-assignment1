DAA Assignment 1 - Divide and Conquer & Asymptotic Notations

Algorithms

This project implements:

- MergeSort
- QuickSort
- QuickSelect
- InsertionSort cutoff for MergeSort

Requirements

- Java 17+
- Maven
- JUnit 5

Run Tests

mvn test

Run Benchmark

Run the Benchmark class.

The benchmark tests:

- Random input
- Sorted input
- Duplicate values (0..9)
- n = 1,000
- n = 10,000
- n = 100,000
- n = 1,000,000
- 5 runs per case
- Median values are recorded

Results are saved to:

results.csv

Project Structure

src/
    main/java/com/example/
        Metrics.java
        MergeSort.java
        QuickSort.java
        QuickSelect.java
        Benchmark.java
    test/java/com/example/
        AlgorithmsTest.java

plots/
    time_vs_n.png
    depth_vs_n.png
    ratio_vs_n.png

Tests

JUnit 5 tests include:

- 100 random arrays for MergeSort
- 100 random arrays for QuickSort
- 100 random arrays for QuickSelect
- Empty arrays
- One-element arrays
- All-equal arrays
- Already sorted arrays
- Invalid QuickSelect indices
- QuickSort recursion depth test for n = 100,000

Git

Main branch:

- main

Feature branches:

- feature/mergesort
- feature/quicksort
- feature/select
- feature/metrics

Version tag:

- v1.0

GitHub

https://github.com/Legwnsa/daa-assignment1