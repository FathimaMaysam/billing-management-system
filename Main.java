import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        BillingDAO dao = new BillingDAO();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            System.out.println("\n=== BILLING SYSTEM ===");
            System.out.println("1. Add Product");
            System.out.println("2. Add Customer");
            System.out.println("3. Generate Bill");
            System.out.println("4. View Bills");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Product name: ");
                    String pname = sc.next();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    dao.addProduct(pname, price);
                    break;
                case 2:
                    System.out.print("Customer name: ");
                    String cname = sc.next();
                    System.out.print("Phone: ");
                    String phone = sc.next();
                    dao.addCustomer(cname, phone);
                    break;
                case 3:
                    System.out.print("Customer ID: ");
                    int cid = sc.nextInt();
                    System.out.print("Product ID: ");
                    int pid = sc.nextInt();
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    dao.generateBill(cid, pid, qty);
                    break;
                case 4:
                    dao.viewBills();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}