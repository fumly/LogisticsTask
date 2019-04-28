package com.fumly.logistics;

import java.util.Comparator;

public class Product implements Comparable<Product>, Comparator<Product> {

  private static int entries;
  private String objectName;
  private String productName;
  private int productWeight;
  private int productPrice;

  public Product() {
  }

  public String getObjectName() {
    return objectName;
  }

  public Product(String productName, int productWeight, int productPrice) {
    this.productName = productName;
    this.productWeight = productWeight;
    this.productPrice = productPrice;
    entries++;
    setObjectName();

  }

  public String getProductName() {
    return productName;
  }

  public int getProductWeight() {
    return productWeight;
  }

  public int getProductPrice() {
    return productPrice;
  }

  private void setObjectName() {
    objectName = this.getClass().getSimpleName() + " " + entries;
  }

  public String printList() {
    return productName + "/" + productWeight + "/" + productPrice;
  }

  @Override
  public boolean equals(Object otherObj) {
    if (this == otherObj) {
      return true;
    }
    if (otherObj == null) {
      return false;
    }
    if (getClass() != otherObj.getClass()) {
      return false;
    }
    Product obj = (Product) otherObj;

    return productName.equals(obj.getProductName()) &&
            productWeight == obj.getProductWeight() &&
            productPrice == obj.getProductPrice() &&
            hashCode() == obj.hashCode();
  }

  @Override
  public int hashCode() {
    int result = productName != null ? productName.hashCode() : 0;
    result = 31 * result + objectName.hashCode();
    return result;
  }

  @Override
  public int compareTo(Product toCompare) {
    return Integer.compare(toCompare.getProductPrice(), this.getProductPrice());
  }

  @Override
  public int compare(Product p1, Product p2) {
    return p1.getProductWeight() - p2.getProductWeight();
  }
}