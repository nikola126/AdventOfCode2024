package com.example.adventofcode;

import lombok.SneakyThrows;

import java.io.File;
import java.util.*;

public class Day6 {
    public final String FILE_DIR = "./src/main/resources/day6.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day6() {
        partOne();
    }

    @SneakyThrows
    public void partOne() {
        scanner = new Scanner(file);

        List<List<Character>> grid = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<Character> row = new ArrayList<>();

            for (Character c : line.toCharArray()) {
                row.add(c);
            }

            grid.add(row);
        }

        attachBoundary(grid);

        int ROWS = grid.size();
        int COLS = grid.getFirst().size();
        int currROW = 0;
        int currCOL = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                Character c = grid.get(i).get(j);

                if (c == '^') {
                    currROW = i;
                    currCOL = j;
                }
            }
        }

        navigate(grid, currROW, currCOL);
        int count = countVisited(grid);

        System.out.println("Day 6 Part 1: " + count);
    }

    private void navigate(List<List<Character>> grid, int currROW, int currCOL) {
        boolean goUp = true;
        boolean goRight = false;
        boolean goDown = false;
        boolean goLeft = false;

        while (grid.get(currROW).get(currCOL) != 'O') {
            if (goUp) {
                char next = grid.get(currROW - 1).get(currCOL);
                if (next == '#') {
                    goUp = false;
                    goRight = true;
                } else {
                    grid.get(currROW).set(currCOL, 'X');
                    currROW--;
                }
            } else if (goRight) {
                char next = grid.get(currROW).get(currCOL + 1);
                if (next == '#') {
                    goRight = false;
                    goDown = true;
                } else {
                    grid.get(currROW).set(currCOL, 'X');
                    currCOL++;
                }
            } else if (goDown) {
                char next = grid.get(currROW + 1).get(currCOL);
                if (next == '#') {
                    goDown = false;
                    goLeft = true;
                } else {
                    grid.get(currROW).set(currCOL, 'X');
                    currROW++;
                }
            } else if (goLeft) {
                char next = grid.get(currROW).get(currCOL - 1);
                if (next == '#') {
                    goLeft = false;
                    goUp = true;
                } else {
                    grid.get(currROW).set(currCOL, 'X');
                    currCOL--;
                }
            }
        }
    }

    private void printGrid(List<List<Character>> grid) {
        for (List<Character> line : grid) {
            for (Character c : line) {
                System.out.printf("%c", c);
            }
            System.out.println();
        }
    }

    private void attachBoundary(List<List<Character>> grid) {
        int COLS = grid.getFirst().size();

        for (List<Character> row : grid) {
            row.addFirst('O');
            row.addLast('O');
        }

        List<Character> horizontal = new ArrayList<>(COLS + 2);
        for (int i = 0; i < COLS + 2; i++) horizontal.add('O');

        grid.addFirst(horizontal);
        grid.addLast(horizontal);
    }

    private int countVisited(List<List<Character>> grid) {
        int count = 0;

        for (List<Character> row : grid) {
            for (Character c : row) {
                if (c == 'X') count++;
            }
        }

        return count;
    }

}
