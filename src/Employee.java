import java.util.InputMismatchException;

class EmpIDAlreadyExistsException extends Exception {
    EmpIDAlreadyExistsException(String str) {
        super(str);
    }
}

public class Employee extends College {
    static int empCount;

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

}


class Teaching extends Employee {
    static int max=10;
    static int teachingCount;
    static Teaching[] Teaching_DB = new Teaching[10];
    String grade;

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
            System.out.printf("%-5s  %-40s %-20s %-20s %-20s %-20s %-30s\n", "EmpID", "Name", "Department", "Basic Salary", "Gross Salary", "Net Salary", "Grade");
            for (Teaching teach : Teaching_DB)
                teach.displayInfo();
        }
        catch (NullPointerException e)
        {
            return;
        }
    }

}


class NonTeaching extends Employee {
    static int max=10;
    static int nonTeachingCount;
    static NonTeaching NonTeaching_DB[] = new NonTeaching[10];
    String job;

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
            System.out.printf("%-5s  %-40s %-20s %-20s %-20s %-30s\n", "EmpID", "Name", "Department", "Basic Salary", "Gross Salary", "Job");
            for (NonTeaching nonTeach : NonTeaching_DB)
                nonTeach.displayInfo();
        }
        catch (NullPointerException e)
        {
            return;
        }
    }

}

