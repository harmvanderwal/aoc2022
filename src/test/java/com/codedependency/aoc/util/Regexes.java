package com.codedependency.aoc.util;

import java.util.regex.Pattern;

public class Regexes {

	public static final Pattern DOUBLE_NEW_LINE_PATTERN = Pattern.compile(System.lineSeparator() + System.lineSeparator());

	public static final Pattern NEW_LINE_PATTERN = Pattern.compile(System.lineSeparator());
}
