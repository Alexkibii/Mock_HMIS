package lct.service.device;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by CLLSDJACKT013 on 26/01/2018.
 */
@Document(collection = "devices")
public class Device {
    @Id
    private String id;
    private String macAddress;
    private String sNO;
    private String retailer;
    private String agency;

    //default class for jpa impl
    public Device(){}

    public Device(String macAddress,String sNO,String retailer,String agency){
        this.macAddress = macAddress;
        this.sNO = sNO;
        this.retailer = retailer;
        this.agency = agency;
    }
    //setter and getter methods go here
    public void setMacAddress(String macAddress){
        this.macAddress = macAddress;
    }
    public String getMacAddress(){
        return macAddress;
    }
    public void setsNO(String sNO){
        this.sNO = sNO;
    }
    public String getsNO(){
        return sNO;
    }
    public void setRetailer(String retailer){
        this.retailer = retailer;
    }
    public String getRetailer(){
        return retailer;
    }
    public void setAgency(String agency){
        this.agency = agency;
    }
    public String getAgency(){
        return agency;
    }
}
