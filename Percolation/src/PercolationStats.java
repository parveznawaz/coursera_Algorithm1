import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private double [] results;
  public PercolationStats(int n, int trials) {
     if (n <= 0 || trials <= 0) {
       throw new IllegalArgumentException();       
     }
    results = new double[trials];

    for (int i = 0; i < trials; i++) {
      Percolation percolation = new Percolation(n);
      while (percolation.numberOfOpenSites() <= n * n) {
        int row = StdRandom.uniform(n) + 1;
        int col = StdRandom.uniform(n) + 1;        
        percolation.open(row, col);
        if (percolation.percolates()) {
          break;
        }
       }     
      results[i] = (double) percolation.numberOfOpenSites() / (n * n);
    }

   /* System.out.printf("mean                    = %.16f\n", mean());
    System.out.printf("stddev                  = %.16f\n", stddev());
    System.out.printf("95%% confidence interval = %.16f, %.16f\n\n", confidenceLo(), confidenceHi());*/
  }

  public double mean() {
    return StdStats.mean(results);
  }

  public double stddev() {
    if (results.length == 1) {
      return Double.NaN;
    }
    return StdStats.stddev(results);
  }

  public double confidenceLo() {
    return mean() - 1.96 * stddev() / Math.sqrt(results.length);
  }

  public double confidenceHi() {
    return mean() + 1.96 * stddev() / Math.sqrt(results.length);
  }
  
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int t = Integer.parseInt(args[1]);
    if (n < 1 || t < 1) {
      throw new IllegalArgumentException();
    }
    new PercolationStats(n, t);
    }
}