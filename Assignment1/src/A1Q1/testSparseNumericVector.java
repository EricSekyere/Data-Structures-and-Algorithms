package A1Q1;

/**
 *
 * @author jameselder
 */
public class testSparseNumericVector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SparseNumericVector X = new SparseNumericVector();
        SparseNumericVector Y = new SparseNumericVector();
        double projection;

        X.add(new SparseNumericElement(150000, 3.1415));
        X.add(new SparseNumericElement(15, 3));
        X.add(new SparseNumericElement(1500, 3.14));
        X.add(new SparseNumericElement(150, 3.1));
        X.add(new SparseNumericElement(15000, 3.141));
        Y.add(new SparseNumericElement(150000, 1));
        Y.add(new SparseNumericElement(15, 1));
        X.remove((long) 150);

        projection = X.project(Y);

        System.out.println("The inner product of");
        System.out.println(X);
        System.out.println("and");
        System.out.println(Y);
        System.out.println("is ");
        System.out.println(projection); //answer should be 3*1 + 3.1415*1 = 6.1415
     }

}