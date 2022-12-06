package com.codedependency.aoc;

import com.codedependency.aoc.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codedependency.aoc.util.Regexes.DOUBLE_NEW_LINE_PATTERN;
import static com.codedependency.aoc.util.Regexes.NEW_LINE_PATTERN;

class DayFiveTest {

	private static final Pattern CHARACTER_PATTERN = Pattern.compile("\\[([A-Z])]");

	private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("^move (\\d*) from (\\d*) to (\\d*)$");

	@Test
	void puzzleOne() {
		solvePuzzle(1);
	}

	@Test
	void puzzleTwo() {
		solvePuzzle(2);
	}

	private List<Deque<Character>> cloneDequeList(List<Deque<Character>> dequeList) {
		List<Deque<Character>> dequeListClone = new ArrayList<>();
		for (Deque<Character> deque : dequeList) {
			dequeListClone.add(((ArrayDeque<Character>)deque).clone());
		}
		return dequeListClone;
	}

	private void executeInstruction9000(List<Deque<Character>> dequeList, String instruction) {
		Matcher matcher = INSTRUCTION_PATTERN.matcher(instruction);
		if (matcher.find()) {
			for (int i = 0; i < Integer.parseInt(matcher.group(1)); i++) {
				int popStack = Integer.parseInt(matcher.group(2)) - 1;
				int offerStack = Integer.parseInt(matcher.group(3)) - 1;
				dequeList.get(offerStack).offer(dequeList.get(popStack).removeLast());
			}
		}
	}

	private void executeInstruction9001(List<Deque<Character>> dequeList, String instruction) {
		Matcher matcher = INSTRUCTION_PATTERN.matcher(instruction);
		if (matcher.find()) {
			Deque<Character> characterDeque = new ArrayDeque<>();
			int amount = Integer.parseInt(matcher.group(1));
			int popStack = Integer.parseInt(matcher.group(2)) - 1;
			int offerStack = Integer.parseInt(matcher.group(3)) - 1;
			for (int i = 0; i < amount; i++) {
				characterDeque.offer(dequeList.get(popStack).removeLast());
			}
			for (int i = 0; i < amount; i++) {
				dequeList.get(offerStack).offer(characterDeque.removeLast());
			}
		}
	}

	private List<Deque<Character>> generateDeques(String[] strings) {
		List<Deque<Character>> dequeList = new ArrayList<>();
		double listEntries = -Math.floorDiv(-strings[0].length(), 4);
		for (int i = 0; i < listEntries; i++) {
			dequeList.add(new ArrayDeque<>());
		}
		for (int i = strings.length - 2; i >= 0; i--) {
			int lineLength = strings[i].length();
			int j = 0;
			int k = 0;
			do {
				String subStr = strings[i].substring(k).length() == 3
						? strings[i].substring(k)
						: strings[i].substring(k, k + 4);
				Matcher matcher = CHARACTER_PATTERN.matcher(subStr);
				if (matcher.find()) {
					dequeList.get(j).offer(matcher.group(1).charAt(0));
				}
				j++;
			}
			while ((k+=4) < lineLength);
		}
		return dequeList;
	}

	private void printDequeList(List<Deque<Character>> dequeList) {
		dequeList = cloneDequeList(dequeList);
		List<String> outputReversed = new ArrayList<>();
		int maxDequeSize = dequeList.stream().mapToInt(Deque::size).max().orElseThrow();
		for (int i = 0; i < maxDequeSize; i++) {
			StringBuilder sb = new StringBuilder();
			for (Deque<Character> characters : dequeList) {
				if (characters.peekFirst() != null) {
					sb.append(String.format("[%s] ", characters.removeFirst()));
				} else {
					sb.append("    ");
				}
			}
			outputReversed.add(sb.toString());
		}
		for (int i = outputReversed.size() - 1; i >= 0; i--) {
			System.out.println(outputReversed.get(i));
		}
		System.out.println(" 1   2   3   4   5   6   7   8   9");
		System.out.println();
	}

	private void solvePuzzle(int puzzle) {
		String[] splitString = DOUBLE_NEW_LINE_PATTERN.split(FileUtil.fromFile("day5.txt"));
		List<Deque<Character>> dequeList = generateDeques(NEW_LINE_PATTERN.split(splitString[0]));
		String[] instructions = NEW_LINE_PATTERN.split(splitString[1]);
		printDequeList(dequeList);
		for (String instruction : instructions) {
			System.out.println(instruction + ":");
			switch (puzzle) {
				case 1 -> executeInstruction9000(dequeList, instruction);
				case 2 -> executeInstruction9001(dequeList, instruction);
			}
			printDequeList(dequeList);
		}
		for (Deque<Character> characters : dequeList) {
			System.out.print(characters.peekLast());
		}
		System.out.println();
		System.out.println();
	}
}
