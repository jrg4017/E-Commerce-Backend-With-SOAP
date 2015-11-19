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
    HashMap<String, String> apptInfo = new HashMap<String, String>();
    /**
     * sets the xml string for parsing
     * @param xml
     */
    public ParseXML(String xml){
        this.xml = xml;
        try{
         this.parseXmlString();
        }catch(Exception e){}
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
    }

    /**
     *
     */
    private void parseXmlString() throws Exception {
        Document doc = getDocumentObject();

        NodeList apptNodes = doc.getElementsByTagName("appointment");
        for(int i = 0; i < apptNodes.getLength(); i++){
            this.element = (Element) apptNodes.item(i);

            this.getNodeItem(0, "date", "Date");
            this.getNodeItem(1, "time", "Time");
            this.getNodeItem(2, "patientId", "Patient");
            this.getNodeItem(element);

            NodeList
        }


//        //grab each element and respective elements
//        NodeList appt = doc.getElementsByTagName("appointment");
//        Element date = (Element) appt.item(0);
//        Element time = (Element) appt.item(1);
//        Element patientID = (Element) appt.item(2);
//        Element physicianId = (Element) appt.item(3);
//        Element pscId = (Element) appt.item(4);
//        Element phlebotomistId = (Element) appt.item(5);

        NodeList lt_node = doc.getElementsByTagName("labTest");
        //get all the lab tests and print
        ArrayList<Element> alLT = new ArrayList<Element>();
        for(int i = 0; i < lt_node.getLength(); i++){
            Element lt = (Element)lt_node.item(i);
            alLT.add(lt);
        }

        this.addToHashMap("Patient", this.elementToString(patientID));
        this.addToHashMap("Appttime", this.elementToString(time));
        this.addToHashMap("Apptdate", this.elementToString(date));
        this.addToHashMap("Physician", this.elementToString(physicianId));
        this.addToHashMap("PSC", this.elementToString(pscId));
        this.addToHashMap("Phlebotomist", this.elementToString(phlebotomistId));

    }

    public void getNodeItem(Element e, String tagName, int n, String key){
        NodeList nl = e.getElementsByTagName(tagName);
        Element line = (Element)nl.item(n);
        this.addToHashMap(key, this.elementToString(line));
    }


    private String elementToString(Element e){
        //get the data and return it as a string
        Node child = e.getFirstChild();
        if(child instanceof CharacterData){
            CharacterData cd = (CharacterData)child;
            System.out.println(cd.getData());
            return cd.getData();
        }
        return "";
    }

}