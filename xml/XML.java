package xml;

import java.util.ArrayList;
import components.data.Appointment;
import components.data.Patient;
import components.data.Phlebotomist;
import components.data.PSC;
import components.data.LabTest;
import components.data.AppointmentLabTest;
import components.data.Diagnosis;
import java.util.List;

/**
 * deals with generating the XML tags for the data input
 */
public class XML{
    public XML(){}
    public XML(Appointment appt){
        this.appt = appt;
    }
/**********************************************************************************************************************/
/********** ATTRIBUTE *************************************************************************************************/
   private final String START_TAG = "<?xml version='1.0' encoding='UTF-8' standalone='no' ?>";
   private Appointment appt;
/**********************************************************************************************************************/
/************ MUTATOR *************************************************************************************************/
   public void setAppointment(Appointment appt){ this.appt = appt; }//end setAppointmnet
/**********************************************************************************************************************/
/*********** ACCESSORS ************************************************************************************************/
   public String getTagNameStart(){ return "<AppointmentList>"; } //end getTagNameStart
   public String getTagNameEnd(){ return "</AppointmentList>"; } //end getTagNameEnd
   public String getStartTag(){ return START_TAG; } //end getStartTad
/**********************************************************************************************************************/
/************* METHODS ************************************************************************************************/
    /**
     * gets the appointment String from the corresponding elements
     * Appointment obj, Patient obj, Phlebotomist obj, PSC obj, LabTest <code>List<obj></code>
     * @return xml String
     */
   public String appointmentXML(){
      String xml = this.getAppointmentInfo() + this.getPatientInfo();
      xml += this.getPhlebInfo() + this.getPscInfo() + this.getLabTests();
      xml += "</appointment>";
      return xml;
   }//end appointmentXML

   /**
    * gets the xml string from appointment object
    * @return xml String
     */
   private String getAppointmentInfo(){
      String xml = "<appointment date='" + appt.getApptdate() + "' id='" + appt.getId() + "' ";
      xml += "time='" + appt.getAppttime() + "'>";
      return xml;
   }//end getAppointment Info

   /**
    * gets the patient info and returns it in xml string
    * @return xml String
     */
   private String getPatientInfo(){
      Patient patient = this.appt.getPatientid();
      String xml = "<patient id='" + patient.getId();
      xml += "'><name>" + patient.getName() + "</name><address>" + patient.getAddress() + "</address>";
      xml += "<insurance>"+ patient.getInsurance() +"</insurance><dob>" + patient.getDateofbirth() + "</dob></patient>";
      return xml;
   }//end getPatientInfo

   /**
    * gets the phlebotomist object information and returns it in xml string
    * @return xml String
     */
   private String getPhlebInfo(){
      Phlebotomist phlebotomist = this.appt.getPhlebid();
      String xml = "<phlebotomist id='" + phlebotomist.getId() + "'><name>" + phlebotomist.getName() + "</name>";
      xml += "</phlebotomist>";
      return xml;
   }//end getPhlebInfo

   /**
    * gets the psc object info and returns it in xml string
    * @return xml String
     */
   private String getPscInfo(){
      PSC psc = this.appt.getPscid();
      String xml = "<psc id='" + psc.getId() + "'><name>" + psc.getName() + "</name></psc>";
      return xml;
   }//end getPscInfp

    /**
     * gets the lab test objects and return it in xml string
     * @return xml String
     */
   private String getLabTests(){
      List<AppointmentLabTest> alt = this.appt.getAppointmentLabTestCollection();
      String xml = "<allLabTests>";
       //goes through the List and adds neccessary string
      for(AppointmentLabTest a: alt){
         Diagnosis diagnosis = a.getDiagnosis();
         LabTest labTest = a.getLabTest();
         xml += "<appointmentLabTest apptId='" + this.appt.getId() + "' dxcode='" + diagnosis.getCode() ;
         xml += "' labTestId='" + labTest.getId() + "'/>";
      }
       xml += "</allLabTests>";
      return xml;
   }//end getLabTests

   /**
    * returns the error string
    * @return String
    */
   public String error(){
      return START_TAG + "<AppoinmentList><error>ERROR: Appointment is not available</error></AppointmentList>";
   }//end error
}//end XML class