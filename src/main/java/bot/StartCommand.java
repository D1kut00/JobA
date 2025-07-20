package bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.UserService;

public class StartCommand implements BotCommand {
    private final UserService userService;

    public StartCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public SendMessage handle(Update update) {
        Long userId = update.getMessage().getFrom().getId();
        Long chatId = update.getMessage().getChatId();
        userService.registerUser(userId, chatId);

        return SendMessage.builder()
                .chatId(chatId.toString())
                .text("Добро пожаловать в бот по поиску вакансий! Введите /menu для начала.")
                .build();
    }

    @Override
    public boolean matches(Update update) {
        return update.hasMessage() && update.getMessage().getText().equals("/start");
    }
}