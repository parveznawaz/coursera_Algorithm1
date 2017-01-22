import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item [] items;
    private int length;
    private int tail;

    public RandomizedQueue()                 // construct an empty randomized queue
    {
        length=2;
        items=(Item[]) new Object[length];
        //head=0;
        tail=-1;
    }
    public boolean isEmpty()                 // is the queue empty?
    {
      return tail==-1;
    }
    public int size()                        // return the number of items on the queue
    {
        return tail+1;
    }
    public void enqueue(Item item)           // add the item
    {
        if(length-1==tail)
        {
            length=2*length;
            Item[] temp=(Item[]) new Object[length];
            for(int i=0;i<items.length;i++)
            {
                temp[i]=(Item)new Object();
                temp[i]=items[i];
                items[i]=null;
            }
            items=temp;


        }
        tail++;
        if(items[tail]==null)
            items[tail]=(Item) new Object();
        items[tail]=item;
    }
    public Item dequeue()                    // remove and return a random item
    {
        int index= StdRandom.uniform(0,tail+1);
        Item temp=items[index];
        for(int i=index;i<tail;i++)
        {
            items[i]=items[i+1];
        }
        items[tail--]=null;
        if(tail+1<=items.length/4)
        {
            Item[] halfItems=(Item[]) new Object[length/2];
            for(int i=0;i<=tail;i++)
            {
                halfItems[i]=(Item)new Object();
                halfItems[i]=items[i];
                items[i]=null;
            }
            items=halfItems;
        }
        return temp;
    }
    public Item sample()                     // return (but do not remove) a random item
    {
        int index= StdRandom.uniform(0,tail)+1;
        Item temp=items[index];
        return temp;
    }
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {

        return new RandomizedQueueIterator();
    }
    private class RandomizedQueueIterator implements Iterator<Item>
    {

        @Override
        public boolean hasNext() {
            return !isEmpty();
        }

        @Override
        public Item next() {
           return dequeue();
        }
    }
    public static void main(String[] args)   // unit testing
    {
 /*       RandomizedQueue<Integer> randomizedQueue=new RandomizedQueue<>();
        randomizedQueue.enqueue(0);
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);
        while(!randomizedQueue.isEmpty())
        {
            System.out.println(randomizedQueue.dequeue());
        }*/

    }
}

