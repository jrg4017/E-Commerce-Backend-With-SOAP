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
     */
    private void parseXmlString() throws Exception {
        //set up a way to grab the XML string rather than XML file
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(xml));

        Document doc = builder.parse(inputSource);
    }

}