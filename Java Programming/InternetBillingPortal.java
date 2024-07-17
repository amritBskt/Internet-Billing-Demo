import java.util.Scanner;

public class InternetBillingPortal {

    public static void main(String[] args) {
        FileManager.printChar('*', 128);
        System.out.println("\t\t\t\t\t\tWORLDLINK COMMUNICATIONS LIMITED");
        System.out.println("\t\t\t\t\t\t\tKathmandu, Nepal");
        FileManager.printChar('*', 128);

        System.out.println("\nWelcome to Internet Billing Portal of Worldlink.");
        System.out.println("Press Enter to continue...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        while (true) {
            System.out.println();
            FileManager.printChar('=', 128);
            System.out.println("1. Add Customer");
            System.out.println("2. Search Customer");
            System.out.println("3. Make Payment");
            System.out.println("4. Remove Customer");
            System.out.println("5. Exit");
            FileManager.printChar('=', 128);

            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    CustomerManager.addCustomer();
                    break;

                case 2:
                    CustomerManager.searchCustomer();
                    break;

                case 3:
                    PaymentManager.makePayment();
                    break;

                case 4:
                    CustomerManager.removeCustomer();
                    break;

                case 5:
                    System.out.println("\n");
                    FileManager.printChar('*', 128);
                    System.out.println("\t\t\t\t\t\tTHANK YOU");
                    System.out.println("\t\t\t\t\tDesigned by Pratika Dhungana...");
                    FileManager.printChar('*', 128);
                    sc.close();
                    return;

                default:
                    System.out.println("\nInvalid choice..."); 
            }
        }
    }
}
