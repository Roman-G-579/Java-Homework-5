package mineSweeper;

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

    public boolean open(int i, int j) {// TODO: 02/06/2021 check the recursion
        //opens the tile
        board[i][j].isOpen = true;

        // checks whether the current tile contains a mine????????????????????????????? WHAT?
        if (!board[i][j].hasMine) {
            return true;
        }

        if (checkNeighbors(i, j) == 0) {
            //north
            open(i - 1, j);
            //north - east
            open(i - 1, j + 1);
            //east
            open(i, j + 1);
            //south - east
            open(i + 1, j + 1);
            //south
            open(i + 1, j);
            //south - west
            open(i + 1, j - 1);
            //west
            open(i, j - 1);
            //north - west
            open(i - 1, j - 1);
        }
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
        return "" + checkNeighbors(i, j);
    }

    private int checkNeighbors(int i, int j) {
        int neighbors = 0;

        //north
        if (i != 0 && board[i - 1][j].hasMine) {
            neighbors++;
        }

        //north - east
        if (i != 0 && j < width && board[i - 1][j + 1].hasMine) {
            neighbors++;
        }

        //east
        if (j < width && board[i][j + 1].hasMine) {
            neighbors++;
        }

        //south - east
        if (i < height && j < width && board[i + 1][j + 1].hasMine) {
            neighbors++;
        }

        //south
        if (i < height && board[i + 1][j].hasMine) {
            neighbors++;
        }

        //south - west
        if (i < height && j > 0 && board[i + 1][j - 1].hasMine) {
            neighbors++;
        }

        //west
        if (j > 0 && board[i][j - 1].hasMine) {
            neighbors++;
        }

        //kim kardashian's son
        if (i > 0 && j > 0 && board[i - 1][j - 1].hasMine) {
            neighbors++;
        }

        return neighbors;
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
        private int bombsNearby;

    }
}

// creates a copy of the board so when its revealed the original "data" wont be overwritten
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