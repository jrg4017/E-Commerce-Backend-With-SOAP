package server;

import javax.ws.rs.core.*;
import javax.ws.rs.*;
import java.util.ArrayList;

/**
 * the web service class for the Appointment program
 * @author Julianna Gabler
 */
@Path("ApointmentService")
public class AppointmentService{
    private Main m = new Main();
    //TODO move uri base elsewhere
    private String uri_base = "http://localhost:8080/LAMSAppointment/webresources";
    /**
     * intitalize the database
     * @return msg String - whether db initialized or not
     */
  /*  @Path("Intialize")
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    public String initialize(){
        boolean initalized = m.initialize();
        if(initalized) return "Database Initialized";
        return "Database failed to initalized";
    }//end initialize*/
    
  
    @GET
    @Produces("application/xml")
    public String getInfo(){
      boolean initialized = m.initialize();
      
      String xml= "<?xml version=\"1.0\" encoding =\"UTF-8\"?><AppointmentList>"
      + "<intro>Welcome to the LAMS Appointment Service</intro><wadl>"
      +  this.uri_base + "/application.wadl" 
      + "</wadl></AppointmentList>"; //TODO get WADL link from actual place it should be
    }

    @Path("/Appoinmments")
    @GET
    @Produces("application/xml")
    @Consumes("text/plain")
    public String getAllAppointments(){
        return this.m.getAllAppointments();
    }//end getAllAppointments

    /**
     * gets & return a specfic appointment and related info
     * @param appointNumber String
     * @return xmlString String
     */
    @Path("/Appointments/{appointment}")
    @GET
    @Produces("application/xml")
    @Consumes("text/plain")
    public String getAppointment(@PathParam("appointment") String appointment){
        return this.m.getAppointment(appointment);
    }//end getAppointment

    /**
     * create a new Appointment providing the required info
     * in XML and receiving XML or error message
     * @param xmlStyle String
     * @return xmlString String
     */
    @Path("/Appointments")
    @PUT
    @Produces("application/xml")
    @Consumes({"text/plain", "application/xml"})
    public String addAppointment(String xmlStyle){
        return this.m.addAppointment(xmlStyle);
    }//end addAppointment
}//end AppointmentService