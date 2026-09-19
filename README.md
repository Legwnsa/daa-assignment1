DAA Assignment 1 - Divide and Conquer

Algorithms

This project implements:

- MergeSort
- QuickSort
- QuickSelect
- InsertionSort cutoff for MergeSort

Requirements:

- Java 17+
- Maven
- JUnit 5

Run Tests:

`mvn test`


Run BenchmarkL:

Run the `Benchmark` class from IntelliJ.

The benchmark tests:

- Random input
- Sorted input
- Duplicate values
- n = 1,000, 10,000, 100,000, 1,000,000

Results are saved to `benchmark.csv`.

Project Structure:


src/
    main/java/com/example/
        Metrics.java
        MergeSort.java
        QuickSort.java
        QuickSelect.java
        Benchmark.java
    test/java/com/example/
        AlgorithmsTest.java


Git

Main branches:

- master
- feature/mergesort
- feature/quicksort
- feature/select
- feature/metrics

Version: v1.0
