package com.codedependency.aoc;

import com.codedependency.aoc.util.FileUtil;
import com.codedependency.aoc.util.Regexes;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.regex.Pattern;

class DayFourTest {

	private static final Pattern ELVES_SPLITTER_PATTERN = Pattern.compile(",");

	@Test
	void puzzleOne() {
		System.out.println(Arrays.stream(Regexes.NEW_LINE_PATTERN.split(FileUtil.fromFile("day4.txt")))
				.map(ELVES_SPLITTER_PATTERN::split)
				.filter(this::isFullOverlap)
				.count());
	}

	@Test
	void puzzleTwo() {
		System.out.println(Arrays.stream(Regexes.NEW_LINE_PATTERN.split(FileUtil.fromFile("day4.txt")))
				.map(ELVES_SPLITTER_PATTERN::split)
				.filter(this::isAnyOverlap)
				.count());
	}

	private boolean isAnyOverlap(String[] elves) {
		return isOverlap(elves, false);
	}

	private boolean isFullOverlap(String[] elves) {
		return isOverlap(elves, true);
	}

	private boolean isOverlap(String[] elves, boolean fullOverlap) {
		String elf1 = elves[0];
		int startElf1 = Integer.parseInt(elf1.substring(0, elf1.indexOf('-')));
		int endElf1 = Integer.parseInt(elf1.substring(elf1.indexOf('-') + 1));
		String elf2 = elves[1];
		int startElf2 = Integer.parseInt(elf2.substring(0, elf2.indexOf('-')));
		int endElf2 = Integer.parseInt(elf2.substring(elf2.indexOf('-') + 1));
		if (fullOverlap) {
			return isFullOverlap(startElf1, endElf1, startElf2, endElf2);
		}
		return isAnyOverlap(startElf1,endElf1, startElf2, endElf2);
	}

	private boolean isAnyOverlap(int startOne, int endOne, int startTwo, int endTwo) {
		return (startOne <= startTwo && endOne >= startTwo)
				|| (startTwo <= startOne & endTwo >= startOne)
				|| (endOne >= startTwo && endOne <= endTwo)
				|| (endTwo >= startOne && endTwo <= endOne);
	}

	private boolean isFullOverlap(int startOne, int endOne, int startTwo, int endTwo) {
		return (startOne <= startTwo && endOne >= endTwo)
				|| (startTwo <= startOne && endTwo >= endOne);
	}
}
