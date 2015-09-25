package A4Q2;
import java.util.*;

/**
 *
 * Represents a set of courses and their prerequisites.  Courses are represented
 * as linked list, ordered in increasing order, according to the course number.
 * Uses the java.util.LinkedList implementation.
 * @author jameselder
 */
public class CSEPreReqs {

    private LinkedList<Course> courses;

    CSEPreReqs() {
        this.courses = new LinkedList<Course>();
    }

    /* Returns the course associated with this course number.
     * Throws InvalidCourseNumberException if the course number is not positive.
     * Throws NoSuchElementException if the course does not exist in the database. */
    Course getCourse(int queryCourseNumber) throws InvalidCourseNumberException, NoSuchElementException {
        ListIterator<Course> itr = courses.listIterator(0);
        Course course;

        if (queryCourseNumber <= 0) {
            throw new InvalidCourseNumberException();
        }

        while (itr.hasNext()) {
            course = itr.next();
            if (course.getCourseNumber() == queryCourseNumber) {
                return (course);
            }

        }
        throw new NoSuchElementException();
    }

    /* Adds a course with this course number to the database.
     * Throws InvalidCourseNumberException if the course number is not positive.
     * Returns true if course added, false if it already existed. */
    boolean addCourse(int newCourseNumber) throws InvalidCourseNumberException {
        ListIterator<Course> itr = courses.listIterator(0);
        int existingCourseNumber = 0;

        if (newCourseNumber <= 0) {
            throw new InvalidCourseNumberException();
        }

        while (itr.hasNext() && ((existingCourseNumber = itr.next().getCourseNumber()) < newCourseNumber)) {
        }
        if (existingCourseNumber == newCourseNumber) {
            return (false);
        } else if (existingCourseNumber > newCourseNumber) {
            itr.previous();
        }
        itr.add(new Course(newCourseNumber));
        return (true);
    }

    /* Adds a prerequisite for a course.  First checks that the new prerequisite
     * will not introduce a circular dependency.
     * Throws InvalidCourseNumberException if the course number or prerequisite number are not positive.
     * Throws NoSuchElementException if either course does not exist in the database.
     * Throws CircularPreREquisiteException if adding the preqrequisite would introduce a circular dependency.
     * Returns true if prerequisite added, false if it already existed. */
    boolean validateAndAddPreReq(int courseNumber, int newPreReqNumber)  throws InvalidCourseNumberException,NoSuchElementException,CircularPreRequisiteException {
        Course course = getCourse(courseNumber);
        Course newPreReq = getCourse(newPreReqNumber);

        if (path(newPreReq,courseNumber)) {
            throw new CircularPreRequisiteException();
        }

        return (course.addPreReq(newPreReq));
    }

    /* private method that checks for a path from newCourse to the course with
     * number given by startCourseNumber */
    private boolean path(Course newCourse, int startCourseNumber) {
    	// iterator over the elements
    	 ListIterator<Course> iterator = newCourse.preReqIterator();
         Course preReq;
         // to check path whether valid or not 
         boolean check = false;
         for (;iterator.hasNext() & !check;) {
             preReq = iterator.next(); // next element
             preReq.setVisited(!check); // to prevent double iteration  
             if (preReq.getCourseNumber() == startCourseNumber &&  preReq.getVisited()== true) {
            	 check = true;
                 return check;
             }
             else if (preReq.getVisited()== false) {
                 check = path(preReq,startCourseNumber);
             }
             //************************playing around***************************************//
             else{
            	 System.out.println("The system has no idea. Just Kidding around!!!!!!!!!!!!");
             }
             //***********************hope you like it.LOL**********************************//
         }
         return check;
    }

    /* Determines whether a course sequence is valid in the sense that all prerequisites
     * are satisfied when each course is taken.  This method uses the temporary
     * variable 'visited' associated with each course to efficiently validate the sequence.
     * Throws InvalidCourseNumberException if any of the course numbers are not positive.
     * Throws NoSuchElementException if any of the courses do not exist in the database.
     */
    boolean validateCourseSequence(int[] sequence) throws InvalidCourseNumberException,NoSuchElementException {     
        ListIterator<Course> itr = courses.listIterator();
        Course course,next;
        boolean visited = true;
        for(;itr.hasNext();) {
        	
        	next = itr.next();
            next.setVisited(!visited);
            
            if(next.getCourseNumber()<=0){
            	throw new InvalidCourseNumberException("The course number is invalid");
            }        
        }
        for (int i = 0; i < sequence.length; i++) {
            course = getCourse(sequence[i]);
 
            if (course.canTakeCourse()) {
                course.setVisited(visited);
            } 
            else {
            	visited = false;
                return visited;
            }
        }
        return visited;
        
    }

    /* Prints out all of the course numbers and their prerequisites */
    void printCourses() {
        ListIterator<Course> itr = courses.listIterator(0);
        while (itr.hasNext()) {
            itr.next().printCourse();
            
        }
    }

}
