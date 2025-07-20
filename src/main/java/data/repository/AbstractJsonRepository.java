package data.repository;

import java.io.File;

public abstract class AbstractJsonRepository {
    protected final String storageDir;

    protected AbstractJsonRepository(String storageDir) {
        this.storageDir = storageDir;
        new File(storageDir).mkdirs();
    }

    protected File getFile(Long id) {
        return new File(storageDir + "/" + id + ".json");
    }
}