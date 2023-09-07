import java.util.Scanner;

public class Main {

    static void mainMenu() {
        System.out.println("\n\nMAIN MENU : ");
        System.out.println("1. Access Employees Section");
        System.out.println("2. Access Courses Section");
        System.out.println("3. Display Complete College Information");
        System.out.println("4. Exit");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        System.out.println("\n-----COLLEGE INFORMATION SYSTEM-----\n\n");
        System.out.print("Enter College name : ");
        College.name = scan.nextLine();
        System.out.print("Enter college Address : ");
        College.address = scan.nextLine();


        int ch;
        do {
            System.out.print("\nType something and press enter to continue...");
            scan.next();
            mainMenu();
            System.out.print("\nEnter your choice : ");
            ch = scan.nextInt();
            switch(ch) {
                case 1:
                    Employee.operations();
                    break;
                case 2:
                    Courses.operations();
                    break;
                case 3:
                    System.out.println("\n-----COLLEGE INFORMATION SYSTEM-----\n\n");
                    College.showCollegeInfo();
                    Employee.displayAll();
                    Courses.displayAll();
                    break;
                case 4:
                    System.out.print("Enter 4 again to confirm exiting the application : ");
                    ch = scan.nextInt();
                    if (ch==4)
                        System.out.println("Closing application...");
                    break;
                default:
                    System.out.println("Invalid Input : Please enter a valid choice from the following choices.");
            }
        } while(ch!=4);
    }
}
