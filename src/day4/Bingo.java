package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bingo {

	private static final boolean TEST = false;
	public static void main(String[] args) {
		List<String> readFile = readFile();
		int[] drawNumbers = getDrawNumbers(readFile);
		List<BingoField> fields = readBingoFields(readFile);
		playGame(fields, drawNumbers);
		playSquidGame(fields, drawNumbers);
	}

	private static void playGame(List<BingoField> fields, int[] drawNumbers) {
		for (int i = 0; i < drawNumbers.length; i++) {
			int draw = drawNumbers[i];
			for (BingoField bf : fields) {
				int checkWin = bf.check(draw);
				if(checkWin > 0) {
					System.out.println(bf);
					return;
				}
			}
		}
		
	}
	private static void playSquidGame(List<BingoField> fields, int[] drawNumbers) {
		List<BingoField> winners = new ArrayList<> ();
		for (int i = 0; i < drawNumbers.length; i++) {
			int draw = drawNumbers[i];
			for (BingoField bf : fields) {
				int checkWin = bf.check(draw);
				if(checkWin > 0) {
					winners.add(bf);
				}
			}
		}
		System.out.println(winners.get(winners.size()-1));
	}

	private static List<BingoField> readBingoFields(List<String> readFile) {
		List<BingoField> fields = new ArrayList<>();
		BingoField bf = null;
		for (int i = 1; i < readFile.size(); i++) {
			String line = readFile.get(i);
			if(line.isEmpty()) {
				if(bf != null) {
					fields.add(bf);
				}
				bf = new BingoField();
			} else {
				bf.addRow(getRowNumbers(line));
			}
		}
		fields.add(bf);
		return fields;
	}

	private static int[] getRowNumbers(String line) {
		int[] intArray = new int[5];
		Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(line);
        int i = 0;
        while(m.find()) {
            intArray[i] = Integer.parseInt(m.group());
            i++;
        }
		return intArray;
	}

	private static int[] getDrawNumbers(List<String> readFile) {
		String firstLine = readFile.get(0);
		String[] strArray = firstLine.split(",");
		int[] intArray = new int[strArray.length];
		for(int i = 0; i < strArray.length; i++) {
		    intArray[i] = Integer.parseInt(strArray[i]);
		}
		return intArray;
	}

	public static List<String> readFile() {
		List<String> list = new ArrayList<>();
		String file = (TEST ? "test": "") + "files/day4.txt";
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

}
