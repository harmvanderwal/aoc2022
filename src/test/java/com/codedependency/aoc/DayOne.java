package com.codedependency.aoc;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;

class DayOne {

	@Test
	void puzzle1() {
		Arrays.stream(fromFile("day1.txt").split("\n\n"))
				.mapToInt(elf -> Arrays.stream(elf.split("\n"))
						.mapToInt(Integer::parseInt)
						.sum())
				.max()
				.ifPresent(System.out::println);
	}

	@Test
	void puzzle2() {
		System.out.println(Arrays.stream(fromFile("day1.txt").split("\n\n"))
				.map(elf -> Arrays.stream(elf.split("\n"))
						.mapToInt(Integer::parseInt)
						.sum())
				.sorted(Collections.reverseOrder())
				.mapToInt(x -> x)
				.limit(3)
				.sum());
	}

	private static String fromFile(String file) {
		try (InputStream is = DayOne.class.getClassLoader().getResourceAsStream(file)) {
			return new String(is.readAllBytes(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
