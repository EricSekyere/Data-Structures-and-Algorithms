package A3Q2;

/**
 * Tests SortedArrayPair.
 * @author jameselder
 */
public class testSortedArrayPair {

    public static void main(String[] args) {
        // TODO code application logic here
        Integer[] a1 = {1, 3, 5, 7, 9};
        Integer[] a2 = {2, 4, 6, 8, 10};
        Integer k = 5;
        Integer kth;
        SortedArrayPair<Integer> myArrayPair;

        try {
            myArrayPair = new SortedArrayPair<Integer>(a1, a2);
            try {
                kth = myArrayPair.kthSmallestOfUnion(k);
                System.out.println("The " + k +
                        "th smallest integer in the two arrays is " + kth);
            } catch (RankOutOfRangeException roorx) {
                System.out.println("Rank out of range");
            }
        } catch (UnmatchedArrayPairException uapx) {
            System.out.println("Arrays not of equal length");
        }
    }
}
