package A4Q2;
import java.util.*;
/**
 *
 * Entry representing a course, including the course number and any
 * pre-requisites, represented as a linked list of courses. Also includes a
 * temporary boolean variable visited that can be used to indicate whether a
 * course vertex has been visited during the current graph search.
 *
 * @author jameselder
 */
public class Course {

    private int courseNumber; //must be positive
    //Temporary variable indicating whether course vertex has been visited 
    //on the current tour of the graph.  Must be initialized by each user.
    private boolean visited;
    private LinkedList<Course> preReqs; //list of prerequisites for this course

    Course(int courseNumber) throws InvalidCourseNumberException {
        if (courseNumber <= 0) {
            throw new InvalidCourseNumberException();
        }
        this.courseNumber = courseNumber;
        visited = false;
        preReqs = new LinkedList<Course>();
    }

    int getCourseNumber() {
        return courseNumber;
    }

    boolean getVisited() {
        return visited;
    }
    LinkedList<Course> getPreReqs() {
        return preReqs;
    }

    void setVisited(boolean vis) {
        visited = vis;
    }

    /** Adds a prerequisite for this course.  Prerequisites are stored in
     increasing order, according to the course number. Returns true if
     prerequisite is added, false if it already existed.  **/
    boolean addPreReq(Course newPreReq) {
        ListIterator<Course> itr = preReqIterator();
        int newPreReqNumber = newPreReq.getCourseNumber();
        int existingPreReqNumber = 0; //dummy value

        while (itr.hasNext() && ((existingPreReqNumber = itr.next().getCourseNumber()) < newPreReqNumber)) {
        }
        if (existingPreReqNumber == newPreReqNumber) {
            return (false); //prerequisite already exists
        } else if (existingPreReqNumber > newPreReqNumber) {
            itr.previous(); //back up 1
        }
        itr.add(newPreReq);
        return (true);
    }

    /** Returns true if all prerequisites for this course have been satisfied,
     *  False otherwise.  Depends on correctness of variable visited.
     */
    boolean canTakeCourse() {
        ListIterator<Course> itr = preReqIterator();
        while (itr.hasNext()) {
            if (!itr.next().getVisited()) {
                return(false);
            }
        }
        return true;
    }

    /* Returns iterator over prerequisites */
    ListIterator<Course> preReqIterator()
    {
  	return preReqs.listIterator(0);
    }

    /* Prints course number followed by list of prerequisites */
    void printCourse() {
        ListIterator<Course> itr = preReqIterator();
        System.out.print(courseNumber + " Prerequisites: ");
        while (itr.hasNext()) {
            System.out.print(itr.next().getCourseNumber()+" ");
        }
        System.out.println();
    }

}