package com.dragon.common;

public class Record {

  String studentId;
  int booknumber;
  
  public String getStudentId() {
    return studentId;
  }
  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }
  public int getBooknumber() {
    return booknumber;
  }
  public void setBooknumber(int booknumber) {
    this.booknumber = booknumber;
  }
  
  @Override
  public String toString() {
    return "record [studentId=" + studentId + ", booknumber=" + booknumber
        + "]";
  }
  
}
