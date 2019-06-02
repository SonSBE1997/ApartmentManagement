/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.utils
 * File name: NumberToWord.java
 * Author: Sanero.
 * Created date: Jun 1, 2019
 * Created time: 5:38:39 PM
 */

package dev.sanero.utils;

import java.text.DecimalFormat;

/*
 * @author Sanero.
 * Created date: Jun 1, 2019
 * Created time: 5:38:39 PM
 * Description: TODO - 
 */
public class NumberToWord {

  private static final String[] tensNames = { "", " mười", " hai mươi", " ba mươi",
      " bốn mươi", " năm mươi", " sáu mươi", " bảy mươi", " tám mươi", " chín mươi" };

  private static final String[] numNames = { "", " một", " hai", " ba",
      " bốn", " năm", " sáu", " bảy", " tám", " chín", " mười", " mười một",
      " mười hai", " mười ba", " mười bốn", " mười lăm", " mười sáu", " mười bảy",
      " mười tám", " mười chín" };

  private static final String[] vietNumNames = { "", " mốt", " hai", " ba",
      " tư", " lăm", " sáu", " bảy", " tám", " chín", " mười" };
  
  private NumberToWord() {
  }

  private static String convertLessThanOneThousand(int number, boolean last) {
    String soFar;

    if (number % 100 < 20) {
      if (last) {
        if (number / 100 > 0 && (number / 10 % 10) == 0) {
          soFar = " linh" + numNames[number % 100];
          number /= 100;
        } else  {
          soFar = numNames[number % 100];
          number /= 100;
        }
      } else {
        soFar = numNames[number % 100];
        number /= 100;
      }
    } else {
//      if (last == true) {
        soFar = vietNumNames[number % 10];
        number /= 10;

        soFar = tensNames[number % 10] + soFar;
        number /= 10;
//      } else {
//        soFar = numNames[number % 10];
//        number /= 10;
//
//        soFar = tensNames[number % 10] + soFar;
//        number /= 10;
//      }
    }
    if (number == 0)
      return soFar;
    return numNames[number] + " trăm" + soFar;
  }

  public static String convert(long number) {
    if (number == 0) {
      return "không";
    }

    String snumber = Long.toString(number);

    String mask = "000000000000";
    DecimalFormat df = new DecimalFormat(mask);
    snumber = df.format(number);

    int billions = Integer.parseInt(snumber.substring(0, 3));
    int millions = Integer.parseInt(snumber.substring(3, 6));
    int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
    int thousands = Integer.parseInt(snumber.substring(9, 12));

    String tradBillions;
    switch (billions) {
      case 0:
        tradBillions = "";
        break;
      case 1:
        tradBillions = convertLessThanOneThousand(billions, false) + " tỷ ";
        break;
      default:
        tradBillions = convertLessThanOneThousand(billions, false) + " tỷ ";
    }
    String result = tradBillions;

    String tradMillions;
    switch (millions) {
      case 0:
        tradMillions = "";
        break;
      case 1:
        tradMillions = convertLessThanOneThousand(millions, false) + " triệu ";
        break;
      default:
        tradMillions = convertLessThanOneThousand(millions, false) + " triệu ";
    }
    result = result + tradMillions;

    String tradHundredThousands;
    switch (hundredThousands) {
      case 0:
        tradHundredThousands = "";
        break;
      case 1:
        tradHundredThousands = "một nghìn ";
        break;
      default:
        tradHundredThousands = convertLessThanOneThousand(hundredThousands, false)
            + " nghìn ";
    }
    result = result + tradHundredThousands;

    String tradThousand;
    tradThousand = convertLessThanOneThousand(thousands, true);
    if ((billions > 0 || millions > 0) && hundredThousands == 0 && (thousands / 100 == 0)) {
      result = result + " lẻ" + tradThousand;
    } else {
      result = result + tradThousand;
    }

    return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
  }

  /**
   * testing
   * @param args
   */
//  public static void main(String[] args) {
//    System.out.println("*** " + NumberToWord.convert(0));
//    System.out.println("*** " + NumberToWord.convert(1));
//    System.out.println("*** " + NumberToWord.convert(16));
//    System.out.println("*** " + NumberToWord.convert(100));
//    System.out.println("*** " + NumberToWord.convert(118));
//    System.out.println("*** " + NumberToWord.convert(200));
//    System.out.println("*** " + NumberToWord.convert(219));
//    System.out.println("*** " + NumberToWord.convert(800));
//    System.out.println("*** " + NumberToWord.convert(801));
//    System.out.println("*** " + NumberToWord.convert(1316));
//    System.out.println("*** " + NumberToWord.convert(1000000));
//    System.out.println("*** " + NumberToWord.convert(2000000));
//    System.out.println("*** " + NumberToWord.convert(3000200));
//    System.out.println("*** " + NumberToWord.convert(700000));
//    System.out.println("*** " + NumberToWord.convert(9000000));
//    System.out.println("*** " + NumberToWord.convert(9001000));
//    System.out.println("*** " + NumberToWord.convert(123456789));
//    System.out.println("*** " + NumberToWord.convert(124499));
//    System.out.println("*** " + NumberToWord.convert(3000300305L));
//    System.out.println("*** " + NumberToWord.convert(3000000005L));
//  }
}
