package com.example.adventofcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Point {
    int x;
    int y;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point other))
            return false;

        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        // https://stackoverflow.com/a/50228389
        int ax = Math.abs(x);
        int ay = Math.abs(y);
        if (ax > ay && x > 0) return 4 * x * x - 3 * x + y + 1;
        if (ax > ay && x <= 0) return 4 * x * x - x - y + 1;
        if (ax <= ay && y > 0) return 4 * y * y - y - x + 1;
        return 4 * y * y - 3 * y + x + 1;
    }

    @Override
    public String toString() {
        return String.format("[%d][%d]", x, y);
    }
}
