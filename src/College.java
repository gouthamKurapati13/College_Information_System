import java.util.Scanner;

public class College {
    static String name;
    static String address;
    Scanner scan = new Scanner(System.in).useDelimiter("\n");

    static void showCollegeInfo() {
        System.out.println("\n" + College.name + "\n" + College.address);
    }

}


