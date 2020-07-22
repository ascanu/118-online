package com.etsy.Tests;

public class AttDataSet {

	private String nodeName;
	private String attributeName;

	private String attributeValue;
	private String path;

	public AttDataSet(String nodeName, String attributeName, String attributeValue, String path) {
		this.nodeName = nodeName;
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
		this.path = path;
	}

	public String getNodeName() {
		return nodeName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "AttDataSet [nodeName=" + nodeName + ", attributeName=" + attributeName + ", attributeValue="
				+ attributeValue + ", path=" + path + "]";
	}
}
