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
    int bestPrice = 0;
    StringBuilder sb = new StringBuilder();
    List<Product> temp = new ArrayList<>(toLoad);
    while (weightLimit > 0 && !toLoad.isEmpty()) {
      for (Iterator<Product> it = temp.iterator(); it.hasNext(); ) {
        temp = new ArrayList<>(toLoad);
        Product current = it.next();
        if (weightLimit - current.getProductWeight() < 0) {
          it.remove();
          toLoad.remove(current);
          continue;
        }
        List<Product> insideTemp = new ArrayList<>(temp);
        Collections.sort(insideTemp, new Product());
        int currentWeightLimit = current.getProductWeight();
        while (currentWeightLimit > 0 && !insideTemp.isEmpty()) {
          int insideBestPrice = 0;
          int insideBestWeight = 0;
          StringBuilder insideBuilder = new StringBuilder();
          for (Iterator<Product> insideIt = insideTemp.iterator(); insideIt.hasNext(); ) {
            Product insideCurrent = insideIt.next();
            if (currentWeightLimit - insideCurrent.getProductWeight() < 0 || insideCurrent.equals(current)) {
              insideIt.remove();
              continue;
            }
            currentWeightLimit -= insideCurrent.getProductWeight();
            insideBestWeight += insideCurrent.getProductWeight();
            insideBuilder.append(insideCurrent.getProductName() + " ");
            insideBestPrice += insideCurrent.getProductPrice();
            toLoad.remove(insideCurrent);
            insideIt.remove();
          }
          if (insideBestPrice > current.getProductPrice()) {
            sb.append(insideBuilder);
            weightLimit -= insideBestWeight;
            bestPrice += insideBestPrice;
          } else {
            sb.append(current.getProductName() + " ");
            weightLimit -= current.getProductWeight();
            bestPrice += current.getProductPrice();
          }
        }
        if (weightLimit - current.getProductWeight() >= 0 && toLoad.size() == 1) {
          sb.append(current.getProductName() + " ");
          weightLimit -= current.getProductWeight();
          bestPrice += current.getProductPrice();
        }
        toLoad.remove(current);
        it.remove();
      }
    }
    if (bestPrice == 0) {
      System.out.println("Not a single value was found");
      System.exit(1);
    }
    result = sb.toString() + bestPrice;

    return result;
  }
}