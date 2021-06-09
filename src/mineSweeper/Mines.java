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

    public boolean open(int i, int j) {// TODO: 02/06/2021 check the recursion
        //opens the tile
        board[i][j].isOpen = true;

        //if the current tile has a mine, returns false
        if (board[i][j].hasMine) {
            return false;
        }

        if (board[i][j].minesNearby == 0) {

            if (i > 0 && i < height - 1 && j > 0 && j < width - 1) {
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
        return false;
    }


    //prints a string representation of the tile
    public String get(int i, int j) {
        if (!board[i][j].isOpen) {
            //if the tile contains a flag
            if (board[i][j].hasFlag) {
                return "F";
            }
            return ".";
        }
        //if the tile contains a mine
        if (board[i][j].hasMine) {
            return "X";
        }

        String neighbors = Integer.toString(checkNeighbors(i, j));
        if (neighbors.equals("0")) {
            neighbors = "";
        }
        return neighbors;
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

        public int getMinesNearby() {
            return minesNearby;
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