package com.example.adventofcode;

import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {
    public final String FILE_DIR = "./src/main/resources/day4.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    Pattern XMAS = Pattern.compile("XMAS");
    Pattern SAMX = Pattern.compile("SAMX");

    public Day4() {
        partOne();
    }

    @SneakyThrows
    public void partOne() {
        scanner = new Scanner(file);

        List<List<Character>> grid = new ArrayList<>();

        List<String> rows = new ArrayList<>();
        List<String> cols = new ArrayList<>();
        List<String> diagonals = new ArrayList<>();

        int ROWS;
        int COLS;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<Character> row = new ArrayList<>();

            for (Character c : line.toCharArray()) {
                row.add(c);
            }

            grid.add(row);
            rows.add(line);
        }

        ROWS = grid.size();
        COLS = grid.getFirst().size();

        for (int c = 0; c < COLS; c++) {
            StringBuilder sb = new StringBuilder();
            for (int r = 0; r < ROWS; r++) {
                sb.append(grid.get(r).get(c));
            }

            cols.add(sb.toString());
        }

        // Down Left
        for (int start = ROWS - 1; start >= 0; start--) {
            StringBuilder sb = new StringBuilder();
            int row = start;
            int col = 0;

            for (int step = 0; step < COLS; step++) {
                if (isOutOfBounds(row, col, ROWS, COLS))
                    continue;

                sb.append(grid.get(row).get(col));
                row++;
                col++;
            }

            if (sb.length() < 4)
                continue;

            diagonals.add(sb.toString());
        }

        for (int start = 1; start < COLS; start++) {
            StringBuilder sb = new StringBuilder();
            int col = start;
            int row = 0;

            for (int step = 0; step < COLS; step++) {
                if (isOutOfBounds(row, col, ROWS, COLS))
                    continue;

                sb.append(grid.get(row).get(col));
                row++;
                col++;
            }

            if (sb.length() < 4)
                continue;

            diagonals.add(sb.toString());
        }

        // Down Right
        for (int start = ROWS - 1; start >= 0; start--) {
            StringBuilder sb = new StringBuilder();
            int row = start;
            int col = COLS - 1;

            for (int step = 0; step < COLS; step++) {
                if (isOutOfBounds(row, col, ROWS, COLS))
                    continue;

                sb.append(grid.get(row).get(col));
                row++;
                col--;
            }

            if (sb.length() < 4)
                continue;

            diagonals.add(sb.toString());
        }

        for (int start = COLS - 2; start >= 0; start--) {
            StringBuilder sb = new StringBuilder();
            int col = start;
            int row = 0;

            for (int step = 0; step < COLS; step++) {
                if (isOutOfBounds(row, col, ROWS, COLS))
                    continue;

                sb.append(grid.get(row).get(col));
                row++;
                col--;
            }

            if (sb.length() < 4)
                continue;

            diagonals.add(sb.toString());
        }

        int count = 0;

        for (String line : rows) {
            count += countMatches(line);
        }

        for (String line : cols) {
            count += countMatches(line);
        }

        for (String line : diagonals) {
            count += countMatches(line);
        }

        System.out.println("Day 4 Part 1: " + count);

    }

    private boolean isOutOfBounds(int row, int col, int ROWS, int COLS) {
        return row < 0 || row >= ROWS || col < 0 || col >= COLS;
    }

    private int countMatches(String line) {
        int count = 0;

        Matcher forward = XMAS.matcher(line);
        while (forward.find()) {
            count += 1;
        }

        Matcher backward = SAMX.matcher(line);
        while (backward.find()) {
            count += 1;
        }

        return count;
    }
}
