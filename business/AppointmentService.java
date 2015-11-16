package server;

import javax.jws.*;
import java.util.ArrayList;

@WebService(serviceName = "AppointmentService")
public class AppointmentService{
   /**
   * intitalize the database
   * @return msg String - whether db initialized or not
   */
   @WebMethod(operationName = "Initialize")
   public String initialize(){
      return "xmlString::initialize()";
   }//end initialize
   
   /**
   * gets & returns a list of All appointment & related info
   * @return xmlString String
   */
   @WebMethod(operationName = "GetAllAppoinments")
   public String getAllAppointments(){
      return "xmlString::getAllAppointments()";
   }//end getAllAppointments
   
   /**
   * gets & return a specfic appointment and related info
   * @param appointNumber String
   * @return xmlString String
   */
   @WebMethod(operationName = "GetAppoinment")
   public String getAppointment(String appointNumber){
      //stuff
      return "xmlString::getAppointment(String appointNumber)";
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
// 
// //annonation, defaults to className service if we don't change it
// @WebService(serviceName = "OurArea")
// public class Area{
//    //no main method in a service
//    
//    @WebMethod(operationName = "ETHello")
//    public String helloWorld(){
//       Helper h = new Helper();
//       return h.zHelloWorld();
//    }
//    
//    @WebMethod(operationName = "ClassicHello")
//    public String helloWorld2(){
//       return "Hello World!";
//    }
//    
//    //defaults to this method name
//    public double calcRectangle(double x, double y){
//       return x*y;
//    }
//    
//    @WebMethod(exclude = true)
//    //defaults to false, true makes it not show up in WDSL
//    public double calcCircle(double r){
//       return r*r*Math.PI;
//    }
// }