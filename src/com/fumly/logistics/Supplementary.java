package com.fumly.logistics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Supplementary
{
  private static ProgramHelper ph = new ProgramHelper();

  public static String getInput()
  {
    String input = null;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try
    {
      input = br.readLine();
    }catch(IOException ioExc)
    {
      System.out.println(ioExc.getMessage());
    }
    return input;
  }

  public static int stringToInt(String weightStr)
  {
    int weightInt = 0;

    try
    {
      weightInt = Integer.parseInt(weightStr);
      return weightInt;
    }catch(NumberFormatException numberExc)
    {
      System.out.println("There was a problem getting an input" +
          " for maximum weight limit");
      System.exit(1);
    }
    return weightInt;
  }

  public static void start(String[] listOfProducts)
  {
    for(int count=0; count<listOfProducts.length; count++)
    {
      try
      {
        ph.discern(listOfProducts[count]);
      }catch(ArrayIndexOutOfBoundsException arrayExc)
      {
        System.out.println("At least one of your values " +
                  "cannot be contained inside of an array.");
        break;
      }
      ph.fillArrayList();
      ph.clearValues();
    }
  }

  public static String bestOption(int maxWeight)
  {
    int weightLimit = maxWeight;
    int finalPrice=0;
    String result = null;
    Product best = null;
    StringBuilder sb = new StringBuilder();
    while(weightLimit>0&&!ph.getToLoad().isEmpty())
    {
      int currentHighestPrice = Integer.MIN_VALUE;
      for(Iterator<Product> it = ph.getToLoad().iterator();it.hasNext();)
      {
        Product current = it.next();
        if(weightLimit-current.getProductWeight()<0)
        {
          it.remove();
          continue;
        }else if(current.getProductPrice()>=currentHighestPrice)
        {
          best = current;
          currentHighestPrice = current.getProductPrice();
        }
      }
      finalPrice+=currentHighestPrice;
      sb.append(best.getProductName() + " ");
      weightLimit -= best.getProductWeight();
      ph.getToLoad().remove(best);
    }
    sb.append(finalPrice);
    result = sb.toString();
    return result;
  }
}
