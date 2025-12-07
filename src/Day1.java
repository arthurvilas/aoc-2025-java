import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day1 {
    private static Integer part1(List<String> input) {
        final var length = 100;
        var currentPosition = 50;
        var zeroes = 0;
        for (var line : input) {
            var rotations = Integer.parseInt(line.substring(1));
            if (line.charAt(0) == 'L') rotations = -rotations;
            currentPosition = ((currentPosition + rotations) + length) % length;
            if (currentPosition == 0) zeroes++;
        }

        return zeroes;
    }

    private static Integer part2(List<String> input) {
        final var length = 100;
        var currentPosition = 50;
        var zeroes = 0;

        for (var line : input) {
            var rotations = Integer.parseInt(line.substring(1));
            if (rotations > length) {
                zeroes += rotations / length;
                rotations = rotations % length;
            }

            if (line.charAt(0) == 'L') rotations = -rotations;
            if (currentPosition != 0 && (currentPosition + rotations > 100 || currentPosition + rotations < 0)) {
                zeroes++;
            }
            currentPosition = ((currentPosition + rotations) + length) % length;
            if (currentPosition == 0) zeroes++;
        }

        return zeroes;
    }

    static void main() {
        try {
            var input = Files.readAllLines(Path.of("./src/day1.txt"));
            System.out.println(part1(input));
            System.out.println(part2(input));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
