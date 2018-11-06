package file_systems;

import java.util.TreeMap;
import java.util.TreeSet;

public class ReadInt {
	char[] nonchar = { '~', '`', '!', '@', '#', '$', '%', '^', '&', '*', '}', '\'', '\\', '/', ',', '.', '|' };
	char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public ReadInt() {

	}

	public boolean checkIntWithoutValue(String readLine, String[] array1, TreeMap<String, String> keywordSet,
			TreeMap<String, Character> mymap,  int count, boolean cnt) {
		char[] array = readLine.toCharArray();
		if (array1[0].equals("সংখ্যা") == false) {
			System.out.println(count + " নম্বর লাইনে ভুল আছে");
			cnt = false;
			return cnt;
		}
		if (readLine.startsWith("সংখ্যা") == false) {
			System.out.println(count + " নম্বর লাইনে ভুল আছে");
			cnt = false;
			return cnt;
		} else {
			String t = new String(array);

			int first = t.indexOf(';');
			int last = t.lastIndexOf(';');
			int l = array1[0].length();

			if (first <= l || first != last) {
				System.out.println(count + " নম্বর লাইনে ভুল আছে");
				cnt = false;
				return cnt;
			} else {
				String sr = new String(array, l + 1, first - l - 1);
				sr = sr.trim();

				if (keywordSet.containsKey(sr) == true) {
					System.out.println(count + " নম্বর লাইনে ভুল আছে" + " কিওয়্যার্ড ভ্যারিয়েবল হতে পারবে না ");
					cnt = false;
					return cnt;

				} else {

					if (mymap.containsKey(sr) == true) {
						System.out.println(count + " নম্বর লাইনে ভুল আছে");
						cnt = false;
						return cnt;
					}

					for (int i = 0; i < nonchar.length; i++) {
						if (t.indexOf(nonchar[i]) != -1) {
							System.out.println(count + " নম্বর লাইনে ভুল আছে");
							cnt = false;
							return cnt;
						}
					}
					if (sr.length() == 0) {
						System.out.println(count + " নম্বর লাইনে ভুল আছে" + " ভ্যারিয়েবল বসাতে হবে");
						cnt = false;
						return cnt;
					}
					if (sr.length() == 1) {
						for (int i = 0; i < digit.length; i++) {
							if (sr.indexOf(digit[i]) != -1) {
								System.out.println(count + " নম্বর লাইনে  ভ্যারিয়েবল  বসানো ভুল হয়েছে");
								cnt = false;
								return cnt;
							}
						}
					}

				}
			}
		}
		return cnt;
	}

	public boolean checkIntWithValue(String readLine, String[] array1, String sr, TreeMap<String, Character> mymap,
			TreeMap<String, String> keywordSet, int count, boolean cnt) {
		if (array1[0].equals("সংখ্যা") == false) {
			System.out.println(count + " নম্বর লাইনে ভুল আছে");
			cnt = false;
			return cnt;
		} else if (keywordSet.containsKey(sr) == true && cnt == true) {
			System.out.println(count + " নম্বর লাইনে ভুল আছে ।" + " কিওয়্যার্ড ভ্যারিয়েবল হতে পারবে না ");
			cnt = false;
			return cnt;

		} else if (cnt == true && mymap.containsKey(sr)) {
			System.out.println(count + " নম্বর লাইনে ভুল আছে ");
			cnt = false;
			return cnt;
		}
		return cnt;
	}
}
