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
    System.out.println(tq.getHighestValues(weightLimit));
  }

  private void saveData(String allValues) {
    try {
      String[] arrOfValues = allValues.split("/");
      if (!allValues.contains("/") || arrOfValues.length != 3)
        throw new Exception("Program stopped with an error.");
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
      System.out.println("Input data must be divided by a whitespace.");
      System.exit(1);
    }
  }

  private String getHighestValues(int maxWeight) {
    int weightLimit = maxWeight;
    int finalPrice = 0;
    Product best = null;
    Product previousBest = null;
    String result = null;
    StringBuilder sb = new StringBuilder();
    while (weightLimit > 0 && !toLoad.isEmpty()) {
      int currentHighestPrice = 0;
      for (Iterator<Product> it = toLoad.iterator(); it.hasNext(); ) {
        Product current = it.next();
        if (weightLimit - current.getProductWeight() < 0) {
          it.remove();
          continue;
        } else if (current.getProductPrice() >= currentHighestPrice) {
          best = current;
          currentHighestPrice = current.getProductPrice();
        }
      }
      try {
        if (!best.equals(previousBest)) {
          sb.append(best.getProductName() + " ");
          finalPrice += currentHighestPrice;
          weightLimit -= best.getProductWeight();
          toLoad.remove(best);
        } else {
          break;
        }
      } catch (NullPointerException npExc) {
        System.out.println("Not a single value was found");
        System.exit(1);
      }
      previousBest = best;
    }
    sb.append(finalPrice);
    result = sb.toString();
    return result;
  }
}