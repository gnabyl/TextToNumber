import java.text.Normalizer;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.commons.lang3.StringEscapeUtils;

public class TextToNumber {
	
	static Scanner sc = new Scanner(System.in);
	static String input;
	static String[] spliter = new String[] {
		"tỷ", "tỉ", "triệu", "vạn", "nghìn", "ngàn", "trăm", "mươi", "mười", "linh", "lẻ"
	};
	
	static HashMap<String, Long> value = new HashMap<>();
	
	static void inputData() {
		System.out.print("Input: ");
		input = sc.nextLine();
		input = StringEscapeUtils.unescapeJava(input);
		input = Normalizer.normalize(input, Normalizer.Form.NFC);
	}
	
	private static long wordsToNumber(String words) {
		if (words.equals(""))
			return -1;
		long result = 0, temp;
		int pos = -1;
		boolean found = false;
		StringBuilder strBuild = new StringBuilder(words);
		
		for (String s : spliter) {
			pos = words.indexOf(s);
			if (pos >= 0) {
				found = true;
				temp = wordsToNumber(words.substring(0, pos).trim());
				if (temp == -1)
					result += value.get(s);
				else
					result += temp * value.get(s);
				strBuild.delete(0, pos + s.length());
				while (strBuild.indexOf(s) >= 0) {
					result *= value.get(s);
					strBuild.delete(0, strBuild.indexOf(s) + s.length());
				}
				words = strBuild.toString();
			}
			if (words.equals(""))
				return result;
		}
		
		if (!found)
			return value.get(words.trim());
		else {
			temp = wordsToNumber(words.trim());
			if (temp != -1)
				result += temp;
		}
		
		return result;
	}
	
	private static void initialize() {
		value.put("tỷ", (long) 1000000000);
		value.put("tỉ", (long) 1000000000);
		value.put("triệu", (long) 1000000);
		value.put("vạn", (long) 10000);
		value.put("nghìn", (long) 1000);
		value.put("ngàn", (long) 1000);
		value.put("trăm", (long) 100);
		value.put("mươi", (long) 10);
		value.put("mười", (long) 10);
		value.put("không", (long) 0);
		value.put("linh", (long) 0);
		value.put("lẻ", (long) 0);
		value.put("một", (long) 1);
		value.put("mốt", (long) 1);
		value.put("hai", (long) 2);
		value.put("ba", (long) 3);
		value.put("tư", (long) 4);
		value.put("bốn", (long) 4);
		value.put("năm", (long) 5);
		value.put("lăm", (long) 5);
		value.put("nhăm", (long) 5);
		value.put("sáu", (long) 6);
		value.put("bảy", (long) 7);
		value.put("tám", (long) 8);
		value.put("chín", (long) 9);
		
	}
	
	public static void main(String args[]) {
		inputData();
		initialize();
		System.out.println(wordsToNumber(input));
	}	
}
