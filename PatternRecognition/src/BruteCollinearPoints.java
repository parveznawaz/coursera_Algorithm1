import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;


public class BruteCollinearPoints {
	private LineSegment[] segments;


    public BruteCollinearPoints(Point[] points) {
    	
    	checkDuplicatedEntries(points);
        ArrayList<LineSegment> foundSegments = new ArrayList<>();

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);

        for (int p = 0; p < pointsCopy.length - 3; p++) {
            for (int q = p + 1; q < pointsCopy.length - 2; q++) {
                for (int r = q + 1; r < pointsCopy.length - 1; r++) {
                    for (int s = r + 1; s < pointsCopy.length; s++) {
                        if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[r]) &&
                                pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[s])) {
                            foundSegments.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
                        }
                    }
                }
            }
        }

        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, numberOfSegments());
    }

    private void checkDuplicatedEntries(Point[] points) {
    	if(points==null) throw new NullPointerException ();
    	for(Point p:points)
    	{
    		if(p==null) throw new NullPointerException ();
    	}
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicated entries in given points.");
                }
            }
        }
    }
	public static void main(String [] args)
	 {
		 In in = new In(args[0]);      // input file
	     int n = in.readInt();         // n
	     Point[] points=new Point[n];
	     for(int i=0;i<n;i++)
	     {
	    	 points[i]=new Point(in.readInt(),in.readInt());
	     }
	     BruteCollinearPoints bruteCollinearPoints=new BruteCollinearPoints(points);
	     for (int i=0;i<bruteCollinearPoints.numberOfSegments();i++) {
			System.out.println(bruteCollinearPoints.segments()[i]);
		}
	 }
}
