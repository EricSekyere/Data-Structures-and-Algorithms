package A3Q1;
import java.util.Iterator;

/**
 * Extends the BinarySearchTree class to allow convenient access to entries
 * within a specified range of key values (findAllInRange).
 * @author jameselder
 */
public class BSTRange<K,V> extends BinarySearchTree<K,V>{


    /* Returns the lowest (deepest) position in the subtree rooted at pos
     * that is a common ancestor to positions with
     * keys k1 and k2, or to the positions they would occupy were they present.
     */
	private BTPosition<Entry<K, V>> newpos;
    protected Position<Entry<K, V>> findLowestCommonAncestor(K k1, K k2, Position<Entry<K, V>> pos) {
		//implement this method
    	checkKey(k1);
    	checkKey(k2);
        if (!(isInternal(pos) ||isRoot(pos))) {
            return pos;
        }
        else{
        	if(C.compare(k2, key(pos)) < 0) {
        		pos = findLowestCommonAncestor(k1, k2, left(pos));
        	}
        	else{
        		if (C.compare(k1, key(pos)) > 0) {
        		pos = findLowestCommonAncestor(k1, k2, right(pos));
        		}
        	} 
            return pos;
        }  
    }

    /* Finds all entries in the subtree rooted at pos  with keys of k or greater
     * and copies them to L, in non-decreasing order.
     */
    protected void findAllAbove(K k, Position<Entry<K, V>> pos, PositionList<Entry<K, V>> L) {
    	checkKey(k);
    	if(isExternal(pos)&&!isRoot(pos)&&!isInternal(pos)){
    		return ;
    	}
    	else{
    		newpos = (BTPosition)super.right(pos);
    		if (C.compare(k, key(pos))> 0){
    			findAllAbove(k, newpos, L);
    		}
    		else{
    			newpos = (BTPosition)super.left(pos);
    			//find the elements on the left with the key k
                findAllAbove(k, newpos, L);
                //add to positionList
                L.addLast(pos.element());
                newpos = (BTPosition)super.right(pos);
              //find the elements on the right with the key k
                findAllAbove(k, newpos, L);
    		}
    	}
	}

    /* Finds all entries in the subtree rooted at pos with keys of k or less
     * and copies them to L, in non-decreasing order.
     */
    protected void findAllBelow(K k, Position<Entry<K, V>> pos, PositionList<Entry<K, V>> L) {
 		//implement this method
    	checkKey(k);
    	
    	if(isExternal(pos)&&!isRoot(pos)&&!isInternal(pos)){
    		return;
    	}
    	else{
       	 newpos = (BTPosition)super.left(pos);
    		if (C.compare(k, key(pos)) < 0) {
    			// recursive call on left
                findAllBelow(k, newpos, L);
            } 
    		else {
                findAllBelow(k, newpos, L);
                L.addLast(pos.element());
                newpos = (BTPosition)super.right(pos);
                findAllBelow(k, newpos, L);
            }
    	}	
	}

    /* Returns all entries with keys no less than k1 and no greater than k2,
     * in non-decreasing order.
     */
    public PositionList<Entry<K, V>> findAllInRange(K k1, K k2) {
    	//validate k1 and k2
        checkKey(k1);
        checkKey(k2);
        // the temporary root
        Position<Entry<K, V>> temp = root();
        // the list to add to
        PositionList<Entry<K, V>> List = new NodePositionList<Entry<K, V>>();
        
        temp = findLowestCommonAncestor(k1, k2, temp);
        if (isExternal(temp)&&!isRoot(temp)&&!isInternal(temp)) {
            return List;
        }
        // find all above
        findAllAbove(k1, left(temp), List);
        // add to list
        List.addLast(temp.element());
        // do for right
        findAllBelow(k2, right(temp), List);
        // output list
        return List;
    }

}
