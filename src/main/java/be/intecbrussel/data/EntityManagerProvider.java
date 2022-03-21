package be.intecbrussel.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityManagerProvider {
    private static final String PERSISTENCE_UNIT_NAME = "datasource";

    private static EntityManagerFactory factory =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME,
                                                   getAccess());

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    private static Map<String, String> getAccess() {
        Path toFile = Paths.get("/Users/erikh/myTestFolder/testFile.txt");
        List<String> contents = null;
        try {
            contents = Files.readAllLines(toFile);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.user", contents.get(0));
        properties.put("javax.persistence.jdbc.password", contents.get(1));

        return properties;
    }


    // TODO: proper close method
    public static void closeFactory() {
        factory.close();
    }
}
