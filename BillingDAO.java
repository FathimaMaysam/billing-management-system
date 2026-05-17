import java.sql.*;

public class BillingDAO {

    public void addProduct(String name, double price) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO products(name, price) VALUES(?,?)");
        ps.setString(1, name);
        ps.setDouble(2, price);
        ps.executeUpdate();
        System.out.println("Product added.");
        con.close();
    }

    public void addCustomer(String name, String phone) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO customers(name, phone) VALUES(?,?)");
        ps.setString(1, name);
        ps.setString(2, phone);
        ps.executeUpdate();
        System.out.println("Customer added.");
        con.close();
    }

    public void generateBill(int customerId, int productId, int qty) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps1 = con.prepareStatement(
                "SELECT price FROM products WHERE id=?");
        ps1.setInt(1, productId);
        ResultSet rs = ps1.executeQuery();
        double total = 0;
        if (rs.next())
            total = rs.getDouble("price") * qty;
        PreparedStatement ps2 = con.prepareStatement(
                "INSERT INTO bills(customer_id, product_id, quantity, total) VALUES(?,?,?,?)");
        ps2.setInt(1, customerId);
        ps2.setInt(2, productId);
        ps2.setInt(3, qty);
        ps2.setDouble(4, total);
        ps2.executeUpdate();
        System.out.println("Bill generated. Total: " + total);
        con.close();
    }

    public void viewBills() throws Exception {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT b.id, c.name, p.name, b.quantity, b.total " +
                        "FROM bills b " +
                        "JOIN customers c ON b.customer_id = c.id " +
                        "JOIN products p ON b.product_id = p.id");
        System.out.println("\n--- BILLS ---");
        while (rs.next()) {
            System.out.println("Bill#" + rs.getInt(1) +
                    " | Customer: " + rs.getString(2) +
                    " | Product: " + rs.getString(3) +
                    " | Qty: " + rs.getInt(4) +
                    " | Total: " + rs.getDouble(5));
        }
        con.close();
    }

    public void viewProducts() throws Exception {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM products");
        System.out.println("\n--- PRODUCTS ---");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt(1) +
                    " | Name: " + rs.getString(2) +
                    " | Price: " + rs.getDouble(3));
        }
        con.close();
    }
}