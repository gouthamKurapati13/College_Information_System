import java.util.InputMismatchException;
import java.util.Scanner;

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
    String department;

    static void coursesMenu() {
        System.out.println("\n\nCOURSES MENU : ");
        System.out.println("1. Access UG Database");
        System.out.println("2. Access PG Database");
        System.out.println("3. Access PHD Database");
        System.out.println("4. Display Complete Courses Information");
        System.out.println("5. Exit Employees Section");
    }

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
                System.out.print("Enter Course department : ");
                this.department = scan.next();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Type found... Enter the details correctly again...");
                scan.nextLine();
            }
        }
    }


    void showInfo() {
        System.out.printf("%-12s %-40s %-20s %-12d", this.courseCode, this.name, this.department, this.credits);
    }

    static void displayAll() {
        System.out.println("\n---------------------------");
        System.out.println("--- COURSES INFORMATION ---");
        System.out.println("---------------------------");
        System.out.println("Total Courses : " + coursesCount);
        UG.displayAll();
        PG.displayAll();
        PHD.displayAll();
    }

    static void operations() {
        Scanner scan = new Scanner(System.in);
        int ch;
        do {
            System.out.print("\nType something and press enter to continue...");
            scan.next();
            coursesMenu();
            System.out.print("\nEnter your choice : ");
            ch = scan.nextInt();
            switch(ch) {
                case 1:
                    UG.operations();
                    break;
                case 2:
                    PG.operations();
                    break;
                case 3:
                    PHD.operations();
                    break;
                case 4:
                    College.showCollegeInfo();
                    displayAll();
                    break;
                case 5:
                    System.out.print("Enter 5 again to confirm exiting the Courses Section : ");
                    ch = scan.nextInt();
                    if (ch==5)
                        System.out.println("Exiting Courses Section...");
                    break;
                default:
                    System.out.println("Invalid Input : Please enter a valid choice from the menu");
            }
        } while(ch!=5);
    }
}

class UG extends Courses {
    static int max=5;
    static int UGCoursesCount;
    static UG[] UGCourses_DB = new UG[max];

    static void UGMenu() {
        System.out.println("\n\nUG MENU : ");
        System.out.println("1. Add a new UG Course");
        System.out.println("2. Get details of any UG Course");
        System.out.println("3. Display complete UG Courses Details");
        System.out.println("4. Exit UG Database");
    }

    int learningHrs;

    UG() {
        try {
            this.readInfo();
            System.out.print("Enter Course learning hours : ");
            this.learningHrs = scan.nextInt();
            UGCourses_DB[UGCoursesCount] = this;
            System.out.println("New UG course added to database...");
            UGCoursesCount++;
            Courses.coursesCount++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("DB is full... Couldn't add any more records...");
        }
    }


    void displayInfo() {
        this.showInfo();
        System.out.printf(" %-20d\n", this.learningHrs);
    }


    static void displayInfo(String courseCode) {
        try {
            for (UG UG_course : UG.UGCourses_DB)
                if (courseCode.equals(UG_course.courseCode )) {
                    System.out.printf("%-12s %-40s %-20s %-12s %-20s\n", "CourseCode", "Name", "Department", "Credits", "Learning Hours");
                    UG_course.displayInfo();
                    break;
                }
        }
        catch (NullPointerException e)
        {
            System.out.println("No Course found with the given CourseCode...");
        }
    }


    static void displayAll() {
        try {
            System.out.println("\n\n--- UG Courses Information ---");
            System.out.println("Total UG Courses : " + UGCoursesCount);
            System.out.printf("%-12s %-40s %-20s %-12s %-20s\n", "CourseCode", "Name", "Department", "Credits", "Learning Hours");
            for (UG UG_course : UG.UGCourses_DB)
                UG_course.displayInfo();
        }
        catch (NullPointerException e)
        {
            return;
        }
    }

    static void operations() {
        Scanner scan = new Scanner(System.in);
        int ch;
        do {
            System.out.print("\nType something and press enter to continue...");
            scan.next();
            UGMenu();
            System.out.print("\nEnter your choice : ");
            ch = scan.nextInt();
            switch(ch) {
                case 1:
                    new UG();
                    break;
                case 2:
                    System.out.print("Enter CourseCode : ");
                    displayInfo(scan.next());
                    break;
                case 3:
                    College.showCollegeInfo();
                    displayAll();
                    break;
                case 4:
                    System.out.print("Enter 4 again to confirm exiting the UG Database : ");
                    ch = scan.nextInt();
                    if (ch==4)
                        System.out.println("Exiting UG Database...");
                    break;
                default:
                    System.out.println("Invalid Input : Please enter a valid choice from the menu");
            }
        } while(ch!=4);
    }
}



class PG extends Courses {
    static int max=5;
    static int PGCoursesCount;
    static PG[] PGCourses_DB = new PG[max];

    int projectHrs;

    static void PGMenu() {
        System.out.println("\n\nPG MENU : ");
        System.out.println("1. Add a new PG Course");
        System.out.println("2. Get details of any PG Course");
        System.out.println("3. Display complete PG Courses Details");
        System.out.println("4. Exit PG Database");
    }

    PG() {
        try {
            this.readInfo();
            System.out.print("Enter Course Project hours : ");
            this.projectHrs = scan.nextInt();
            PGCourses_DB[PGCoursesCount] = this;
            System.out.println("New UG course added to database...");
            PGCoursesCount++;
            Courses.coursesCount++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("DB is full... Couldn't add any more records...");
        }
    }


    void displayInfo() {
        this.showInfo();
        System.out.printf(" %-20d\n", this.projectHrs);
    }


    static void displayInfo(String courseCode) {
        try {
            for (PG PG_course : PG.PGCourses_DB)
                if (courseCode.equals(PG_course.courseCode )) {
                    System.out.printf("%-12s %-40s %-20s %-12s %-20s\n", "CourseCode", "Name", "Department", "Credits", "Project Hours");
                    PG_course.displayInfo();
                    break;
                }
        }
        catch (NullPointerException e)
        {
            System.out.println("No Course found with the given CourseCode...");
        }
    }

    static void displayAll() {
        try {
            System.out.println("\n\n--- PG Courses Information ---");
            System.out.println("Total PG Courses : " + PGCoursesCount);
            System.out.printf("%-12s %-40s %-20s %-12s %-20s\n", "CourseCode", "Name", "Department", "Credits", "Project Hours");
            for (PG PG_course : PG.PGCourses_DB)
                PG_course.displayInfo();
        }
        catch (NullPointerException e)
        {
            return;
        }
    }

    static void operations() {
        Scanner scan = new Scanner(System.in);
        int ch;
        do {
            System.out.print("\nType something and press enter to continue...");
            scan.next();
            PGMenu();
            System.out.print("\nEnter your choice : ");
            ch = scan.nextInt();
            switch(ch) {
                case 1:
                    new PG();
                    break;
                case 2:
                    System.out.print("Enter CourseCode : ");
                    displayInfo(scan.next());
                    break;
                case 3:
                    College.showCollegeInfo();
                    displayAll();
                    break;
                case 4:
                    System.out.print("Enter 4 again to confirm exiting the PG Database : ");
                    ch = scan.nextInt();
                    if (ch==4)
                        System.out.println("Exiting PG Database...");
                    break;
                default:
                    System.out.println("Invalid Input : Please enter a valid choice from the menu");
            }
        } while(ch!=4);
    }
}



class PHD extends Courses {
    static int max=5;
    static int PHDCoursesCount;
    static PHD[] PHDCourses_DB = new PHD[max];

    static void PHDMenu() {
        System.out.println("\n\nPG MENU : ");
        System.out.println("1. Add a new PHD Course");
        System.out.println("2. Get details of any PHD Course");
        System.out.println("3. Display complete PHD Courses Details");
        System.out.println("4. Exit PHD Database");
    }

    int researchHrs;

    PHD() {
        try {
            this.readInfo();
            System.out.print("Enter Course Research hours : ");
            this.researchHrs = scan.nextInt();
            PHDCourses_DB[PHDCoursesCount] = this;
            System.out.println("New UG course added to database...");
            PHDCoursesCount++;
            Courses.coursesCount++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("DB is full... Couldn't add any more records...");
        }
    }


    void displayInfo() {
        this.showInfo();
        System.out.printf(" %-20d\n", this.researchHrs);
    }


    static void displayInfo(String courseCode) {
        try {
            for (PHD PHD_course : PHD.PHDCourses_DB)
                if (courseCode.equals(PHD_course.courseCode )) {
                    System.out.printf("%-12s %-40s %-20s %-12s %-20s\n", "CourseCode", "Name", "Department", "Credits", "Research Hours");
                    PHD_course.displayInfo();
                    break;
                }
        }
        catch (NullPointerException e)
        {
            System.out.println("No Course found with the given CourseCode...");
        }
    }

    static void displayAll() {
        try {
            System.out.println("\n\n--- PHD Courses Information ---");
            System.out.println("Total PHD Courses : " + PHDCoursesCount);
            System.out.printf("%-12s %-40s %-20s %-12s %-20s\n", "CourseCode", "Name", "Department", "Credits", "Research Hours");
            for (PHD PHD_course : PHD.PHDCourses_DB)
                PHD_course.displayInfo();
        }
        catch (NullPointerException e)
        {
            return;
        }
    }


    static void operations() {
        Scanner scan = new Scanner(System.in);
        int ch;
        do {
            System.out.print("\nType something and press enter to continue...");
            scan.next();
            PHDMenu();
            System.out.print("\nEnter your choice : ");
            ch = scan.nextInt();
            switch(ch) {
                case 1:
                    new PHD();
                    break;
                case 2:
                    System.out.print("Enter CourseCode : ");
                    displayInfo(scan.next());
                    break;
                case 3:
                    College.showCollegeInfo();
                    displayAll();
                    break;
                case 4:
                    System.out.print("Enter 4 again to confirm exiting the PHD Database : ");
                    ch = scan.nextInt();
                    if (ch==4)
                        System.out.println("Exiting PHD Database...");
                    break;
                default:
                    System.out.println("Invalid Input : Please enter a valid choice from the menu");
            }
        } while(ch!=4);
    }

}

