//<?xml version="1.0" encoding="utf-8" standalone="no"?>
//<appointment>
//<date>2016-12-30
//<time>10:05
//<patientId>220</patientId>
//<physicianId>20</physicianId>
//<pscId>520</pscId>
//<phlebotomistId>110</phlebotomistId>
//<labTests>
//<test id="86900" dxcode="292.9" />
//<test id="86609" dxcode="307.3" />
//</labTests>

package xml;

import java.util.*;
import javax.xml.*;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.*;
import org.w3c.dom.NodeList;
import java.io.*;

public class ParseXML{
    String xml = "";
    Element element = null;
    HashMap<String, String> apptInfo = new HashMap<String, String>();
    /**
     * sets the xml string for parsing
     * @param xml
     */
    public ParseXML(String xml){
        this.xml = xml;
        try{
         this.parseXmlString();
        }catch(Exception e){ e.printStackTrace();}
    }//end parse XML
    
    public static void main(String[] args){
      String x = "<?xml version='1.0' encoding='utf-8' standalone='no'?><appointment>";
      x += "<date>2016-12-30</date><time>10:05</time><patientId>220</patientId><physicianId>20</physicianId>";
      x += "<pscId>520</pscId><phlebotomistId>110</phlebotomistId><labTests>";
      x += "<test id='86900' dxcode='292.9' /><test id='86609' dxcode='307.3' /></labTests></appointment>";
      
      ParseXML px = new ParseXML(x);
    }

    /**
     * add key, value to the apptInfo HashMAp
     * @param key
     * @param value
     */
    private void addToHashMap(String key, String value){
        this.apptInfo.put(key, value);
    }//end addToHashMap

    /**
     * returns the hashmap with all information
     * @return this.apptInfo
     */
    public HashMap<String, String> getApptInfo(){ return this.apptInfo; }

    /**
     *
     * @return
     * @throws Exception
     */
    private Document getDocumentObject() throws Exception{
        //set up a way to grab the XML string rather than XML file
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(this.xml));

        return builder.parse(inputSource);
    }//edn getDocument object

    /**
     * parses through the xml string and grabs the information passed in
     * @throws Exception
     */
    private void parseXmlString() throws Exception {
        Document doc = getDocumentObject(); //return the doc object of the XML String

        //get the nodes under appointment
        NodeList apptNodes = doc.getElementsByTagName("appointment");
        //can get data for multiple appointment objects, but not neccssary
        for(int i = 0; i < apptNodes.getLength(); i++){
            this.element = (Element) apptNodes.item(i); //set the main element
            //get each corresponding item in the xml list
            this.addNodeItem("date", "Date");
            this.addNodeItem("time", "Time");
            this.addNodeItem("patientId", "Patient");
            this.addNodeItem("physicianId", "Physician");
            this.addNodeItem("pscId", "PSC");
            this.addNodeItem("phlebotomistId", "Phlebotomist");
        }//end for loop
         System.out.println("outside");
        addLabTest(doc);

    }

    /**
     * gets the String value for each node and inserts it into the HashMap
     * @param tagName
     * @param key
     */
    public void addNodeItem(String tagName, String key){
        NodeList nl = this.element.getElementsByTagName(tagName);
        Element line = (Element)nl.item(0);
        this.addToHashMap(key, this.elementToString(line));
    }//end addNodeItem

    public void addLabTest(Document doc){
    
    //TODO grab lab test item; test nodes have inner xml data, need to grab those too
        ArrayList<String> labTests = new ArrayList<String>();
        NodeList nl = doc.getElementsByTagName("labTests");
        Element e = (Element)nl.item(0);
        for(int i = 0; i < nl.getLength(); i++){
            NodeList nl2 = e.getElementsByTagName("test");
            Element line = (Element)nl2.item(0);
            labTests.add(this.elementToString(line));
            line = (Element)nl2.item(1);
            labTests.add(this.elementToString(line));
            
            System.out.println( this.elementToString(line));
        }
    }//end addLabTest

    /**
     * acutally conversion of the nodelist's child to a string
     * @param e - the passed in element from addNodeItem
     * @return String
     */
    private String elementToString(Element e){
        //get the data and return it as a string
        Node child = e.getFirstChild();
        if(child instanceof CharacterData){ //converts to string here
            CharacterData cd = (CharacterData)child;
            return cd.getData();
        }
        return "";
    }//end elementToString

}//end class