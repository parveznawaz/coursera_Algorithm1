import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private  class Node
    {
        Item data;
        Node next;
    }

    private  Node head,tail;
    private  int numberOfNode;
    public Deque()                           // construct an empty deque
    {
        head=null;
        tail=null;
    }
    public boolean isEmpty()                 // is the deque empty?
    {
        return head==null;
    }
    public int size()                        // return the number of items on the deque
    {
        return numberOfNode;
    }
    public void addFirst(Item item)          // add the item to the front
    {
        numberOfNode++;
        Node newNode=new Node();
        newNode.data=item;

        if(head==null)
        {
            newNode.next=null;
            tail=newNode;
        }
        else
        {
            newNode.next=head;
        }
        head=newNode;
    }
    public void addLast(Item item)           // add the item to the end
    {
        numberOfNode++;
        Node newNode=new Node();
        newNode.data=item;
        if(head==null)
        {
            newNode.next=null;
            head=newNode;
            tail=newNode;
        }
        else
        {
            tail.next=newNode;
            tail=newNode;
        }
    }
    public Item removeFirst()                // remove and return the item from the front
    {
        if(head!=null)
        {
            Node temp=head;
            if(head.next!=null)
            {
                head=head.next;
            }
            else
            {
                head=null;
                tail=null;
            }
            numberOfNode--;
            return temp.data;
        }
        return null;
    }
    public Item removeLast()                 // remove and return the item from the end
    {
        if(tail!=null)
        {
            Node temp=tail;
            if(head.next==null)
            {
                head=null;
                tail=null;
            }
            else
            {
                Node beforeLast=head;
                while(beforeLast.next!=tail)
                    beforeLast=beforeLast.next;
                beforeLast.next=null;
                tail=beforeLast;
            }
            numberOfNode--;
            return temp.data;
        }
        return  null;
    }
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item>
    {

        @Override
        public boolean hasNext() {
            return !isEmpty();
        }

        @Override
        public Item next() {
            return (Item) removeLast();
        }
    }
    public static void main(String[] args)   // unit testing
    {
        Deque<String> deque=new Deque<>();
        deque.addFirst("B");
        deque.addFirst("A");
        deque.addLast("C");
        deque.addLast("D");
        while(!deque.isEmpty())
        {
            System.out.println(deque.removeFirst());
        }
    }
}
