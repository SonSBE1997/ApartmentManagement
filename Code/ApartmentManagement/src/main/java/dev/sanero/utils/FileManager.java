/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.utils
 * File name: File.java
 * Author: Sanero.
 * Created date: Mar 18, 2019
 * Created time: 10:31:04 PM
 */

package dev.sanero.utils;

import javax.servlet.ServletContext;

import org.springframework.http.MediaType;

/*
 * @author Sanero.
 * Created date: Mar 18, 2019
 * Created time: 10:31:04 PM
 * Description: TODO - 
 */
public class FileManager {

  public static MediaType getMediaTypeForFileName(ServletContext servletContext,
      String fileName) {
    String mineType = servletContext.getMimeType(fileName);
    try {
      MediaType mediaType = MediaType.parseMediaType(mineType);
      return mediaType;
    } catch (Exception e) {
      return MediaType.APPLICATION_OCTET_STREAM;
    }

  }
}
