package Main;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main extends TelegramLongPollingCommandBot {

    public static final String USERNAME = "luckyanyaBot";
    public static final String TOKEN = "945476768:AAH_vW0WuJlBssboE258Smc7x6a-6boo2cc";

    Main(DefaultBotOptions botOptions) {
        super(botOptions, USERNAME);
        register(new Command());
        register(new InlineKeyboard());
        register(new ReplyKeyboard());
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        if (message.getText().equals("Hello")) {
            sendMessage.setText("Hello, I am a little bot");
        } else {
            sendMessage.setText("I do not know what to answer. Maybe say me Hello?");
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
           // botOptions.setProxyHost("177.8.226.254");
           // botOptions.setProxyPort(8080);
            //botOptions.setProxyType(DefaultBotOptions.ProxyType.HTTP);
            telegramBotsApi.registerBot(new Main(botOptions));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}




