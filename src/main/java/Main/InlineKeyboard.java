package Main;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboard extends BotCommand {

    private static final String CommandIdentifier = "inline";
    private static final String Description = "inline keyboard command";
    private static final String[] CatchWords = {"GitHub", "example", "share", "hello"};

    public InlineKeyboard() {
        super(CommandIdentifier, Description);
    }

    private ReplyKeyboard getInlineKeyboardMarkup(String[] args) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> keyboardFirstRow = new ArrayList<>();
        if (args != null && args.length > 0) {
            if (args[0].contains(CatchWords[0])) {
                keyboardFirstRow.add(new InlineKeyboardButton("open GitHub TelegramBot repository").setUrl("https://github.com/luckyanya/luckyanyaBot"));
            }
            if (args[0].contains(CatchWords[1])) {
                keyboardFirstRow.add(new InlineKeyboardButton("open an example of a Bot").setUrl("https://habr.com/ru/post/432548/"));
            }
            if (args[0].contains(CatchWords[2])) {
                keyboardFirstRow.add(new InlineKeyboardButton("give me a compliment").setSwitchInlineQuery("it's amazing"));
            }
            if (args[0].contains(CatchWords[3])) {
                keyboardFirstRow.add(new InlineKeyboardButton("hello BotCommand").setCallbackData("/hello"));
            }
        } else {
            keyboardFirstRow.add(new InlineKeyboardButton("Text").setSwitchInlineQueryCurrentChat("GitHub/example/share/hello"));
        }
        keyboard.add(keyboardFirstRow);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        SendMessage answer = new SendMessage();
        StringBuilder messageTextBuilder;
        if (arguments != null && arguments.length > 0) {
            messageTextBuilder = new StringBuilder("your request is:");
        } else {
            messageTextBuilder = new StringBuilder("where is a request?");
        }
        answer.setChatId(chat.getId().toString());
        answer.setText(messageTextBuilder.toString());
        answer.setReplyMarkup(getInlineKeyboardMarkup(arguments));
        try {
            absSender.execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
