import java.util.Arrays;

public class GameOfLife {
    public static void main(String[] args) {
        int rows = 10;
        int cols = 10;
        int[][] grid = new int[rows][cols];

        // Initialize the grid with some initial pattern
        grid[2][2] = 1;
        grid[2][3] = 1;
        grid[3][2] = 1;
        grid[3][3] = 1;

        // Number of generations to simulate
        int generations = 5;

        for (int generation = 0; generation < generations; generation++) {
            System.out.println("Generation " + generation);
            printGrid(grid);
            grid = getNextGeneration(grid);
        }
    }

    // Calculate the next generation of the grid
    public static int[][] getNextGeneration(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] newGrid = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int aliveNeighbors = countAliveNeighbors(grid, row, col);

                if (grid[row][col] == 1) {
                    // Cell is currently alive
                    if (aliveNeighbors < 2 || aliveNeighbors > 3) {
                        newGrid[row][col] = 0; // Dies due to underpopulation or overpopulation
                    } else {
                        newGrid[row][col] = 1; // Lives on
                    }
                } else {
                    // Cell is currently dead
                    if (aliveNeighbors == 3) {
                        newGrid[row][col] = 1; // Becomes alive due to reproduction
                    } else {
                        newGrid[row][col] = 0; // Remains dead
                    }
                }
            }
        }

        return newGrid;
    }

    // Count the number of alive neighbors for a cell
    public static int countAliveNeighbors(int[][] grid, int row, int col) {
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
        int aliveNeighbors = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < 8; i++) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];

            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 1) {
                aliveNeighbors++;
            }
        }

        return aliveNeighbors;
    }

    // Print the current state of the grid
    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}
