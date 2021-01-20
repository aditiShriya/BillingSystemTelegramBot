import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class VidhyutBot extends TelegramLongPollingBot
{

    public String getBotUsername() {
        return "Viduhtbot";
    }

    @Override
    public String getBotToken() {
        return "1584939971:AAHmxAsRJQ0ADj5zxcnE5tLtRWfXe8OpALU";
    }

    public void onUpdateReceived(Update update)
    {
        String command=update.getMessage().getText();

        SendMessage message=new SendMessage();

        if (command.equals("/start")) {
            message.setText("Welcome to Vidhyut Bot\n Please press /login command to login into the application.");
        }

        if (command.equals("/myname"))
        {
            System.out.println(update.getMessage().getFrom().getFirstName());
            message.setText("update.getMessage().getFrom().getFirstName()\nhello");
        }
        if(command.equals("/mylastname"))
        {
            System.out.println(update.getMessage().getFrom().getLastName());
            message.setText(update.getMessage().getFrom().getLastName());
        }
        if (command.equals("/myfullname"))
        {
            System.out.println(update.getMessage().getFrom().getFirstName()+" "+update.getMessage().getFrom().getLastName());
            message.setText(update.getMessage().getFrom().getFirstName()+" "+update.getMessage().getFrom().getLastName());
        }

        if (command.equals("/login")) {
            message.setText("Enter your user ID and password");
            message.setText(update.getMessage().getFrom().getUserID());
            message.setText(update.getMessage().getFrom().getPassword());
            //Login l= new Login();
            //L.run();
        }


        message.setChatId(String.valueOf(update.getMessage().getChatId()));

        try {
            execute(message);
        }catch(TelegramApiException e){
            e.printStackTrace();
        }
    }
}
