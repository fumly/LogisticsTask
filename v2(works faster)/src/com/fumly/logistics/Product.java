package com.fumly.logistics;

public class Product implements Comparable<Product> {

  private static int entries;
  private String objectName;
  private String productName;
  private int productWeight;
  private int productPrice;

  private Product() {
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
    return (double)(this.getProductPrice()/this.getProductWeight()) != 1 ?
            Double.compare((double)(toCompare.getProductPrice()/toCompare.getProductWeight()),
                    (double)(this.getProductPrice()/this.getProductWeight())) :
            Double.compare(((double)(toCompare.getProductPrice())), (double)(this.getProductPrice()));
  }
}