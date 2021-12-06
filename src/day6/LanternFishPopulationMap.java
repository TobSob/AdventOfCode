package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LanternFishPopulationMap {

	private static final boolean TEST = false;

	public static void main(String[] args) {
		List<String> readFile = readFile();
		Map<Long, Long> fishies = getInitialFish(readFile);
		fishies = runDays(256, fishies);
		afterPrint(fishies);
	}

	private static void afterPrint(Map<Long, Long> fishies) {
		long sum = 0;
		Set<Long> keySet = fishies.keySet();
		for (Long Long : keySet) {
			Long numOfFish = fishies.get(Long);
			sum += numOfFish;
		}
		System.out.println(sum);
	}

	private static Map<Long, Long> runDays(int i, Map<Long, Long> fishies) {
		for (int j = 0; j < i; j++) {
			Set<Long> keySet = fishies.keySet();
			Map<Long, Long> newFishies = new HashMap<>();
			for (Long l : keySet) {
				Long numOfFish = fishies.get(l);
				if (l < 1) {
					putToMap(newFishies, 6L, numOfFish);
					putToMap(newFishies, 8L, numOfFish);
				}
				else {
					putToMap(newFishies, l - 1, numOfFish);
				}
			}
			if (TEST) {
				System.out.println(fishies.toString());
			}
			fishies = newFishies;
		}
		return fishies;
	}

	private static Map<Long, Long> getInitialFish(List<String> readFile) {
		Map<Long, Long> fishies = new HashMap<>();
		String[] split = readFile.get(0).split(",");
		for (int i = 0; i < split.length; i++) {
			long initFish = Long.parseLong(split[i]);
			putToMap(fishies, initFish, 1l);
		}
		return fishies;
	}

	private static void putToMap(Map<Long, Long> fishies, long l, Long numOfFish) {
		if (fishies.containsKey(l)) {
			fishies.put(l, fishies.get(l) + numOfFish);
		}
		else {
			fishies.put(l, numOfFish);
		}
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
