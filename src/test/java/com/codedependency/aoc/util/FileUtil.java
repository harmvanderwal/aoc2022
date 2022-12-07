package com.codedependency.aoc.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileUtil {

	public static String fromFile(String file) {
		try (InputStream is = FileUtil.class.getClassLoader().getResourceAsStream(file)) {
			assert is != null;
			return new String(is.readAllBytes(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}