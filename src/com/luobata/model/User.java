package com.luobata.model;

public class User implements java.io.Serializable{

	private	String UserName;
	private String Password_Md5;
	private int type;
	
	
	
	public User(String userName, String password_Md5) {
		super();
		UserName = userName;
		Password_Md5 = password_Md5;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword_Md5() {
		return Password_Md5;
	}
	public void setPassword_Md5(String password_Md5) {
		Password_Md5 = password_Md5;
	}
	@Override
	public String toString() {
		return "User [UserName=" + UserName + ", Password_Md5=" + Password_Md5
				+ "]";
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	
	
	
	
	
	
	
}
