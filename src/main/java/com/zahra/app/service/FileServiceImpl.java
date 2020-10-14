package com.zahra.app.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.zahra.app.model.FileData;
import com.zahra.app.repository.FileRepository;

@Service
public class FileServiceImpl implements FileDataService{
	
	@Autowired
	private FileRepository fileRepository; 

	@Override
	public FileData  storeFile (MultipartFile file) throws IOException {
		System.out.println(file);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    FileData fileData = new FileData(fileName, file.getContentType(), file.getBytes());
	    return fileRepository.save(fileData);
	}

	@Override
	public FileData getFile(long id) {
		return fileRepository.findById(id).get();
	}
	
}
