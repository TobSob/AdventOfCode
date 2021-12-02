package day_one;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Measurement {

	private static final int MAX_MES = 3;

	public static void main(String[] args) {
		List<Integer> measurements = readMeasurements();
		System.out.println(countIncreases(measurements));
		System.out.println(countIncreasesSum(measurements));
	}

	public static List<Integer> readMeasurements() {
		ArrayList<Integer> list = new ArrayList<>();
		try(Scanner scanner = new Scanner(new File("puzzle_input.txt"))) {
			while (scanner.hasNextInt()) {
				list.add(scanner.nextInt());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static int countIncreases(List<Integer> measurements) {
		int count = 0;
		int last = -1;
		for (Integer mes : measurements) {
			if (last != -1 && mes > last) {
				count++;
			}
			last = mes;
		}
		return count;
	}

	public static int countIncreasesSum(List<Integer> measurements) {
		ArrayList<Integer> sums = new ArrayList<>();
		for (int i = 0; i < measurements.size() - MAX_MES + 1 ; i++) {
			ArrayList<Integer> list = new ArrayList<>();
			list.add(measurements.get(i));
			list.add(measurements.get(i + 1));
			list.add(measurements.get(i + 2));
			sums.add(findSum(list));
		}
		return countIncreases(sums);
	}

	public static int findSum(Collection<Integer> lastMes) {
		int sum = 0;
		for (Integer integer : lastMes) {
			sum += integer;
		}
		return sum;
	}
}
