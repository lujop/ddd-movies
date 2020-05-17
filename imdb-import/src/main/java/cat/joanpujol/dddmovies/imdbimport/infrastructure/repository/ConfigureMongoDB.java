package cat.joanpujol.dddmovies.imdbimport.infrastructure.repository;

import cat.joanpujol.dddmovies.imdbimport.infrastructure.entities.TitleEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ConfigureMongoDB {
  private static final Logger logger = LoggerFactory.getLogger(ConfigureMongoDB.class);

  private final MongoClient client;
  private final String databaseName;

  @Inject
  public ConfigureMongoDB(
      MongoClient client, @ConfigProperty(name = "quarkus.mongodb.database") String databaseName) {
    this.client = client;
    this.databaseName = databaseName;
  }

  @PostConstruct
  public void configure() {
    MongoDatabase database = client.getDatabase(databaseName);
    String collectionName = TitleEntity.class.getSimpleName();
    database.getCollection(collectionName).createIndex(Indexes.text("mainTitle"));
    database.getCollection(collectionName).createIndex(Indexes.text("originalTitle"));

    logger.info("MongoDB configured");
  }
}
