record Cell(int row, int column) {}

static class Grid {
    private final char[][] internalArray;

    Grid(List<String> array) {
        internalArray = array.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    boolean inBounds(Cell cell) {
        return cell.row() >= 0
                && cell.row() < internalArray.length
                && cell.column() >= 0
                && cell.column() < internalArray[cell.row()].length;
    }

    char getValue(Cell cell) {
        return internalArray[cell.row()][cell.column()];
    }

    List<Cell> allCells() {
        List<Cell> result = new ArrayList<>();
        for (int r = 0; r < internalArray.length; r++) {
            for (int c = 0; c < internalArray[r].length; c++) {
                result.add(new Cell(r, c));
            }
        }
        return result;
    }

    List<Cell> getNeighbors(Cell cell) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        List<Cell> neighbors = new ArrayList<>();
        for (int[] direction : directions) {
            var neighbour = new Cell(
                    cell.row() + direction[0],
                    cell.column() + direction[1]
            );
            if (inBounds(neighbour)) neighbors.add(neighbour);
        }
        return neighbors;
    }

    void removeCell(Cell cell) {
        internalArray[cell.row()][cell.column()] = '.';
    }

    void removeCells(List<Cell> cells) {
        cells.forEach(this::removeCell);
    }

}

boolean hasFewerThanNOccupiedNeighbors(Grid grid, Cell cell, int n) {
    var neighbors = grid.getNeighbors(cell);
    var occupied = neighbors.stream()
            .filter(neighbor -> grid.getValue(neighbor) == '@')
            .limit(n)
            .count();
    return occupied < 4;
}

int part1(List<String> input) {
    var grid = new Grid(input);
    var cells = grid.allCells();
    return (int) cells.stream()
            .filter(cell -> grid.getValue(cell) == '@')
            .filter(cell -> hasFewerThanNOccupiedNeighbors(grid, cell, 4))
            .count();
}

int part2(List<String> input) {
    var grid = new Grid(input);
    var cells = grid.allCells();

    boolean hasCellsToMove = true;
    int movedCells = 0;
    while (hasCellsToMove) {
        var accessibleCells = cells.stream()
                .filter(cell -> grid.getValue(cell) == '@')
                .filter(cell -> hasFewerThanNOccupiedNeighbors(grid, cell, 4))
                .toList();

        if (accessibleCells.isEmpty()) {
            hasCellsToMove = false;
        } else {
            movedCells += accessibleCells.size();
            grid.removeCells(accessibleCells);
        }
    }
    return movedCells;
}

void main() {
    try {
        var input = Files.readAllLines(Path.of("./src/day4.txt"));
        IO.println(part1(input));
        IO.println(part2(input));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
