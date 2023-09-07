import java.util.InputMismatchException;

class CourseCodeAlreadyExistsException extends Exception {
    CourseCodeAlreadyExistsException(String str) {
        super(str);
    }
}

class CreditsInvalidException extends Exception {
    CreditsInvalidException(String str) {
        super(str);
    }
}

public class Courses extends College {
    static int coursesCount;
    String courseCode;
    String name;
    int credits;
    String preRequisite;

    Courses() {
        coursesCount++;
    }

    void readCourseCode(String courseCode) throws CourseCodeAlreadyExistsException {
        try {
            for (UG UGCourse : UG.UGCourses_DB)
            {
                if (courseCode.equals(UGCourse.courseCode))
                    throw new CourseCodeAlreadyExistsException("CourseCode " + courseCode + " already exists in UGCourses_DB. Enter a new CourseCode.");
            }
        }
        catch (NullPointerException ignored) {}
        this.courseCode = courseCode;
    }


    void readCredits(int credits) throws CreditsInvalidException {
        if (credits <= 0)
            throw new CreditsInvalidException("Credits cannot be <=0. Enter valid credits");
        else
            this.credits = credits;
    }


    void readInfo() {
        while (true) {
            try {
                while (true) {
                    try {
                        System.out.print("Enter Course Code : ");
                        readCourseCode(scan.next());
                        break;
                    }
                    catch(CourseCodeAlreadyExistsException e) {
                        System.out.println(e);
                    }
                }
                System.out.print("Enter Course name : ");
                this.name = scan.next();
                while (true) {
                    try {
                        System.out.print("Enter Credits for this course : ");
                        readCredits(scan.nextInt());
                        break;
                    } catch (CreditsInvalidException e) {
                        System.out.println(e);
                    }
                }
                System.out.print("Enter Employee department : ");
                this.department = scan.next();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Type found... Enter the details correctly again...");
                scan.nextLine();
            }
        }
    }

}

class UG extends Courses {
    static int max=10;
    static int UGCoursesCount;
    static UG[] UGCourses_DB = new UG[max];

    int learningHrs;





}
