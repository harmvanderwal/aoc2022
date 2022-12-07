package com.codedependency.aoc;

import com.codedependency.day.seven.Directory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.codedependency.aoc.util.FileUtil.fromFile;

class DaySevenTest {

	private static final Pattern COMMAND_PATTERN = Pattern.compile("(?=^\\$)", Pattern.MULTILINE);

	@Test
	void puzzleOne() {
		System.out.println(getDirectoriesFromCommands().stream()
				.mapToInt(Directory::getSize)
				.filter(x -> x < 100_000)
				.sum());
	}

	@Test
	void puzzleTwo() {
		List<Directory> directories = getDirectoriesFromCommands();
		int spaceUnused = 70_000_000 - directories.get(0).getSize();
		int spaceNeeded = 30_000_000 - spaceUnused;
		System.out.println(directories.stream()
				.mapToInt(Directory::getSize)
				.sorted()
				.filter(x -> x > spaceNeeded)
				.findFirst().orElseThrow());
	}

	private List<Directory> getDirectoriesFromCommands() {
		List<Directory> directories = new ArrayList<>();
		directories.add(new Directory(null));
		Directory curr = directories.get(0);
		for (String command : COMMAND_PATTERN.split(fromFile("day7.txt"))) {
			curr = curr.executeCommand(command);
			if (!directories.contains(curr)) {
				directories.add(curr);
			}
		}
		return directories;
	}
}
