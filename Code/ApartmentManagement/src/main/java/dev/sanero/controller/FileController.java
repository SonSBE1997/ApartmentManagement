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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dev.sanero.service.RoomService;
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
  @Autowired
  RoomService roomService;

  @GetMapping("/download-sample/{fileName}")
  public ResponseEntity<InputStreamResource> download(
      @PathVariable String fileName) throws IOException {
    MediaType mediaType = FileManager.getMediaTypeForFileName(servletContext,
        fileName);
    File file = new File("template/" + fileName);
    InputStreamResource resource = new InputStreamResource(
        new FileInputStream(file));
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment;filename=" + file.getName())
        .contentType(mediaType).contentLength(file.length()).body(resource);
  }

  @PostMapping("/upload-room")
  public ResponseEntity<String> readExcelFile(
      @RequestParam("file") MultipartFile file) {
    String extension = FilenameUtils.getExtension(file.getOriginalFilename());
    if (!(("xlsx".equals(extension)) || ("xls".equals(extension)))) {
      return new ResponseEntity<String>("Extension not matching",
          HttpStatus.OK);
    }
    String result = roomService.readFileRoom(file, extension);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
  
  @PostMapping("/change-photo")
  public ResponseEntity<String> changePhoto(
      @RequestParam("file") MultipartFile file) {
    try {
      byte[] bytes = file.getBytes();
      Path path = Paths.get("C:\\Users\\SonSB\\Desktop\\DATN\\Code\\frontend\\src\\assets\\image\\" + file.getOriginalFilename());
      Files.write(path, bytes);
      file.getInputStream();
      return ResponseEntity.ok("upload success");
    } catch (IOException e) {
      e.printStackTrace();
      return new ResponseEntity<String>("failed", HttpStatus.OK);
    }
 }
  
}
