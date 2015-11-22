package server;

import business.*;
import xml.*;

public class Main{
    Database database = null;
    XMLTagsGenerator xtg = new XMLTagsGenerator();

    /**
     * intialize the database
     * @return boolean
     */
    public boolean initialize(){
        this.database = new Database();
        return true;
    }

    public String getAllAppointments(){
        List<Object> obj = this.database.getData("Appointment","");
        xtg.setRoot("AppointmentList");
        String xml = xtg.getStartTag();

        for(Object objs : obj){
            xtg.setObject(obj);
            xml += xtg.getObjectXML();
        }
    }




}