import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {
    public String getCustomerDetails(String custId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_system", "root", "root");

            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Consumer_Information where cust_id in ('" + custId + "')");
            while (rs.next()) {
                String s = "Consumer Number: " + rs.getString(6) + "\nConsumer Name: " + rs.getString(7) + "\nAddress: " + rs.getString(10) + "," +
                        rs.getString(4) + "," + rs.getString(3) + "," + rs.getString(2) + "," + rs.getString(1) + "\nMobile Number: " + rs.getString(8) +
                        "\nE-mail: " + rs.getString(9);
                return s;
            }
            return "No such user found.";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Some error occurred";
    }
}
