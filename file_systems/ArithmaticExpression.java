package file_systems;

import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class ArithmaticExpression {
	char[] operators = { '+', '-', '*', '/', '(', ')' };

	public ArithmaticExpression() {

	}

	public boolean GenerateArithmaticExpression(String readLine, String[] allvariable, TreeMap<String, Character> mymap,
			TreeSet<String> myIntset, TreeSet<String> myStringset, int count, boolean flag) {
		Stack<Character> bracket = new Stack<>();
		char[] array = readLine.toCharArray();
		for (int i = 0; i < operators.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (operators[i] == array[j]) {
					if (array[j] == '(') {
						bracket.push('(');
					} else if (array[j] == ')') {
						if (!bracket.empty()) {
							bracket.pop();
						} else {
							System.out.println(count + " নম্বর লাইনে ভুল আছে1");
							flag = false;
							return flag;
						}
					}
					array[j] = ' ';
				}
			}
		}
		if (!bracket.empty()) {
			System.out.println(count + " নম্বর লাইনে ভুল আছে2");
			flag = false;
			return flag;
		} else {
			String line = new String(array);
			allvariable = line.split(" ");
			for (int i = 0; i < allvariable.length; i++) {
				if (myIntset.contains(allvariable[i])) {
					char ch = mymap.get(allvariable[i]);
					allvariable[i] = Character.toString(ch);
				} else {

					if (allvariable[i].contains("\"")) {
						System.out.println(count + " নম্বর লাইনে ভুল আছে3");
						flag = false;
						return flag;
					}
					boolean check = true;
					for (char c : allvariable[i].toCharArray()) {
						if (Character.isDigit(c)) {
							continue;
						} else {
							check = false;
							break;
						}
					}
					if (check == false) {
						System.out.println(count + " নম্বর লাইনে ভুল আছে4");
						flag = false;
						return flag;
					}
				}
			}
		}
		return flag;
	}

	
}
