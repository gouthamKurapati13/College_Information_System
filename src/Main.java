import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    static void showMenu() {
        System.out.println("\n\nCHOICES : ");
        System.out.println("1. Enter a record into Teaching DB");
        System.out.println("2. Display Teaching Employee details from DB");
        System.out.println("3. Show all teaching Employees details");
        System.out.println("4. Enter a record into Non-Teaching DB");
        System.out.println("5. Display Non-Teaching Employee details from DB");
        System.out.println("6. Show all non-teaching Employees details");
        System.out.println("7. Exit");
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
            showMenu();
            System.out.print("\nEnter your choice : ");
            ch = scan.nextInt();
            switch(ch) {
                case 1:
                    new Teaching();
                    break;
                case 2:
                    System.out.print("Enter EmpID : ");
                    Teaching.displayInfo(scan.nextInt());
                    break;
                case 3:
                    College.showCollegeInfo();
                    System.out.println("\n--- Teaching Employees Information ---\n");
                    Teaching.displayAll();
                    break;
                case 4:
                    new NonTeaching();
                    break;
                case 5:
                    System.out.print("Enter EmpID : ");
                    NonTeaching.displayInfo(scan.nextInt());
                    break;
                case 6:
                    College.showCollegeInfo();
                    System.out.println("\n--- Non-Teaching Employees Information ---\n");
                    NonTeaching.displayAll();
                    break;
                case 7:
                    System.out.print("Enter 7 again to confirm exiting the application : ");
                    ch = scan.nextInt();
                    if (ch==4)
                        System.out.println("Closing application...");
                    break;
                default:
                    System.out.println("Invalid Input : Please enter a valid choice from the following choices.");
            }
        } while(ch!=7);
    }
}
