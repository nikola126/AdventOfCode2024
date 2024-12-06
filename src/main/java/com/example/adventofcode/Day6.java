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
        partTwo();
    }

    @SneakyThrows
    public void partOne() {
        scanner = new Scanner(file);

        List<List<Character>> grid = readGrid();

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

        navigate(grid, ROWS * COLS, currROW, currCOL);
        int count = getMatchingPoints(grid, 'X').size();

        System.out.println("Day 6 Part 1: " + count);
    }

    @SneakyThrows
    public void partTwo() {
        scanner = new Scanner(file);

        List<List<Character>> grid = readGrid();
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

        List<List<Character>> navigationCopy = copyGrid(grid);
        navigate(navigationCopy, ROWS * COLS, currROW, currCOL);
        Set<Point> visitedPoints = getMatchingPoints(navigationCopy, 'X');
        int maxSteps = getMatchingPoints(grid, '.').size();

        int countLoops = 0;

        for (Point candidate : visitedPoints) {
            List<List<Character>> obstacleCopy = copyGrid(grid);
            obstacleCopy.get(candidate.getX()).set(candidate.getY(), '#');

            if (!navigate(obstacleCopy, maxSteps, currROW, currCOL)) {
                countLoops += 1;
            }
        }

        System.out.println("Day 6 Part 2: " + countLoops);
    }

    private List<List<Character>> readGrid() {
        List<List<Character>> grid = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<Character> row = new ArrayList<>();

            for (Character c : line.toCharArray()) {
                row.add(c);
            }

            grid.add(row);
        }

        return grid;
    }

    private List<List<Character>> copyGrid(List<List<Character>> grid) {
        List<List<Character>> copy = new ArrayList<>();

        for (List<Character> row : grid) {
            copy.add(new ArrayList<>(row));
        }

        return copy;
    }

    private boolean navigate(List<List<Character>> grid, int maxSteps, int currROW, int currCOL) {
        boolean goUp = true;
        boolean goRight = false;
        boolean goDown = false;
        boolean goLeft = false;

        int steps = 0;

        while (grid.get(currROW).get(currCOL) != 'O' && steps < maxSteps) {
            if (goUp) {
                char next = grid.get(currROW - 1).get(currCOL);
                if (next == '#') {
                    goUp = false;
                    goRight = true;
                } else {
                    grid.get(currROW).set(currCOL, 'X');
                    currROW--;
                    steps += 1;
                }
            } else if (goRight) {
                char next = grid.get(currROW).get(currCOL + 1);
                if (next == '#') {
                    goRight = false;
                    goDown = true;
                } else {
                    grid.get(currROW).set(currCOL, 'X');
                    currCOL++;
                    steps += 1;
                }
            } else if (goDown) {
                char next = grid.get(currROW + 1).get(currCOL);
                if (next == '#') {
                    goDown = false;
                    goLeft = true;
                } else {
                    grid.get(currROW).set(currCOL, 'X');
                    currROW++;
                    steps += 1;
                }
            } else if (goLeft) {
                char next = grid.get(currROW).get(currCOL - 1);
                if (next == '#') {
                    goLeft = false;
                    goUp = true;
                } else {
                    grid.get(currROW).set(currCOL, 'X');
                    currCOL--;
                    steps += 1;
                }
            }
        }

        return grid.get(currROW).get(currCOL) == 'O';
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

    private Set<Point> getMatchingPoints(List<List<Character>> grid, char target) {
        Set<Point> points = new HashSet<>();

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (grid.get(i).get(j) == target) {
                    points.add(new Point(i, j));
                }
            }
        }

        return points;
    }

}
