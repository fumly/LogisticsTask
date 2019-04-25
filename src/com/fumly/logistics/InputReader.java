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
      return intInput;
    } catch (NumberFormatException numberExc) {
      System.out.println("There was a problem getting an input " +
              "for maximum weight limit - use integer " +
              "numbers only to represent the weight.");
      System.exit(1);
    }
    return intInput;
  }
}
