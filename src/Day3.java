int part1(List<String> input) {
    return input.stream()
            .map(line -> line.chars().map(c -> c - '0').toArray())
            .mapToInt(digitsArray -> findLargestJoltage(digitsArray))
            .sum();
}

int findLargestJoltage(int[] batteries) {
    var largestSeenJoltage = 0;
    var largestSeenDigit = 0;
    for (var digit : batteries) {
        var joltage = (largestSeenDigit * 10 + digit);
        if (joltage > largestSeenJoltage) largestSeenJoltage = joltage;
        if (digit > largestSeenDigit) largestSeenDigit = digit;
    }
    return largestSeenJoltage;
}

long part2(List<String> input) {
    return input.stream()
            .map(line -> line.chars().map(c -> c - '0').toArray())
            .mapToLong(digitsArray -> findLargestJoltageByAmount(digitsArray, 12))
            .sum();
}

long findLargestJoltageByAmount(int[] batteries, int amount) {
    var leftmostIndexAvailable = 0;
    var result = 0L;
    for (var i = 0; i < amount; i++) {
        var rightmostIndexAvailable = batteries.length - amount + i;
        var largestDigit = 0;
        for (int j = leftmostIndexAvailable; j <= rightmostIndexAvailable; j++) {
            if (batteries[j] > largestDigit) {
                largestDigit = batteries[j];
                leftmostIndexAvailable = j + 1;
            }
        }
        result = (result * 10) + largestDigit;
    }

    return result;
}

void main() {
    try {
        var input = Files.readAllLines(Path.of("./src/day3.txt"));
        IO.println(part1(input));
        IO.println(part2(input));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
