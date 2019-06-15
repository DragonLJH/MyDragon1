package com.dragon.common;

public class Discuss {
  
  private int studentId;
  private String discuss;
  private int booknumber;
  
  
  public int getStudentId() {
    return studentId;
  }
  public void setStudentId(int studentId) {
    this.studentId = studentId;
  }
  public String getDiscuss() {
    return discuss;
  }
  public void setDiscuss(String discuss) {
    this.discuss = discuss;
  }
  public int getBooknumber() {
    return booknumber;
  }
  public void setBooknumber(int booknumber) {
    this.booknumber = booknumber;
  }
  @Override
  public String toString() {
    return "Discuss [studentId=" + studentId + ", discuss=" + discuss
        + ", booknumber=" + booknumber + "]";
  }
}
