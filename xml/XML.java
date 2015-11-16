package xml;

import java.util.ArrayList;

public class XML{
   private String tagName;
   private ArrayList<String> alIDs;
   private ArrayList<String> alData;
   private boolean addIDs = false;
   private boolean addInner = false;
/***********************************************************************************************/
/********* CONSTRUCTORS ************************************************************************/
/***********************************************************************************************/   
   /**
   * set the necessary information to create a tag
   * @param tagName String
   * @param alIDs ArrayList<String>
   * @param alData ArrayList<String>
   */
   public XML(String tagName, ArrayList<String> alIDs, ArrayList<String> alData){
      this.tagName = tagName;
      this.alIDs = alIDs;
      this.alData = alData;
      
      this.setAddIDs();
      this.setAddInner();
   }
   
   public XML(){}
   /**
   * a constructor for the root tag name
   * doesn't need anything else
   */
   public XML(String tagName){
     this.tagName = tagName; 
   }//end 
   
/***********************************************************************************************/
/************ MUTATORS *************************************************************************/
/***********************************************************************************************/   
   /**
   * if the size of alids is bigger than 0 
   * then allow the addition of ids
   */
   public void setAddIDs(){
      if(this.alIDs.size() != 0 ){
         this.addIDs = true;
      }
   } 
   
   /**
   * if the aldata size is greater than the alIDs size
   * set the addInner to true
   */
   public void setAddInner(){
      if( (this.alIDs.size() < this.alData.size()) && this.alData.size() > 0){
         this.addInner = true;
      }
   }//end setAddInner

/************************************************************************************************/
/*********** ACCESSORS **************************************************************************/
/************************************************************************************************/   
   /**
   * if the addIDs is ture, return with ids
   * else return ">"
   * @return String
   */
   public String getAddIDs(){ 
      if(this.addIDs){ 
         return this.addIDs(); 
       }
       return "";
   }//end getAddIDs
  
   /**
   * returns the closing tag and the inner data if any
   * @return String
   */
   public String getAddInner(){
      if(this.addInner){
         return this.addInnerData();
      }
       return ">";
   }//end getAddInner
   
   /**
   * gets the beginning tag name
   * @return String 
   */
   public String getTagNameStart(){ return "<" + this.tagName; }
   
   /**
   * gets the ending tag name
   * @return String 
   */
   public String getTagNameEnd(){ return "</" + this.tagName + ">"; }
   
/***********************************************************************************************/
/************* METHODS *************************************************************************/
/***********************************************************************************************/   
   /**
   * adds the ids inside of the tag 
   * @return xml String
   */
   private String addIDs(){
      String xml = " ";
      for(int i =0; i < alIDs.size(); i++){
         xml += this.alIDs.get(i) + "='" + this.alData.get(i) + "' ";
      }   
     return xml; 
   } //end addIDs
   
   /**
   * grabs inner data if alData is longer than alIDs
   * else just a closing tag is returned
   * @return xml String
   */
   private String addInnerData(){
      String xml = ">" + this.alData.get( this.alData.size() - 1 );   
      return xml;
   }//end innerData
   
   /**
   * gets the entire xml string with closing tag if desired
   * @param end boolean - whether to add ending tag
   * @return xml String
   */
   public String toString(boolean end){
      String xml = this.getTagNameStart() + this.getAddIDs() + this.getAddInner();
      
      if(end) xml += this.getTagNameEnd();
      
      return xml;
   }//end toString
   
}//end XML class