package com.example.adventofcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.File;
import java.util.*;

public class Day7 {
    public final String FILE_DIR = "./src/main/resources/day7.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day7() {
        partOne();
    }

    @SneakyThrows
    public void partOne() {
        scanner = new Scanner(file);
        List<Problem> problems = new ArrayList<>();

        while (scanner.hasNextLine()) {
            problems.add(readLine(scanner.nextLine()));
        }

        long sum = 0;

        for (Problem p : problems) {
            for (List<Character> variation : p.getOperatorVariations()) {
                if (evaluate(p.getOperands(), variation, p.getResult())) {
                    sum += p.getResult();
                    break;
                }
            }
        }

        System.out.println("Day 7 Part 1: " + sum);
    }

    private Problem readLine(String line) {
        String[] tokens = line.split(":");

        long result = Long.parseLong(tokens[0]);

        List<Long> operands = new ArrayList<>();
        for (String token : tokens[1].trim().split(" ")) {
            operands.add(Long.parseLong(token));
        }

        int requiredOperators = operands.size() - 1;
        List<List<Character>> variations = variations(List.of('*', '+'), requiredOperators);

        return new Problem(result, operands, variations);
    }

    private List<List<Character>> variations(List<Character> operators, int size) {
        List<List<Character>> variations = new ArrayList<>();

        if (size == 0) {
            variations.add(new ArrayList<>());
            return variations;
        }

        for (Character operand : operators) {
            List<List<Character>> subVariations = variations(operators, size - 1);
            for (List<Character> subVariation : subVariations) {
                subVariation.add(operand);
                variations.add(subVariation);
            }
        }

        return variations;
    }

    private boolean evaluate(List<Long> operands, List<Character> operators, Long result) {
        long left = 0;
        int i = 0;

        for (Character c : operators) {
            left = left == 0 ? operands.get(i) : left;
            long right = operands.get(i + 1);

            if (c == '+') {
                left = (left + right);
            } else {
                left = (left * right);
            }

            i += 1;
        }

        return result.equals(left);
    }

    @AllArgsConstructor
    @Getter
    @Setter
    private static class Problem {
        private Long result;
        private List<Long> operands;
        private List<List<Character>> operatorVariations;

        @Override
        public String toString() {
            return String.format("%d : %s [%d variations]", result, operands, operatorVariations.size());
        }
    }

}
