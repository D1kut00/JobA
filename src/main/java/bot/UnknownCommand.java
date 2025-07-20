package bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand implements BotCommand {
    @Override
    public SendMessage handle(Update update) {
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text("Извините, я не понял эту команду. Попробуйте /start.")
                .build();
    }

    @Override
    public boolean matches(Update update) {
        return true; // Always matches as fallback
    }
}
