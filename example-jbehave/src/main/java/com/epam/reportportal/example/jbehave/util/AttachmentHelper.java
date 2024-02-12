package com.epam.reportportal.example.jbehave.util;

import com.epam.reportportal.utils.files.ByteSource;
import com.epam.reportportal.utils.files.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utility file to copy / read resource files.
 */
public class AttachmentHelper {
	public static File getFileFromResources(String path, String name, String extension) {
		File file = null;
		try {
			file = File.createTempFile("rp-test", "." + extension);
			ByteSource source = Utils.getFileAsByteSource(new File(String.format("%s/%s.%s", path, name, extension)));
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
