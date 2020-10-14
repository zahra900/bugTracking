package com.zahra.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zahra.app.service.FileDataService;

@RestController
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private FileDataService fileDataService;
	
	@PostMapping("/upload")
	  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    try {
	    	fileDataService.storeFile(file);
	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return new ResponseEntity<>(message, HttpStatus.OK);
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	    }
	  }
	

}
