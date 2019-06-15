package com.dragon.common;

public class Management {
  private int number;
  private String name;
  private String summary;
  private int quantity;
  private String author;
  private String images;
  private String type;
  
  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getSummary() {
    return summary;
  }
  public void setSummary(String summary) {
    this.summary = summary;
  }
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public String getAuthor() {
    return author;
  }
  public void setAuthor(String author) {
    this.author = author;
  }
  public String getImages() {
    return images;
  }
  public void setImages(String images) {
    this.images = images;
  }
  
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  
  @Override
  public String toString() {
    return "Management [number=" + number + ", name=" + name + ", summary="
        + summary + ", quantity=" + quantity + ", author=" + author
        + ", images=" + images + ", type=" + type + "]";
  }
}
