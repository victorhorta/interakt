package me.interakt.app;

import java.io.File;

public class Node {
	//board pai e filho sao tratados aqui no Node
	//
	private long id;
	private long parent;
	private String label;
	private String color;
	private String filename;
	private String filepath;
	//public File fileInstance;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getParent() {
		return parent;
	}
	public void setParent(long parent) {
		this.parent = parent;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	/* public String toString(){
		return ?????
	}
	*/
}
