import java.util.InputMismatchException;
import java.util.Scanner;

class EmpIDAlreadyExistsException extends Exception {
    EmpIDAlreadyExistsException(String str) {
        super(str);
    }
}

public class Employee extends College {
    static int empCount;

    static void employeeMenu() {
        System.out.println("\n\nEMPLOYEE MENU : ");
        System.out.println("1. Access Teaching Database");
        System.out.println("2. Access Non-Teaching Database");
        System.out.println("3. Display Complete Employees Information");
        System.out.println("4. Exit Employees Section");
    }

    int empID;
    String name;
    Salary salary;
    String department;
    String type;

    Employee() {
        empCount++;
    }

    void readEmpID(int empID) throws EmpIDAlreadyExistsException {
        try {
            for (Teaching teach : Teaching.Teaching_DB)
            {
                if (teach.empID == empID)
                    throw new EmpIDAlreadyExistsException("EmpID " + empID + " already exists in Teaching_DB. Enter a new EmpID.");
            }
        }
        catch (NullPointerException ignored) {}
        try {
            for (NonTeaching nonTeach : NonTeaching.NonTeaching_DB)
            {
                if (nonTeach.empID == empID)
                    throw new EmpIDAlreadyExistsException("EmpID " + empID + " already exists in NonTeaching_DB. Enter a new EmpID.");
            }
        }
        catch (NullPointerException ignored) {}
        this.empID = empID;
    }

    void readInfo() {
        while (true) {
            try {
                while (true) {
                    try {
                        System.out.print("Enter Employee ID : ");
                        readEmpID(scan.nextInt());
                        break;
                    }
                    catch(EmpIDAlreadyExistsException e) {
                        System.out.println(e);
                    }
                }
                System.out.print("Enter Employee name : ");
                this.name = scan.next();
                while (true) {
                    try {
                        System.out.print("Enter Employee Basic salary : ");
                        this.salary = new Salary(scan.nextFloat(), this.type.equals("Teaching"));
                        break;
                    } catch (InvalidSalaryException e) {
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

    void showInfo() {
        System.out.printf("%-5d  %-40s %-20s", this.empID, this.name, this.department);
        this.salary.display();
    }

    static void displayAll() {
        System.out.println("\n-----------------------------");
        System.out.println("--- EMPLOYEES INFORMATION ---");
        System.out.println("-----------------------------");
        System.out.println("Total Employees : " + empCount);
        Teaching.displayAll();
        NonTeaching.displayAll();
    }

    static void operations() {
        Scanner scan = new Scanner(System.in);
        int ch;
        do {
            System.out.print("\nType something and press enter to continue...");
            scan.next();
            employeeMenu();
            System.out.print("\nEnter your choice : ");
            ch = scan.nextInt();
            switch(ch) {
                case 1:
                    Teaching.operations();
                    break;
                case 2:
                    NonTeaching.operations();
                    break;
                case 3:
                    College.showCollegeInfo();
                    displayAll();
                    break;
                case 4:
                    System.out.print("Enter 4 again to confirm exiting the Employees Section : ");
                    ch = scan.nextInt();
                    if (ch==4)
                        System.out.println("Exiting Employees Section...");
                    break;
                default:
                    System.out.println("Invalid Input : Please enter a valid choice from the menu");
            }
        } while(ch!=4);
    }

}


class Teaching extends Employee {
    static int max=5;
    static int teachingCount;
    static Teaching[] Teaching_DB = new Teaching[10];
    String grade;


    static void teachingMenu() {
        System.out.println("\n\nTEACHING MENU : ");
        System.out.println("1. Add a new Teaching Employee");
        System.out.println("2. Get details of any Teaching Employee");
        System.out.println("3. Display complete Teaching Employees Details");
        System.out.println("4. Exit Teaching Database");
    }


    Teaching() {
        try {
            this.type = "Teaching";
            this.readInfo();
            System.out.print("Enter Employee Grade : ");
            this.grade = scan.next();
            Teaching_DB[teachingCount] = this;
            System.out.println("New teaching employee added to database...");
            teachingCount++;
            empCount++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("DB is full... Couldn't add any more records...");
        }
    }

    void displayInfo() {
        this.showInfo();
        System.out.printf("%-30s\n", this.grade);
    }

    static void displayInfo(int empID) {
        try {
            for (Teaching teach : Teaching_DB)
                if (teach.empID == empID) {
                    System.out.printf("%-5s  %-40s %-20s %-20s %-20s %-20s %-30s\n", "EmpID", "Name", "Department", "Basic Salary", "Gross Salary", "Net Salary", "Grade");
                    teach.displayInfo();
                    break;
                }
        }
        catch (NullPointerException e)
        {
            System.out.println("No Employee found with the given EmpID...");
        }
    }

    static void displayAll() {
        try {
            System.out.println("\n\n--- Teaching Employees Information ---");
            System.out.println("Total Teaching Employees : " + teachingCount);
            System.out.printf("%-5s  %-40s %-20s %-20s %-20s %-20s %-30s\n", "EmpID", "Name", "Department", "Basic Salary", "Gross Salary", "Net Salary", "Grade");
            for (Teaching teach : Teaching_DB)
                teach.displayInfo();
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
            teachingMenu();
            System.out.print("\nEnter your choice : ");
            ch = scan.nextInt();
            switch(ch) {
                case 1:
                    new Teaching();
                    break;
                case 2:
                    System.out.print("Enter EmpID : ");
                    displayInfo(scan.nextInt());
                    break;
                case 3:
                    College.showCollegeInfo();
                    displayAll();
                    break;
                case 4:
                    System.out.print("Enter 4 again to confirm exiting the Teaching Database : ");
                    ch = scan.nextInt();
                    if (ch==4)
                        System.out.println("Exiting Teaching Database");
                    break;
                default:
                    System.out.println("Invalid Input : Please enter a valid choice from the menu");
            }
        } while(ch!=4);
    }

}


class NonTeaching extends Employee {
    static int max=5;
    static int nonTeachingCount;
    static NonTeaching NonTeaching_DB[] = new NonTeaching[10];
    String job;


    static void nonTeachingMenu() {
        System.out.println("\n\nNON-TEACHING MENU : ");
        System.out.println("1. Add a new Non-Teaching Employee");
        System.out.println("2. Get details of any Non-Teaching Employee");
        System.out.println("3. Display complete Non-Teaching Employees Details");
        System.out.println("4. Exit Non-Teaching Database");
    }


    NonTeaching() {
        try {
            this.type = "Non-Teaching";
            this.readInfo();
            NonTeaching_DB[nonTeachingCount] = this;
            System.out.print("Enter Employee Job : ");
            this.job = scan.next();
            System.out.println("New non-teaching employee added to database...");
            nonTeachingCount++;
            empCount++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("DB is full... Couldn't add any more records...");
        }
    }

    void displayInfo() {
        this.showInfo();
        System.out.printf("%-30s\n", this.job);
    }

    static void displayInfo(int empID) {
        try {
            for (NonTeaching nonTeach : NonTeaching_DB)
                if (nonTeach.empID == empID) {
                    System.out.printf("%-5s  %-40s %-20s %-20s %-20s %-30s\n", "EmpID", "Name", "Department", "Basic Salary", "Gross Salary", "Job");
                    nonTeach.displayInfo();
                    break;
                }
        }
        catch (NullPointerException e)
        {
            System.out.println("No Employee found with the given EmpID...");
        }
    }

    static void displayAll() {
        try {
            System.out.println("\n\n--- Non-Teaching Employees Information ---");
            System.out.println("Total Non-teaching Employees : " + nonTeachingCount);
            System.out.printf("%-5s  %-40s %-20s %-20s %-20s %-30s\n", "EmpID", "Name", "Department", "Basic Salary", "Gross Salary", "Job");
            for (NonTeaching nonTeach : NonTeaching_DB)
                nonTeach.displayInfo();
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
            nonTeachingMenu();
            System.out.print("\nEnter your choice : ");
            ch = scan.nextInt();
            switch(ch) {
                case 1:
                    new NonTeaching();
                    break;
                case 2:
                    System.out.print("Enter EmpID : ");
                    displayInfo(scan.nextInt());
                    break;
                case 3:
                    College.showCollegeInfo();
                    displayAll();
                    break;
                case 4:
                    System.out.print("Enter 4 again to confirm exiting the Non-Teaching Database : ");
                    ch = scan.nextInt();
                    if (ch==4)
                        System.out.println("Exiting Non-Teaching Database");
                    break;
                default:
                    System.out.println("Invalid Input : Please enter a valid choice from the menu");
            }
        } while(ch!=4);
    }
}

