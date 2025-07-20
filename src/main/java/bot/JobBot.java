package bot;

import config.Config;
import data.model.BotUser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.UserService;

public class JobBot extends TelegramLongPollingBot {
    private final Config config;
    private final CommandDispatcher dispatcher;

    public JobBot(Config config, UserService userService) {
        this.config = config;
        this.dispatcher = new CommandDispatcher(userService);
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            SendMessage message = dispatcher.dispatch(update);
            if (message != null) {
                execute(message);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "Job Vacancy Bot";
    }

    @Override
    public String getBotToken() {
        return config.botApiToken();
    }
}