package lct.service.device;

import com.mongodb.MongoException;
import lct.service.mongo.db.MongoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by CLLSDJACKT013 on 26/01/2018.
 */
@Component
public class DeviceRepository {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
    MongoOperations mongoOperations = (MongoOperations)ctx.getBean("mongoTemplate");
    private Logger logger = LoggerFactory.getLogger(DeviceRepository.class);

    //repository methods follow here
    public Device saveDevice(Device device){
        try{
            mongoOperations.save(device,"devices");
        }
        catch (MongoException e){
            e.printStackTrace();
        }
        return device;
    }
    public List<Device> insertDevices(List<Device> devices){
        try {
            mongoOperations.insert(devices, Device.class);
        }
        catch(MongoException e){
            e.printStackTrace();
        }
        return devices;
    }
    public Device findOneByMac(String macAddress){
        Device foundDevice = null;
        try{
            Query searchQuery = new Query();
            searchQuery.addCriteria(Criteria.where("macAddress").is(macAddress));
            foundDevice = mongoOperations.findOne(searchQuery,Device.class,"devices");
        }
        catch(MongoException e){
            e.printStackTrace();
        }
        return foundDevice;
    }

}
