package com.epam.reportportal.example.junit5.util;

public enum Attachment {
	CMD(".cmd"),
	CSS(".css"),
	CSV(".csv"),
	HAR(".har"),
	HTML(".html"),
	JPG(".jpg"),
	JS(".js"),
	JSON(".json"),
	PDF(".pdf"),
	PHP(".php"),
	PNG(".png"),
	TAR(".tar"),
	TAR_GZ(".tar.gz"),
	TXT(".txt"),
	XML(".xml"),
	ZIP(".zip");

	private String extension;

	public String getExtension() {
		return extension;
	}

	Attachment(String extension) {
		this.extension = extension;
	}
}
