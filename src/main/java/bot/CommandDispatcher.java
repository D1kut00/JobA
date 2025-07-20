package bot;

import data.model.BotUser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.UserService;

import java.util.List;

public class CommandDispatcher {
    private final List<BotCommand> commands;
    private final BotCommand fallback = new UnknownCommand();
    private final UserService userService;

    public CommandDispatcher(UserService userService) {
        this.userService = userService;
        this.commands = List.of(
                new StartCommand(userService),
                new MenuCommand(),
                new RegionCommand(userService),
                new ExperienceCommand(userService),
                new SalaryCommand(userService),
                new KeywordCommand(userService),
                new NotifyCommand(userService),
                new StopCommand(userService)
        );
    }

    public SendMessage dispatch(Update update) {
        Long userId = update.getMessage().getFrom().getId();
        BotUser user = userService.getUser(userId);

        if (user.getInputState() != null) {
            String input = update.getMessage().getText();
            switch (user.getInputState()) {
                case "waiting_for_region" -> {
                    user.setRegion(input);
                    user.setInputState(null);
                    userService.saveUser(user);
                    return SendMessage.builder()
                            .chatId(update.getMessage().getChatId().toString())
                            .text("Регион сохранён: " + input)
                            .build();
                }
                case "waiting_for_experience" -> {
                    user.setExperience(input);
                    user.setInputState(null);
                    userService.saveUser(user);
                    return SendMessage.builder()
                            .chatId(update.getMessage().getChatId().toString())
                            .text("Опыт сохранён: " + input)
                            .build();
                }
                case "waiting_for_salary" -> {
                    try {
                        user.setSalary(Integer.parseInt(input));
                        user.setInputState(null);
                        userService.saveUser(user);
                        return SendMessage.builder()
                                .chatId(update.getMessage().getChatId().toString())
                                .text("Зарплата сохранена: " + input)
                                .build();
                    } catch (NumberFormatException e) {
                        return SendMessage.builder()
                                .chatId(update.getMessage().getChatId().toString())
                                .text("Пожалуйста, введите число.")
                                .build();
                    }
                }
                case "waiting_for_keyword" -> {
                    user.setKeyword(input);
                    user.setInputState(null);
                    userService.saveUser(user);
                    return SendMessage.builder()
                            .chatId(update.getMessage().getChatId().toString())
                            .text("Ключевое слово сохранено: " + input)
                            .build();
                }
                case "waiting_for_notify" -> {
                    try {
                        int hour = Integer.parseInt(input);
                        if (hour < 0 || hour > 23) throw new NumberFormatException();
                        user.setNotifyHour(hour);
                        user.setInputState(null);
                        userService.saveUser(user);
                        return SendMessage.builder()
                                .chatId(update.getMessage().getChatId().toString())
                                .text("Время уведомлений установлено на: " + hour + ":00")
                                .build();
                    } catch (NumberFormatException e) {
                        return SendMessage.builder()
                                .chatId(update.getMessage().getChatId().toString())
                                .text("Введите число от 0 до 23")
                                .build();
                    }
                }
            }
        }

        return commands.stream()
                .filter(cmd -> cmd.matches(update))
                .findFirst()
                .orElse(fallback)
                .handle(update);
    }
}
