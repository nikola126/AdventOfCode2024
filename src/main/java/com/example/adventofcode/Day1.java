package com.example.adventofcode;

import lombok.SneakyThrows;

import java.io.File;
import java.util.*;

public class Day1 {
    public final String FILE_DIR = "./src/main/resources/day1.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day1() {
        partOne();
        partTwo();
    }

    @SneakyThrows
    public void partOne() {
        scanner = new Scanner(file);

        List<Long> leftSide = new ArrayList<>();
        List<Long> rightSide = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" {3}");

            long right = Long.parseLong(tokens[0]);
            long left = Long.parseLong(tokens[1]);

            leftSide.add(right);
            rightSide.add(left);
        }

        leftSide = leftSide.stream().sorted().toList();
        rightSide = rightSide.stream().sorted().toList();

        long sum = 0;

        for (int i = 0; i < leftSide.size(); i++) {
            sum += (Math.abs(leftSide.get(i) - rightSide.get(i)));
        }

        System.out.println("Day 1 Part 1: " + sum);
    }

    @SneakyThrows
    public void partTwo() {
        scanner = new Scanner(file);

        List<Long> leftSide = new ArrayList<>();
        Map<Long, Long> rightSideOccurrences = new HashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" {3}");

            long left = Long.parseLong(tokens[0]);
            long right = Long.parseLong(tokens[1]);

            leftSide.add(left);
            long countPrevious = rightSideOccurrences.getOrDefault(right, 0L);
            rightSideOccurrences.put(right, countPrevious + 1);
        }

        long sum = leftSide.stream()
                .map(left -> left * rightSideOccurrences.getOrDefault(left, 0L))
                .reduce(0L, Long::sum);

        System.out.println("Day 1 Part 2: " + sum);
    }

}
