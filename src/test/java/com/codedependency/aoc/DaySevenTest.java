package com.codedependency.aoc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
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
		Terminal terminal = new Terminal(new Directory(null));
		List<Directory> directories = new ArrayList<>();
		directories.add(terminal.pwd);
		for (String command : COMMAND_PATTERN.split(fromFile("day7.txt"))) {
			if (terminal.isNewPwdAfterCommand(command)) {
				directories.add(terminal.pwd);
			}
		}
		return directories;
	}

	static class Directory {

		private final Directory parentDirectory;

		private final List<Directory> subDirectories = new ArrayList<>();

		private int folderContentSize = 0;

		public Directory(Directory parentDirectory) {
			this.parentDirectory = parentDirectory;
		}

		public int getSize() {
			return folderContentSize + subDirectories.stream()
					.mapToInt(Directory::getSize)
					.sum();
		}
	}

	static class Terminal {

		private static final Pattern FILE_PATTERN = Pattern.compile("^(\\d+) .*$", Pattern.MULTILINE);

		private Directory pwd;

		public Terminal(Directory pwd) {
			this.pwd = pwd;
		}

		public boolean isNewPwdAfterCommand(String command) {
			if (command.startsWith("$ cd /") && pwd.parentDirectory == null) {
				return false;
			}
			if (command.startsWith("$ cd ..")) {
				pwd = pwd.parentDirectory;
				return true;
			}
			if (command.startsWith("$ cd")) {
				Directory subDirectory = new Directory(pwd);
				pwd.subDirectories.add(subDirectory);
				pwd = subDirectory;
				return true;
			}
			if (command.startsWith("$ ls")) {
				Matcher matcher = FILE_PATTERN.matcher(command);
				while (matcher.find()) {
					pwd.folderContentSize += Integer.parseInt(matcher.group(1));
				}
				return false;
			}
			return false;
		}
	}
}
