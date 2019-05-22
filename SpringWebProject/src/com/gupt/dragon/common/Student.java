package com.gupt.dragon.common;

import org.springframework.stereotype.Component;

@Component("student")
public class Student {
	private String studentName;
	private int atudentAge;
	private int studentNum;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getAtudentAge() {
		return atudentAge;
	}

	public void setAtudentAge(int atudentAge) {
		this.atudentAge = atudentAge;
	}

	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	@Override
	public String toString() {
		return "Student [studentName=" + studentName + ", atudentAge=" + atudentAge + ", studentNum=" + studentNum
				+ "]";
	}

}
