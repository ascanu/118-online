package com.etsy.Tests;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.io.*;

import org.apache.commons.collections4.CollectionUtils;
import org.w3c.dom.*;

import javax.xml.parsers.*;

import org.xml.sax.*;

public class XMLPArse {

	public static void main(String[] args) throws SAXException, Exception {
		File sourceFile = new File("./src/main/java/com/etsy/qa/testdata/source/Input.xml");
		processAttributesForIds(sourceFile);
	}

	private static void processAttributesForIds(File sourceFile) 
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document sourcedoc = dBuilder.parse(sourceFile);
	
		Element sourceRoot = sourcedoc.getDocumentElement();
		System.out.println("Source RootNodeName:" + sourceRoot.getNodeName());
		
		List<AttDataSet> attDataSet = new ArrayList<AttDataSet>();
	
		printAttributesInfomation(sourceRoot.getChildNodes(),attDataSet);
		
		return attDataSet;
		
		
		  for(AttDataSet attribute : attDataSet) { 
			  String nodeName = attribute.getNodeName(); 
		  String attributeName = attribute.getAttributeName();
		  String attributeValue = attribute.getAttributeValue(); 
		  String path = attribute.getPath(); 
		  
		  System.out.println(nodeName);
		  System.out.println(attributeName);
		  System.out.println(attributeValue);
		  System.out.println(path);
		  }

	}
	
	private static void printAttributesInfomation(NodeList nodelist, List<AttDataSet> attDataSet) {
		for (int count = 0; count < nodelist.getLength(); count++) {
			Node tempNode = nodelist.item(count);
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				// System.out.println("Node Name = " + tempNode.getNodeName() );
				if (tempNode.hasAttributes()) {
					//System.out.println("Att Node Name = " + tempNode.getNodeName());
					NamedNodeMap attributes = tempNode.getAttributes();
					for (int att = 0; att < attributes.getLength(); att++) {
						Node attNode = attributes.item(att);
						if(attNode.getNodeName().endsWith("id") || attNode.getNodeName().endsWith("Id") || attNode.getNodeName().equalsIgnoreCase("id")) {
							if(isSpecialCharacterPresent(attNode.getNodeValue())) {
								attDataSet.add(new AttDataSet(tempNode.getNodeName(), attNode.getNodeName(), attNode.getNodeValue(),tempNode.getNodeName() + "->" + getBaseNode(tempNode)));
								//System.out.println("Attr name : " + attNode.getNodeName()+ "; Value = " + attNode.getNodeValue());	
							}
						}
					}

				}
				if (tempNode.hasChildNodes()) {
					printAttributesInfomation(tempNode.getChildNodes(),attDataSet);
				}
			}
		}
	}
	
	private static boolean isSpecialCharacterPresent(String str) {
		Pattern p = Pattern.compile("[^a-z0-9: ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		return m.find();
	}
	
	private static String getBaseNode(Node tempNode) {
		Node parentNode = tempNode.getParentNode();
		if (parentNode != null && parentNode.getParentNode() != null) {
			return parentNode.getNodeName() + "->" + getBaseNode(parentNode.getParentNode());
		} else {
			// System.out.println("tempValue : " + tempNode.getNodeName());
			return tempNode.getNodeName();
		}
	}


	

	
		}
