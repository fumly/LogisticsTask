package com.fumly.logistics;

import java.util.*;

public class TransportQueue {
  private TransportQueue() {
  }

  public static void defineBestOption(int weightLimit, String[] listOfProducts) {
    List<Product> toLoad = new ArrayList<>();
    for (int count = 0; count < listOfProducts.length; count++) {
      try {
        saveData(listOfProducts[count], toLoad);
      } catch (ArrayIndexOutOfBoundsException arrayExc) {
        System.out.println("At least one of your values " +
                "cannot be contained inside of an array.");
        break;
      }
    }
    Collections.sort(toLoad);
    System.out.println(getHighestValues(weightLimit, toLoad));
  }

  private static void saveData(String allValues, List<Product> toLoad) {
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

  private static String getHighestValues(int maxWeight, List<Product> toLoad) {
    String result = null;
    int weightLimit = maxWeight;
    StringBuilder sb = new StringBuilder();
    List<Product> usedProduct = new ArrayList<>();
    int price = 0;
    while (weightLimit > 0 && !toLoad.isEmpty()) {
      for (Iterator<Product> it = toLoad.iterator(); it.hasNext(); ) {
        Product current = it.next();
        if (usedProduct.contains(current)) {
          it.remove();
          continue;
        }
        if (weightLimit - current.getProductWeight() >= 0) {
          int insideWeight = 0;
          int insidePrice = 0;
          StringBuilder insideSb = new StringBuilder();
          for (Product p : toLoad) {
            if (!p.equals(current) && p.getProductPrice() != current.getProductPrice() &&
                    p.getProductWeight() != current.getProductWeight() &&
                    !p.getProductName().equals(current.getProductName()) &&
                    (double) (p.getProductPrice() / p.getProductWeight()) == 1) {
              if (usedProduct.contains(p)) {
                continue;
              }
              insideWeight += p.getProductWeight();
              insidePrice += p.getProductPrice();
              insideSb.append(p.getProductName() + " ");
              usedProduct.add(p);
            }
            if (insidePrice > current.getProductPrice() && insideWeight <= weightLimit) {
              break;
            }
          }
          if (insidePrice > current.getProductPrice() && insideWeight <= weightLimit) {
            sb.append(insideSb);
            price += insidePrice;
            weightLimit -= insideWeight;
            continue;
          } else {
            sb.append(current.getProductName() + " ");
            weightLimit -= current.getProductWeight();
            price += current.getProductPrice();
            it.remove();
          }
        } else {
          it.remove();
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