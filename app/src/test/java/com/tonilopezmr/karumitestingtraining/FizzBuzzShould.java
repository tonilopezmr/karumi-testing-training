package com.tonilopezmr.karumitestingtraining;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FizzBuzzShould {

  @Test
  public void
  return_fizz_when_number_is_multiple_of_three() throws Exception {
    assertThat(FizzBuzz.fizzBuzz(3), is(FizzBuzz.FIZZ));
  }

  @Test
  public void
  return_buzz_when_number_is_multiple_of_five() throws Exception {
    assertThat(FizzBuzz.fizzBuzz(5), is(FizzBuzz.BUZZ));
  }

  @Test
  public void
  return_same_input_number_convert_to_string() throws Exception {
    assertThat(FizzBuzz.fizzBuzz(2), is("2"));
  }

  @Test
  public void
  return_fizzbuzz_when_number_is_multiple_of_five_and_three() throws Exception {
    assertThat(FizzBuzz.fizzBuzz(15), is(FizzBuzz.FIZZ_BUZZ));
  }

}