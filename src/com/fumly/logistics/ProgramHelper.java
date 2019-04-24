package com.fumly.logistics;

import java.util.ArrayList;

public class ProgramHelper {
  private String name;
  private int weight;
  private int value;
  private ArrayList<Product> toLoad = new ArrayList<>();
//  private static ProgramHelper instance = null;

  public ProgramHelper() {
  }

//  public static ProgramHelper createProgramHelper()
//  {
//    if(instance==null)
//      instance = new ProgramHelper();
//    else System.out.println("ProgramHelper is already created.");
//    return instance;
//  }

  public void discern(String allValues) {
    throwException(allValues);
    String[] arrOfValues = allValues.split("/");
    name = arrOfValues[0];
    try {
      weight = Integer.parseInt(arrOfValues[1]);
      value = Integer.parseInt(arrOfValues[2]);
    } catch (NumberFormatException numberExc) {
      System.out.println("There was a problem getting an input" +
              " for maximum weight limit - use integer numbers only to represent the weight.");
      System.exit(1);
    }
  }

  public void fillArrayList() {
    toLoad.add(new Product(name, weight, value));
  }

  public void clearValues() {
    name = null;
    weight = 0;
    value = 0;
  }

  public ArrayList<Product> getToLoad() {
    return toLoad;
  }

  private void throwException(String allValues) {
    String[] arrOfValues = allValues.split("/");
    try {
      if (!allValues.contains("/") || arrOfValues.length != 3)
        throw new InputValuesException("Program stopped with an error.");
    } catch (InputValuesException ivExc) {
      System.out.println(ivExc.getMessage());
      System.out.print("You are using incorrect syntax. ");
      System.out.println("The correct syntax for this program " +
              "is: product_name/weight/price.");
      System.out.println("Input data must be divided by a whitespace.");
      System.exit(1);
    }
  }
}