package com.codedependency.aoc;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileUtil {
	public static String fromFile(String file) {
		try (InputStream is = DayOneTest.class.getClassLoader().getResourceAsStream(file)) {
			return new String(is.readAllBytes(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}