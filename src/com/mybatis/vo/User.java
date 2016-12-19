package com.mybatis.vo;

public class User {
	private int id;
	private String userName;
	private int sex;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String userName, int sex) {
		super();
		this.id = id;
		this.userName = userName;
		this.sex = sex;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", sex=" + sex + "]";
	}
	
}
