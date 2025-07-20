package bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.UserService;

public class RegionCommand implements BotCommand {
    private final UserService userService;

    public RegionCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public SendMessage handle(Update update) {
        Long userId = update.getMessage().getFrom().getId();
        var user = userService.getUser(userId);
        user.setInputState("waiting_for_region");
        userService.saveUser(user);

        return SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text("Введите код региона (например, 77 для Москвы):")
                .build();
    }

    @Override
    public boolean matches(Update update) {
        return update.hasMessage() && update.getMessage().getText().equals("/region");
    }
}