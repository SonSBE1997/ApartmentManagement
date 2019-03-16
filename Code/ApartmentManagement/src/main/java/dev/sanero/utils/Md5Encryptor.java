/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.utils
 * File name: Md5Encryptor.java
 * Author: Sanero.
 * Created date: Mar 16, 2019
 * Created time: 9:08:27 PM
 */

package dev.sanero.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * @author Sanero.
 * Created date: Mar 16, 2019
 * Created time: 9:08:27 PM
 * Description: TODO - 
 */
public class Md5Encryptor {
  public static String encrypt(String str) {
      String result = "";
      MessageDigest digest;
      try {
        digest = MessageDigest.getInstance("MD5");
        digest.update(str.getBytes());
        BigInteger bigInteger = new BigInteger(1,digest.digest());
        result = bigInteger.toString(16);
      } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
      }
      return result;
  }
}
