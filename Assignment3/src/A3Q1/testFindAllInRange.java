package A3Q1;
import java.util.*;

/**
 * Tests BSTRange
 * @author jameselder
 */
public class testFindAllInRange {

    public static void main(String[] args) {
        BSTRange<Integer, String> medals = new BSTRange<Integer, String>();
        PositionList<Entry<Integer, String>> medalList;
        Iterator<Entry<Integer, String>> entryIter;
        int k1 = 3;
        int k2 = 7;

        medals.insert(2, "Germany");
        medals.insert(8, "China");
        medals.insert(3, "Canada");
        medals.insert(9, "Sweden");
        medals.insert(4, "Norway");
        medals.insert(10, "France");
        medals.insert(1, "USA");
        medals.insert(5, "Austria");
        medals.insert(6, "Russian Federation");
        medals.insert(7, "Korea");
        medalList = medals.findAllInRange(k1, k2);
        entryIter = medalList.iterator();

        System.out.println("The countries ranked from " + k1 + " to " + k2 + " in medal standings are:");
        while (entryIter.hasNext()) {
            System.out.println(entryIter.next().getValue());
        }
    }
}
