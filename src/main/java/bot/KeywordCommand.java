package bot;

import data.model.BotUser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.UserService;

public class KeywordCommand implements BotCommand {
    private final UserService userService;

    public KeywordCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public SendMessage handle(Update update) {
        Long userId = update.getMessage().getFrom().getId();
        BotUser user = userService.getUser(userId);
        user.setInputState("waiting_for_keyword");
        userService.saveUser(user);

        return SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text("Введите ключевое слово для поиска вакансий")
                .build();
    }

    @Override
    public boolean matches(Update update) {
        return update.hasMessage() && update.getMessage().getText().equals("/keyword");
    }
}