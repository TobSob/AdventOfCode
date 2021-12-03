package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

	private static final boolean TEST = false;
	public static void main(String[] args) {
		List<String> readFile = readFile();
		String[] findBinary = findBinary(readFile);
		int gamma = Integer.parseInt(findBinary[0], 2);
		int epsy = Integer.parseInt(findBinary[1], 2);
		System.out.println(gamma);
		System.out.println(epsy);
		System.out.println(gamma * epsy);
		String boxygen = findOxygen(readFile);
		String bco2 = findCO2(readFile);
		int oxygen = Integer.parseInt(boxygen, 2);
		int co2 = Integer.parseInt(bco2, 2);
		System.out.println(oxygen);
		System.out.println(co2);
		System.out.println(oxygen * co2);
	}

	public static List<String> readFile() {
		List<String> list = new ArrayList<>();
		String file = TEST ? "test": "" + "files/day3.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static String[] findBinary(List<String> list) {
		int[][] count = new int[list.get(0).length()][2];
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			for (int i = 0; i < count.length; i++) {
				int numericValue = Character.getNumericValue(string.charAt(i));
				count[i][numericValue]++;
			}
		}
		StringBuilder bGamma = new StringBuilder();
		StringBuilder bEpssy = new StringBuilder();
		for (int i = 0; i < count.length; i++) {
			if (count[i][0] > count[i][1]) {
				bGamma.append("0");
				bEpssy.append("1");
			} else {
				bGamma.append("1");
				bEpssy.append("0");
			}
		}
		return new String[] { bGamma.toString(), bEpssy.toString() };
	}

	public static String findOxygen(List<String> param) {
		int posInBinArr = 0;
		return findBest(param, posInBinArr);
	}

	public static String findCO2(List<String> param) {
		int posInBinArr = 1;
		return findBest(param, posInBinArr);
	}

	private static String findBest(List<String> param, int posInBinArr) {
		List<String> list = new ArrayList<>(param);
		int length = list.get(0).length();
		for (int i = 0; i < length && list.size() > 1; i++) {
			String[] findBinary = findBinary(list);
			String beps = findBinary[posInBinArr];
			list = filter(list, beps, i);
		}
		return list.get(0);
	}

	private static List<String> filter(List<String> list, String bgamma, int i) {
		List<String> ret = new ArrayList<>();
		for (String string : list) {
			if (string.charAt(i) == bgamma.charAt(i)) {
				ret.add(string);
			}
		}
		return ret;
	}
}
