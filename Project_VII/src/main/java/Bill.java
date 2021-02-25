import java.sql.*;

public class Bill {
    public int personal_meter_charge = 6;
    public int commercial_meter_charge = 8;
    public int personal_fixed_charge = 75;
    public int commercial_fixed_charge = 120;

    public String getCustomerDetails(String custId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_system?useSSL=false", "root", "root");

            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Consumer_Information where cust_id in ('" + custId + "')");
            while (rs.next()) {
                String s = "Consumer Number: " + rs.getString(6) + "\nConsumer Name: " + rs.getString(7) + "\nAddress: " + rs.getString(10) + "," +
                        rs.getString(4) + "," + rs.getString(3) + "," + rs.getString(2) + "," + rs.getString(1) + "\nMobile Number: " + rs.getString(8) +
                        "\nE-mail: " + rs.getString(9);
                if (rs.isLast()) {
                    con.close();
                }
                return s;
            }
            return "No such user found.";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Some error occurred";
    }

    public String getCustomerDueBills(String custId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_system?useSSL=false", "root", "root");

            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select c.Consumer_No, a.user_id, a.bill_month, a.year, a.meter_reading_end, " +
                    "a.meter_reading_start, a.meter_type, b.cust_id, c.Consumer_Name from current_active_bill a inner join " +
                    "user_details b on a.user_id = b.user_id inner join Consumer_Information c on " +
                    "b.cust_id = c.cust_id where b.cust_id in ('" + custId + "') order by a.timestamp desc");
            int count = 0;
            String s = "";
            int total = 0;
            while (rs.next()) {
                count++;
                if (count == 1) {
                    s += "Consumer Number: " + rs.getString(1) + "\nConsumer Name: " + rs.getString(9) +
                    "\nMeter Type: " + rs.getString(7) + "\n\nDue Bill(s):";
                }
                s += "\n\nBill for the month of: " + rs.getString(3) + ", " + rs.getInt(4) +
                "\nPrevious KWH: " + rs.getInt(6) + "\nCurrent KWH: " + rs.getInt(5);
                if (rs.getString(7).equals("commercial")) {
                    s += "\nFixed Charge: " + this.commercial_fixed_charge +
                            "\nEnergy Charges: " + ((rs.getInt(5) - rs.getInt(6)) * this.commercial_meter_charge);
                    total += this.commercial_fixed_charge + ((rs.getInt(5) - rs.getInt(6)) * this.commercial_meter_charge);
                    s += "\nNet demand: " + (this.commercial_fixed_charge + ((rs.getInt(5) - rs.getInt(6)) * this.commercial_meter_charge));
                } else if (rs.getString(7).equals("personal")) {
                    s += "\nFixed Charge: " + this.personal_fixed_charge +
                            "\nEnergy Charges: " + ((rs.getInt(5) - rs.getInt(6)) * this.personal_meter_charge);
                    total += this.personal_fixed_charge + ((rs.getInt(5) - rs.getInt(6)) * this.personal_meter_charge);
                    s += "\nNet demand: " + (this.personal_fixed_charge + ((rs.getInt(5) - rs.getInt(6)) * this.personal_meter_charge));
                }
            }
            s += "\n\nTotal due bill: " + total;
            if (count == 0) {
                return "No due bills!!";
            }
            if (rs.isLast()) {
                con.close();
            }
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Some error occurred";
    }

    public String getCustomerLastPaidBillDetails(String custId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_system?useSSL=false", "root", "root");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT a.user_id, a.transaction_id, " +
                    "(case when c.meter_type = 'personal' then 75 else 120 end) + " +
                    "((case when c.meter_type = 'personal' then 6 else 8 end) * + (a.closing_unit - a.opening_unit)) " +
                    "as bill_amt, a.timestamp FROM last_payment_details a inner join user_details b on " +
                    "a.user_id = b.user_id inner join Consumer_Information c on " +
                    "b.cust_id = c.cust_id where c.cust_id in ('" + custId + "') order by a.timestamp desc limit 1;");
            while (rs.next()) {
                String s = "Last Paid bill details:\n" + "\nTransaction Id: " + rs.getString(2) +
                        "\nBill amount: " + rs.getInt(3) + "\nBill paid on(YYYY-MM-DD): " + rs.getString(4);
                if (rs.isLast()) {
                    con.close();
                }
                return s;
            }
            return "No previously paid bill";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Some error occurred";
    }

    public String getCalculatedBill(String userId, int current_meter_reading) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_system?useSSL=false", "root", "root");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select meter_reading_end, meter_type FROM current_active_bill " +
                    "where user_id in ('" + userId + "') order by timestamp desc limit 1");
            int total = 0;
            while (rs.next()) {
                if (current_meter_reading <= rs.getInt(1) ) {
                    return "Enter valid current meter reading";
                } else {
                    if (rs.getString(2).equals("personal")) {
                        total = personal_fixed_charge + (current_meter_reading - rs.getInt(1)) * personal_meter_charge;
                    } else if (rs.getString(2).equals("commercial")) {
                        total = commercial_fixed_charge + (current_meter_reading - rs.getInt(1)) * commercial_meter_charge;
                    }
                }
                if (rs.isLast()) {
                    con.close();
                }
                return "Calculated bill amount: " + total;
            }
            return "No previous readings present";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Some error occurred";
    }
}
