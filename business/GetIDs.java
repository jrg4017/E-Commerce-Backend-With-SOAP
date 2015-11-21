//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package business;

import components.data.IComponentsData;
import components.data.LabTest;
import components.data.PSC;
import components.data.Patient;
import components.data.Phlebotomist;
import components.data.Physician;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetIDs {
    private ArrayList<String> objectIds = new ArrayList();
    private IComponentsData db;

    public GetIDs(IComponentsData db) {
        this.db = db;
    }

    protected String getPhlebIdFromName(String name) {
        Object o = this.getObject("Phlebotomist", name);
        if(o != "") {
            Phlebotomist p = (Phlebotomist)o;
            return p.getId();
        } else {
            return "";
        }
    }

    protected String getPscIdFromName(String name) {
        Object o = this.getObject("PSC", name);
        if(o != "") {
            PSC psc = (PSC)o;
            return psc.getId();
        } else {
            return "";
        }
    }

    protected String getPhysicianIdFromName(String name) {
        Object o = this.getObject("Physician", name);
        if(o != "") {
            Physician pn = (Physician)o;
            return pn.getId();
        } else {
            return "";
        }
    }

    protected String getPatientIdFromName(String name) {
        Object o = this.getObject("Patient", name);
        if(o != "") {
            Patient p = (Patient)o;
            return p.getId();
        } else {
            return "";
        }
    }

    protected String getLabTestIdFromName(String name) {
        Object o = this.getObject("LabTest", name);
        if(o != "") {
            LabTest lt = (LabTest)o;
            return lt.getId();
        } else {
            return "";
        }
    }

    public Object getObject(String object, String paramValue) {
        String param = "name=\'" + paramValue + "\'";
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
    }
}
