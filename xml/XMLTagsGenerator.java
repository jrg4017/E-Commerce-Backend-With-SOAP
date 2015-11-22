package xml;

import java.util.ArrayList;

public class XMLTagsGenerator{
   private String root;
   private ArrayList<XML> alXml;
   private final String START_TAG = "<?xml version='1.0' encoding='UTF-8' standalone='no' ?>";

   /**
   * gets the list of required XML objects generated
   * @param root String
   * @param alXml ArrayList<XML>
   */
   public XMLTagsGenerator(String root){
      this.root = root;
   }//end XMLTagsGenerator

   public XMLTagsGenerator(){}

/**********************************************************************************/
/********* MUTATORS ***************************************************************/
/**********************************************************************************/   
   /**
   * set the root string for xml string return
   * @param root String
   */
   public void setRoot(String root){ this.root = root; }
   
   public void setObject(Appointment appt){ this.Appointment = appt; }
   
/**********************************************************************************/
/********* ACCESSORS **************************************************************/
/**********************************************************************************/
   /**
   * gets the root string for xml string retrun
   * @return this.root String
   */
   public String getRoot(){ return this.root; }
   
   /**
   * gets the start tag for the xml print out
   * @return this.START_TAG String
   */
   protected String getStartTag(){ return this.START_TAG; }

/**********************************************************************************/
/********* METHODS ****************************************************************/
/**********************************************************************************/
    /**
     * returns the error string
     * @return String
     */
    public String error(){
      return this.getStartTag() + "<AppoinmentLis><error>ERROR: Appointment is not available</error></AppointmentList>";
   }//end error

   /**
   * gets the full xml string and returns it
   * @return xml String
   */
   public String toString(){
      String xml = this.getStartTag() + "<" + this.getRoot() + ">";
      XML x = new XML();
      for(int i = 0; i < this.alXml.size(); i++){
         x = alXml.get(i);
         xml += x.toString(true);
      }     
      xml += "</" + this.getRoot() + ">";
      return xml;
   }//end toString
/**********************************************************************************/
/**** TEST TEST TEST TEST TEST REMOVE *********************************************/   
  /**
  * for testing purpose
  * @param args String[]
  */ 
   public static void main(String[] args){
      ArrayList<String> id = new ArrayList<String>();
      ArrayList<String> data = new ArrayList<String>();
      
     
      
      XML xml = new XML("Appointment", id, data);
      id.add("id");
      data.add("2233433");
      id.add("apptDate");
      data.add("11/23/15");
      data.add("Jessie Oranges");
      XML xl = new XML("Patient", id, data);
      XML m = new XML("LabTest", id, data);
      
      ArrayList<XML> x = new ArrayList<XML>();
      x.add(xml);
      x.add(xl);
      x.add(m);
      XMLTagsGenerator xtg = new XMLTagsGenerator("AppoinmentList", x);
      //xtg.setRoot("AppointmentList");
      System.out.println(xtg.toString());
   }
}