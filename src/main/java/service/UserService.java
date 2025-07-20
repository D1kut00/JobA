package service;

import data.model.BotUser;

public interface UserService {
    void registerUser(Long userId, Long chatId);
    BotUser getUser(Long userId);
    void saveUser(BotUser user);
    void removeUser(Long userId);
}
