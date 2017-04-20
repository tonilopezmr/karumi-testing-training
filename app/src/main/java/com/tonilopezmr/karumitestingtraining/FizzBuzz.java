package com.tonilopezmr.karumitestingtraining;

public class FizzBuzz {

  public static String fizzBuzz(int num) {
    if (num % 3 == 0) {
      return "Fizz";
    } else if (num % 5 == 0) {
      return "Buzz";
    } else {
      return String.valueOf(num);
    }
  }

}
