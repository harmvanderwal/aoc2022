package com.codedependency.aoc;

import com.codedependency.aoc.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.codedependency.aoc.util.Regexes.DOUBLE_NEW_LINE_PATTERN;
import static com.codedependency.aoc.util.Regexes.NEW_LINE_PATTERN;

class DayOneTest {

	@Test
	void puzzle1() {
		Arrays.stream(DOUBLE_NEW_LINE_PATTERN.split(FileUtil.fromFile("day1.txt")))
				.mapToInt(elf -> Arrays.stream(NEW_LINE_PATTERN.split(elf))
						.mapToInt(Integer::parseInt)
						.sum())
				.max()
				.ifPresent(System.out::println);
	}

	@Test
	void puzzle2() {
		System.out.println(Arrays.stream(DOUBLE_NEW_LINE_PATTERN.split(FileUtil.fromFile("day1.txt")))
				.map(elf -> Arrays.stream(NEW_LINE_PATTERN.split(elf))
						.mapToInt(Integer::parseInt)
						.sum())
				.sorted(Collections.reverseOrder())
				.mapToInt(x -> x)
				.limit(3)
				.sum());
	}

}
