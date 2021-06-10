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

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = new Tile();
            }
        }

        //adds the mines randomly to the board
        while (numMines != 0) {
            if (addMine((int) (Math.random() * height), (int) (Math.random() * width))) {
                numMines--;
            }
        }
    }

    protected Tile getTile(int i, int j) {
        return board[i][j];
    }

    //adds a mine at the specified location on the board
    public boolean addMine(int i, int j) {
        if (board[i][j].hasMine) {
            return false;
        }
        board[i][j].hasMine = true;
        return true;
    }

    public boolean open(int i, int j) {// TODO: 10/06/2021 fix the conditions in the loop
        if (board[i][j].isOpen) {
            return true;
        }
        //if the current tile has a mine, returns false
        if (board[i][j].hasMine) {
            return false;
        }

        //opens the tile
        board[i][j].isOpen = true;


        if (board[i][j].minesNearby == 0) {
            //north
            if (i - 1 >= 0) {
                open(i - 1, j);
            }
            //east
            if (j < width - 1) {
                open(i, j + 1);
            }
            //south
            if (i < height - 1) {
                open(i + 1, j);
            }
            //west
            if (j - 1 >= 0) {
                open(i, j - 1);
            }
        }
        return true;
    }

    //places a flag on the specified tile
    public void toggleFlag(int x, int y) {
        if (board[x][y].hasFlag) {
            board[x][y].hasFlag = false;
            return;
        }
        board[x][y].hasFlag = true;
    }

    //checks whether the winning state has occurred
    public boolean isDone() {
        int counter = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!board[i][j].hasMine) {
                    counter++;
                }
            }
        }
        return counter == (width * height) - numMines;
    }

    //prints a string representation of the tile
    public String get(int i, int j) { //TODO: add showAll handling

        if (board[i][j].isOpen() && board[i][j].isHasMine()) {
            return "X";
        }
        if (board[i][j].isOpen() && !board[i][j].isHasMine()) {
            String neighbors = Integer.toString(checkNeighbors(i, j));
            if (neighbors.equals("0")) {
                return " ";
            }
            return neighbors;
        }
        if (!board[i][j].isOpen() && board[i][j].isHasFlag()) {
            return "F";
        }
        if (!board[i][j].isOpen() && !board[i][j].isHasFlag()) {
            return ".";
        }

        throw new IndexOutOfBoundsException();
    }

    //checks the amount of mine adjacent to the tile
    private int checkNeighbors(int i, int j) {
        int neighbors = 0;

        //north
        if (i != 0 && board[i - 1][j].hasMine) {
            neighbors++;
        }

        //north - east
        if (i != 0 && j < width - 1 && board[i - 1][j + 1].hasMine) {
            neighbors++;
        }

        //east
        if (j < width - 1 && board[i][j + 1].hasMine) {
            neighbors++;
        }

        //south - east
        if (i < height - 1 && j < width - 1 && board[i + 1][j + 1].hasMine) {
            neighbors++;
        }

        //south
        if (i < height - 1 && board[i + 1][j].hasMine) {
            neighbors++;
        }

        //south - west
        if (i < height - 1 && j > 0 && board[i + 1][j - 1].hasMine) {
            neighbors++;
        }

        //west
        if (j > 0 && board[i][j - 1].hasMine) {
            neighbors++;
        }

        //north - west
        if (i > 0 && j > 0 && board[i - 1][j - 1].hasMine) {
            neighbors++;
        }

        board[i][j].minesNearby = neighbors;
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
            stringBoard.append("\n");
        }
        return stringBoard.toString();
    }

    protected static class Tile {

        private boolean hasMine;
        private boolean isOpen;
        private boolean hasFlag;
        private int minesNearby;

        public boolean isHasMine() {
            return hasMine;
        }

        public boolean isOpen() {
            return isOpen;
        }

        public boolean isHasFlag() {
            return hasFlag;
        }
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