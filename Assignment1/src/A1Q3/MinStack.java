package A1Q3;
import java.util.*;

/**
 * Specializes the stack data structure for comparable elements, and provides
 * a method for determining the minimum element on the stack in O(1) time.
 * @author jameselder
 */
public class MinStack<E extends Comparable> extends Stack<E> {

    private Stack<E> minStack;
    

    public MinStack() {
        minStack = new Stack<E>();
    }

    /* must run in O(1) time */
    /**
     * @Overide
     */
    public E push(E element) {
        super.addElement(element);
        if (minStack.empty()||minStack.peek().compareTo(element)>=0) { 
        	minStack.addElement(element);
        } 
        return element;
    }

    /* @exception  EmptyStackException  if this stack is empty. */
    /* must run in O(1) time */
   public synchronized E pop() {
	   if (minStack.isEmpty()) {
		   return null;
	   }
	   E current = minStack.peek();
	   E element = super.pop();
	   if (element == current){
		   minStack.pop();
	   }
	   return element;
	   
    }
   

    /* Returns the minimum value currenctly on the stack. */
    /* @exception  EmptyStackException  if this stack is empty. */
    /* must run in O(1) time */
    public synchronized E min() {
    	if (minStack.isEmpty()){
    		return null;
    	}
    	return minStack.peek();
    }
}
