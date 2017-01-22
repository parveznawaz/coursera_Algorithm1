import edu.princeton.cs.algs4.StdIn;

/**
 * Created by nawaz on 1/3/2017.
 */
public class Permutation {
    public static void main(String[] args)
    {
        //String inputLine="AA BB BB BB BB BB CC CC";
        int n=Integer.parseInt(args[0]);
        String inputLine=StdIn.readLine();

        RandomizedQueue<String> randomizedQueue=new RandomizedQueue<>();
        for (String s:inputLine.split(" ")) {
            randomizedQueue.enqueue(s);
        }

        for(int i=0;i<n;i++)
        {
           System.out.println(randomizedQueue.dequeue());
        }
    }
}