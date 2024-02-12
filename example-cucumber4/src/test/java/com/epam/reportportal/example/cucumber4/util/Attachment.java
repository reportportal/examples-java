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

public enum Attachment {
	CMD("cmd"),
	CSS("css"),
	CSV("csv"),
	HAR("har"),
	HTML("html"),
	JPG("jpg"),
	JS("js"),
	JSON("json"),
	PDF("pdf"),
	PHP("php"),
	PNG("png"),
	TAR("tar"),
	TAR_GZ("tar.gz"),
	TXT("txt"),
	XML("xml"),
	ZIP("zip");

	private final String extension;

	public String getExtension() {
		return extension;
	}

	Attachment(String extension) {
		this.extension = extension;
	}
}
