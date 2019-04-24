package com.fumly.logistics;

public class Main {
    public static void main(String[] args) {
        int maxWeight = Supplementary.stringToInt(Supplementary.getInput());
        Supplementary.start(Supplementary.getInput().split(" "));
        System.out.println(Supplementary.bestOption(maxWeight));
    }
}



