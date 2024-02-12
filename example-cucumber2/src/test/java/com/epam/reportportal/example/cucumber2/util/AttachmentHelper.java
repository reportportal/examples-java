package com.epam.reportportal.example.cucumber2.util;

import com.epam.reportportal.utils.files.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class AttachmentHelper {

	public static final String FILE_NAME = "file";

	public static File getFileFromResources(String name, String extension) {
		File file = null;
		try {
			file = File.createTempFile("rp-test", extension);
			ByteSource source = Utils.getFileAsByteSource(new File(String.format("files/%s%s", name, extension)));
			try (InputStream is = source.openStream()) {
				try (OutputStream os = java.nio.file.Files.newOutputStream(file.toPath())) {
					Utils.copyStreams(is, os);
				}
			}
		} catch (IOException ignored) {
		}
		return file;
	}
}
