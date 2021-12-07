package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Crabs {

	private static final boolean TEST = false;

	public static void main(String[] args) {
		List<String> readFile = readFile();
		List<Integer> crabs = getInitialFish(readFile);
		Integer min = Collections.min(crabs);
		Integer max = Collections.max(crabs);
		List<Integer> calculateFuelCosts = calculateFuelCosts(crabs, min, max);
		List<Integer> calculateFuelCostsComplex = calculateFuelCostsComplex(crabs, min, max);
		Integer lowestCosts = Collections.min(calculateFuelCosts);
		Integer lowestCostsComplex = Collections.min(calculateFuelCostsComplex);
		System.out.println(lowestCosts);
		System.out.println(lowestCostsComplex);
	}

	private static List<Integer> calculateFuelCosts(List<Integer> crabs, Integer min, Integer max) {
		List<Integer> allCosts = new ArrayList<>();
		for (int i = min; i < max; i++) {
			int totalCosts = 0;
			for (Integer pos : crabs) {
				int costs = Math.abs(pos - i);
				totalCosts += costs;
			}
			allCosts.add(totalCosts);
		}
		return allCosts;
	}

	private static List<Integer> calculateFuelCostsComplex(List<Integer> crabs, Integer min,
		Integer max) {
		List<Integer> allCosts = new ArrayList<>();
		for (int i = min; i < max; i++) {
			int totalCosts = 0;
			for (Integer pos : crabs) {
				int diff = Math.abs(pos - i);
				totalCosts += diff * (diff + 1) / 2;
			}
			allCosts.add(totalCosts);
		}
		return allCosts;
	}

	private static List<Integer> getInitialFish(List<String> readFile) {
		ArrayList<Integer> list = new ArrayList<>();
		String[] split = readFile.get(0).split(",");
		for (int i = 0; i < split.length; i++) {
			int initFish = Integer.parseInt(split[i]);
			list.add(initFish);
		}
		return list;
	}

	public static List<String> readFile() {
		List<String> list = new ArrayList<>();
		String file = (TEST ? "test" : "") + "files/day7.txt";
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
