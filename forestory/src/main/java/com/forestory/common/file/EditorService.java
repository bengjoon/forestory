package com.forestory.common.file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

@Service
public class EditorService {

	private String imageFolder = "C://forestory//img//board//";
	
	private String imagePath = "/upload/";
	
	public String imageUpload(MultipartFile image, HttpServletResponse resp) {
		JsonObject json = new JsonObject();
		PrintWriter writer = null;
		
		if(image != null && image.isEmpty() == false) {
			String imageName = UUID.randomUUID() + "-" + image.getOriginalFilename();
			
			File file = new File(imageFolder + imageName);
			
			try {
				image.transferTo(file);
				
				json.addProperty("uploaded", 1);
				json.addProperty("fileName", imageName);
				json.addProperty("url", imagePath + imageName);
				
				writer = resp.getWriter();
				
				writer.println(json);
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			} finally {
				if(writer != null) writer.close();
			}
			
		}
		
		return null;
	}

}
