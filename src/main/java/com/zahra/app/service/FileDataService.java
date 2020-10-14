package com.zahra.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.zahra.app.model.FileData;

public interface FileDataService {
 
	FileData storeFile(MultipartFile file) throws IOException;
	
	FileData getFile(long id);
	
}
