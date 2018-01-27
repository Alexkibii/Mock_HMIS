package lct.service;

import com.mongodb.MongoException;
import hellios.wsdl.*;
import hellios.wsdl.Device;
import lct.service.device.*;
import lct.service.mongo.db.MongoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by CLLSDJACKT013 on 25/01/2018.
 */
/*
-@Endpoint annotation identies this class as a potential handler for incoming SOAP messages
 */
@Endpoint
public class WsEndPoint {
    private Logger logger = LoggerFactory.getLogger(WsEndPoint.class);
    ObjectFactory factory = new ObjectFactory();
    @Autowired
    private DeviceRepository deviceRepository;
    /*
    -@PayloadRoot gives a qualified namespace for a particular endpoint method. Qualified namespace = namespace + localPart
    -If incoming request has this qualified namespace, then the endpoint method is invoked
    -@ResponsePayload maps the returned object to the expected object type
    -@RequestPayload puts a "strict" to the incoming request i.e checks if the incoming request maps the expected object
     */
    @PayloadRoot(namespace = "http://codenotfound.com/types/lct",localPart = "person")
    @ResponsePayload
    public Greeting sayHello(@RequestPayload  Person request){
        logger.info("Request for person " +request.getLastName() + " " +request.getFirstName() +" " +"received");
        String greeting = "Hello " +request.getFirstName() + " " +request.getLastName();
        Greeting response = new Greeting();
         response.setGreeting(greeting);
         return response;
    }

    @PayloadRoot(namespace = "http://codenotfound.com/types/lct", localPart = "device")
    @ResponsePayload
    public DeviceInfo queryDevice(@RequestPayload Device device){
        logger.info("Request for device " +device.getMacAddress() + " " +"received");
        //mock creation of a new device. Real functionality is to query for the device from db
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setMacAddress(device.getMacAddress());
        deviceInfo.setSNo(device.getSNO());
        deviceInfo.setRetailer("Like Mike");
        deviceInfo.setAgency("House Music");
        return deviceInfo;
    }

    @PayloadRoot(namespace = "http://codenotfound.com/types/lct", localPart = "deviceToSave")
    @ResponsePayload
    public SavedDevice saveDevice(@RequestPayload DeviceToSave device){
        logger.info("POST request for device saving received");
        SavedDevice savedDevice = factory.createSavedDevice();
        savedDevice.setMacAddress(device.getMacAddress());
        savedDevice.setSerial(device.getSerial());
        savedDevice.setRetailer(device.getRetailer());
        savedDevice.setAgency(device.getAgency());
        /******************Mongo Operations*****************************/
            lct.service.device.Device saveDevice = new lct.service.device.Device(device.getMacAddress(),device.getSerial(),device.getRetailer(),device.getAgency());
            try{
                deviceRepository.saveDevice(saveDevice);
            }
            catch (MongoException e){
                e.printStackTrace();
            }

        return savedDevice;
    }
}
