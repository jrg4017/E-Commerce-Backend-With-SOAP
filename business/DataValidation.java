package business;

import components.data.*;
import java.util.*;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DataValidation{
/**********************************************************************************************************************/
/******** ATTRIBUTES **************************************************************************************************/
    private HashMap<String, String> apptIds = new HashMap<String, String>();
    private ArrayList<String> tests = new ArrayList<String>();
    Appointment newAppt = null;
    GetInfo gi = null;
    Database db = null;
/**********************************************************************************************************************/
/******************* CONSTRUCTOR **************************************************************************************/
    /**
     * initalizes the getinfo class
     */
    public DataValidation(){
        this.gi = new GetInfo();
   }//end DataValidation
    /**
     * intializes the getinfo class
     * also sets the hashmaps for apptIds and tests
     * @param apptIds
     * @param tests
     */
    public DataValidation(HashMap<String, String> apptIds, ArrayList<String> tests){
        this.setApptIds(apptIds);
        this.setTests(tests);
        this.gi = new GetInfo();
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
    /**
     * @param date
     * @return java.sql.Date
     */
    private java.sql.Date getDate(String date){
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = format.parse(date);
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            return sql;
        }catch(ParseException pe){ pe.printStackTrace(); }
        java.sql.Date date2 = new java.sql.Date(0,0,0);
        return date2;
    }//end getDate
    /**
     * @param time
     * @return java.sql.Time
     */
    private java.sql.Time getTime(String time){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            long ms = sdf.parse(time).getTime();
            java.sql.Time t = new Time(ms);
            return t;
        }catch(ParseException pe){ pe.printStackTrace(); }
        Time t2;
        return t2 = new java.sql.Time(0,0,0);
    }//end getTime
/********************** TEST TEST TEST TEST ***************************************************************************/
   public static void main(String[] args){
      //boolean b = v.appointmentReequirements("2004-02-01", "11:00:00");
      //System.out.println(b);
       HashMap<String, String> ai = new HashMap<String, String>();
       ai.put("Date", "2010-09-14");
       ai.put("Time", "10:00:00");
       ai.put("Physician", "20");
       ai.put("Phlebotomist", "110");
       ai.put("Patient", "210");
       ai.put("PSC", "500");

       ArrayList<String> tests = new ArrayList<String>();
       tests.add("82088");
       tests.add("290.0");
       tests.add("86900");
       tests.add("292.9");

       DataValidation v = new DataValidation(ai, tests);
       System.out.println(v.apptRequirements());
   }//end main
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

        boolean apptTime = this.isValidApptDateTime();  //looks to see if appointment is open
        //boolean conflict;                               //to see if appointment request is far enough away

        //if not valid, grabs next appointment time else look to see if there's a conflict
        if(!apptTime) return this.nextAvailableAppt();
       // else conflict = this.isScheduleConflict();

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
        return db.isValidObject("Appointment", newAppt.getId());
    }//end validateAppointment

    private String nextAvailableAppt(){
        return "next avail";
    }

    private boolean isScheduleConflict(){
        //get phlebotomist and psc objects to check conflicts
        // Phlebotomist p = this.newAppt.getPhlebid();
//         PSC psc = this.newAppt.getPscid();
// 
//         List<Appointment> pl = p.getAppointmentCollection();
//         for(Appointment appt : ){
//             if(appt.getDate() == java.sql.Date.valueOf(this.apptIds.get("Date"))){
//                 this.compareDateTime(appt);
//                 //TODO compare time
//             }
//         }

      return false;
    }

    private boolean compareDateTime(Appointment pa){
//         Time ptime = new Time(pa.getTime());
//         Time newtime = new Time(this.apptIds.get("Time"));
// 
//         long c = ptime.getTime() - newtime.getTime();
//         Time diff = new Time(c);

      return false;
    }

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
                return false;
        }
        return true;
    }//end validatePeople

    /**
     * sets the new Appointment object
     */
    public void setAppointment(){
        java.sql.Date d =this.getDate(this.apptIds.get("Date")); //get date
        java.sql.Time t = this.getTime(this.apptIds.get("Time")); //get time
        //create a new appointment object
        this.newAppt = new Appointment("",d, t);
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
        this.setAppointmentLabTests();
    }//end setAppointment

    private getLastId(){

    }
    /**
     * set the appointment's labtests
     */
    public void setAppointmentLabTests(){
        List<AppointmentLabTest> list = new ArrayList<AppointmentLabTest>(); //create a new list
        for(int i=0; i < list.size(); i++) {
            //create an appointment object with necessary information
            AppointmentLabTest test = new AppointmentLabTest(this.newAppt.getId(), this.tests.get(i), this.tests.get(i + 1));
            //set the diagnosis and lab tests after getting the object
            test.setDiagnosis(this.gi.getDiagnosis(this.tests.get(i + 1)));
            test.setLabTest(this.gi.getLabTest(this.tests.get(i)));
            list.add(test);//add to the list of objects
            i++; //to skip next set
        }
        this.newAppt.setAppointmentLabTestCollection(list); //set the labTestCollection
    }//end setAppointmentLabTest



}//end class