/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.utils
 * File name: Common.java
 * Author: Sanero.
 * Created date: Apr 1, 2019
 * Created time: 9:59:36 PM
 */

package dev.sanero.utils;

import java.util.Random;

/*
 * @author Sanero.
 * Created date: Apr 1, 2019
 * Created time: 9:59:36 PM
 * Description: TODO - 
 */
public class GenerateRandomString{
  public static void givenUsingPlainJava_whenGeneratingRandomStringUnbounded_thenCorrect() {
    byte[] array = new byte[7]; // length is bounded by 7
    new Random().nextBytes(array);
    String generatedString = new String(array);

    System.out.println(generatedString);
  }

  public static void givenUsingPlainJava_whenGeneratingRandomStringBounded_thenCorrect() {

    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();
    StringBuilder buffer = new StringBuilder(targetStringLength);
    for (int i = 0; i < targetStringLength; i++) {
      int randomLimitedInt = leftLimit + (int)
          (random.nextFloat() * (rightLimit - leftLimit + 1));
      buffer.append((char) randomLimitedInt);
    }
    String generatedString = buffer.toString();

    System.out.println(generatedString);
  }

  public static void main(String[] args){
    givenUsingPlainJava_whenGeneratingRandomStringBounded_thenCorrect();
  }
}
