package com.epam.reportportal.example.cucumber.util;

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
			Utils.copyStreams(
					Utils.getFile(new File(String.format("files/%s%s", name, extension))).openStream(),
					Files.newOutputStream(file.toPath())
			);
		} catch (IOException ignored) {
		}
		return file;
	}
}
