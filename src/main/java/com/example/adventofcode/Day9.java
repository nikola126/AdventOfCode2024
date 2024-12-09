package com.example.adventofcode;

import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day9 {
    public final String FILE_DIR = "./src/main/resources/day9.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day9() {
        partOne();
    }

    @SneakyThrows
    public void partOne() {
        scanner = new Scanner(file);
        String input = "";

        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
        }

        long expectedLength = calculateExpectedLength(input);
        List<Long> expanded = expand(input);
        List<Long> converted = convert(expanded, expectedLength);
        long checksum = calculateChecksum(converted);

        System.out.println("Day 9 Part 1: " + checksum);
    }

    private static long calculateExpectedLength(String input) {
        long expectedLength = 0;
        boolean isOdd = true;

        for (Character c : input.toCharArray()) {
            if (isOdd) {
                expectedLength += Long.parseLong(c.toString());
                isOdd = false;
            } else {
                isOdd = true;
            }
        }
        return expectedLength;
    }

    private List<Long> expand(String input) {
        List<Long> sb = new ArrayList<>();
        long id = 0;
        boolean isOdd = true;

        for (Character c : input.toCharArray()) {
            long currentBlockLength = Long.parseLong(c.toString());

            if (isOdd) {
                for (int i = 0; i < currentBlockLength; i++) {
                    sb.add(id);
                }
                isOdd = false;
                id++;
            } else {
                for (int i = 0; i < currentBlockLength; i++) {
                    sb.add(null);
                }
                isOdd = true;
            }
        }

        return sb;
    }

    private List<Long> convert(List<Long> input, long expectedLength) {
        List<Long> sb = new ArrayList<>();
        int lastEmpty = input.lastIndexOf(null);

        int left = 0;
        int right = input.size() - 1;

        while (sb.size() < expectedLength) {
            Long current = input.get(left);

            if (current != null) {
                sb.add(current);
                left += 1;
            } else {
                while (input.get(right) == null) {
                    right--;
                }

                sb.add(input.get(right));
                right--;
                left += 1;
            }

            if (left == lastEmpty)
                break;
        }

        return sb;
    }

    private long calculateChecksum(List<Long> input) {
        long checksum = 0;
        long pos = 0;

        for (Long l : input) {
            checksum += (pos * l);
            pos += 1L;
        }

        return checksum;
    }

}
