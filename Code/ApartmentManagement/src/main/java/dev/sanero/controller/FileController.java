/**
 * Project name: ApartmentManagement
 * Package name: dev.sanero.controller
 * File name: FileController.java
 * Author: Sanero.
 * Created date: Mar 18, 2019
 * Created time: 10:27:50 PM
 */

package dev.sanero.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.sanero.utils.FileManager;

/*
 * @author Sanero.
 * Created date: Mar 18, 2019
 * Created time: 10:27:50 PM
 * Description: TODO - 
 */
@Controller
@RequestMapping("/file")
public class FileController {
  @Autowired
  ServletContext servletContext;
  
  @GetMapping("/download-sample/{fileName}")
  public ResponseEntity<InputStreamResource> download(@PathVariable String fileName) throws IOException {
         MediaType mediaType = FileManager.getMediaTypeForFileName(servletContext, fileName);
         File file = new File("template/" + fileName);
         InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
         return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                      .contentType(mediaType).contentLength(file.length()).body(resource);
  }
}
