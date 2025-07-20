package bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MenuCommand implements BotCommand {

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.getMessage().getChatId();

        String text = """
                Главное меню:
                🔍 /region — Выбрать регион
                📅 /experience — Указать опыт
                💰 /salary — Указать минимальную зарплату
                🔑 /keyword — Ключевые слова
                ⏰ /notify — Время уведомлений
                ❌ /stop — Остановить бота
                """;

        return SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .build();
    }

    @Override
    public boolean matches(Update update) {
        return update.hasMessage() && update.getMessage().getText().equals("/menu");
    }
}
