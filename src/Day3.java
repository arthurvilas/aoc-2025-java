import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day3 {
    private static int part1(List<String> input) {
        return input.stream()
                .map(line -> line.chars().map(c -> c - '0').toArray())
                .mapToInt(digitsArray -> findLargestJoltage(digitsArray))
                .sum();
    }

    private static int findLargestJoltage(int[] batteries) {
        var largestSeenJoltage = 0;
        var largestSeenDigit = 0;
        for (var digit : batteries) {
            var joltage = (largestSeenDigit * 10 + digit);
            if (joltage > largestSeenJoltage) largestSeenJoltage = joltage;
            if (digit > largestSeenDigit) largestSeenDigit = digit;
        }
        return largestSeenJoltage;
    }

    static void main() {
        try {
            var input = Files.readAllLines(Path.of("./src/day3.txt"));
            System.out.println(part1(input));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
