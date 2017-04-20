package com.tonilopezmr.karumitestingtraining;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FizzBuzzShould {

  @Test
  public void
  return_fizz_when_number_is_divisor_of_three() throws Exception {
    assertThat(FizzBuzz.fizzBuzz(3), is("Fizz"));
  }

  @Test
  public void
  return_buzz_when_number_is_divisor_of_five() throws Exception {
    assertThat(FizzBuzz.fizzBuzz(5), is("Buzz"));
  }



}