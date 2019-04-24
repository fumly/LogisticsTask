package com.fumly.logistics;

public class Product
{
  private String productName;
  private int productWeight;
  private int productPrice;

  public Product(String productName, int productWeight, int productPrice)
  {
    this.productName = productName;
    this.productWeight = productWeight;
    this.productPrice = productPrice;
  }

  public String getProductName()
  {
    return productName;
  }

  public int getProductWeight()
  {
    return productWeight;
  }

  public int getProductPrice()
  {
    return productPrice;
  }

  public String printList()
  {
    return productName+"/"+productWeight+"/"+ productPrice;
  }
}