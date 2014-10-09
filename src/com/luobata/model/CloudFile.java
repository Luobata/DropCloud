package com.luobata.model;

import java.io.File;


/*
 * ∂®“ÂCloudFile Model
 */
public class CloudFile implements java.io.Serializable{
	
	private File file;
	private String fileName;
	private String date;
	private String author;
	private String size;
	private String root_abs;
	private String root_rel;
	private int file_id;
	private String file_type;
	private String fileName_rel;
	public CloudFile(){
		
	}
	
	public CloudFile(String fileName, String date, String size) {
		super();
		this.fileName = fileName;
		this.date = date;
		this.size = size;
	}
	

	public CloudFile(String fileName, String date, String size, int file_id ) {
		super();
		this.fileName = fileName;
		this.date = date;
		this.size = size;
		this.file_id = file_id;
		//this.root_rel=root_rel;
	}

	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getRoot_abs() {
		return root_abs;
	}

	public void setRoot_abs(String root_abs) {
		this.root_abs = root_abs;
	}

	public String getRoot_rel() {
		return root_rel;
	}

	public void setRoot_rel(String root_rel) {
		this.root_rel = root_rel;
	}


	public int getFile_id() {
		return file_id;
	}

	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	
	public String getFileName_rel() {
		return fileName_rel;
	}

	public void setFileName_rel(String fileName_rel) {
		this.fileName_rel = fileName_rel;
	}

	@Override
	public String toString() {
		return "CloudFile [file=" + file + ", fileName=" + fileName + ", date="
				+ date + ", author=" + author + ", size=" + size
				+ ", root_abs=" + root_abs + ", root_rel=" + root_rel
				+ ", file_id=" + file_id + ", file_type=" + file_type
				+ ", fileName_rel=" + fileName_rel + "]";
	}

	


	

	
}
