package data.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.model.BotUser;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class JsonUserRepository implements UserRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String storageDir = "data/users";
    private String region;
    private String experience;
    private Integer salary;
    private String keyword;
    private Integer notifyHour;
    private String inputState; // e.g. "waiting_for_region"

    public JsonUserRepository() {
        new File(storageDir).mkdirs();
    }

    @Override
    public Optional<BotUser> findById(Long id) {
        File file = new File(storageDir + "/" + id + ".json");
        if (!file.exists()) return Optional.empty();
        try {
            return Optional.of(mapper.readValue(file, BotUser.class));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean exists(Long id) {
        return new File(storageDir + "/" + id + ".json").exists();
    }

    @Override
    public void save(BotUser user) {
        try {
            mapper.writeValue(new File(storageDir + "/" + user.getUserId() + ".json"), user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        File file = new File(storageDir + "/" + id + ".json");
        if (file.exists()) file.delete();
    }
}