package A2Q2;

import java.util.*;

/**
 * Adaptible priority queue using location-aware entries in a min-heap, based on
 * an extendable array.  The order in which equal entries were added is preserved.
 *
 * @author jameselder
 * @param <E> The entry type.
 */
public class APQ<E> {

    private final ArrayList<E> apq; //will store the min heap
    private final Comparator<E> comparator; //to compare the entries
    private final Locator<E> locator;  //to locate the entries within the queue

    /**
     * Constructor
     * @param comparator used to compare the entries
     * @param locator used to locate the entries in the queue
     */
    public APQ(Comparator<E> comparator, Locator<E> locator) throws NullPointerException {
        if (comparator == null || locator == null) {
            throw new NullPointerException();
        }
        apq = new ArrayList<>();
        apq.add(null); //dummy value at index = 0
        this.comparator = comparator;
        this.locator = locator;
    }

    /**
     * Inserts the specified entry into this priority queue.
     *
     * @param e the entry to insert
     * @throws NullPointerException
     */
    public void offer(E e) throws NullPointerException {
    	if (e == null) {
    		throw new NullPointerException();
    	}
    	apq.add(e);
    	locator.set(e, size());
    	upheap(size());
    }

   /**
     * Removes the entry at the specified location.
     *
     * @param pos the location of the entry to remove
     * @throws BoundaryViolationException
     */
    public void remove(int pos) throws BoundaryViolationException {
    	if(pos > size() || pos <= 0){
    		throw new BoundaryViolationException();
    	}
    	swap(size(), pos);
    	locator.set(apq.remove(size()), -1);
    	downheap(pos);
    }
   /**
     * Removes the first entry in the priority queue.
     */
    public E poll() {
    	swap(apq.size()-1, 1);
    	E temp = apq.remove(size());
    	locator.set(temp, -1);
    	downheap(1);
    	return temp;
    }

  /**
     * Returns but does not remove the first entry in the priority queue.
     */
     public E peek() {
        if (isEmpty()) {
            return null;
        }
        return apq.get(1);
    }

   public boolean isEmpty() {
        return (size() == 0); 
    }

    public int size() {
        return apq.size() - 1; //dummy node at location 0
    }


    /**
     * Shift the entry at pos upward in    	if(pos >= apq.size()|| pos <= 0){
    		throw new BoundaryViolationException();
    	}
    	swap(apq.size()-1, pos);
    	apq.remove(apq.size()-1);
    	downheap(pos the heap to restore the minheap property
     * @param pos the location of the entry to move
     */
    private void upheap(int pos) {
    	int i = pos;
    	for (;;) {
    		// parent index
    		int parent = (i - 1) / 2;
    		//stop if root
    		if (parent <= 0) {
    			break;
    		}
    		//parent smaller than current position
    		if (comparator.compare(apq.get(parent), apq.get(i)) <= 0) {
    			break;
    		}
    		swap(parent, i);
    		i = parent;
    	}
    }

    /**
     * Shift the entry at pos downward in the heap to restore the minheap property
     * @param pos the location of the entry to move
     */
    private void downheap(int pos) {
    	int i = pos;
    	for (;;) {
    		// get index of left child
    		int c = (2*i)+1;
    		// no children
    		if (c > size()) {
    			break;
    		}    		
    		// choose the smaller child
    		if (c < size() && comparator.compare(apq.get(c), apq.get(c + 1)) > 0) {
    			c += 1;
    		}
    		// parent is smaller or equal than children, no more actions
    		if (comparator.compare(apq.get(i), apq.get(c)) <= 0) {
    			break;
    		}
    		// replace parent with child and
    		// continue down the heap
    		swap(i, c);
    		i = c;
    	}
    }

    /**
     * Swaps the entries at the specified locations.
     *
     * @param pos1 the location of the first entry 
     * @param pos2 the location of the second entry 
     */
    private void swap(int pos1, int pos2) {
       E temp  = apq.get(pos1);
       apq.set(pos1, apq.get(pos2));
       apq.set(pos2, temp);
       locator.set(apq.get(pos1), pos1);
       locator.set(apq.get(pos2), pos2);
    }
}