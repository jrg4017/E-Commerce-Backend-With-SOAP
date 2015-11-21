package business;

public class Database{
    IComponentsData icdDB;
/***************************************************************************************************/
/******** CONSTRUCTOR ******************************************************************************/
    public Database(){
        this.icdDb = new DB();
        this.icdDB.initialLoad("LAMS");
    }
/***************************************************************************************************/
 /******** ACCESSORS *******************************************************************************/
    /**
     *  gets the db
     * @return this.db
     */
    public IComponentsData getDB(){ return this.icdDb; }//end getDB

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
        if(this.isValidObject("Appointment", this.newAppt.getId()))Sytem.out.println(true)) return true;
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
    public boolean isValidApptDateTime(){
        String params = "apptdate='" +  this.getDate(this.apptIds.get("Date"));
        params += "' AND appttime='" + this.getTime(this.apptIds.get("Time")) + "'";
        params += " AND " + "phlebid" + "='" + this.apptIds.get("Phlebotomist") + "'";
        params += " AND " + "pscid" + "='" + this.apptIds.get("PSC") + "'";

        List<Object> objs = this.icdDB.getData("Appointment", params);

        //if greater than 0, then means appt exists
        if(objs.size() > 0) return false;
        return true;
    }//end isValidApptDateTime

}//end