package com.example.adventofcode;

import lombok.SneakyThrows;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public final String FILE_DIR = "./src/main/resources/day3.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    Pattern MUL_PATTERN = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
    Pattern MUL_DO_DONT_PATTERN = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)");
    Pattern NUMBERS_PATTERN = Pattern.compile("\\d{1,3}");

    public Day3() {
        partOne();
        partTwo();
    }

    @SneakyThrows
    public void partOne() {
        scanner = new Scanner(file);
        long sum = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Matcher MUL_MATCHER = MUL_PATTERN.matcher(line);

            while (MUL_MATCHER.find()) {
                String group = MUL_MATCHER.group();
                Matcher NUMBERS_MATCHER = NUMBERS_PATTERN.matcher(group);

                long product = 1;

                while (NUMBERS_MATCHER.find()) {
                    product *= Long.parseLong(NUMBERS_MATCHER.group());
                }

                sum += product;
            }
        }

        System.out.println("Day 3 Part 1: " + sum);
    }

    @SneakyThrows
    public void partTwo() {
        scanner = new Scanner(file);

        long sum = 0;
        boolean isEnabled = true;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Matcher MUL_MATCHER = MUL_DO_DONT_PATTERN.matcher(line);

            while (MUL_MATCHER.find()) {
                String group = MUL_MATCHER.group();

                if (group.equals("do()")) {
                    isEnabled = true;
                    continue;
                } else if (group.equals("don't()")) {
                    isEnabled = false;
                    continue;
                } else if (!isEnabled) {
                    continue;
                }

                Matcher NUMBERS_MATCHER = NUMBERS_PATTERN.matcher(group);

                long product = 1;

                while (NUMBERS_MATCHER.find()) {
                    product *= Long.parseLong(NUMBERS_MATCHER.group());
                }

                sum += product;
            }
        }

        System.out.println("Day 3 Part 2: " + sum);
    }

}
