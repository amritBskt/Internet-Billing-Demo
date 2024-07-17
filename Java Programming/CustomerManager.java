import java.io.*;
import java.util.*;

public class CustomerManager {
    static void addCustomer() {
        FileManager.printChar('=', 128);
        System.out.println("\t\t\t\t\t\tCUSTOMER ADDITION PORTAL");
        FileManager.printChar('=', 128);

        try {
            Scanner sc=new Scanner(System.in);
            File obj=new File("Customers.txt");
            ObjectOutputStream oo=new ObjectOutputStream(new FileOutputStream(obj));
            Customer c = new Customer();

            System.out.print("Enter customer id: ");
            c.id = sc.nextInt();

            sc.nextLine();
            System.out.print("Enter customer name: ");
            c.name = sc.nextLine();

            System.out.print("Enter phone number: ");
            c.phon_num = sc.nextLine();

            System.out.print("Enter address: ");
            c.address = sc.nextLine();

            System.out.print("Enter the nearest branch: ");
            c.branch = sc.nextLine();

            System.out.print("Enter due balance in rupees: ");
            c.balance = sc.nextInt();

            sc.nextLine();

            System.out.print("Enter date of last payment (dd/mm/yyyy): ");
            c.lastpayment = sc.nextLine();;

            oo.writeObject(c);

            System.out.println("\nCustomer Details Added Successfully...");
            oo.close();
        } catch (IOException e) {
            System.err.println("\nERROR IN OPENING FILE!");
        }
    }

    static void searchCustomer() {
        FileManager.printChar('=', 128);
        System.out.println("\t\t\t\t\t\tCUSTOMER SEARCH PORTAL");
        FileManager.printChar('=', 128);

        try {
            File obj=new File("Customers.txt");
            ObjectInputStream oi=new ObjectInputStream(new FileInputStream(obj));
            Customer c = new Customer();
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter customer id: ");
            int id = sc.nextInt();
            sc.nextLine();
            boolean found = false;

            try{
                while (true) {
                    c=(Customer)oi.readObject();
                    if (c.id == id) {
                        found = true;
                        System.out.println("Customer id         : "+ c.id);
                        System.out.println("Customer name       : " + c.name);
                        System.out.println("Phone number        : "+ c.phon_num);
                        System.out.println("Address             : "+c.address);
                        System.out.println("Branch              : "+c.branch);
                        System.out.println("Due Balance         : Rs. " + c.balance);
                        System.out.println("Last date of payment: " + c.lastpayment);
                        break;
                    }
                }
            }
            catch(EOFException e){}
            catch(Exception e){
                System.out.println(e);
            }
            if (found!=true) {
                System.out.println("\nCustomer not found...");
            }
            oi.close();
        } catch (IOException e) {
            System.err.println("\nERROR IN OPENING FILE!");
        }
    }

    static void removeCustomer() {
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
                    if (c.id == id)
                    {
                        found=true;
                        continue;
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
            else{
                System.out.println("\nCustomer removed successfully...");
            }
            oi.close();
            oo.close();
            obj.delete();
            tmp.renameTo(obj);
        } 
        catch (IOException e) {
            System.err.println("\nERROR IN OPENING FILE!");
        }
    }
}

