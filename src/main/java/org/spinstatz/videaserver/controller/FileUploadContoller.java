package org.spinstatz.videaserver.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadContoller {
	@Value("${file.upload.location}")
	private String fileUploadLocation;

	@RequestMapping(path = "/file/upload", method = RequestMethod.POST)
	public ResponseEntity<String> uploadFile(@RequestParam("userId") String userId,
			@RequestParam("uploadFile") MultipartFile uploadfile, HttpServletRequest request) {
		try {
			
			String filename = uploadfile.getOriginalFilename();
			String filePath = Paths.get(fileUploadLocation).toString();
			String absoluteFilePath = filePath + File.separator + filename;
			System.out.println(absoluteFilePath);
			//File file = new File(absoluteFilePath);
			BufferedOutputStream stream =
		            new BufferedOutputStream(new FileOutputStream(new File(absoluteFilePath)));
		        stream.write(uploadfile.getBytes());
		        stream.close();
			/*if(file.createNewFile()) {
				file.setWritable(uploadfile.getBytes())
				System.out.println("File is created");
			} else {
				System.out.println("File already exist");
			}*/
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>(request.getContextPath(), HttpStatus.ACCEPTED);
	
	}
}
