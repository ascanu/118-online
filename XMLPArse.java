package com.etsy.Tests;

import java.util.*;
import java.util.stream.IntStream;
import java.io.*;

import org.apache.commons.collections4.CollectionUtils;
import org.w3c.dom.*;

import javax.xml.parsers.*;

import org.xml.sax.*;

public class XMLPArse {

	/*
	 * DiffElements processXMLDiffDocument =
	 * processXMLDiffDocument(sourceFile,targetFile);
	 * 
	 * List<XMLDiffMap> addedElements = processXMLDiffDocument.getAddedElements();
	 * List<XMLDiffMap> deletedElements =
	 * processXMLDiffDocument.getDeletedElements();
	 * 
	 * 
	 * for(XMLDiffMap element : addedElements) {
	 * System.out.println(element.getPath());
	 * System.out.println(element.getValue()); }
	 * 
	 */

	private static DiffElements processXMLDiffDocument(File sourceFile, File targetFile)
			throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document sourcedoc = dBuilder.parse(sourceFile);
		Document targetdoc = dBuilder.parse(targetFile);

		System.out.println("SourceDoc : " + sourcedoc.getChildNodes());
		Map<String, String> sourceList = new HashMap<String, String>();
		Map<String, String> targetList = new HashMap<String, String>();
		printNode(sourcedoc.getChildNodes(), sourceList);
		printNode(targetdoc.getChildNodes(), targetList);

		// sourceList.stream().forEach(element -> System.out.println(element));

		// System.out.println("SourceList : " + SourceList);

		// System.out.println("targetlist : " + targetList);
		return listDifference(sourceList, targetList);

	}

	private static void printNode(NodeList nodelist, Map<String, String> targetList) {
		String Keys = null;
		Set<Integer> NodeTypesSet = new HashSet<Integer>();
		for (int count = 0; count < nodelist.getLength(); count++) {
			Node tempNode = nodelist.item(count);
			NodeTypesSet.add(Integer.valueOf(tempNode.getNodeType()));
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				parseElementNode(tempNode, targetList);
			} else if (tempNode.getNodeType() == Node.TEXT_NODE) {
				// tempNode
				String tempString = tempNode.getNodeValue().trim().replaceAll("\\s+", " ");
				// System.out.println("index : " + tempString + "lenght is :" +
				// tempString.length());
				if (tempString.length() > 0) {

					/*
					 * System.out.println("###################################");
					 * System.out.println("Base URI : " + tempNode.getParentNode().getNodeName());
					 */
					// System.out.println("Base URI : " + getBaseNode(tempNode));
					// System.out.println("Base URI : " + getBaseNode2(tempNode));
					// System.out.println("Text value : " + tempString);
					/*
					 * System.out.println("###################################");
					 */

					targetList.put(getBaseNode(tempNode), tempString);
				}

			}

			// Keys=tempNode.getNodeValue().replaceAll("\\s+"," ");

		}

		// System.out.println("Node Type Set : " + NodeTypesSet);
		// System.out.println("TextList :" + textList);
		// return textList;
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

	private static void parseElementNode(Node elementNode, Map<String, String> targetList) {

		if (elementNode.getChildNodes().getLength() > 0) {
			NodeList childNodes = elementNode.getChildNodes();
			printNode(childNodes, targetList);
		}

	}

	public static DiffElements listDifference(Map<String, String> sourceList, Map<String, String> targetList) {

		final Map<String, String> dupSourceMap = new HashMap<String, String>();
		final Map<String, String> dupTargetMap = new HashMap<String, String>();

		sourceList.forEach((k, v) -> dupSourceMap.put(v, k));
		targetList.forEach((k, v) -> dupTargetMap.put(v, k));

		List<String> addValues = (List<String>) CollectionUtils.removeAll(targetList.values(), sourceList.values());
		List<String> deletedValues = (List<String>) CollectionUtils.removeAll(sourceList.values(), targetList.values());

		List<XMLDiffMap> addedElements = new ArrayList<XMLDiffMap>();
		List<XMLDiffMap> deletedElements = new ArrayList<XMLDiffMap>();

		System.out.println("ADDED ELEMENTS");
		addValues.stream().forEach(element -> {
			System.out.println();
			String key = dupTargetMap.get(element);
			String value = element;
			addedElements.add(new XMLDiffMap(key, value));
			System.out.println("Path is : " + key);
			System.out.println("Value is : " + value);
		});

		System.out.println("DELETED ELEMENTS");
		deletedValues.stream().forEach(element -> {
			System.out.println();
			String key = dupSourceMap.get(element);
			String value = element;
			deletedElements.add(new XMLDiffMap(key, value));
			System.out.println("Path is : " + key);
			System.out.println("Value is : " + value);
		});

		return new DiffElements(addedElements, deletedElements);
	}

}

class DiffElements {

	private List<XMLDiffMap> deletedElements;
	private List<XMLDiffMap> addedElements;

	public DiffElements() {
		// TODO Auto-generated constructor stub
	}

	public DiffElements(List<XMLDiffMap> addedElements, List<XMLDiffMap> deletedElements) {
		// TODO Auto-generated constructor stub
		this.addedElements = addedElements;
		this.deletedElements = deletedElements;
	}

	public List<XMLDiffMap> getAddedElements() {
		return addedElements;
	}

	public List<XMLDiffMap> getDeletedElements() {
		return deletedElements;
	}

}

class XMLDiffMap {

	private String path;
	private String value;

	XMLDiffMap(String path, String value) {
		this.path = path;
		this.value = value;
	}

	public String getPath() {
		return path;
	}

	public String getValue() {
		return value;
	}

}
