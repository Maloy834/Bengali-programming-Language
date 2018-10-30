package file_systems;

import java.util.TreeMap;

public class PrintLine {

	public PrintLine() {

	}

	public boolean printLinecheckWithoutQuote(String readLine, String[] array1, TreeMap<String, Character> mymap,
			int count, boolean cnt) {

		char[] array = readLine.toCharArray();
		String t = new String(array);
		int firstbracket = t.indexOf('(');
		int lastbracket = t.lastIndexOf(')');
		if (t.indexOf('"') != -1) {
			System.out.println(count + " নম্বর লাইনে ভুল আছে ");
			cnt = false;
			return cnt;
		}

		String sr1 = new String(array, firstbracket + 1, lastbracket - firstbracket - 1);
		sr1 = sr1.trim();

		if (sr1.length() == 0 && mymap.get(sr1) == null) {
			System.out.println(count + " নম্বর লাইনে ভুল আছে ");
			cnt = false;
			return cnt;
		}
		
		return cnt;

	}

	public boolean PrintLineCheckWithQuote(String readLine, int count, boolean cnt) {
		char[] array = readLine.toCharArray();
		String t = new String(array);

		int first = t.indexOf('"');
		int last = t.lastIndexOf('"');
		String line;
		if (first != last) {

			line = new String(array, first + 1, last - first - 1);
			if (line.length() == 0) {
				System.out.println(count + " নম্বর লাইনে ভুল আছে ");
				cnt = false;
				return cnt;

			} else if (line.indexOf('"') != -1) {
				System.out.println(count + " নম্বর লাইনে ভুল আছে ");
				cnt = false;
				return cnt;
			}
		}
		return cnt;
	}
}
