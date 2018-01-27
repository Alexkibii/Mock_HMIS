package lct.service.mongo.db;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by CLLSDJACKT013 on 26/01/2018.
 */
@Configuration
public class MongoConfig {

    public @Bean
    MongoTemplate mongoTemplate () throws Exception{
        MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("127.0.0.1"),"hmis");
        return mongoTemplate;
    }

}
