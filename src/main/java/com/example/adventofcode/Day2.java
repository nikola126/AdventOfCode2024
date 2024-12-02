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
    }

    @SneakyThrows
    public void partOne() {
        scanner = new Scanner(file);
        int countSafe = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");

            List<Integer> levels = Arrays.stream(tokens).map(Integer::parseInt).toList();
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
                }

                if (diff == 0) {
                    allIncreasing = false;
                    allDecreasing = false;
                }

                if (!isIncreasing) {
                    allIncreasing = false;
                }

                if (!isDecreasing) {
                    allDecreasing = false;
                }
            }

            if (allSafe && (allIncreasing || allDecreasing)) {
                countSafe += 1;
            }
        }

        System.out.println("Day 2 Part 1: " + countSafe);
    }

}
