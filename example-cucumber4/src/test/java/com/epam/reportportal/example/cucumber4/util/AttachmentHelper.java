/*
 * Copyright 2021 EPAM Systems
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.reportportal.example.cucumber4.util;

import com.epam.reportportal.utils.files.ByteSource;
import com.epam.reportportal.utils.files.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AttachmentHelper {
	public static final String FILE_NAME = "file";

	public static File getFileFromResources(String path, String name, String extension) {
		File file = null;
		try {
			file = File.createTempFile("rp-test", extension);
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

	public static File getFileFromResources(String name, String extension) {
		return getFileFromResources("src/test/resources/files", name, extension);
	}
}
