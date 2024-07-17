import java.io.*;
import java.util.*;

public class PaymentManager {
    static void makePayment() {
        Scanner sc=new Scanner(System.in);
        FileManager.printChar('=', 128);
        System.out.println("\t\t\t\t\t\tPAYMENT PORTAL");
        FileManager.printChar('=', 128);
        System.out.print("Enter Customer id: ");
        int id=sc.nextInt();

        try{
            File obj=new File("Customers.txt");
            File tmp=new File("temp.txt");
            tmp.createNewFile();
            ObjectInputStream oi=new ObjectInputStream(new FileInputStream(obj));
            ObjectOutputStream oo=new ObjectOutputStream(new FileOutputStream(tmp));
            boolean found = false;
            try{
                while (true) {
                    Customer c=(Customer)oi.readObject();
                    if (c.id == id) {
                        found = true;
                        System.out.println("Customer name       : " + c.name);
                        System.out.println("Due Balance         : Rs. " + c.balance);
                        System.out.println("Last date of payment: " + c.lastpayment);

                        if (c.balance == 0) {
                            System.out.println("\nNo dues left...");
                            break;
                        }

                        System.out.print("\nEnter amount to be paid: ");
                        int amount = sc.nextInt();

                        if (amount <= c.balance) {
                            c.balance -= amount;
                            System.out.println("\nPayment Successful!!");
                            System.out.println("\nRemaining Amount for next month: Rs. "+c.balance);
                        } 
                        else {
                            System.out.println("\nAmount greater than the due balance...");
                        }
                    }
                    oo.writeObject(c);
                }
            }
            catch(EOFException e){}
            catch(Exception e){
                System.out.println(e);
            }

            if (!found) {
                System.out.println("\nCustomer not found...");
            }
            oo.close();
            oi.close();
            obj.delete();
            tmp.renameTo(obj);
        } 
        catch (IOException e) {
            System.err.println("\nERROR IN OPENING FILE!");
        }
    }
}
