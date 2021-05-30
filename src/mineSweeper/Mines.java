package mineSweeper;

public class Mines {

    private int height;
    private int width;
    private int numMines;
    private Cell[][] board;

    public Mines(int height, int width, int numMines) {
        this.height = height;
        this.width = width;
        this.numMines = numMines;

        board = new Cell[height][width];
    }

    public boolean addMine(int i, int j) {
        if (!board[i][j].hasMine) {
            board[i][j].hasMine = true;
            return true;
        }
        board[i][j].hasMine = false;
        return false;
    }

    public boolean open(int i, int j) {
        return true;
    }

    public void toggleFlag(int x, int y) {// FIXME: 31/05/2021 is the x and y is on purpose or can it be i and j?
        if (board[x][y].Flagged) {
            board[x][y].Flagged = false;
            return;
        }
        board[x][y].Flagged = true;
    }

    public String get(int i, int j) {
        if (!board[i][j].isOpen) {
            if (board[i][j].Flagged) {
                return "F";
            }
            return ".";
        }
        if (board[i][j].hasMine) {
            return "X";
        }
        return "99";// FIXME: 31/05/2021 supposed to return the number of neighbors
    }

    public void setShowAll(boolean showAll) {

    }

    @Override
    public String toString() {
        StringBuilder stringBoard = new StringBuilder();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                stringBoard.append(get(i, j));
            }
        }
        return stringBoard.toString();
    }

    private class Cell {

        private boolean hasMine;
        private boolean isOpen;
        private boolean Flagged;

    }
}