package com.forestory.common.file;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.Setter;

@RestController
public class EditorController {

	@Setter(onMethod_ = @Autowired)
	private EditorService editorService;
	
	@PostMapping("/editor/imageUpload")
	public void imageUpload(MultipartFile upload, HttpServletResponse resp){
		editorService.imageUpload(upload, resp);
	}
}
