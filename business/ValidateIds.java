package business;

import components.data.*;
import java.util.*;

/**
 * for later if want to grab ids from the name submitted by the
 * website and get the list of keys
 */
public class ValidateIds{
    private ArrayList<String> objectIds = new ArrayList<String>();
    private IComponentsData db;

    /**
     * sets the db
     * @param db
     */
    public ValidateIds(IComponentsData db){ this.db = db; }
/**********************************************************************************************************************/
/**********************************************************************************************************************/
/**********************************************************************************************************************/
/*************** GET ID FROM NAME *************************************************************************************/
/**********************************************************************************************************************/
    /**
     * gets the phleb id from the obj the name
     * @param name
     * @return
     */
    protected String getPhlebIdFromName(String name){
        Object o = this.getObject("Phlebotomist", name);
        if(o != "" ){
          Phlebotomist p = (Phlebotomist)o;
          return p.getId();
         }
         return "";
    }

    /**
     * gets the psc id from the obj the name
     * @param name
     * @return
     */
    protected String getPscIdFromName(String name){
        Object o = this.getObject("PSC", name);
        if(o != "" ){
          PSC psc = (PSC)o;
          return psc.getId();
         }
         return "";
    }
    
    protected String getPhysicianIdFromName(String name){
       Object o = this.getObject("Physician", name);
           if(o != "" ){
             Physician pn = (Physician)o;
             return pn.getId();
            }
            return "";
    }

    /**
     * gets the patient id from the obj using the name
     * @param name
     * @return
     */
    protected String getPatientIdFromName(String name){
        Object o = this.getObject("Patient", name);
        if(o != "" ){
          Patient p = (Patient)o;
          return p.getId();
         }
         return "";
    }

    protected String getLabTestIdFromName(String name){
       Object o = this.getObject("LabTest", name);
        if(o != "" ){
          LabTest lt = (LabTest)o;
          return lt.getId();
         }
         return "";

    }



    /**
     * gets the object with associated params
     * @param object
     * @param paramValue
     * @return
     */
    public Object getObject(String object, String paramValue){
        String param = "name='" + paramValue + "'";
        List<Object> objs = this.db.getData(object, param);
        if(objs.size() != 1) return ""; //means no object or too many for the id
        //looks at the object retreived and retutns it
        Object rtn = null;
        for(Object obj : objs) {  rtn = obj; }
        return rtn;
    }//end getObjId

}//end class