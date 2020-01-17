package com.epam.reportportal.example.junit5;

import rp.com.google.common.io.Files;
import rp.com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;

public class Util {

	public static File getFileFromResources(String name, String extension) throws IOException {
		File file = File.createTempFile("rp-test", String.format(".%s", extension));
		Resources.asByteSource(Resources.getResource(String.format("files/%s.%s", name, extension))).copyTo(Files.asByteSink(file));
		return file;
	}
}
