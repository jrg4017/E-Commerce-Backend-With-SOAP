package business;

import components.data.*;
import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;


public class DataValidation{
    private IComponentsData db;
    private HashMap<String, String> apptIds = new HashMap<String, String>();
    private HashMap<String, String> tests = new HashMap<String, String>();
    Appointment newAppt;

/**********************************************************************************************************************/
/******************* CONSTRUCTOR **************************************************************************************/
/**********************************************************************************************************************/
    /**
     * initalizes the db and sets it for the class
     */
    public DataValidation(){
           this.db = new DB();
           this.db.initialLoad("LAMS");
   }//end DataValidation
/**********************************************************************************************************************/
/******************** ACCESSORS ***************************************************************************************/
/**********************************************************************************************************************/
    /**
     *  gets the db
     * @return this.db
     */
    public IComponentsData getDB(){ return this.db; }//end getDB
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
      //"2004-02-01", "11:00:00", "Florence Nightengale", "JacBeanstalk"
      v.apptRequirements();
     // System.out.println(p);
   }//end main
/**********************************************************************************************************************/
/*************** METHODS **********************************************************************************************/
/**********************************************************************************************************************/

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

    /**
     *
     * @param date
     * @param time
     * @param phlebotomistName
     * @param patientName
     */
    public String apptRequirements(){
        String msg = "";
        //validate everything and if not valid, return the message
        if( !this.isValidObject("Patient", this.apptIds.get("Patient"))           ||
            !this.isValidObject("Phlebotomist", this.apptIds.get("Phlebotomist")) ||
            !this.isValidObject("Physician", this.apptIds.get("Physician"))       ||
            !this.isValidObject("PSC", this.apptIds.get("PSC"))                      ){
            return "ERROR";
        }

        //boolean available = this.isValidApptDateTime(date, time);
        boolean valid = this.isValidApptDateTime();
        if(valid){
            this.setAppointment();
            return "Appointment was successfully scheduled.";
        }


        //TODO add grab next available time on this day
        /*
         *
         * if phlebotomist is free at time, check previous appt to see if same patient service
         * if NOT at same phlebotomist, check to see if have at least 30 minutes between scheduled and requested
         * check to see if that next appt slot is free
         *
         * grab next available appointment
         */
         return "";
    }


    public void setAppointment(){
        //set the object
        String d = this.apptIds.get("Date");
        String t = this.apptIds.get("Time");
        this.newAppt = new Appointment(this.apptIds.get("Patient"),java.sql.Date.valueOf(d),java.sql.Time.valueOf(t));
        //set additional parts of the
       //Phlebomotist obj this.newAppt.setPhlebid(this.apptIds.get("Phlebotomist"));
       //PSC obj this.newAppt.setPscid(this.apptIds.get("PSC"));

        //set the tests
        //need to make an AppointmentLabTest obj - new AppointmentLabTest( //PARAMS?? );
        // this.newAppt.setAppointmentLabTestCollection();
    }

    public Appointment getAppointment(){ return this.newAppt; }

    // /**
//      * validate the keys grabbed and make sure there's no empty ids ("")
//      * @param tag the tag name
//      * @param obj the object to found
//      * @return String
//      */
//     public String validateApptInfo(String tag, String obj){
//         //grab information and verify that
//         boolean valid = this.isValidObject(tag, obj);
//         if(!valid) return "ERROR";
//         return "";
//     }//end validateAppointmentInfo

    /**
     * grab the keys from the names
     * @param apptInfo <code>ArrayList<String></code>
     * @return ids <code>HashMap<String, String></code>
     */
//    public HashMap<String, String> validateGetIds(ArrayList<String> apptInfo){
//        HashMap<String, String> ids = new HashMap<String, String>();
//
//        ValidateIds vi = new ValidateIds(this.db);
//        ids.put("Patient", this.get);
//        ids.put("Phlebotomist" ,this.getPhlebIdFromName(apptInfo.get(1)));
//        ids.put("Physician", this.getPhysicianIdFromName(apptInfo.get(2)));
//       // ids.add("LabTest", this.getLabTestIdFromName(apptInfo.get(3)));
//        ids.put("PSC", this.getPscIdFromName(apptInfo.get(4)));
//        //return ids of all neccessary information whether
//        return ids;
//    }

    /**
     * checks to see if date and time is taken or not
     * @param date - the requested date
     * @param time - the requested time
     * @param paramName - the requested phlebotomist
     * @param id - the requested
     * @return
     */
    public boolean isValidApptDateTime(){
        String params = "apptdate='" +  java.sql.Date.valueOf(this.apptIds.get("Date"));
        params += "' AND appttime='" + java.sql.Time.valueOf(this.apptIds.get("Time")) + "'";
        params += " AND " + "phlebid" + "='" + this.apptIds.get("Phlebotomist") + "'";
        params += " AND " + "pscid" + "='" + this.apptIds.get("PSC") + "'";

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

   
}