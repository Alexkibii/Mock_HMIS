package lct.service;

import hellios.wsdl.DeviceToSave;
import hellios.wsdl.ObjectFactory;
import hellios.wsdl.SavedDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Created by CLLSDJACKT013 on 26/01/2018.
 */
@Component
public class WsClient {
    @Autowired
    private WebServiceTemplate webServiceTemplate;


    private ObjectFactory factory = new ObjectFactory();

    public SavedDevice saveDevice(){
        DeviceToSave deviceToSave = factory.createDeviceToSave();
        deviceToSave.setMacAddress("00:25:7E:03");
        deviceToSave.setSerial("xxxx");
        deviceToSave.setRetailer("Tiesto");
        deviceToSave.setAgency("Humlot");

        SavedDevice device = (SavedDevice)webServiceTemplate.marshalSendAndReceive(deviceToSave);
        return device;
    }
}
