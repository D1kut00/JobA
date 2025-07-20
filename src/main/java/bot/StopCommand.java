package bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.UserService;

public class StopCommand implements BotCommand {
    private final UserService userService;

    public StopCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public SendMessage handle(Update update) {
        Long userId = update.getMessage().getFrom().getId();
        userService.removeUser(userId);
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text("Вы были удалены из базы. Для повторной регистрации введите /start")
                .build();
    }

    @Override
    public boolean matches(Update update) {
        return update.hasMessage() && update.getMessage().getText().equals("/stop");
    }
}