package com.example.adventofcode;

import lombok.SneakyThrows;

import java.io.File;
import java.util.*;

public class Day2 {
    public final String FILE_DIR = "./src/main/resources/day2.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day2() {
        partOne();
        partTwo();
    }

    @SneakyThrows
    public void partOne() {
        scanner = new Scanner(file);
        int countSafe = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");

            List<Integer> levels = Arrays.stream(tokens).map(Integer::parseInt).toList();

            if (isReportSafe(levels)) {
                countSafe += 1;
            }
        }

        System.out.println("Day 2 Part 1: " + countSafe);
    }

    @SneakyThrows
    public void partTwo() {
        scanner = new Scanner(file);
        int countSafe = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");

            List<Integer> levels = Arrays.stream(tokens).map(Integer::parseInt).toList();

            if (isReportSafe(levels)) {
                countSafe += 1;
            } else {
                for (int i = 0; i < levels.size(); i++) {
                    List<Integer> shortened = new ArrayList<>(levels);
                    shortened.remove(i);

                    if (isReportSafe(shortened)) {
                        countSafe += 1;
                        break;
                    }
                }
            }
        }

        System.out.println("Day 2 Part 2: " + countSafe);
    }

    private boolean isReportSafe(List<Integer> levels) {
        boolean allIncreasing = true;
        boolean allDecreasing = true;
        boolean allSafe = true;

        for (int i = 1; i < levels.size(); i++) {
            int diff = levels.get(i - 1) - levels.get(i);
            int absDiff = Math.abs(diff);

            boolean isIncreasing = diff > 0;
            boolean isDecreasing = diff < 0;
            boolean isDiffSafe = absDiff >= 1 && absDiff <= 3;

            if (!isDiffSafe) {
                allSafe = false;
            } else if (diff == 0) {
                allIncreasing = false;
                allDecreasing = false;
            } else if (!isIncreasing) {
                allIncreasing = false;
            } else if (!isDecreasing) {
                allDecreasing = false;
            }
        }

        return allSafe && (allIncreasing || allDecreasing);
    }

}
