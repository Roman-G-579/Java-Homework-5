package mines;

public class Mines {

    private int height;
    private int width;
    private int minesOnBoard;
    private boolean showAllField;
    private Tile[][] board;

    public Mines(int height, int width, int numMines) {
        this.height = height;
        this.width = width;

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
        minesOnBoard++;
        return true;
    }

    public boolean open(int i, int j) {
        if (board[i][j].isOpen || board[i][j].hasFlag) {
            return false;
        }

        if (board[i][j].isHasMine()) {
            return false;
        }

        //opens the tile
        board[i][j].isOpen = true;

        if (checkNeighbors(i, j) == 0) {
            //north
            if (i - 1 >= 0) {
                open(i - 1, j);
            }
            //north-east
            if (i - 1 >= 0 && j + 1 < width) {
                open(i - 1, j + 1);
            }
//            //east
            if (j < width - 1) {
                open(i, j + 1);
            }
            //south - east
            if (i + 1 < height && j + 1 < width) {
                open(i + 1, j + 1);
            }
            //south
            if (i < height - 1) {
                open(i + 1, j);
            }
            //south - west
            if (i + 1 < height && j - 1 >= 0) {
                open(i + 1, j - 1);
            }
            //west
            if (j - 1 >= 0) {
                open(i, j - 1);
            }
            //north - west
            if (i - 1 >= 0 && j - 1 >= 0) {
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
        int counter = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j].isOpen()) {
                    counter++;
                }
            }
        }
        return counter == (width * height) - minesOnBoard;
    }

    //prints a string representation of the tile
    public String get(int i, int j) {
        if (board[i][j].isOpen() || showAllField) {
            if (board[i][j].isHasMine()) {
                return "X";
            }
            checkNeighbors(i, j);
            if (board[i][j].getMinesNearby() == 0) {
                return " ";
            }
            return Integer.toString(board[i][j].getMinesNearby());
        }
        if (!board[i][j].isOpen() && board[i][j].isHasFlag()) {
            return "F";
        }
        if (!board[i][j].isOpen() && !board[i][j].isHasFlag()) {
            return ".";
        }
        throw new IllegalArgumentException();
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
        showAllField = showAll;
    }

    @Override
    public String toString() {
        StringBuilder stringBoard = new StringBuilder();

        int smallerLen;
        int biggerLen;


        biggerLen = height >= width ? height : width;
        smallerLen = width < height ? width : height;

        for (int i = 0; i < biggerLen; i++) {
            for (int j = 0; j < smallerLen; j++) {
                if (height >= width) {
                    stringBoard.append(get(i, j));
                } else {
                    stringBoard.append(get(j, i));
                }
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

        public int getMinesNearby() {
            return minesNearby;
        }

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