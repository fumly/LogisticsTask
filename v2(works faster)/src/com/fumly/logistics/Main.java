package com.fumly.logistics;

public class Main {
  public static void main(String[] args) {
    Car transport = new Car(InputReader.stringToInt(InputReader.getInput()));
    TransportQueue.defineBestOption(transport.getMaxWeight(),
            InputReader.getInput().split(" "));
  }
}