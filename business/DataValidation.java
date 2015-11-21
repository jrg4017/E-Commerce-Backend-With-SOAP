package business;

import components.data.*;
import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;


public class DataValidation{
    private IComponentsData db = null;
    private HashMap<String, String> apptIds = new HashMap<String, String>();
    private HashMap<String, String> tests = new HashMap<String, String>();
    Appointment newAppt = null;
    GetInfo gi = null;

/**********************************************************************************************************************/
/******************* CONSTRUCTOR **************************************************************************************/
/**********************************************************************************************************************/
    /**
     * initalizes the db and sets it for the class
     */
    public DataValidation(){
        this.db = new DB();
        this.db.initialLoad("LAMS");
        this.gi = new GetInfo(this.db);
   }//end DataValidation
    /**
     * intializes the db and sets it for the class
     * also sets the hashmaps for apptIds and tests
     * @param apptIds
     * @param tests
     */
    public DataValidation(HashMap<String, String> apptIds, HashMap<String, String> tests){
        this.db = new DB();
        this.db.initialLoad("LAMS");
        this.setTests(tests);
        this.gi = new GetInfo(this.db);
    }//end DataValidation
/**********************************************************************************************************************/
/******************** MUTATORS  ***************************************************************************************/
/**********************************************************************************************************************/
    /**
     * sets the apptIDs so it can be validated
     * @param a <code>HashMap<String, String></code>
     */
    public void setApptIds(HashMap<String, String> a){ this.apptIds = a; }
    /**
     * sets the test id/dxcode so it can be validated
     * @param t <code>HashMap<String, String></code>
     */
    public void setTests(HashMap<String, String> t){ this.tests = t; }
/**********************************************************************************************************************/
/******************** ACCESSORS ***************************************************************************************/
/**********************************************************************************************************************/
    /**
     *  gets the db
     * @return this.db
     */
    public IComponentsData getDB(){ return this.db; }//end getDB
    /**
     * gets the new Appointment that has been validated
     * @return this.newAppt
     */
    public Appointment getNewAppt(){ return this.newAppt; }
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
     * looks at the appointment requirements to see if requested appt is open
     * and if the appointment is scheduled far enough away from the next appointment
     * @return String - error or empty(good)
     */
    public String apptRequirements(){
        //validate everyone
        if(!this.validatePeople()) return "ERROR: not valid people";

        boolean apptTime = this.isValidApptDateTime();  //looks to see if appointment is open
        boolean conflict;                               //to see if appointment request is far enough away

        //if not valid, grabs next appointment time else look to see if there's a conflict
        if(!apptTime) return this.nextAvailableAppt();
        else conflict = this.isScheduleConflict();

        if(conflict){ return this.nextAvailableAppt(); }

        //if no conflict (false) set the appointment
        this.setAppointment();
        return "";
    }//end apptRequirements

    /**
     * validate theat appointment has been added to the db
     * @return
     */
    public boolean validateAppointment(){
        return this.isValidObject("Appointment", newAppt.getId());
    }//end validateAppointment

    private String nextAvailableAppt(){
        return "";
    }

    private boolean isScheduleConflict(){
      return false;
    }

    /**
     * makses sure that the Patient, Phlebotomist, Physician and PSC are valid people
     * in the DB system
     * @return bool
     */
    private boolean validatePeople(){
        //validate everything and if not valid, return the message
        if( !this.isValidObject("Patient", this.apptIds.get("Patient"))           ||
                !this.isValidObject("Phlebotomist", this.apptIds.get("Phlebotomist")) ||
                !this.isValidObject("Physician", this.apptIds.get("Physician"))       ||
                !this.isValidObject("PSC", this.apptIds.get("PSC"))                      ){
            return false;
        }
        return true;
    }

    public void setAppointment(){
        //set the object
        String d = this.apptIds.get("Date");
        String t = this.apptIds.get("Time");

        this.newAppt = new Appointment("800",java.sql.Date.valueOf(d),java.sql.Time.valueOf(t));
        //set the patient attribute
        Patient p = this.gi.getPatient(this.apptIds.get("Patient"));
        this.newAppt.setPatientid(p);
        //set the phlebotomist attribute
        Phlebotomist ph = this.gi.getPhlebotomist(this.apptIds.get("Phlebotomist"));
        this.newAppt.setPhlebid(ph);
        //set the psc attribute
        PSC psc = this.gi.getPSC(this.apptIds.get("PSC"));
        this.newAppt.setPscid(psc);

        //set the tests
        //need to make an AppointmentLabTest obj - new AppointmentLabTest( //PARAMS?? );
        // this.newAppt.setAppointmentLabTestCollection();
    }





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



//   validPhysician         - The patient’s physician is valid and exists in the system
//   validLabTest           - The ordered lab test is valid and exists in the system/
//                          -  It takes phlebotomist 30 minutes to get from one PSC to another & be ready for an appointment
//   validPhlebotomist      - The requested clinicalian (phlebotomist) is valid and exists in the syste
//   appointmentRegistered  - The Appointment has been registered in the system once complete addAppointment process
//   isAppointmentAvailable - checks to see if patient requested appointment is available
//   appointmentParameters  -  see if appt parameters are valid, duration is 15 minutes, made from 8am to 5pm



}