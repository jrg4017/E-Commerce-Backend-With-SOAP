package server;

import javax.jws.*;
import java.util.ArrayList;

@WebService(serviceName = "AppointmentService")
public class AppointmentService{
    Main main = new Main();
    /**
     * intitalize the database
     * @return msg String - whether db initialized or not
     */
    @WebMethod(operationName = "Initialize")
    public String initialize(){
        boolean initalized = m.initialize();
        if(initalized) return "Database Initialized";
        return "Database failed to initalized"
    }//end initialize

    /**
     * gets & returns a list of All appointment & related info
     * @return xmlString String
     */
    @WebMethod(operationName = "GetAllAppoinments")
    public String getAllAppointments(){
      return "";
    }//end getAllAppointments

    /**
     * gets & return a specfic appointment and related info
     * @param appointNumber String
     * @return xmlString String
     */
    @WebMethod(operationName = "GetAppoinment")
    public String getAppointment(String xml){
      return "";
    }//end getAppointment

    /**
     * create a new Appointment providing the required info
     * in XML and receiving XML or error message
     * @param xmlStyle String
     * @return xmlString String
     */
    @WebMethod(operationName = "AddAppointment")
    public String addAppointment(String xmlStyle){
        //stuff
        return "xmlString::addAppointment(String xmlStyle)";
    }//end addAppointment
}
