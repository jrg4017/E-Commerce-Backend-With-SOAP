package business;

import components.data.*;
import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;


public class Validate{
   IComponentsData db;
   public Validate(){
      this.db = new DB();
      this.db.initialLoad("LAMS");
   }
/********************** TEST TEST TEST TEST ***************************************************************************/
   public static void main(String[] args){
      Validate v = new Validate();
      //boolean b = v.appointmentReequirements("2004-02-01", "11:00:00");
      //System.out.println(b);
      v.apptRequirements("2004-02-01", "11:00:00");
   }//end main
/**********************************************************************************************************************/
// Validation rules:


    /**
     * check to see if it is a valid object or not
     * used to check to see if an appointment, patient, phlebotomist, physician
     * and LabTest exist inside of the database
     * @param object - the object aka table we are looking at
     * @param paramValue - the value of the param
     * @return boolean
     * @see getObjectList
     */ // @param paramName = the name of the parameter
    public boolean isValidObject(String object, String paramValue){
        String param = "id='" + paramValue + "'";
        List<Object> objs = this.getObjectList(object, param);
        //if size zero, it means the patient doesn't exist
        if(objs.size() > 0) return true;
        return false;
    }//end isValidObject()
    
    private List<Object> getObjectList(String object, String param){
         List<Object> objs = this.db.getData(object, param);
         return objs;
    }

    public boolean apptRequirements(String date, String time, Phlebotomist phleb){
        boolean available = this.isValidApptDateTime(date, time);
        //TODO add grab next available time on this day
        String param = "apptdate='" + java.sql.Date.valueOf(date) + "'";
        
        //TODO --below
        /*
         * check appt requirements -> check to see if phlebotomist and patient service center is free at time
         *
         * if phlebotomist is free at time, check previous appt to see if same patient service
         * if NOT at same phlebotomist, check to see if have at least 30 minutes between scheduled and requested
         * check to see if that next appt slot is free
         *
         * grab next available appointment
         */

        
        for (Appointment appt : phlebList){
            
        }
       return available;

    }

    /**
     *
     * @param apptInfo - set up as String patientId, String phlebId, String physId, String
     * @return
     */
    public String validateAppointmentInfo(ArrayList<String> apptInfo){ //assumption the website automaticatically generates name / id association

        if( !isValidObject("Appointment", apptInfo.get(0)) )  return "Patient not found. Please try again or register with Celluar One.";

        if( !apptInfo.get(1).equals("") && !isValidObject("Phlebotomist", apptInfo.get(1)) ) return "Phlebotomist not found. Please try again";

        if( !apptInfo.get(2).equals("") && !isValidObject("Physician", apptInfo.get(2) ))    return "Physician not found. Please try again";

        if( ! )
            return "";
    }
   
   

    /**
     * checks to see if date and time is taken or not
     * @param date
     * @param time
     * @return boolean
     */
    public boolean isValidApptDateTime(String date, String time){
        String params = "apptdate='" +  java.sql.Date.valueOf(date) + "' AND appttime='" + java.sql.Time.valueOf(time) + "'";
        List<Object> objs = this.getObjectList("Appointment", params);
        
        //if greater than 0, then means appt exists
        if(objs.size() > 0) return false;
        return true;
    }

//    /**
//     * ensures that the date entered is in a sql safe format
//     * @param date - the date to enter into sql db
//     * @return String
//     */
//    public Date getApptDate(String date){
//        return
//    }//end getApptDate
//
//    /**
//     * ensures that the time entered is in a sql safe format
//     * @param time - the time to enter into sql db
//     * @return String
//     */
//    public Time getApptTime(String time){
//        return java.sql.Time.valueOf(time);
//    }//end getApptTime


//   validPhysician         - The patient’s physician is valid and exists in the system
//   validLabTest           - The ordered lab test is valid and exists in the system/
//                          -  It takes phlebotomist 30 minutes to get from one PSC to another & be ready for an appointment
//   validPhlebotomist      - The requested clinicalian (phlebotomist) is valid and exists in the syste
//   appointmentRegistered  - The Appointment has been registered in the system once complete addAppointment process
//   isAppointmentAvailable - checks to see if patient requested appointment is available
//   appointmentParameters  -  see if appt parameters are valid, duration is 15 minutes, made from 8am to 5pm
//   santize                - santize the data input

    /**
   * returns the object in a string regardless of type
   * @return String
   */
   public String toString(){
      return "";
   }//end toString
   
}