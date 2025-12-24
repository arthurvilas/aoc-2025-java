
record Range(long min, long max) {

    boolean contains(long n) {
        return n >= min && n <= max;
    }

    static Range fromString(String s) {
        var rangeValues = s.split("-");
        return new Range(
                Long.parseLong(rangeValues[0]),
                Long.parseLong(rangeValues[1])
        );
    }

}

int part1(List<String> input) {
    int splitIndex = input.indexOf("");
    List<Range> ranges = input.subList(0, splitIndex).stream()
            .map(Range::fromString)
            .toList();

    List<Long> ids = input.subList(splitIndex + 1, input.size()).stream()
            .map(Long::parseLong)
            .toList();

    int count = 0;
    for (long id : ids) {
        for (Range range : ranges) {
            if (range.contains(id)) {
                count++;
                break;
            }
        }
    }
    return count;
}

void main() {
    try {
        var input = Files.readAllLines(Path.of("./src/day5.txt"));
        IO.println(part1(input));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}