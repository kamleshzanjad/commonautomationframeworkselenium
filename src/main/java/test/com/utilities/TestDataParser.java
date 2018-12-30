package test.com.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import test.com.framework.FrameworkCommon.FileTypes;
import test.com.framework.FrameworkCommon.ModuleName;

public class TestDataParser {
	private String TestCaseFilePath=null;
	private HashMap<String, String> values= new HashMap<String, String>();
	private ArrayList<String> list= new ArrayList<String>();
	
	public HashMap<String,String> setTestCaseFileProduct(ModuleName moduleName, FileTypes fileType, String testCaseName)
	throws Exception{
		HashMap<String, String> testInput=null;
		try{
			TestCaseFilePath= ReadConfigData.getPropertyValue("TEST_CASE_FILE");
			TestCaseFilePath=TestCaseFilePath
					.replace("MODULE", String.valueOf(moduleName))
					.replace("TYPE", String.valueOf(fileType));
			switch(fileType){
			case xml:
				testInput= getKeyValuesFromXml(testCaseName);
				break;
			/*case xlsx:
				testInput= getKeyValuesFromExcel(testCaseName);
				break;*/
			 default: 
				 TestCaseFilePath=null;		 
				break;
			}			
			
			return testInput;
			
		}catch(IOException e){
			TestLogger.error("ParseConfiguration Exception in method TestDataParser.setTestCaseFileProduct  "+ e.getMessage());
			throw new IOException ("ParseConfiguration Exception in method TestDataParser.setTestCaseFileProduct  "+ e.getMessage());
		}catch(Exception e){
			TestLogger.error(" Exception in method TestDataParser.setTestCaseFileProduct  "+ e.getMessage());
			throw new IOException (" Exception in method TestDataParser.setTestCaseFileProduct  "+ e.getMessage());
		}
	}

	
	private HashMap<String, String> getKeyValuesFromXml(String testCaseName) throws Exception{
		TestLogger.info("Inside Method -TestDataParser.getKeyValuesFromXml " );
		String s[];
		s=testCaseName.split("_");
		String testId=s[1];
		TestLogger.info("TCid" +testId);
		File testCaseFile;
		DocumentBuilderFactory docBuildFactory;
		DocumentBuilder docBuilder;
		Document xmlDocument;
		String elementValue=null;
		String tagValue=null;
		
		try{
			testCaseFile=new File(TestCaseFilePath);
			docBuildFactory=DocumentBuilderFactory.newInstance();
			docBuilder=docBuildFactory.newDocumentBuilder();
			
			xmlDocument=docBuilder.parse(testCaseFile);
			xmlDocument.getDocumentElement().normalize();
			NodeList nList= xmlDocument.getElementsByTagName("TestCase");
			Boolean var =true;
			int temp=0;
			Node nNode= null;
			Element element =null;
			
			while(var){
				nNode= nList.item(temp);
				if(nNode.getNodeType()==Node.ELEMENT_NODE){
					element =(Element) nNode;
					if(element.getAttribute("id").equals(testId)){
						var=false;
					}else{
						temp++;
					}
				}
			}
			nList= element.getElementsByTagName("*");
			for(int i=0; i<nList.getLength(); i++){
				element= (Element) nList.item(i);
				if(element.getNodeType()==Node.ELEMENT_NODE){
					tagValue=element.getTagName();
					elementValue=element.getTextContent();
					values.put(tagValue, elementValue);
				}
			}
			
			return values;
			
			
		}catch(ParserConfigurationException pe){
			TestLogger.error(" ParserConfigurationException in method TestDataParser.getKeyValuesFromXml  "+ pe.getMessage());
			throw new IOException (" ParserConfigurationException in method TestDataParser.getKeyValuesFromXml  "+ pe.getMessage());
			
		}catch(IOException ioe){
			TestLogger.error(" IOException in method TestDataParser.getKeyValuesFromXml  "+ ioe.getMessage());
			throw new IOException (" IOException in method TestDataParser.getKeyValuesFromXml  "+ ioe.getMessage());
			
		}catch(SAXException se){
			TestLogger.error(" SAXException in method TestDataParser.getKeyValuesFromXml  "+ se.getMessage());
			throw new IOException (" SAXException in method TestDataParser.getKeyValuesFromXml  "+ se.getMessage());
			
		}catch(NullPointerException ne){
			TestLogger.error(" NullPointerException in method TestDataParser.getKeyValuesFromXml  "+ ne.getMessage());
			throw new IOException (" NullPointerException in method TestDataParser.getKeyValuesFromXml  "+ ne.getMessage());
			
		}		catch(Exception e){
			TestLogger.error(" Exception in method TestDataParser.getKeyValuesFromXml  "+ e.getMessage());
			throw new IOException (" Exception in method TestDataParser.getKeyValuesFromXml  "+ e.getMessage());
		}finally{
			if(elementValue==null){
				TestLogger.error(" XML Input is not  correct for Test case id "+ testId);
			}
		}
		
		
		
		
	}
	
}
