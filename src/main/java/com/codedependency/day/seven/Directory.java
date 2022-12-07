package com.codedependency.day.seven;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Directory {

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

	public List<Directory> getSubDirectories() {
		return subDirectories;
	}

	@Override
	public int hashCode() {
		return Objects.hash(folderContentSize, parentDirectory, subDirectories);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Directory directory = (Directory) o;
		return Objects.equals(folderContentSize, directory.folderContentSize) && Objects.equals(subDirectories, directory.subDirectories);
	}
}
