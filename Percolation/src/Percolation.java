import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int gridSize;
    private boolean[][] matrix;
    private WeightedQuickUnionUF unionFindTopObj; /* , unionFindBottonObj;*/
    private int totalOpenedSites;
    
    public Percolation(int n) {
      if (n <= 0) {
        throw new IllegalArgumentException();
      }

      totalOpenedSites = 0;
      gridSize = n;
      matrix = new boolean[gridSize + 1][gridSize + 1];
      for (int i = 1; i <= gridSize; i++) {
        for (int j = 1; j <= gridSize; j++) {
           matrix[i][j] = false;
        }
      }

      unionFindTopObj = new WeightedQuickUnionUF(gridSize * gridSize + 1);
     /* unionFindBottonObj = new WeightedQuickUnionUF(gridSize * gridSize + 1);*/
    }

    public void open(int row, int col) {
      if (row < 1 || row > gridSize || col < 1 || col > gridSize) {
        throw new IndexOutOfBoundsException();
      }

      if (!matrix[row][col]) {
        matrix[row][col] = true;
        totalOpenedSites++;
        
      if (row > 1 && matrix[row][col] && matrix[row - 1][col]) {
        unionFindTopObj.union(xyTo1D(row, col), xyTo1D(row - 1, col));
       /* unionFindBottonObj.union(xyTo1D(row, col), xyTo1D(row - 1, col));*/
      }

      if (row < gridSize && matrix[row][col] && matrix[row + 1][col]) {
        unionFindTopObj.union(xyTo1D(row, col), xyTo1D(row + 1, col));
       /* unionFindBottonObj.union(xyTo1D(row, col), xyTo1D(row + 1, col));*/
      }

      if (col > 1 && matrix[row][col] && matrix[row][col - 1]) {
        unionFindTopObj.union(xyTo1D(row, col), xyTo1D(row, col - 1));
       /* unionFindBottonObj.union(xyTo1D(row, col), xyTo1D(row, col - 1));*/
      }

      if (col < gridSize && matrix[row][col] && matrix[row][col + 1]) {
        unionFindTopObj.union(xyTo1D(row, col), xyTo1D(row, col + 1));
       /* unionFindBottonObj.union(xyTo1D(row, col), xyTo1D(row, col + 1));*/
      }
           
      if (row == 1) {
        unionFindTopObj.union(xyTo1D(row, col), 0);
        }

     /* if (row == gridSize) {
          unionFindBottonObj.union(xyTo1D(row, col), 0);
          }*/
      }

    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || row > gridSize || col < 1 || col > gridSize) {
            throw new IndexOutOfBoundsException();
            }
        return matrix[row][col];
        }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > gridSize || col < 1 || col > gridSize) {
            throw new IndexOutOfBoundsException();
            }

        return unionFindTopObj.connected(xyTo1D(row, col), 0) && matrix[row][col];
        }

    public int numberOfOpenSites() {       
      return totalOpenedSites;
    }

    private int xyTo1D(int row, int col) {
      return gridSize * (row - 1) + col;
    }

    public boolean percolates() {
      for (int i = 1; i <= gridSize; i++)
      {
        if (matrix[gridSize][i] && unionFindTopObj.connected(0, xyTo1D(gridSize, i))) {
          return true;
        }         
      }      
      return false;
     }

    public static void main(String[] args) {
      Percolation p = new Percolation(3);
      System.out.println(p.isFull(3, 3));
   }

}
