import java.util.Random;

public class Demo {
    static int COUNT = 0;
    static int rows = 10;
    static int columns = 15;
    static final int result[][] = new int[rows][columns];
    static int visited[][] = new int[rows][columns];

    public static void main(String[] args) {
        Grid[][] grids = createGrid();
        int response = selectBlock(grids);

        System.out.println("The largest connected component :" +

                response);
        System.out.println("Design of * for response");
        System.out.println();
        System.out.println();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (result[i][j] != 0){

                System.out.println("*");}
                else{System.out.print(". ");}

            }
            System.out.println();
        }
    }

    public static Grid[][] createGrid() {
        Random random = new Random();
        Grid[][] grids = new Grid[rows][columns];
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j] = new Grid();
                grids[i][j].setColour(random.nextInt(4 - 1) + 1);
            }
        }
        return grids;
    }



    private static int selectBlock(Grid[][] gridtoCheck) {
        int current_max = Integer.MIN_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                for (int k = 0; k < rows; k++) {
                    for (int m = 0; m < columns; m++) {
                        visited[k][m] = 0;
                    }
                }

                COUNT = 0;

                if (j + 1 < columns)
                    BFS(gridtoCheck[i][j].getColour(), gridtoCheck[i][j + 1].getColour(),
                            i, j, gridtoCheck);

                if (COUNT >= current_max) {
                    current_max = COUNT;
                    resetResult(gridtoCheck[i][j].getColour(), gridtoCheck);
                }
                resetVisited();

                COUNT = 0;

                if (i + 1 < rows) {
                    BFS(gridtoCheck[i][j].getColour(),
                            gridtoCheck[i + 1][j].getColour(), i, j, gridtoCheck);
                }

                if (COUNT >= current_max) {
                    current_max = COUNT;
                    resetResult(gridtoCheck[i][j].getColour(), gridtoCheck);
                }
            }
        }
        return current_max;
    }




    static void BFS(int x, int y, int i,
                    int j, Grid[][] input) {
        if (x != y)
            return;

        visited[i][j] = 1;
        COUNT++;

        int[] x_move = {0, 0, 1, -1};
        int[] y_move = {1, -1, 0, 0};

        for (int u = 0; u < 4; u++)
            if ((isValid(i + y_move[u], j + x_move[u], x, input))) {
                BFS(x, y, i + y_move[u], j + x_move[u], input);
            }
    }

    static boolean isValid(int x, int y,
                            int key,
                            Grid[][] input) {
        if (x < rows && y < columns &&
                x >= 0 && y >= 0) {
            return visited[x][y] == 0 &&
                    input[x][y].getColour() == key;
        } else
            return false;
    }

    static void resetResult(int key, Grid[][] input) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (visited[i][j] == 1 &&
                        input[i][j].getColour() == key)
                    result[i][j] = visited[i][j];
                else
                    result[i][j] = 0;
            }
        }
    }

    static void resetVisited() {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                visited[i][j] = 0;
    }

}

class Grid {
    private int colour;

    public Grid() {
    }

    public Grid(int colour, int x, int y) {
        this.colour = colour;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "colour='" + colour + '\'' +
                '}';
    }
}