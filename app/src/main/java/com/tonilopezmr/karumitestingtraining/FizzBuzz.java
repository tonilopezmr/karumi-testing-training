package com.tonilopezmr.karumitestingtraining;

public class FizzBuzz {

  public static final String FIZZ_BUZZ = "FizzBuzz";
  public static final String FIZZ = "Fizz";
  public static final String BUZZ = "Buzz";

  public static String fizzBuzz(int num) {
    if ((num % 3 == 0) && (num % 5 == 0)) {
      return FIZZ_BUZZ;
    } else if (num % 3 == 0) {
      return FIZZ;
    } else if (num % 5 == 0) {
      return BUZZ;
    } else {
      return String.valueOf(num);
    }
  }

}
