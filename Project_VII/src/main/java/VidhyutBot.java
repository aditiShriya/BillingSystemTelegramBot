import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;


public class VidhyutBot extends TelegramLongPollingBot
{
    Login login = new Login();
    Bill bill = new Bill();

    String last_command = "/start";

    public String getBotUsername() {
        return "VidyuhtBot";
    }

    @Override
    public String getBotToken() {
        return "1685411057:AAHMVARzF6tZfQIrCelDgZxC3fHZ0-dunGw";
    }

    public void setLoginKeyboard(ReplyKeyboardMarkup replyKeyboardMarkup) {
        List<KeyboardRow> keyboardRowList =new ArrayList<>();
        KeyboardRow keyboardRow1= new KeyboardRow();
        KeyboardButton keyboardButton1= new KeyboardButton();
        keyboardButton1.setText("Login");
        keyboardRow1.add(keyboardButton1);
        keyboardRowList.add(keyboardRow1);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public void onUpdateReceived(Update update)
    {
        if (update.hasMessage())
        {
            Message message=update.getMessage();
            if (message.hasText())
            {
                String text=message.getText();
                SendMessage sendMessage =new SendMessage();
                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

                if (text.equals("/start"))
                {
                    last_command = text;
                    sendMessage.setText("Welcome to Vidhyut Bot \n Please use the /login command to continue.");
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));

                    setLoginKeyboard(replyKeyboardMarkup);
                    sendMessage.setReplyMarkup(replyKeyboardMarkup);

                    try {
                        execute(sendMessage);
                    }catch(TelegramApiException e){
                        e.printStackTrace();
                    }
                } else if (text.equals("/login") || text.equals("Login")) {
                    last_command = text;
                    sendMessage.setText("Please enter your username and password in space separated format. \n\n Example: userxxx passwxxxord");
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));

                    try {
                        execute(sendMessage);
                    }catch(TelegramApiException e){
                        e.printStackTrace();
                    }
                } else if (Pattern.matches("user[0-9]* [a-zA-Z]*[0-9]*", text)) {
                    last_command = text;
                    sendMessage.setText(login.checkCredentials(text.split(" ")[0], text.split(" ")[1], sendMessage));
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));

                    try {
                        execute(sendMessage);
                    }catch(TelegramApiException e){
                        e.printStackTrace();
                    }
                } else if (text.equals("View my details")) {
                    last_command = text;
                    if (login.custName.equals("")) {
                        sendMessage.setText("Please login to view your details");
                    } else {
                        sendMessage.setText(bill.getCustomerDetails(login.getCustId()));
                    }
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));

                    try {
                        execute(sendMessage);
                    }catch(TelegramApiException e){
                        e.printStackTrace();
                    }
                } else if (text.equals("View current due bill")) {
                    last_command = text;
                    if (login.custName.equals("")) {
                        sendMessage.setText("Please login to view your details");
                    } else {
                        sendMessage.setText(bill.getCustomerDueBills(login.getCustId()));
                    }
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));

                    try {
                        execute(sendMessage);
                    }catch(TelegramApiException e){
                        e.printStackTrace();
                    }
                } else if (text.equals("View last paid bill")) {
                    last_command = text;
                    if (login.custName.equals("")) {
                        sendMessage.setText("Please login to view your details");
                    } else {
                        sendMessage.setText(bill.getCustomerLastPaidBillDetails(login.getCustId()));
                    }
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));

                    try {
                        execute(sendMessage);
                    }catch(TelegramApiException e){
                        e.printStackTrace();
                    }
                } else if (text.equals("Calculate bill")) {
                    last_command = text;
                    if (login.custName.equals("")) {
                        sendMessage.setText("Please login to view your details");
                    } else {
                        sendMessage.setText("Please enter your current meter reading.");
                    }
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));

                    try {
                        execute(sendMessage);
                    }catch(TelegramApiException e){
                        e.printStackTrace();
                    }
                } else if (Pattern.matches("[0-9]*", text)) {
                    if (last_command.equals("Calculate bill")) {
                        sendMessage.setText(bill.getCalculatedBill(login.getUserId(), parseInt(text)));
                    } else {
                        sendMessage.setText("Please choose 'Calculate bill' option before");
                    }
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));

                    try {
                        execute(sendMessage);
                    }catch(TelegramApiException e){
                        e.printStackTrace();
                    }
                } else if (text.equals("Pay bill")) {
                    sendMessage.setText("Functionality coming soon!");
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));

                    try {
                        execute(sendMessage);
                    }catch(TelegramApiException e){
                        e.printStackTrace();
                    }
                } else if (text.equals("Contact us")) {
                    sendMessage.setText("Please reach out to us at billing.bot@abc.com or you can call us at 9003245621");
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));

                    try {
                        execute(sendMessage);
                    }catch(TelegramApiException e){
                        e.printStackTrace();
                    }
                } else if (text.equals("/logout") || text.equals("Logout")) {
                    if (login.getCustId().equals("")) {
                        sendMessage.setText("You did not login!\nPlease use /login to login and continue.");
                    } else {
                        sendMessage.setText("You have successfully been logged out!");
                        login.setCustId("");
                        login.setCustName("");
                        login.setMeterNo(null);
                        login.setUserId("");
                        login.setPwd("");
                    }
                    setLoginKeyboard(replyKeyboardMarkup);
                    sendMessage.setReplyMarkup(replyKeyboardMarkup);

                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));

                    try {
                        execute(sendMessage);
                    }catch(TelegramApiException e){
                        e.printStackTrace();
                    }
                } else {
                    sendMessage.setText("Please choose a correct option or enter a valid command");
                    sendMessage.setParseMode(ParseMode.MARKDOWN);
                    sendMessage.setChatId(String.valueOf(message.getChatId()));

                    try {
                        execute(sendMessage);
                    }catch(TelegramApiException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
