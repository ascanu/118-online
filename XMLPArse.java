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
		File targetFile=new File("./src/main/java/com/etsy/qa/testdata/Destinatioin/dest.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document sourcedoc = dBuilder.parse(sourceFile);
		Document targetdoc = dBuilder.parse(targetFile);
		
		
		System.out.println("SourceDoc : " + sourcedoc.getChildNodes());
		List<String> sourceList= new ArrayList<String>();
		List<String> targetList = new ArrayList<String>();
		printNode(sourcedoc.getChildNodes(), sourceList);
		printNode(targetdoc.getChildNodes(),targetList);
	
		//sourceList.stream().forEach(element -> System.out.println(element));
		
		
	//	System.out.println("SourceList : " + SourceList);
		
		
		//System.out.println("targetlist : " + targetList);
		listDifference(sourceList,targetList);
		
	}
	private static void printNode(NodeList nodelist, List<String> textList) {
		String Keys=null;
		Set<Integer> NodeTypesSet = new HashSet<Integer>();
		for(int count=0;count<nodelist.getLength();count++) {
			Node tempNode=nodelist.item(count);
			NodeTypesSet.add(Integer.valueOf(tempNode.getNodeType()));
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				parseElementNode(tempNode,textList);
			} else if (tempNode.getNodeType() == Node.TEXT_NODE) {
				String tempString = tempNode.getNodeValue().trim().replaceAll("\\s+"," ");
					//System.out.println("index : " +  tempString + "lenght is :" + tempString.length());
				if(tempString.length() >0) {
					textList.add(tempString);
				}
				
				
			}
			 
		//	Keys=tempNode.getNodeValue().replaceAll("\\s+"," ");
			
		}
		
		//System.out.println("Node Type Set : " + NodeTypesSet);
		//System.out.println("TextList :" + textList);
		//return textList;	
	}
	
	
	

	private static void parseTextNode(Node tempNode) {
		//System.out.println(tempNode.getNodeValue());
		
		
		
	}
	private static void parseElementNode(Node elementNode, List<String> textList) {
		
		if(elementNode.getChildNodes().getLength() > 0) {
			NodeList childNodes = elementNode.getChildNodes();
			printNode(childNodes,textList);
		}
		
	}
	public static void listDifference(List<String> sourceList, List<String> targetList) {
		/*
		 * System.out.print(source); // System.out.println(target);
		 * 
		 * String SourceData=source.replace(".", " . "); String
		 * targetData=source.replace(".", " . ");
		 * 
		 * List<String> sourceList=new
		 * ArrayList<String>(Arrays.asList(SourceData.split("\\s"))); List<String>
		 * targetList=new ArrayList<String>(Arrays.asList(targetData.split("\\s")));
			soureceList = [a,b,c,d,e]
			targetList = [c,d,e,f,g]
			added =[f,g]
			deleted = [a,b]
		 */		
		
		/*
		 * List<String> list1 = Arrays.asList("a","b","c","d","e"); List<String> list2 =
		 * Arrays.asList("c","d","e","f","g");
		 * 
		 * System.out.println(CollectionUtils.removeAll(list2,list1));
		 * System.out.println(CollectionUtils.removeAll(list1,list2));
		 */
		List<String>tempSourceList = new ArrayList<>();
		List<String>tempTargetList = new ArrayList<>();
		
		IntStream.range(0, 30).forEach(index -> {
		//	tempSourceList.add(sourceList.get(index));
		//	System.out.println("Index is :" + index + " and value is:" +sourceList.get(index));
		}); 

		IntStream.range(0, 30).forEach(index -> {
		//	tempTargetList.add(targetList.get(index));
		//	System.out.println("Index is :" + index + " and value is:" +targetList.get(index));
		}); 
		
	//	System.out.println(sourceList);
	System.out.println(targetList);
	//	System.out.print("Added"+CollectionUtils.removeAll(targetList,sourceList)); 
	//	System.out.print("<----------------------------------------.>"); 
	//	System.out.print("deleted"+CollectionUtils.removeAll(sourceList,targetList));
		  
		
		
	
		
	}

	}
