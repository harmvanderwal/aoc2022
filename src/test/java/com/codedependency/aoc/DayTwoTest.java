package com.codedependency.aoc;

import com.codedependency.aoc.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static com.codedependency.aoc.util.Regexes.NEW_LINE_PATTERN;

class DayTwoTest {

	@Test
	void puzzleOne() {
		System.out.println(Arrays.stream(NEW_LINE_PATTERN.split(FileUtil.fromFile("day2.txt")))
				.mapToInt(Map.of(
						"B X", 1,
						"C Y", 2,
						"A Z", 3,
						"A X", 4,
						"B Y", 5,
						"C Z", 6,
						"C X", 7,
						"A Y", 8,
						"B Z", 9)::get)
				.sum());
	}

	@Test
	void puzzleTwo() {
		System.out.println(Arrays.stream(NEW_LINE_PATTERN.split(FileUtil.fromFile("day2.txt")))
				.mapToInt(Map.of(
						"B X", 1,
						"C X", 2,
						"A X", 3,
						"A Y", 4,
						"B Y", 5,
						"C Y", 6,
						"C Z", 7,
						"A Z", 8,
						"B Z", 9)::get)
				.sum());
	}
}
