package com.codedependency.aoc;

import com.codedependency.aoc.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codedependency.aoc.util.Regexes.NEW_LINE_PATTERN;

class DayThreeTest {

	private static final IllegalArgumentException NO_MATCHING_CHAR_EXCEPTION = new IllegalArgumentException("No matching char");

	@Test
	void puzzleOne() {
		System.out.println(Arrays.stream(NEW_LINE_PATTERN.split(FileUtil.fromFile("day3.txt")))
				.map(this::findMatchingChar)
				.mapToInt(this::getLetterValue)
				.sum());
	}

	@Test
	void puzzleTwo() {
		String[] elves = NEW_LINE_PATTERN.split(FileUtil.fromFile("day3.txt"));
		List<Character> chars = new ArrayList<>();
		for (int i = 0; i < elves.length / 3; i++) {
			chars.add(findBadge(elves[i * 3], elves[i * 3 + 1], elves[i * 3 + 2]));
		}
		System.out.println(chars.stream()
				.mapToInt(this::getLetterValue)
				.sum());
	}

	private char findBadge(String... strings) {
		for (char c1 : strings[0].toCharArray()) {
			for (char c2 : strings[1].toCharArray()) {
				for (char c3 : strings[2].toCharArray()) {
					if (c1 == c2 && c1 == c3) {
						return c1;
					}
				}
			}
		}
		throw NO_MATCHING_CHAR_EXCEPTION;
	}

	private char findMatchingChar(String s) {
		for (char c1 : s.substring(0, s.length() / 2).toCharArray()) {
			for (char c2 : s.substring(s.length() / 2).toCharArray()) {
				if (c1 == c2) {
					return c1;
				}
			}
		}
		throw NO_MATCHING_CHAR_EXCEPTION;
	}

	private int getLetterValue(char c) {
		return c < 97 ? c + 27 - 'A' : c + 1 - 'a';
	}
}
