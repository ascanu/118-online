package com.etsy.Tests;

import java.util.*;
import java.util.stream.IntStream;
import java.io.*;

import org.apache.commons.collections4.CollectionUtils;
import org.w3c.dom.*;

import javax.xml.parsers.*;

import org.xml.sax.*;




public class XMLPArse {
	
	
	public static void main(String[] args) throws SAXException, Exception {
		File sourceFile=new File("./src/main/java/com/etsy/qa/testdata/source/source.xml");
		File targetFile=new File("./src/main/java/com/etsy/qa/testdata/Destinatioin/desti.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document sourcedoc = dBuilder.parse(sourceFile);
		Document targetdoc = dBuilder.parse(targetFile);
		
		
		System.out.println("SourceDoc : " + sourcedoc.getChildNodes());
		Map<String,String> sourceList= new HashMap<String,String>();
		Map<String,String> targetList = new HashMap<String,String>();
		printNode(sourcedoc.getChildNodes(), sourceList);
		printNode(targetdoc.getChildNodes(),targetList);
	
		//sourceList.stream().forEach(element -> System.out.println(element));
		
		
	//	System.out.println("SourceList : " + SourceList);
		
		
		//System.out.println("targetlist : " + targetList);
		 listDifference(sourceList,targetList);
		
	}
	
	
	
	
	private static void printNode(NodeList nodelist, Map<String, String> targetList) {
		String Keys=null;
		Set<Integer> NodeTypesSet = new HashSet<Integer>();
		for(int count=0;count<nodelist.getLength();count++) {
			Node tempNode=nodelist.item(count);
			NodeTypesSet.add(Integer.valueOf(tempNode.getNodeType()));
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				parseElementNode(tempNode,targetList);
			} else if (tempNode.getNodeType() == Node.TEXT_NODE) {
				//tempNode
				String tempString = tempNode.getNodeValue().trim().replaceAll("\\s+"," ");
				//System.out.println("index : " +  tempString + "lenght is :" + tempString.length());
				if(tempString.length() >0) {
					
					/*
					 * System.out.println("###################################");
					 * System.out.println("Base URI : " + tempNode.getParentNode().getNodeName());
					 */
					 System.out.println("Base URI : " + getBaseNode(tempNode));
				//	 System.out.println("Base URI : " + getBaseNode2(tempNode));
					 System.out.println("Text value : " + tempString);
					/*
					 * System.out.println("###################################");
					 */	
					
					targetList.put(getBaseNode(tempNode), tempString);
				}
				
				
			}
			 
		//	Keys=tempNode.getNodeValue().replaceAll("\\s+"," ");
			
		}
		
		//System.out.println("Node Type Set : " + NodeTypesSet);
		//System.out.println("TextList :" + textList);
		//return textList;	
	}

	private static String getBaseNode(Node tempNode) {
		Node parentNode = tempNode.getParentNode();
		if(parentNode != null && parentNode.getParentNode() != null) {
			return parentNode.getNodeName() + "->" + getBaseNode(parentNode.getParentNode());
		}
		else {
			//System.out.println("tempValue : " + tempNode.getNodeName());
			return tempNode.getNodeName();
		}
	}
	
	
	
	

	
	private static void parseElementNode(Node elementNode, Map<String, String> targetList) {
		
		if(elementNode.getChildNodes().getLength() > 0) {
			NodeList childNodes = elementNode.getChildNodes();
			printNode(childNodes,targetList);
		}
		
	}
	public static void listDifference(Map<String, String> sourceList, Map<String, String> targetList) {
		
		
        System.out.print("Added"+CollectionUtils.removeAll(targetList,sourceList)); 
		System.out.print("<----------------------------------------.>"); 
		System.out.print("deleted"+CollectionUtils.removeAll(sourceList,targetList));
		  
		
		
	
		
	}

	}
