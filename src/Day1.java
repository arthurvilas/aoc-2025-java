int part1(List<String> input) {
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

int part2(List<String> input) {
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

void main() {
    try {
        var input = Files.readAllLines(Path.of("./src/day1.txt"));
        IO.println(part1(input));
        IO.println(part2(input));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
