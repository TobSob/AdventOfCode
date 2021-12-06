package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LanternFishPopulation {

	private static final boolean TEST = true;

	public static void main(String[] args) {
		List<String> readFile = readFile();
		List<Fish> fishies = getInitialFish(readFile);
		runDays(TEST ? 256 : 256, fishies);
		afterPrint(fishies);
	}

	
	private static void afterPrint(List<Fish> fishies) {
		int sum = 0;
		StringBuilder sb = new StringBuilder();
		for (Iterator iterator = fishies.iterator(); iterator.hasNext();) {
			Fish fish = (Fish) iterator.next();
			sb.append(fish.day).append(",");
			sum += fish.day;
		}
		System.out.println(sb.toString());
		System.out.println(sum);
		System.out.println(fishies.size());
	}


	private static void runDays(int i, List<Fish> fishies) {
		for (int j = 0; j < i; j++) {
			List<Fish> newFishies = new ArrayList<Fish>();
			for (Iterator iterator = fishies.iterator(); iterator.hasNext();) {
				Fish fish = (Fish) iterator.next();
				fish.oneDay(newFishies);
			}
			fishies.addAll(newFishies);
		}
	}


	private static List<Fish> getInitialFish(List<String> readFile) {
		ArrayList<Fish> list = new ArrayList<>();
		String[] split = readFile().get(0).split(",");
		for (int i = 0; i < split.length; i++) {
			int initFish = Integer.parseInt(split[i]);
			list.add(new Fish(initFish));
		}
		return list;
	}


	public static List<String> readFile() {
		List<String> list = new ArrayList<>();
		String file = (TEST ? "test" : "") + "files/day6.txt";
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
