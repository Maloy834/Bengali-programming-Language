package file_systems;

import java.util.Stack;
import java.util.TreeSet;

import javax.lang.model.element.VariableElement;

public class ErrorChecking {

	public ErrorChecking() {

	}

	public boolean checkError(String readLine, String[] array1, int count, boolean cnt) {
		if (readLine.endsWith(";") == false && readLine.contains("শুরু") == false && readLine.length() != 0
				&& readLine.endsWith("{") == false && readLine.endsWith("}") == false
				&& array1[0].contains("যদি") == false && array1[0].contains("নাহলে") == false
				&&array1[0].contains("লুপ")==false)

		{

			System.out.println(count + " নম্বর লাইনে ভুল আছে1");
			
			cnt = false;
			return cnt;

		}
		if (readLine.endsWith(";") == true && readLine.contains("শুরু ") == true

				|| (readLine.endsWith(";") == true && readLine.contains("{") == true)
				|| (readLine.endsWith(";") == true && array1[0].equals("যদি") == true)
				|| (readLine.endsWith(";") == true && array1[0].equals("লুপ") == true)
				|| (readLine.endsWith(";") == true && array1[0].equals("নাহলে") == true)) {

			System.out.println(count + " নম্বর লাইনে ভুল আছে");

			cnt = false;
			return cnt;
		}
		return cnt;

	}

	public boolean ChecckStartWithMain(String readLine, String[] array1, char[] nonchar, int count, boolean cnt) {
		char ar[] = readLine.toCharArray();
		String s1 = new String(ar);
		s1 = s1.trim();

		for (int i = 0; i < nonchar.length; i++) {
			if (s1.indexOf(nonchar[i]) != -1) {
				System.out.println(count + " নম্বর লাইনে ভুল আছে");
				cnt = false;
				return cnt;

			}
		}

		if (cnt == true) {

			if (s1.indexOf(')') > s1.indexOf('(') && s1.indexOf('(') != -1) {

				int index = s1.indexOf('(');
				String str = new String(ar, 0, index);
				str = str.trim();
                if(count==1 && str.contains("শুরু")==false){
                	System.out.println(count + " নম্বর লাইনে ভুল আছে");
					cnt = false;
					return cnt;
                }
                else if (str.equals("শুরু") == false && count!=1) {
					System.out.println(count + " নম্বর লাইনে ভুল আছে");
					cnt = false;
					return cnt;
				}
				if (s1.indexOf('(') == s1.lastIndexOf('(') && s1.indexOf(')') == s1.lastIndexOf(')') && cnt == true) {
					if (s1.contains("{") && s1.indexOf('{') + 1 != s1.length()) {
						System.out.println(count + " নম্বর লাইনে ভুল আছে");
						cnt = false;
						return cnt;
					} else if (s1.contains("{") == false) {
						if (s1.indexOf(')') + 1 != s1.length()) {
							System.out.println(count + " নম্বর লাইনে ভুল আছে");

							cnt = false;
							return cnt;
						} else if (cnt == true) {

							int lastIndex = s1.indexOf(")");
							for (int i = index + 1; i < lastIndex; i++) {
								if (ar[i] != ' ') {
									System.out.println(count + " নম্বর লাইনে ভুল আছে");
									cnt = false;
									return cnt;
								}
							}
						}
					} else if (s1.contains("{") && cnt == true) {
						int lastIndex = s1.indexOf("{");
						for (int i = index; i < lastIndex; i++) {
							if (ar[i] != '(' && ar[i] != ' ' && ar[i] != ')') {
								System.out.println(count + " নম্বর লাইনে ভুল আছে");
								cnt = false;
								return cnt;
							}
						}
					} else if (s1.contains("{") == false && cnt == true) {
						int lastIndex = s1.indexOf(")");
						for (int i = index; i < lastIndex; i++) {
							if (ar[i] != '(' && ar[i] != ' ') {
								System.out.println(count + " নম্বর লাইনে ভুল আছে");
								cnt = false;
								return cnt;
							}
						}
					}

				} else if (s1.indexOf('(') != s1.lastIndexOf('(') && s1.indexOf(')') != s1.lastIndexOf(')')
						&& cnt == true) {
					System.out.println(count + " নম্বর লাইনে ভুল আছে");
					cnt = false;
					return cnt;
				}

			} else {
				System.out.println(count + " নম্বর লাইনে ভুল আছে");
				cnt = false;
				return cnt;
			}

		}
		return cnt;

	}

	public boolean cheeckPrintingError(String readLine, char[] nonchar, int count, boolean cnt) {
		char[] array = readLine.toCharArray();

		String t = new String(array);
		
		if (t.indexOf(';') != t.lastIndexOf(';')) {
			System.out.println(count + " নম্বর লাইনে ভুল আছে");
			cnt = false;
			return cnt;
		}
		int firstbracket = t.indexOf('(');
		int lastbracket = t.lastIndexOf(')');

		if (firstbracket == lastbracket || firstbracket == -1 || lastbracket == -1) {
			System.out.println(count + " নম্বর লাইনে ভুল আছে");
			cnt = false;
			return cnt;
		}
		if (cnt == true) {
			String str3 = new String(array, 0, firstbracket);
			str3 = str3.trim();
			if (str3.equals("দেখাও") == false) {
				System.out.println(count + " নম্বর লাইনে ভুল কিওয়্যার্ড আছে ");
				cnt = false;
				return cnt;
			}
		}
		
		return cnt;
	}

	public boolean checkConditionalError(String readLine, String[] array1, char[] nonchar, int count, boolean cnt) {
		char[] array = readLine.toCharArray();
		String t = new String(array);
		int firstBracket = t.indexOf('(');
		int firstBracketLast = t.lastIndexOf('(');
		int lastBrcket = t.indexOf(')');
		int lastBracketLast = t.lastIndexOf(')');
		String checker = readLine;
		String checkLine = new String(array, 0, firstBracket - 0);
		checkLine = checkLine.trim();
		for (int i = 0; i < nonchar.length; i++) {
			if (checker.indexOf(nonchar[i]) != -1) {
				
				System.out.println(count + " নম্বর লাইনে ভুল আছে");
				cnt = false;
				return cnt;
				
			}
		}
		if (firstBracket > lastBrcket
				|| (firstBracket != firstBracketLast && lastBrcket != lastBracketLast) && cnt == true) {
			System.out.println(count + " নম্বর লাইনে ভুল আছে");
			cnt = false;
			return cnt;
		} else if (checkLine.equals("যদি") == false) {
			System.out.println(count + " নম্বর লাইনে ভুল আছে");
			cnt = false;
			return cnt;
		}
		return cnt;
	}

	public boolean checkLoopingError(String readLine, String[] array1, char[] nonchar, int count, boolean cnt) {
		char[] array = readLine.toCharArray();
		String t = new String(array);
		int firstBracket = t.indexOf('(');
		int firstBracketLast = t.lastIndexOf('(');
		int lastBrcket = t.indexOf(')');
		int lastBracketLast = t.lastIndexOf(')');
		String checker = readLine;
		String checkLine = new String(array, 0, firstBracket - 0);
		checkLine = checkLine.trim();
		for (int i = 0; i < nonchar.length; i++) {
			if (checker.indexOf(nonchar[i]) != -1) {
				System.out.println(count + " নম্বর লাইনে ভুল আছে");
				cnt = false;
				return cnt;
			}
		}
		if (firstBracket > lastBrcket
				|| (firstBracket != firstBracketLast && lastBrcket != lastBracketLast) && cnt == true) {
			System.out.println(count + " নম্বর লাইনে ভুল আছে");
			cnt = false;
			return cnt;
		} else if (checkLine.equals("লুপ") == false) {
			System.out.println(count + " নম্বর লাইনে ভুল আছে");
			cnt = false;
			return cnt;
		}
		return cnt;
	}

	public boolean checkSpace(String readLine, int lastBrcket, int count, boolean cnt) {
		char[] array = readLine.toCharArray();
		for (int i = lastBrcket + 1; i < readLine.length() - 1; i++) {
			if (array[i] != ' ') {
				System.out.println(count + " নম্বর লাইনে ভুল আছে");
				cnt = false;
				return cnt;
			}

		}
		return cnt;
	}

	public boolean checkArithmaticError(String readLine, TreeSet<String> myIntset, TreeSet<String> myStringset,
			Stack<String> Number, int lineNumber, boolean flag) {
		Stack<Character> bracket = new Stack<Character>();
		char[] array = readLine.toCharArray();
		String[] value = new String[1000];
		int p = readLine.indexOf('=');
		int m = readLine.indexOf(';');
		for (int i = p + 1; i < m; i++) {
			if (array[i] == '(')
				bracket.push(array[i]);
			else if (array[i] == ')') {
				if (!bracket.empty()) {
					bracket.pop();
				} else {
					System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
					flag = false;
					return flag;
				}
			}
		}
		if (!bracket.empty()) {
			System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
			flag = false;
			return flag;
		} else {
			String ch;
			int k = 0;
			while (!Number.empty()) {

				ch = Number.pop();
				// System.out.println(ch);
				value[k] = ch;
				k++;
			}
			for (int i = 0; i < value.length; i++) {
				// System.out.println(value[i]);
				/*
				 * if(value[i].contains("(") ){
				 * 
				 * if(value[i+1].equals(")")||value[i+1].equals("+")||value[i+1]
				 * .equals("-")||value[i+1].equals("*")||value[i+1].equals("/"))
				 * { System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে3");
				 * flag = false; return flag; } }
				 * if(value[i].equalsIgnoreCase(")")){
				 * if(value[i+1].equals("(")){ System.out.println(lineNumber +
				 * " নম্বর লাইনে ভুল আছে4");
				 * 
				 * flag = false; return flag; } } if(value[i].equals("+")){
				 * if(value[i+1].equals(")")||value[i+1].equals("+")||value[i+1]
				 * .equals("-")||value[i+1].equals("*")||value[i+1].equals("/"))
				 * { System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে5");
				 * flag = false; return flag; } } else if(value[i].equals("-")){
				 * if(value[i+1].equals(")")||value[i+1].equals("+")||value[i+1]
				 * .equals("-")||value[i+1].equals("*")||value[i+1].equals("/"))
				 * { System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে6");
				 * flag = false; return flag; } } else if(value[i].equals("*")){
				 * if(value[i+1].equals(")")||value[i+1].equals("+")||value[i+1]
				 * .equals("-")||value[i+1].equals("*")||value[i+1].equals("/"))
				 * { System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে7");
				 * flag = false; return flag; } } else if(value[i].equals("/")){
				 * if(value[i+1].equals(")")||value[i+1].equals("+")||value[i+1]
				 * .equals("-")||value[i+1].equals("*")||value[i+1].equals("/"))
				 * { System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে8");
				 * flag = false; return flag; } } else{ boolean checkNum=true;
				 * for(char c:value[i].toCharArray()){ if(Character.isDigit(c))
				 * continue; else { checkNum=false; break; } }
				 * if(checkNum==false){ if(value[i].contains("\"")){
				 * System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে"); flag
				 * = false; return flag;
				 * 
				 * } else if(myStringset.contains(value[i])){
				 * System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে"); flag
				 * = false; return flag; } else
				 * if(myIntset.contains(value[i])==false &&
				 * value[i].equalsIgnoreCase("(")==false){
				 * System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
				 * 
				 * flag = false; return flag; } } } }
				 */
			}
			return flag;
		}
	}
}
