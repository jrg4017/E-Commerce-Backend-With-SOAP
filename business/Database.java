package business;
import components.data.*;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.Time;

public class Database{
    IComponentsData icdDB;
/***************************************************************************************************/
/******** CONSTRUCTOR ******************************************************************************/
    public Database(){
        this.icdDB = new DB();
        this.icdDB.initialLoad("LAMS");
    }
/***************************************************************************************************/
 /******** ACCESSORS *******************************************************************************/
    /**
     *  gets the db
     * @return this.db
     */
    public IComponentsData getDB(){ return this.icdDB; }//end getDB
   /**
     * @param date
     * @return java.sql.Date
     */
    public java.sql.Date getDate(String date){
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
    public java.sql.Time getTime(String time){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            long ms = sdf.parse(time).getTime();
            java.sql.Time t = new Time(ms);
            return t;
        }catch(ParseException pe){ pe.printStackTrace(); }
        Time t2;
        return t2 = new java.sql.Time(0,0,0);
    }//end getTime
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
        List<Object> objs = this.icdDB.getData(object, param);
        //if size zero, it means the patient doesn't exist
        if(objs.size() > 0) return true;
        return false;
    }//end isValidObject()

    /**
     * add the appointment to the database and verify it's been added
     * @param appt Appointment
     * @return boolean
     */
    public boolean addApptToDB(Appointment appt){
        if(!this.icdDB.addData(appt)) return false;
        //verify that it's been added to the database
        if(this.isValidObject("Appointment", appt.getId())) return true;
        return false;
    }//end addApptToDB

    /**
     * checks to see if date and time is taken or not
     * @param date - the requested date
     * @param time - the requested time
     * @param paramName - the requested phlebotomist
     * @param id - the requested
     * @return
     */
    public boolean isValidApptDateTime(HashMap<String, String> apptIds){
        String params = "apptdate='" +  this.getDate(apptIds.get("Date"));
        params += "' AND appttime='" + this.getTime(apptIds.get("Time")) + "'";
        params += " AND " + "phlebid" + "='" + apptIds.get("Phlebotomist") + "'";
        params += " AND " + "pscid" + "='" + apptIds.get("PSC") + "'";

        List<Object> objs = this.icdDB.getData("Appointment", params);

        //if greater than 0, then means appt exists
        if(objs.size() > 0) return false;
        return true;
    }//end isValidApptDateTime

    /**
     * gets the last appointment id
     * @return String
     */
    public String getLastAppointmentId(){
        return "";
    }//end getLastAppointmentId

    public List<Object> getAppointment(String _id){
        String id = "id='" + _id + "'";
        List<Object> obj = this.icdDB.getData("Appointment", id);
        return obj;
    }
}//end Database class