package mineSweeper;

public class Mines {

    private int height;
    private int width;
    private int numMines;
    private String[][] board = new String[height][width];

    public Mines(int height, int width, int numMines) {
        this.height = height;
        this.width = width;
        this.numMines = numMines;
    }

    public boolean addMine(int i, int j) {
        return true;
    }

    public boolean open(int i, int j) {
        return true;
    }

    public void toggleFlag(int x, int y) {

    }

    public String get(int i, int j) {
        return null;
    }

    public void setShowAll(boolean showAll) {

    }

    @Override
    public String toString() {
        StringBuilder stringBoard = new StringBuilder();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                stringBoard.append(board[i][j]);
            }
        }
        return stringBoard.toString();
    }
}