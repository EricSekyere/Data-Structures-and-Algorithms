package A4Q2;

/**
 *
 * @author jameselder
 */
public class testCSEPreReqs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CSEPreReqs csePreReqs = new CSEPreReqs();

        try {
            csePreReqs.addCourse(3101);
        } catch (Exception x) {
            System.out.println("Test Case 1 Failed");
        }
        try {
            csePreReqs.addCourse(1030);
        } catch (Exception x) {
            System.out.println("Test Case 2 Failed");
        }
        try {
            csePreReqs.addCourse(2011);
        } catch (Exception x) {
            System.out.println("Test Case 3 Failed");
        }
        try {
            csePreReqs.addCourse(1020);
        } catch (Exception x) {
            System.out.println("Test Case 4 Failed");
        }
        try {
            csePreReqs.validateAndAddPreReq(3101, 2011);
        } catch (Exception x) {
            System.out.println("Test Case 4 Failed");
        }
        try {
            csePreReqs.validateAndAddPreReq(2011, 3101);
        } catch (CircularPreRequisiteException x) {
            System.out.println("Test Case 5 Passed");
        } catch (Exception x) {
            System.out.println("Test Case 5 Failed");
        }
        try {
            int[] sequence1 = {1030, 2011, 3101};
            for (int i=0; i < sequence1.length; i++) {
                System.out.print(sequence1[i] + " ");
            }
            if (csePreReqs.validateCourseSequence(sequence1)) {
                System.out.println("is a valid course sequence");
            } else {
                System.out.println("is not a valid course sequence");
            }
        } catch (Exception x) {
            System.out.println("Test Case 6 Failed");
        }
        try {
            int[] sequence2 = {1030, 3101, -2011};
            for (int i=0; i < sequence2.length; i++) {
                System.out.print(sequence2[i] + " ");
            }
            if (csePreReqs.validateCourseSequence(sequence2)) {
                System.out.println("is a valid course sequence");
            } else {
                System.out.println("is not a valid course sequence");
            }
        } catch (Exception x) {
            System.out.println("Test Case 7 Failed");
        }

        csePreReqs.printCourses();
    }

}
