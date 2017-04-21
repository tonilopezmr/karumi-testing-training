package com.tonilopezmr.karumitestingtraining;

public class FizzBuzz {

  public static final String FIZZ_BUZZ = "FizzBuzz";
  public static final String FIZZ = "Fizz";
  public static final String BUZZ = "Buzz";

  public static String fizzBuzz(int num) {
    String result = "";

    if (isFizz(num)) {
      result += FIZZ;
    }

    if (isBuzz(num)) {
      result += BUZZ;
    }

    if (!isFizz(num) && !isBuzz(num)){
      result = String.valueOf(num);
    }

    return result;
  }

  private static boolean isBuzz(int num) {
    return num % 5 == 0;
  }

  private static boolean isFizz(int num) {
    return num % 3 == 0;
  }

}
