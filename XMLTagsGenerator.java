package xml;

import java.util.ArrayList;

public class XMLTagsGenerator{
   private String root;
   
   private final String START_TAG = "<?xml version='1.0' encoding='UTF-8' standalone='no' ?>";
    
   /**
   * default constructor
   */
   public XMLTagsGenerator(){}
   
   
/**********************************************************************************/
/********* MUTATORS ***************************************************************/
/**********************************************************************************/   
   /**
   * set the root string for xml string return
   * @param root String
   */
   public void setRoot(String root){ this.root = root; }
   
   /**
   * sets the arrayList of Tags
   *
   */
   
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
   * gets the full xml string and returns it
   * @return xml String
   */
   public String toString(){
      String xml = this.getStartTag() + "<" + this.getRoot() + ">";
      
      //other strings here
      
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
      XMLTagsGenerator xtg = new XMLTagsGenerator();
      xtg.setRoot("AppointmentList");
      System.out.println(xtg.toString());
   }
}