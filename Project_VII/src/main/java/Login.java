import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Login {
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public void setMeterNo(Integer meterNo) {
        this.meterNo = meterNo;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String userId;
    public String pwd;
    public String custName;
    public Integer meterNo;
    public String custId;

    Login() {
        this.userId = "";
        this.pwd = "";
        this.custName = "";
        this.meterNo = null;
        this.custId = "";
    }

    public String getUserId() {
        return userId;
    }

    public String getCustId() {
        return custId;
    }

    String checkCredentials(String userId, String pwd, SendMessage msg) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_system","root","root");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from user_details where user_id in ('" + userId + "') and pwd in ('" + pwd + "')");

            while (rs.next()) {
                this.userId = userId;
                this.pwd = pwd;
                this.custId = rs.getString(5);
                this.custName = rs.getString(3);
                this.meterNo = rs.getInt(4);
                setOptionsKeyboard(msg);
                String s = "Welcome " + rs.getString(3) + "\n\nCustomer ID: " + rs.getString(5) + "\n\nMeter Number: " + rs.getInt(4);
                return s;
            }
            return "Invalid user details entered.\nPlease input correct details.";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Some error occurred";
    }

    void setOptionsKeyboard(SendMessage msg) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList =new ArrayList<>();
        KeyboardRow keyboardRow1= new KeyboardRow();
        KeyboardButton keyboardButton1= new KeyboardButton();
        keyboardButton1.setText("View my details");
        keyboardRow1.add(keyboardButton1);
        keyboardRowList.add(keyboardRow1);
        KeyboardRow keyboardRow2 = new KeyboardRow();
        KeyboardButton keyboardButton2 = new KeyboardButton();
        keyboardButton2.setText("View current due bill");
        keyboardRow2.add(keyboardButton2);
        keyboardRowList.add(keyboardRow2);
        KeyboardRow keyboardRow3 = new KeyboardRow();
        KeyboardButton keyboardButton3 = new KeyboardButton();
        keyboardButton3.setText("View last paid bill");
        keyboardRow3.add(keyboardButton3);
        keyboardRowList.add(keyboardRow3);
        KeyboardRow keyboardRow4 = new KeyboardRow();
        KeyboardButton keyboardButton4 = new KeyboardButton();
        keyboardButton4.setText("Calculate bill");
        keyboardRow4.add(keyboardButton4);
        keyboardRowList.add(keyboardRow4);
        KeyboardRow keyboardRow5 = new KeyboardRow();
        KeyboardButton keyboardButton5 = new KeyboardButton();
        keyboardButton5.setText("Pay bill");
        keyboardRow5.add(keyboardButton5);
        keyboardRowList.add(keyboardRow5);
        KeyboardRow keyboardRow6 = new KeyboardRow();
        KeyboardButton keyboardButton6 = new KeyboardButton();
        keyboardButton6.setText("Contact us");
        keyboardRow6.add(keyboardButton6);
        keyboardRowList.add(keyboardRow6);
        KeyboardRow keyboardRow7 = new KeyboardRow();
        KeyboardButton keyboardButton7 = new KeyboardButton();
        keyboardButton7.setText("Logout");
        keyboardRow7.add(keyboardButton7);
        keyboardRowList.add(keyboardRow7);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        msg.setReplyMarkup(replyKeyboardMarkup);
    }

}
