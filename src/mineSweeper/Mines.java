package mineSweeper;

import java.util.List;

public class Mines {

    private int height;
    private int width;
    private int numMines;
    private boolean showAll;
    private Tile[][] board;

    public Mines(int height, int width, int numMines) {
        this.height = height;
        this.width = width;
        this.numMines = numMines;

        board = new Tile[height][width];

        //adds the mines randomly to the board
        while (numMines != 0) {
            if (addMine((int) (Math.random() * height), (int) (Math.random() * width))) {
                numMines--;
            }
        }
    }

    public boolean addMine(int i, int j) {
        if (board[i][j].hasMine) {
            return false;
        }
        board[i][j].hasMine = true;
        return true;
    }

    public boolean open(int i, int j) {// TODO: 31/05/2021 finish the recursion
        board[i][j].isOpen = true;

        if (!board[i][j].hasMine) {
            return true;
        }

        //north
        if (open(i-1,j);
        //north-east
        open(i-1,j+1);
        //east
        open(i,j+1);

        return false;
    }

    public void toggleFlag(int x, int y) {
        if (board[x][y].Flagged) {
            board[x][y].Flagged = false;
            return;
        }
        board[x][y].Flagged = true;
    }

    public boolean isDone() {
        return false;
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
        showAll = true;
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

    private static class Tile {

        private boolean isOpen;
        private boolean hasMine;
        private boolean Flagged;

    }
}

//        if (!showAll) {
//            return;
//        }
//
//        Tile[][] boardCopy = new Tile[width][height];
//
//        for (int i = 0; i < width; i++) {
//            boardCopy[i] = board[i];
//            for (int j = 0; j < height; j++) {
//                boardCopy[i][j].isOpen = true;
//            }
//        }
//        boardCopy.toString();