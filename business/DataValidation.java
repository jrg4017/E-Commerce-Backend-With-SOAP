package business;

import components.data.*;
import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;


public class DataValidation extends Access{
   public DataValidation(){
      super();
   }
/********************** TEST TEST TEST TEST ***************************************************************************/
   public static void main(String[] args){
      DataValidation v = new DataValidation();
      //boolean b = v.appointmentReequirements("2004-02-01", "11:00:00");
      //System.out.println(b);
       // ArrayList<String> apptInfo = new ArrayList<String>();
//        apptInfo.add("240"); //24444
//        apptInfo.add("110"); //115
//        apptInfo.add("");
//        apptInfo.add("80200");
//        //set up as String patientId, String phlebId, String physId
//       System.out.println(v.validateAppointmentInfo(apptInfo));

      v.apptRequirements("2004-02-01", "11:00:00", "Florence Nightengale", "JacBeanstalk");
     // System.out.println(p);
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
     */ // @param paramName = the name of the parameter
    public boolean isValidObject(String object, String paramValue){
        String param = "id='" + paramValue + "'";
        List<Object> objs = this.db.getData(object, param);
        //if size zero, it means the patient doesn't exist
        if(objs.size() > 0) return true;
        return false;
    }//end isValidObject()
    


    public void apptRequirements(String date, String time, String phlebotomistName, String patientName){
        String patientId = this.getPatientIdFromName(patientName);
        System.out.println(patientId);


        //boolean available = this.isValidApptDateTime(date, time);
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


       

    }



    /**
     * validate the keys grabbed and make sure there's no empty ids ("")
     * @param apptInfo - set up as String patientId, String phlebId, String physId, String labTest
     * @return String
     */
    public String validateAppointmentInfo(HashMap<String, String> ids){
        //grab information and verify that
        if(ids.get("Patient") == "")      return "Patient was not found. Please try again or register patient.";
        if(ids.get("Phlebotomist") == "") return "Phlebotomist not found. Please try another phlebotomist";
        if(ids.get("PSC") == "" )         return "Patient Service Center not found. Please try another center";
        if(ids.get("Physician") == "")    return "Physician not found. Please try another physician.";
       // if(ids.get("LabTest" == "" )      return "Not a valid lab test. Please trya again";
        //return messages if they don't exists; "" return if all is well
        return "";
    }//end validateAppointmentInfo

    /**
     * grab the keys from the names
     * @param apptInfo <code>ArrayList<String></code>
     * @return ids <code>HashMap<String, String></code>
     */
    public HashMap<String, String> validateGetIds(ArrayList<String> apptInfo){
        HashMap<String, String> ids = new HashMap<String, String>();

        ids.put("Patient", this.getPatientIdFromName(apptInfo.get(0)));
        ids.put("Phlebotomist" ,this.getPhlebIdFromName(apptInfo.get(1)));
        ids.put("Physician", this.getPhysicianIdFromName(apptInfo.get(2)));
       // ids.add("LabTest", this.getLabTestIdFromName(apptInfo.get(3)));
        ids.put("PSC", this.getPscIdFromName(apptInfo.get(4)));
        //return ids of all neccessary information whether
        return ids;
    }



    /**
     * checks to see if date and time is taken or not
     * @param date - the requested date
     * @param time - the requested time
     * @param paramName - the requested phlebotomist
     * @param id - the requested
     * @return
     */
    public boolean isValidApptDateTime(String date, String time, String plhebId, String pscid){
        String params = "apptdate='" +  java.sql.Date.valueOf(date) + "' AND appttime='" + java.sql.Time.valueOf(time) + "'";
        if(!plhebId.equals("")) params += " AND " + "phlebid" + "='" + plhebId + "'";
        if(!pscid.equals(""))   params += " AND " + "pscid" + "='" + pscid + "'";

        List<Object> objs = this.db.getData("Appointment", params);
        
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