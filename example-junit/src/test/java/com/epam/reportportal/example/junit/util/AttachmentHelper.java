package com.epam.reportportal.example.junit.util;

import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;

public class AttachmentHelper {

	public static final String FILE_NAME = "file";

	public static File getFileFromResources(String name, String extension) {
		File file = null;
		try {
			file = File.createTempFile("rp-test", extension);
			Resources.asByteSource(Resources.getResource(String.format("files/%s%s", name, extension))).copyTo(Files.asByteSink(file));
		} catch (IOException ignored) {
		}
		return file;
	}
}
