package com.codedependency.aoc;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.codedependency.aoc.util.FileUtil.fromFile;
import static com.codedependency.aoc.util.Regexes.NEW_LINE_PATTERN;

class DayEightTest {

	@Test
	void puzzleOne() {
		System.out.println(countVisibleTrees(getGrid()));
	}

	@Test
	void puzzleTwo() {
		System.out.println(getLargestViewingDistance(getGrid()));
	}

	private int countVisibleTrees(char[][] grid) {
		int sum = 2 * (grid.length + grid[0].length - 2);
		for (int i = 1; i < grid.length - 1; i++) {
			for (int j = 1; j < grid[i].length - 1; j++) {
				if (isVisible(grid, i, j)) {
					sum++;
				}
			}
		}
		return sum;
	}

	private char[][] getGrid() {
		List<char[]> list =  Arrays.stream(NEW_LINE_PATTERN.split(fromFile("day8.txt")))
				.map(String::toCharArray)
				.toList();
		char[][] grid = new char[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			grid[i] = list.get(i);
		}
		return grid;
	}

	private int getLargestViewingDistance(char[][] grid) {
		int largest = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				int dist = getViewingDistance(grid, i, j);
				if (dist > largest) {
					largest = dist;
				}
			}
		}
		return largest;
	}

	private int getViewingDistance(char[][] grid, int i, int j) {
		return getViewingDistanceBottom(grid, i, j)
				* getViewingDistanceLeft(grid, i, j)
				* getViewingDistanceRight(grid, i, j)
				* getViewingDistanceTop(grid, i, j);
	}

	private int getViewingDistanceBottom(char[][] grid, int i, int j) {
		int dist = 0;
		int val = grid[i][j];
		for (i++; i < grid.length; i++) {
			dist++;
			if (val <= grid[i][j]) {
				return dist;
			}
		}
		return dist;
	}

	private int getViewingDistanceLeft(char[][] grid, int i, int j) {
		int dist = 0;
		int val = grid[i][j];
		for (j--; j >= 0; j--) {
			dist++;
			if (val <= grid[i][j]) {
				return dist;
			}
		}
		return dist;
	}

	private int getViewingDistanceRight(char[][] grid, int i, int j) {
		int dist = 0;
		int val = grid[i][j];
		for (j++; j < grid[i].length; j++) {
			dist++;
			if (val <= grid[i][j]) {
				return dist;
			}
		}
		return dist;
	}

	private int getViewingDistanceTop(char[][] grid, int i, int j) {
		int dist = 0;
		int val = grid[i][j];
		for (i--; i >= 0; i--) {
			dist++;
			if (val <= grid[i][j]) {
				return dist;
			}
		}
		return dist;
	}

	private boolean isVisible(char[][] grid, int i, int j) {
		return isVisibleBottom(grid, i, j)
				|| isVisibleLeft(grid, i, j)
				|| isVisibleRight(grid, i, j)
				|| isVisibleTop(grid, i, j);
	}

	private boolean isVisibleBottom(char[][] grid, int i, int j) {
		int val = grid[i][j];
		for (i++ ; i < grid.length; i++) {
			if (val <= grid[i][j]) {
				return false;
			}
		}
		return true;
	}

	private boolean isVisibleLeft(char[][] grid, int i, int j) {
		int val = grid[i][j];
		for (j-- ; j >= 0; j--) {
			if (val <= grid[i][j]) {
				return false;
			}
		}
		return true;
	}

	private boolean isVisibleRight(char[][] grid, int i, int j) {
		int val = grid[i][j];
		for (j++ ; j < grid[i].length; j++) {
			if (val <= grid[i][j]) {
				return false;
			}
		}
		return true;
	}

	private boolean isVisibleTop(char[][] grid, int i, int j) {
		int val = grid[i][j];
		for (i-- ; i >= 0; i--) {
			if (val <= grid[i][j]) {
				return false;
			}
		}
		return true;
	}
}
