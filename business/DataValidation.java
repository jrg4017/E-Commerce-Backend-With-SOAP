package business;

import components.data.*;
import java.util.*;
import java.sql.Date;
import java.sql.Time;
import java.text.*;

/**
 * deals with the data validation for the appointment service
 * @author Julianna Gabler
 */
public class DataValidation{
/**********************************************************************************************************************/
/******** ATTRIBUTES **************************************************************************************************/
    private HashMap<String, String> apptIds = new HashMap<String, String>();
    private ArrayList<String> tests = new ArrayList<String>();
    Appointment newAppt = null;
    Database db = new Database();
/**********************************************************************************************************************/
/******************* CONSTRUCTOR **************************************************************************************/
    /**
     * initalizes the getinfo class
     */
    public DataValidation(){}//end DataValidation
    /**
     * intializes the getinfo class
     * also sets the hashmaps for apptIds and tests
     * @param apptIds
     * @param tests
     */
    public DataValidation(HashMap<String, String> apptIds, ArrayList<String> tests){
        this.setApptIds(apptIds);
        this.setTests(tests);
    }//end DataValidation
/**********************************************************************************************************************/
/******************** MUTATORS  ***************************************************************************************/
    /**
     * sets the apptIDs so it can be validated
     * @param a <code>HashMap<String, String></code>
     */
    public void setApptIds(HashMap<String, String> a){ this.apptIds = a; }
    /**
     * sets the test id/dxcode so it can be validated
     * @param t <code>HashMap<String, String></code>
     */
    public void setTests(ArrayList<String> t){ this.tests = t; }

/**********************************************************************************************************************/
/******************** ACCESSORS ***************************************************************************************/
    /**
     * gets the new Appointment that has been validated
     * @return this.newAppt
     */
    public Appointment getNewAppt(){ return this.newAppt; }
/**********************************************************************************************************************/
/*************** METHODS **********************************************************************************************/
    /**
     * looks at the appointment requirements to see if requested appt is open
     * and if the appointment is scheduled far enough away from the next appointment
     * @return String - error or empty(good)
     */
    public String apptRequirements(){
        //validate everyone
        if(!this.validatePeople()) return "ERROR: not valid people";

        boolean apptTime = this.db.isValidApptDateTime(this.apptIds);  //looks to see if appointment is open
        boolean conflict;                               //to see if appointment request is far enough away

        //if not valid, grabs next appointment time else look to see if there's a conflict
        if(!apptTime) return this.nextAvailableAppt();
        else conflict = this.isScheduleConflict();

        // if(conflict){ return this.nextAvailableAppt(); }//if conflict, return the nextAvailable appointment

        //if no conflict (false) set the appointment
        
        // /this.setAppointment();
        return "appt";
    }//end apptRequirements

    /**
     * validate theat appointment has been added to the db
     * @return
     */
    public boolean validateAppointment(){
        return this.db.isValidObject("Appointment", newAppt.getId());
    }//end validateAppointment

    private String nextAvailableAppt(){
        return "next avail";
    }

    /**
     * checks to see if there is a schedule conflict
     * @return boolean
     */
    private boolean isScheduleConflict(){
       boolean temp = true;
        //get phlebotomist and psc objects to check conflicts
         Phlebotomist apptPhleb = (Phlebotomist) this.db.getObject("Phlebotomist", this.apptIds.get("Phlebotomist"));
         PSC apptPscId = (PSC) this.db.getObject("PSC", this.apptIds.get("PSC"));
// 
        List<Appointment> phlebAppts = apptPhleb.getAppointmentCollection();
        for(Appointment a: phlebAppts){
            //if the date is the same, calculate time difference
            if(a.getApptdate() == this.db.getDate(this.apptIds.get("Date"))) {
                temp = this.calculateTime(a);
            }
            //if a false is ever returned, means that there is a conflict
            if(temp == false) return true; //true means IS CONFLICT
        }
      return false; //means NO CONLICT 
    }//end isScheduleConflict

    /**
     * calculates to make sure at least 15 / 30 minutes apart from the next appointment
     * @param a - the appointment being looked at
     * @return boolean
     */
    private boolean calculateTime(Appointment a){
        long diffRequired = 15;
        //if they don't match make sure the minute difference is 30 instead
        if(a.getPhlebid().toString() != this.apptIds.get("Phlebotomist")) diffRequired = 30;

        java.sql.Time phlebTime = a.getAppttime();
        java.sql.Time apptTime = db.getTime(this.apptIds.get("Time"));

        //get the difference
        long diff = phlebTime.getTime() - apptTime.getTime();
        long diffMinutes = diff / (60*1000) % 60;
        System.out.println(diffRequired + "::" + diffMinutes);
        //doesn't matter if -15 or +15 (must be atleast 15 minutes between appt
        if(diffMinutes >= Math.abs(diffRequired))  return true;
        return false;
    }//end caluclate

    /**
     * makses sure that the Patient, Phlebotomist, Physician and PSC are valid people
     * in the DB system
     * @return bool
     */
    private boolean validatePeople(){
        //validate everything and if not valid, return the message
         if( !this.db.isValidObject("Patient", this.apptIds.get("Patient") ) ||
            !this.db.isValidObject("Phlebotomist", this.apptIds.get("Phlebotomist") ) ||
            !this.db.isValidObject("Physician", this.apptIds.get("Physician") ) ||
            !this.db.isValidObject("PSC", this.apptIds.get("PSC") ) ){
            System.out.println("false");    
                return false;
        }
        return true;
    }//end validatePeople
}//end class