package business;

import components.data.IComponentsData;
import components.data.LabTest;
import components.data.PSC;
import components.data.Patient;
import components.data.Phlebotomist;
import components.data.Physician;
import components.data.Diagnosis;
import components.data.LabTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GetInfo {
/**********************************************************************************************************************/
/******** ATTRIBUTE ***************************************************************************************************/
    private IComponentsData db; //the db to grab information
/**********************************************************************************************************************/
/*********** GET OBJ METHODS ******************************************************************************************/
    /**
     * @param id the phleb id
     * @return Phlebotomist object
     */
    public Phlebotomist getPhlebotomist(String id){ return (Phlebotomist)this.getObject("Phlebotomist", "id", id); }
    /**
     * @param id the psc id
     * @return PSC object
     */
    public PSC getPSC(String id){ return (PSC)this.getObject("PSC", "id", id); }
    /**
     * @param id the physician id
     * @return Physician object
     */
    public Physician getPhysician(String id){ return (Physician)this.getObject("Physician", "id", id); }
    /**
     * @param id the patient id
     * @return Patient obj
     */
    public Patient getPatient(String id){ return (Patient)this.getObject("Patient", "id", id); }
    /**
     * @param code the diagnosis id
     * @return Diagnosis obj
     */
    public Diagnosis getDiagnosis(String code){ return (Diagnosis)this.getObject("Diagnosis", "code", code); }
    /**
     * @param id the labtest id
     * @return LabTest obj
     */
    public LabTest getLabTest(String id){ return (LabTest)this.getObject("LabTest", "id", id); }
/**********************************************************************************************************************/
/************** GET ID FROM NAME METHODS ******************************************************************************/
    /**
     * @param name the name of psc
     * @return String id / empty if doesn't exist
     */
    public String PscIdFromName(String name) {
        Object o = this.getObject("PSC", "anem", name);
        if(o != "") {
            PSC psc = (PSC)o;
            return psc.getId();
        } else return "";
    }//end PscIdFromName
    /**
     * @param name of the physician
     * @return String id / empty if doesn't exist
     */
    public String PhysicianIdFromName(String name) {
        Object o = this.getObject("Physician", "name", name);
        if(o != "") {
            Physician pn = (Physician)o;
            return pn.getId();
        } else return "";
    }//end PhysicianIdFromName
    /**
     * @param name of the phlebotomist
     * @return String id / empty if doesn't exist
     */
    protected String PhlebIdFromName(String name) {
        Object o = this.getObject("Phlebotomist", "name", name);
        if(o != "") {
            Phlebotomist p = (Phlebotomist)o;
            return p.getId();
        } else {
            return "";
        }
    }//end PhlebIdFromName
    /**
     * @param name of the patient
     * @return String id / empty if doesn't exist
     */
    public String PatientIdFromName(String name) {
        Object o = this.getObject("Patient", "name", name);
        if(o != "") {
            Patient p = (Patient)o;
            return p.getId();
        } else  return "";
    }//end PatientIdFromName

    /**
     * @param name of the LabTest
     * @return String id / empty if doesn't exist
     */
    public String LabTestIdFromName(String name) {
        Object o = this.getObject("LabTest", "name", name);
        if(o != "") {
            LabTest lt = (LabTest)o;
            return lt.getId();
        } else return "";
    }//end LabTestIdFromName
/**********************************************************************************************************************/
/******* METHODS ******************************************************************************************************/
    /**
     * gets the object from the value passed in
     * @param object the table name
     * @param paramName the parameter name
     * @param paramValue the parameter valie
     * @return
     */
    protected  Object getObject(String object, String paramName, String paramValue) {
        String param = paramName + "='" + paramValue + "\'";
        List objs = this.db.getData(object, param);
        if(objs.size() != 1) {
            return "";
        } else {
            Object rtn = null;

            Object obj;
            for(Iterator var6 = objs.iterator(); var6.hasNext(); rtn = obj) {
                obj = var6.next();
            }
            return rtn;
        }
    }//end getObject

    /**
     * grab the keys from the names
     * @param apptInfo <code>ArrayList<String></code>
     * @return ids <code>HashMap<String, String></code>
     */
    public HashMap<String, String> validateGetIds(ArrayList<String> apptInfo){
        HashMap<String, String> ids = new HashMap<String, String>();
        //return the ids in the form of a hashmap
        ids.put("Patient", this.PatientIdFromName(apptInfo.get(0)));
        ids.put("Phlebotomist" ,this.PhlebIdFromName(apptInfo.get(1)));
        ids.put("Physician", this.PhysicianIdFromName(apptInfo.get(2)));
        ids.put("PSC", this.PscIdFromName(apptInfo.get(4)));
        //return ids of all neccessary information whether
        return ids;
    }//end validateGetIds
}//end class
