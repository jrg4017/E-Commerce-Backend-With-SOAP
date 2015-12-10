package service;

import java.util.*;
import components.data.*;
import business.*;
import business.xml.*;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(serviceName = "LAMSService")
public class LAMSService{
   private Main m = new Main(); 
   
   /**
     * intitalize the database
     * @return msg String - whether db initialized or not
     */
   @WebMethod(operationName = "initialize")
   public String initialize(){
        boolean initalized = m.initialize();
        if(initalized) return "Database Initialized";
        return "Database failed to initalized";
    }//end initialize*/
    
    @WebMethod(operationName = "getAllAppointments")
     public String getAllAppointments(){
        return this.m.getAllAppointments();
    }//end getAllAppointments
    
     /**
     * gets & return a specfic appointment and related info
     * @param appointNumber String
     * @return xmlString String
     */
    @WebMethod(operationName = "getAppointments")
    public String getAppointment(String appointNumber){
        return this.m.getAppointment(appointNumber);
    }//end getAppointment
    
    /**
     * create a new Appointment providing the required info
     * in XML and receiving XML or error message
     * @param xmlStyle String
     * @return xmlString String
     */
    @WebMethod(operationName = "addAppointment")
    public String addAppointment(String xmlStyle){
        return this.m.addAppointment(xmlStyle);
    }//end addAppointment


}