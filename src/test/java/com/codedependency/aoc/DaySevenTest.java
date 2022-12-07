package com.codedependency.aoc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
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
			Directory beforeCommand = curr;
			Directory parent = curr.parentDirectory;
			curr = curr.executeCommand(command);
			if (curr != beforeCommand && curr != parent) {
				directories.add(curr);
			}
		}
		return directories;
	}

	static class Directory {

		private static final Pattern FILE_PATTERN = Pattern.compile("^(\\d+) .*$", Pattern.MULTILINE);

		private final Directory parentDirectory;

		private final List<Directory> subDirectories;

		private int folderContentSize = 0;

		public Directory(Directory parentDirectory) {
			this.parentDirectory = parentDirectory;
			this.subDirectories = new ArrayList<>();
		}

		public Directory executeCommand(String command) {
			if (command.startsWith("$ cd /") && parentDirectory == null) {
				return this;
			}
			if (command.startsWith("$ cd ..")) {
				return parentDirectory;
			}
			if (command.startsWith("$ cd")) {
				Directory subDirectory = new Directory(this);
				subDirectories.add(subDirectory);
				return subDirectory;
			}
			if (command.startsWith("$ ls")) {
				Matcher matcher = FILE_PATTERN.matcher(command);
				while (matcher.find()) {
					folderContentSize += Integer.parseInt(matcher.group(1));
				}
				return this;
			}
			return this;
		}

		public int getSize() {
			return folderContentSize + subDirectories.stream()
					.mapToInt(Directory::getSize)
					.sum();
		}
	}
}
