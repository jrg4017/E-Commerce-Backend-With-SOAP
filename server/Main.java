package server;

import business.*;
import xml.*;
import java.util.*;
import components.data.*;
public class Main{
    Database database = null;

    /**
     * intialize the database
     * @return boolean
     */
    public boolean initialize(){
        this.database = new Database();
        return true;
    }//end intiialize

    /**
     * get all of the appointments in the database
     * @return xml String
     */
    public String getAllAppointments(){
        //grab the list of appointments from database
        List<Object> obj = this.database.getDB().getData("Appointment","");
        //create objects for appt and xml
        Appointment appt = new Appointment();
        XML xml = new XML();
        String xmlStr = xml.getStartTag() + xml.getTagNameStart();//add starting tags
        //for each appointment grab the XML tags corresponding it
        for(Object objs : obj){
            xml.setAppointment((Appointment)objs);
            xmlStr += xml.appointmentXML();
        }
        //add the </AppointmentList> ending
        xmlStr += xml.getTagNameEnd();
        return xmlStr;
    }//end getAllAppointments

    /**
     * gets the appointment in xml string format
     * @param appointNumber the number of the appt
     * @return xmlStr String
     */
    public String getAppointment(String appointNumber){
        List<Object> obj = this.database.getAppointment(appointNumber);
        Object rtn = null;
        for(Object objs : obj){
            rtn = objs;
        }
        //if null or empty, return the error string
        if(rtn == "" || rtn == null){
            XML xml = new XML();
            return xml.error();
        }
        Appointment appt = (Appointment)rtn;
        XML xml = new XML(appt);
        String xmlStr = xml.getStartTag() + xml.getTagNameStart() + xml.appointmentXML();
        xmlStr += xml.getTagNameEnd();
        //return it in full
        return xmlStr;
    }//end getAppointment

    public String addAppointment(String xmlStyle){

        return "";
    }

    public static void main(String[] args){
        Main m = new Main();
        m.initialize();
        System.out.println(m.getAppointment("810"));
    }


}