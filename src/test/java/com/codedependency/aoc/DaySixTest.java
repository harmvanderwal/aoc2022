package com.codedependency.aoc;

import com.codedependency.aoc.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.HashSet;

class DaySixTest {

	@Test
	void puzzleOne() {
		findMarkerByNumberOfDistinctCharacters(4);
	}

	@Test
	void puzzleTwo() {
		findMarkerByNumberOfDistinctCharacters(14);
	}

	private void findMarkerByNumberOfDistinctCharacters(int distinctNoOfChars) {
		char[] chars = FileUtil.fromFile("day6.txt").toCharArray();
		ArrayDeque<Character> characterDeque = new ArrayDeque<>();
		for(int i = 0; i < chars.length; i++) {
			characterDeque.offerLast(chars[i]);
			if (characterDeque.size() == distinctNoOfChars) {
				if (new HashSet<>(characterDeque).size() == distinctNoOfChars) {
					System.out.println("Answer: " + (i + 1));
					break;
				}
				characterDeque.removeFirst();
			}
		}
	}
}
