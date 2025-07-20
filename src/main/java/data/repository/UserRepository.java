package data.repository;

import data.model.BotUser;

import java.util.Optional;

public interface UserRepository {
    Optional<BotUser> findById(Long id);
    boolean exists(Long id);
    void save(BotUser user);
    void delete(Long id);
}