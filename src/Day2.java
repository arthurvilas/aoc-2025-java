long part1(String input) {
    return Arrays.stream(input.split(","))
            .map(range -> range.split("-"))
            .flatMapToLong(rangeArray -> LongStream.rangeClosed(
                    Long.parseLong(rangeArray[0]),
                    Long.parseLong(rangeArray[1])
            ))
            .mapToObj(Long::toString)
            .filter(id -> {
                if (id.length() % 2 != 0) return false;
                var halfLen = id.length() / 2;
                return id.substring(0, halfLen).equals(id.substring(halfLen));
            })
            .mapToLong(Long::parseLong)
            .sum();
}

long part2(String input) {
    return Arrays.stream(input.split(","))
            .map(range -> range.split("-"))
            .flatMapToLong(rangeArray -> LongStream.rangeClosed(
                    Long.parseLong(rangeArray[0]),
                    Long.parseLong(rangeArray[1])
            ))
            .mapToObj(Long::toString)
            .filter(id -> isRepeatedSequence(id))
            .mapToLong(Long::parseLong)
            .sum();
}

boolean isRepeatedSequence(String s) {
    var len = s.length();
    for (var i = 1; i <= (len / 2); i++) {
        if ((len % i) != 0) continue;
        var chunk = s.substring(0, i);
        var candidate = chunk.repeat(len / i);
        if (candidate.equals(s)) {
            return true;
        }
    }
    return false;
}

void main() {
    try {
        var input = Files.readString(Path.of("./src/day2.txt"));
        IO.println(part1(input));
        IO.println(part2(input));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
