package com.example.adventofcode;

import lombok.SneakyThrows;

import java.io.File;
import java.util.*;

public class Day5 {
    public final String FILE_DIR = "./src/main/resources/day5.txt";
    public File file = new File(FILE_DIR);
    public Scanner scanner;

    public Day5() {
        partOne();
    }

    @SneakyThrows
    public void partOne() {
        scanner = new Scanner(file);

        Map<Integer, HashSet<Integer>> map = new LinkedHashMap<>();
        List<List<Integer>> sequences = new ArrayList<>();
        int sum = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.isBlank()) continue;

            if (!line.contains("|")) {
                String[] tokens = line.split(",");

                List<Integer> sequence = new ArrayList<>();
                for (String token : tokens) {
                    sequence.add(Integer.parseInt(token));
                }
                sequences.add(sequence);
                continue;
            }

            String[] tokens = line.split("\\|");
            Integer left = Integer.parseInt(tokens[0]);
            Integer right = Integer.parseInt(tokens[1]);

            HashSet<Integer> before = map.getOrDefault(right, new HashSet<>());
            before.add(left);

            map.put(right, before);
        }

        for (List<Integer> sequence : sequences) {
            boolean beforeCorrect = true;
            boolean afterCorrect = true;

            for (int i = 0; i < sequence.size(); i++) {
                int number = sequence.get(i);

                List<Integer> numbersBefore = sequence.subList(0, i);
                List<Integer> numbersAfter = sequence.subList(i + 1, sequence.size());

                if (!numbersBefore.isEmpty()) {
                    for (int numberBefore : numbersBefore) {
                        if (!map.containsKey(number) || !map.get(number).contains(numberBefore)) {
                            beforeCorrect = false;
                            break;
                        }
                    }
                }

                if (!numbersAfter.isEmpty()) {
                    for (int numberAfter : numbersAfter) {
                        if (!map.containsKey(numberAfter)) continue;

                        HashSet<Integer> set = map.get(numberAfter);

                        if (!set.contains(number)) {
                            afterCorrect = false;
                            break;
                        }
                    }
                }

                if (!beforeCorrect || !afterCorrect) break;
            }

            if (beforeCorrect && afterCorrect) {
                sum += sequence.get(sequence.size() / 2);
            }
        }

        System.out.println("Day 5 Part 1: " + sum);
    }

}
