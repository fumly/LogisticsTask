package com.fumly.logistics;

import java.util.*;

public class TransportQueue {
  List<Product> toLoad = new ArrayList<>();

  private TransportQueue() {
  }

  public static void defineBestOption(int weightLimit, String[] listOfProducts) {
    TransportQueue tq = new TransportQueue();
    for (int count = 0; count < listOfProducts.length; count++) {
      try {
        tq.saveData(listOfProducts[count]);
      } catch (ArrayIndexOutOfBoundsException arrayExc) {
        System.out.println("At least one of your values " +
                "cannot be contained inside of an array.");
        break;
      }
    }
    Collections.sort(tq.toLoad);
    System.out.println(tq.getHighestValues(weightLimit));
  }

  private void saveData(String allValues) {
    try {
      String[] arrOfValues = allValues.split("/");
      if (!allValues.contains("/") || arrOfValues.length != 3 ||
              Integer.parseInt(arrOfValues[1]) < 0 || Integer.parseInt(arrOfValues[2]) < 0) {
        throw new Exception("Program stopped with an error.");
      }
      toLoad.add(new Product(arrOfValues[0],
              Integer.parseInt(arrOfValues[1]), Integer.parseInt(arrOfValues[2])));
    } catch (NumberFormatException numberExc) {
      System.out.println("There was a problem getting an input" +
              " for maximum weight limit - use a single integer number to represent the weight.");
      System.exit(1);
    } catch (Exception exc) {
      System.out.println(exc.getMessage());
      System.out.print("You are using incorrect syntax. ");
      System.out.println("The correct syntax for this program " +
              "is: product_name/weight/price.");
      System.out.println("Parameters weight and price should be positive values");
      System.out.println("Input data must be divided by a whitespace.");
      System.exit(1);
    }
  }

  private String getHighestValues(int maxWeight) {

    String result = null;
    int weightLimit = maxWeight;
    StringBuilder sb = new StringBuilder();
    int price = 0;
    while (weightLimit > 0)
    {
      for (Iterator<Product> it =toLoad.iterator();it.hasNext();)
      {
        Product current = it.next();
        if(weightLimit - current.getProductWeight() >=0)
        {
          sb.append(current.getProductName() + " ");
          weightLimit -= current.getProductWeight();
          price+=current.getProductPrice();
          it.remove();
        }
        else if (weightLimit-current.getProductWeight()<0)
        {
          weightLimit -= current.getProductWeight();
        }
      }
    }
    sb.append(price);
    if (price == 0) {
      System.out.println("Not a single value was found");
      System.exit(1);
    }
    result = sb.toString();
    return result;
  }
}