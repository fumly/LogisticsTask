package com.fumly.logistics;

import java.io.*;

public class InputReader {

  public static String getInput() {
    String input = null;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
      input = br.readLine();
    } catch (IOException ioExc) {
      System.out.println(ioExc.getMessage());
    }
    return input;
  }

  public static int stringToInt(String weightStr) {
    int intInput = 0;
    try {
      intInput = Integer.parseInt(weightStr);
      if (intInput < 0) {
        throw new Exception("You've entered a negative number.");
      }
      return intInput;
    } catch (NumberFormatException numberExc) {
      System.out.println("There was a problem getting an input " +
              "for maximum weight limit - use integer " +
              "numbers only to represent the weight.");
      System.exit(1);
    } catch (Exception negativeExc) {
      System.out.println(negativeExc.getMessage());
      System.out.println("Weight should not be represented by a negative number.");
      System.exit(1);
    }
    return intInput;
  }
}
