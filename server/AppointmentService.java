package server;

import javax.jws.*;
import java.util.ArrayList;

@WebService(serviceName = "AppointmentService")
public class AppointmentService{
    Main m = new Main();
    /**
     * intitalize the database
     * @return msg String - whether db initialized or not
     */
    @WebMethod(operationName = "Initialize")
    public String initialize(){
        boolean initalized = m.initialize();
        if(initalized) return "Database Initialized";
        return "Database failed to initalized";
    }//end initialize

    /**
     * gets & returns a list of All appointment & related info
     * @return xmlString String
     */
    @WebMethod(operationName = "GetAllAppoinments")
    public String getAllAppointments(){
      return this.m.getAllAppointments();
    }//end getAllAppointments

    /**
     * gets & return a specfic appointment and related info
     * @param appointNumber String
     * @return xmlString String
     */
    @WebMethod(operationName = "GetAppoinment")
    public String getAppointment(String appointNumber){
      return this.m.getAppointment(appointNumber);
    }//end getAppointment

    /**
     * create a new Appointment providing the required info
     * in XML and receiving XML or error message
     * @param xmlStyle String
     * @return xmlString String
     */
    @WebMethod(operationName = "AddAppointment")
    public String addAppointment(String xmlStyle){
        return this.m.addAppointment(xmlStyle);
    }//end addAppointment
}
