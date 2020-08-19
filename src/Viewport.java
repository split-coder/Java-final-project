public final class Viewport
{
    private int row;
    private int col;
    private int numRows;
    private int numCols;

    public Viewport(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
    }

    //getters
    public int getRow(){ return row;}
    public int getCol(){ return col;}
    public int getNumRows(){ return numRows;}
    public int getNumCols(){return numCols;}
    // Functions below this are all added functions from the functions from the file.
    //1
    public void shift(int col, int row) {
        this.col = col;
        this.row = row;
    }

    //2
    public boolean contains(Point p) {
        return p.y >=  this.row && p.y < this.row + this.numRows
                && p.x >= this.col && p.x < this.col + this.numCols;
    }

    //3
    public Point viewportToWorld(int col, int row) {
        return new Point(col + this.col, row + this.row);
    }

    //4
    public Point worldToViewport(int col, int row) {
        return new Point(col - this.col, row - this.row);
    }

}
