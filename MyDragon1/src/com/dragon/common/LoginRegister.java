package com.dragon.common;

public class LoginRegister {

	private int studentId;
	private String studentName;
	private String password;
	private String sex;
	private String email;
	private String hone;
	private String sign;
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHone() {
		return hone;
	}
	public void setHone(String hone) {
		this.hone = hone;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@Override
	public String toString() {
		return "LoginRegist [studentId=" + studentId + ", studentName=" + studentName + ", password=" + password
				+ ", sex=" + sex + ", email=" + email + ", hone=" + hone + ", sign=" + sign + "]";
	}
}
