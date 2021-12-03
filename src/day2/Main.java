package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	
	static int horizontal = 0;
	static int depth = 0;
	static int aim = 0;
	
	public static void main(String[] args) {
		List<String> readFile = readFile();
		for (Iterator iterator = readFile.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			String[] split = string.split(" ");
			moving(split[0], Integer.parseInt(split[1]));
		}
		System.out.println(horizontal);
		System.out.println(depth);
		System.out.println(horizontal * depth);
		reset();
		for (Iterator iterator = readFile.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			String[] split = string.split(" ");
			moving2(split[0], Integer.parseInt(split[1]));
		}
		System.out.println(horizontal);
		System.out.println(depth);
		System.out.println(horizontal * depth);
	}

	private static void reset() {
		horizontal = 0;
		depth = 0;
		aim = 0;
	}

	public static List<String> readFile() {
		List<String> list = new ArrayList<>();
		try(Scanner scanner = new Scanner(new File("files/day2.txt"))) {
			while (scanner.hasNext()) {
				list.add(scanner.next() + " " + scanner.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void moving(String where, int steps) {
		if("forward".equals(where)) {
			horizontal += steps;
		}
		else if("down".equals(where)) {
			depth += steps;
		}
		else if("up".equals(where)) {
			depth -= steps;
		}
	}
	
	public static void moving2(String where, int steps) {
		if("forward".equals(where)) {
			horizontal += steps;
			depth += steps * aim;
		}
		else if("down".equals(where)) {
			aim += steps;
		}
		else if("up".equals(where)) {
			aim -= steps;
		}
	}
}
