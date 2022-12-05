package com.codedependency.aoc;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

class DayOneTest {

	@Test
	void puzzle1() {
		Arrays.stream(FileUtil.fromFile("day1.txt").split("\n\n"))
				.mapToInt(elf -> Arrays.stream(elf.split("\n"))
						.mapToInt(Integer::parseInt)
						.sum())
				.max()
				.ifPresent(System.out::println);
	}

	@Test
	void puzzle2() {
		System.out.println(Arrays.stream(FileUtil.fromFile("day1.txt").split("\n\n"))
				.map(elf -> Arrays.stream(elf.split("\n"))
						.mapToInt(Integer::parseInt)
						.sum())
				.sorted(Collections.reverseOrder())
				.mapToInt(x -> x)
				.limit(3)
				.sum());
	}

}
