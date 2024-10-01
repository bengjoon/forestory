package com.forestory.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OpenApiDTO {
	private String siteName;
	private String surviceKey;
	private String method;
	private String contentType;
	
	@Builder
	public OpenApiDTO(String siteName, String method, String surviceKey, String contentType) {
		this.siteName = siteName;
		this.method = method;
		this.surviceKey = surviceKey;
		this.contentType = contentType;
	}
}
