package service;

import data.model.BotUser;
import data.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void registerUser(Long userId, Long chatId) {
        if (!userRepository.exists(userId)) {
            BotUser user = new BotUser(userId, chatId);
            userRepository.save(user);
        }
    }

    @Override
    public BotUser getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
    }

    @Override
    public void saveUser(BotUser user) {
        userRepository.save(user);
    }

    @Override
    public void removeUser(Long userId) {
        userRepository.delete(userId);
    }
}