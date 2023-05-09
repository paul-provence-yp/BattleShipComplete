


public class Coordinates {
    int row;
    int col;

    public Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static boolean compareTo(Coordinates coordinates1, Coordinates coordinates2) {
        boolean same = false;

        if (coordinates1.getRow() == coordinates2.getRow()
            && coordinates1.getCol() == coordinates2.getCol()) {
              same = true;
            }

        return same;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        String s = "row: " + row + " col:" + col;

        return s;
    }


}
