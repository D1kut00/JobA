import config.Config;
import config.ConfigReader;
import config.ConfigReaderIEnvironment;
import data.repository.JsonUserRepository;
import data.repository.UserRepository;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.UserService;
import service.UserServiceImpl;
import bot.JobBot;

public class Main {
    public static void main(String[] args) {
        try {
            ConfigReader configReader = new ConfigReaderIEnvironment();
            Config config = configReader.read();

            UserRepository userRepository = new JsonUserRepository();
            UserService userService = new UserServiceImpl(userRepository);

            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new JobBot(config, userService));

            System.out.println("Bot started successfully!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}