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
    }

    public String getAllAppointments(){
        List<Object> obj = this.database.getDB().getData("Appointment","");
        Appointment appt = new Appointment();
        XML xml = new XML(appt);
        String xmlStr = xml.getStartTag() + xml.getTagNameStart();

        for(Object objs : obj){
            xml.setAppointment((Appointment)objs);
            xmlStr += xml.appointmentXML();
        }
        
        xmlStr += xml.getTagNameEnd();
        return xmlStr;
    }

    public String getAppointment(String appointNumber){
        Object obj = this.database.getAppointment(appointNumber);

        if(obj == ""){
            XML xml = new XML();
            return xml.error();
        }
        XML xml = new XML((Appointment)obj);
        String xmlStr = xml.getStartTag() + xml.getTagNameStart() + xml.appointmentXML();
        xmlStr += xml.getTagNameEnd();

        return xmlStr;
    }

    public String addAppointment(String xmlStyle){

        return "";
    }

    public static void main(String[] args){
        Main m = new Main();
        m.initialize();
        System.out.println(m.getAllAppointments());
    }


}