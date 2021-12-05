package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HydrothermalVenture {

	private static final boolean TEST = false;

	public static void main(String[] args) {
		List<String> readFile = readFile();
		List<VentEntry> allEntries = readAllEntries(readFile);
		int[][] vents = createField(allEntries);
		insertEntries(allEntries, vents);
		int countOverlapping = countOverlapping(vents);
		System.out.println(Arrays.deepToString(vents).replace("], ", "]\n "));
		System.out.println(countOverlapping);
	}

	private static int countOverlapping(int[][] vents) {
		int count = 0;
		for (int i = 0; i < vents.length; i++) {
			for (int j = 0; j < vents[i].length; j++) {
				if (vents[i][j] > 1) {
					count++;
				}
			}
		}
		return count;
	}

	private static void insertEntries(List<VentEntry> allEntries, int[][] vents) {
		for (VentEntry ventEntry : allEntries) {
			vents[ventEntry.x][ventEntry.y]++;
		}
	}

	private static int[][] createField(List<VentEntry> allEntries) {
		int maxX = 0;
		int maxY = 0;
		for (VentEntry ventEntry : allEntries) {
			maxX = Math.max(maxX, ventEntry.x);
			maxY = Math.max(maxY, ventEntry.y);
		}
		return new int[maxX + 1][maxY + 1];
	}

	private static List<VentEntry> readAllEntries(List<String> readFile) {
		List<VentEntry> entries = new ArrayList<>();
		for (String string : readFile) {
			addingEntries(entries, string);

		}
		return entries;
	}

	private static void addingEntries(List<VentEntry> entries, String string) {
		String[] split = string.split(" -> ");
		String[] start = split[0].split(",");
		String[] end = split[1].split(",");
		final int startX = Integer.parseInt(start[0]);
		final int startY = Integer.parseInt(start[1]);
		final int endX = Integer.parseInt(end[0]);
		final int endY = Integer.parseInt(end[1]);
		if (startX != endX && startY != endY) {
			addDiagonal(entries, startX, startY, endX, endY);
		}
		else if (startX == endX) {
			addYLine(entries, startX, startY, endY);
		}
		else {
			addXLine(entries, startX, startY, endX);
		}
		entries.add(new VentEntry(endX, endY));
	}

	private static void addXLine(List<VentEntry> entries, final int startX, final int startY,
		final int endX) {
		int y = startY;
		for (int x = startX; x != endX; x += startX > endX ? -1 : 1) {
			entries.add(new VentEntry(x, y));
		}
	}

	private static void addYLine(List<VentEntry> entries, final int startX, final int startY,
		final int endY) {
		int x = startX;
		for (int y = startY; y != endY; y += startY > endY ? -1 : 1) {
			entries.add(new VentEntry(x, y));
		}
	}

	private static void addDiagonal(List<VentEntry> entries, final int startX, final int startY,
		final int endX, final int endY) {
		int x = startX;
		int y = startY;
		while (x != endX && y != endY) {
			entries.add(new VentEntry(x, y));
			x += startX > endX ? -1 : 1;
			y += startY > endY ? -1 : 1;
		}
	}

	public static List<String> readFile() {
		List<String> list = new ArrayList<>();
		String file = (TEST ? "test" : "") + "files/day5.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

}
