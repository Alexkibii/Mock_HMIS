package lct.service;

import hellios.wsdl.SavedDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by CLLSDJACKT013 on 25/01/2018.
 */
@SpringBootApplication
public class Application{

    private Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String [] args){
        System.out.println("Init.....");
        SpringApplication.run(Application.class,args);
    }
    @Bean
    CommandLineRunner run(){
        return args ->{
            logger.info("Mock_HMIS WebService initialized....");
            logger.info("-------------------------------------");
        };
    }
}
