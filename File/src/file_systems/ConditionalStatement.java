package file_systems;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.TreeMap;
import java.util.TreeSet;

public class ConditionalStatement {
	char[] nonchar = { '~', '`', '!', '@', '#', '$', '%', '^', '&', '*', '}', '\'', '\\', '/', ',', '.', '|' };
	char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public ConditionalStatement() {

	}

	public boolean checkConditionWithBracket(String readLine, String fileName, String[] array1, TreeMap<String, Character> mymap,
			TreeSet<String> myIntset, TreeSet<String> myStringset, TreeMap<String,String> keywordSet, int lineNumber,
			boolean flag,boolean[]cntLine) throws IOException {
		Writer writer = null;
		try {
			writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("src/file_systems/"+fileName+".java"), "UTF-8"));

			char[] array = readLine.toCharArray();
			
			String t = new String(array);
			int firstBracket = t.indexOf('(');
			int lastBrcket = t.indexOf(')');
			ErrorChecking error = new ErrorChecking();
			flag = error.checkConditionalError(readLine, array1, nonchar, lineNumber, flag);
			if (readLine.endsWith("{") == true && flag == true) {

				String s = new String(array, firstBracket + 1, lastBrcket - firstBracket - 1);
				ErrorChecking error1 = new ErrorChecking();
				flag = error1.checkSpace(readLine, lastBrcket, lineNumber, flag);
				if(flag==false)
					return false;
				if (s.contains("<=") && flag == true) {

					int p = t.indexOf('<');
					int m = t.lastIndexOf('<');
					int q = t.indexOf('=');
					int n = t.lastIndexOf('=');
					if (p > q || p != m || q != n) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return false;
					} else {
						for (int i = p + 1; i < q; i++) {

							if (array[i] != ' ') {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							}

						}

						if (flag == true) {

							String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
							String sr1 = new String(array, q + 1, lastBrcket - q - 1);
							sr = sr.trim();
							sr1 = sr1.trim();

							if (sr.length() <= 0 || sr.length() <= 0) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else if (myIntset.contains(sr) == true && flag == true) {

								if (myStringset.contains(sr1) == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								} else if (myIntset.contains(sr1) == true) {
									writer.write("		if(" + mymap.get(sr) + "<=" + mymap.get(sr1) + ") {\n");
                                    flag=true;
                                    return flag;
								} else {
									boolean checkNum = true;
									for (char c : sr1.toCharArray()) {
										if (Character.isDigit(c)) {
											continue;
										} else {
											checkNum = false;
											break;
										}
									}
									if (checkNum == true){
										writer.write("		if(" + sr + "<=" + sr1 + ") {\n");
										flag=true;
	                                    return flag;
									}
									else {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										return flag;
									}
								}
							} else if (myStringset.contains(sr) == true) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;

							} else if (flag == true) {
								boolean check = true;

								for (char c : sr.toCharArray()) {
									if (Character.isDigit(c)) {
										continue;
									} else {
										check = false;
										break;
									}
								}

								if (check == true) {
									if (myStringset.contains(sr1) == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										
	                                    return flag;
									} else if (myIntset.contains(sr1) == true) {
										writer.write("		if(" + sr + "<=" + mymap.get(sr1) + ") {\n");
										flag=true;
	                                    return flag;
									} else if (sr.contains("\"") == true || sr1.contains("\"") == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										return flag;
									} else {
										writer.write("		if(" + sr + "<=" + sr1 + ") {\n");
										flag=true;
	                                    return flag;
									}
								}

								else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								}
							}
						}
					}
				} else if (s.contains(">=")) {

					int p = t.indexOf('>');
					int m = t.lastIndexOf('>');
					int q = t.indexOf('=');
					int n = t.lastIndexOf('=');
					if (p > q || p != m || q != n) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					} else {
						for (int i = p + 1; i < q; i++) {

							if (array[i] != ' ') {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							}

						}
						if (flag == true) {

							String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
							String sr1 = new String(array, q + 1, lastBrcket - q - 1);
							sr = sr.trim();
							sr1 = sr1.trim();

							if (sr.length() <= 0 || sr1.length() <= 0) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else if (myStringset.contains(sr) == true && flag == true) {

								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;

							} else if (myIntset.contains(sr) == true) {
								if (myStringset.contains(sr1) == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								} else if (myIntset.contains(sr1) == true) {
									writer.write("		if(" + mymap.get(sr) + ">=" + mymap.get(sr1) + ") {\n");
									flag=true;
                                    return flag;
								} else {
									boolean checkNum = true;
									for (char c : sr1.toCharArray()) {
										if (Character.isDigit(c)) {
											continue;
										} else {
											checkNum = false;
											break;
										}
									}
									if (checkNum == true){
										writer.write("		if(" + sr + ">=" + sr1 + ") {\n");
										flag=true;
	                                    return flag;
									}
									else {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										return flag;
									}
								}
							} else if (flag == true) {
								boolean check = true;

								for (char c : sr.toCharArray()) {
									if (Character.isDigit(c)) {
										continue;
									} else {
										check = false;
										break;
									}
								}

								if (check == true) {
									if (myStringset.contains(sr1) == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										return flag;
									} else if (myIntset.contains(sr1) == true) {
										writer.write("		if(" + sr + "<=" + mymap.get(sr1) + ") {\n");
										flag=true;
	                                    return flag;
									} else if (sr.contains("\"") == true || sr1.contains("\"") == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										return flag;
									} else {
										writer.write("		if(" + sr + "<=" + sr1 + ") {\n");
										flag=true;
	                                    return flag;
									}
								}

								else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								}
							}

						}
					}

				} else if (s.contains("==")) {
					int p = t.indexOf('=');
					int q = t.lastIndexOf('=');
					for (int i = p + 1; i < q; i++) {
						if (array[i] != ' ') {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						}
					}
					if (flag == true) {
						String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
						String sr1 = new String(array, q + 1, lastBrcket - q - 1);
						sr = sr.trim();
						sr1 = sr1.trim();
						if (sr.length() <= 0 || sr.length() <= 0) {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						} else if (myStringset.contains(sr) == true && flag == true) {
							if (myIntset.contains(sr1) == true) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else if (myStringset.contains(sr1) == true) {
								writer.write("		if(" + mymap.get(sr) + "==" + mymap.get(sr1) + ") {\n");
								flag=true;
                                return flag;
							} else {
								if (sr1.charAt(0) == '"' && sr1.charAt(sr1.length() - 1) == '"') {

									writer.write("		if(" + mymap.get(sr) + "==" + sr1 + ") {\n");
									flag=true;
                                    return flag;
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								}
							}
						} else if (myStringset.contains(sr1) == true && flag == true) {
							if (myIntset.contains(sr) == true) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else if (myStringset.contains(sr) == true) {
								writer.write("		if(" + mymap.get(sr) + "==" + mymap.get(sr1) + ") {\n");
								flag=true;
                                return flag;
							} else {
								if (sr.charAt(0) == '"' && sr.charAt(sr.length() - 1) == '"') {

									writer.write("		if(" + sr + "==" + mymap.get(sr1) + ") {\n");
									flag=true;
                                    return flag;
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								}
							}
						} else if (myIntset.contains(sr) == true) {
							if (myStringset.contains(sr1) == true) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
							} else if (myIntset.contains(sr1) == true) {
								writer.write("		if(" + mymap.get(sr) + "==" + mymap.get(sr1) + ") {\n");
								flag=true;
                                return flag;
							} else {
								boolean checkNum = true;
								for (char c : sr1.toCharArray()) {
									if (Character.isDigit(c)) {
										continue;
									} else {
										checkNum = false;
										break;
									}
								}
								if (checkNum == true){
									writer.write("		if(" + mymap.get(sr) + "==" + sr1 + ") {\n");
									flag=true;
                                    return flag;
								}
								else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								}
							}
						} else if (flag == true && myIntset.contains(sr1) == true) {

							if (myStringset.contains(sr) == true) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else if (myIntset.contains(sr) == true) {
								writer.write("		if(" + mymap.get(sr) + "==" + mymap.get(sr1) + ") {\n");
								flag=true;
                                return flag;
							} else {
								if (sr.contains("\"") == false && sr1.contains("\"") == false) {
									writer.write("		if(" + sr + "==" + sr1 + ") {\n");
									flag=true;
                                    return flag;
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
                                    return flag;
								}
							}
						} else if (flag == true) {
							if (sr.contains("\"") && sr1.contains("\"")) {
								if (sr.charAt(0) == '"' && sr.charAt(sr.length() - 1) == '"' && sr1.charAt(0) == '"'
										&& sr1.charAt(sr1.length() - 1) == '"') {
									writer.write("		if(" + sr + "==" + sr1 + ") {\n");
									flag=true;
                                    return flag;
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								}
							} else {
								if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"' && sr1.charAt(0) != '"'
										&& sr1.charAt(sr1.length() - 1) != '"') {
									writer.write("		if(" + sr + "==" + sr1 + ") {\n");
									flag=true;
                                    return flag;
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								}
							}
						} else {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						}

					}

				} else if (s.contains(">")) {
					int p = t.indexOf('>');
					int q = t.lastIndexOf('>');
					if (p != q) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					}
					String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
					String sr1 = new String(array, p + 1, lastBrcket - p - 1);
					sr = sr.trim();
					sr1 = sr1.trim();

					if (sr.length() <= 0 || sr.length() <= 0) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					} else if (myStringset.contains(sr) == true && flag == true) {

						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					} else if (myStringset.contains(sr1) == true && flag == true) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					} else if (myIntset.contains(sr) == true) {
						if (myIntset.contains(sr1) == true) {
							writer.write("		if(" + mymap.get(sr) + ">" + mymap.get(sr1) + ") {\n");
							System.out.println("joy");
							writer.write("if");
							flag=true;
                            return flag;
						} else {
							boolean checkNum = true;
							for (char c : sr1.toCharArray()) {
								if (Character.isDigit(c)) {
									continue;
								} else {
									checkNum = false;
									break;
								}
							}
							if (checkNum == true){
								writer.write("		if(" + mymap.get(sr) + ">" + sr1 + ") {\n");
								flag=true;
                                return flag;
							}
							else {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							}
						}
					} else if (myIntset.contains(sr1) == true && flag == true) {
						if (myStringset.contains(sr) == true) {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						} else {
							if (sr.contains("\"") || sr1.contains("\"")) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else {
								writer.write("		if(" + sr + ">" + mymap.get(sr1) + ") {\n");
								flag=true;
                                return flag;
							}
						}
					} else if (flag == true) {
						if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"' && sr1.charAt(0) != '"'
								&& sr1.charAt(sr1.length() - 1) != '"') {
							writer.write("		if(" + sr + ">" + sr1 + ") {\n");
							flag=true;
                            return flag;
						} else {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						}
					} else {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					}

				} else if (s.contains("<")) {
					int p = t.indexOf('<');
					int q = t.lastIndexOf('<');
					if (p != q) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					}
					String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
					String sr1 = new String(array, p + 1, lastBrcket - p - 1);
					sr = sr.trim();
					sr1 = sr1.trim();
					if (sr.length() <= 0 || sr.length() <= 0) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
					} else if (myStringset.contains(sr) == true && flag == true) {

						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					} else if (myStringset.contains(sr1) == true) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;

					} else if (myIntset.contains(sr) == true) {
						if (myIntset.contains(sr1) == true) {
							writer.write("		if(" + mymap.get(sr) + "<" + mymap.get(sr1) + ") {\n");
							return flag;
						} else {
							boolean checkNum = true;
							for (char c : sr1.toCharArray()) {
								if (Character.isDigit(c)) {
									continue;
								} else {
									checkNum = false;
									break;
								}
							}
							if (checkNum == true){
								writer.write("		if(" + mymap.get(sr) + "<" + sr1 + ") {\n");
								flag=true;
                                return flag;
							}
							else {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							}
						}
					} else if (myIntset.contains(sr1) == true) {
						if (myStringset.contains(sr) == true) {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						} else {
							if (sr.contains("\"") || sr1.contains("\"")) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else {
								writer.write("		if(" + sr + "<" + mymap.get(sr1) + ") {\n");
								flag=true;
                                return flag;
							}
						}
					} else if (flag == true) {
						if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"' && sr1.charAt(0) != '"'
								&& sr1.charAt(sr1.length() - 1) != '"') {
							writer.write("		if(" + sr + "<" + sr1 + ") {\n");
							flag=true;
                            return flag;
						} else {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						}
					} else {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					}
				}
			} else if (readLine.contains("{") == false && flag == true) {
				String s = new String(array, firstBracket + 1, lastBrcket - firstBracket - 1);
				cntLine[0] = true;
				for (int i = lastBrcket + 1; i < readLine.length() - 1; i++) {
					if (array[i] != ' ') {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					}

				}
				if (s.contains("<=") && flag == true) {

					int p = t.indexOf('<');
					int m = t.lastIndexOf('<');
					int q = t.indexOf('=');
					int n = t.lastIndexOf('=');
					if (p > q || p != m || q != n) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					} else {
						for (int i = p + 1; i < q; i++) {

							if (array[i] != ' ') {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							}

						}

						if (flag == true) {

							String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
							String sr1 = new String(array, q + 1, lastBrcket - q - 1);
							sr = sr.trim();
							sr1 = sr1.trim();

							if (sr.length() <= 0 || sr.length() <= 0) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else if (myIntset.contains(sr) == true && flag == true) {

								if (myStringset.contains(sr1) == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								} else if (myIntset.contains(sr1) == true) {
									writer.write("		if(" + mymap.get(sr) + "<=" + mymap.get(sr1) + ") \n");
									return flag;

								} else {
									boolean checkNum = true;
									for (char c : sr1.toCharArray()) {
										if (Character.isDigit(c)) {
											continue;
										} else {
											checkNum = false;
											break;
										}
									}
									if (checkNum == true){
										writer.write("		if(" + sr + "<=" + sr1 + ") \n");
									    return flag;
									}
									else {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										return flag;
									}
								}
							} else if (myStringset.contains(sr) == true) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;

							} else if (flag == true) {
								boolean check = true;

								for (char c : sr.toCharArray()) {
									if (Character.isDigit(c)) {
										continue;
									} else {
										check = false;
										break;
									}
								}

								if (check == true) {
									if (myStringset.contains(sr1) == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										return flag;
									} else if (myIntset.contains(sr1) == true) {
										writer.write("		if(" + sr + "<=" + mymap.get(sr1) + ") \n");
										return flag;
									} else if (sr.contains("\"") == true || sr1.contains("\"") == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										return flag;
									} else {
										writer.write("		if(" + sr + "<=" + sr1 + ") \n");
										return flag;
									}
								}

								else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								}
							}
						}
					}
				} else if (s.contains(">=")) {

					int p = t.indexOf('>');
					int m = t.lastIndexOf('>');
					int q = t.indexOf('=');
					int n = t.lastIndexOf('=');
					if (p > q || p != m || q != n) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					} else {
						for (int i = p + 1; i < q; i++) {

							if (array[i] != ' ') {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							}

						}
						if (flag == true) {
							// System.out.println(firstBracket);
							// System.out.println(p);
							String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
							String sr1 = new String(array, q + 1, lastBrcket - q - 1);
							sr = sr.trim();
							sr1 = sr1.trim();

							if (sr.length() <= 0 || sr1.length() <= 0) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else if (myStringset.contains(sr) == true && flag == true) {

								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;

							} else if (myIntset.contains(sr) == true) {
								if (myStringset.contains(sr1) == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								} else if (myIntset.contains(sr1) == true) {
									writer.write("		if(" + mymap.get(sr) + ">=" + mymap.get(sr1) + ") \n");
									return flag;
								} else {
									boolean checkNum = true;
									for (char c : sr1.toCharArray()) {
										if (Character.isDigit(c)) {
											continue;
										} else {
											checkNum = false;
											break;
										}
									}
									if (checkNum == true){
										writer.write("		if(" + sr + ">=" + sr1 + ") \n");
										return flag;
									}
									else {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										return flag;
									}
								}
							} else if (flag == true) {
								boolean check = true;

								for (char c : sr.toCharArray()) {
									if (Character.isDigit(c)) {
										continue;
									} else {
										check = false;
										break;
									}
								}

								if (check == true) {
									if (myStringset.contains(sr1) == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										return flag;
									} else if (myIntset.contains(sr1) == true) {
										writer.write("		if(" + sr + "<=" + mymap.get(sr1) + ") \n");
										return flag;
									} else if (sr.contains("\"") == true || sr1.contains("\"") == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										return flag;
									} else {
										writer.write("		if(" + sr + "<=" + sr1 + ") \n");
										return flag;
									}
								}

								else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								}
							}

						}
					}

				} else if (s.contains("==")) {
					int p = t.indexOf('=');
					int q = t.lastIndexOf('=');
					for (int i = p + 1; i < q; i++) {
						if (array[i] != ' ') {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						}
					}
					if (flag == true) {
						String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
						String sr1 = new String(array, q + 1, lastBrcket - q - 1);
						sr = sr.trim();
						sr1 = sr1.trim();
						if (sr.length() <= 0 || sr.length() <= 0) {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						} else if (myStringset.contains(sr) == true && flag == true) {
							if (myIntset.contains(sr1) == true) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else if (myStringset.contains(sr1) == true) {
								writer.write("		if(" + mymap.get(sr) + "==" + mymap.get(sr1) + ") \n");
								return flag;
							} else {
								if (sr1.charAt(0) == '"' && sr1.charAt(sr1.length() - 1) == '"') {

									writer.write("		if(" + mymap.get(sr) + "==" + sr1 + ") \n");
									return flag;
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								}
							}
						} else if (myStringset.contains(sr1) == true && flag == true) {
							if (myIntset.contains(sr) == true) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else if (myStringset.contains(sr) == true) {
								writer.write("		if(" + mymap.get(sr) + "==" + mymap.get(sr1) + ") \n");
								return flag;
							} else {
								if (sr.charAt(0) == '"' && sr.charAt(sr.length() - 1) == '"') {

									writer.write("		if(" + sr + "==" + mymap.get(sr1) + ") {\n");
									return flag;
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								}
							}
						} else if (myIntset.contains(sr) == true) {
							if (myStringset.contains(sr1) == true) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else if (myIntset.contains(sr1) == true) {
								writer.write("		if(" + mymap.get(sr) + "==" + mymap.get(sr1) + ") \n");
								return flag;
							} else {
								boolean checkNum = true;
								for (char c : sr1.toCharArray()) {
									if (Character.isDigit(c)) {
										continue;
									} else {
										checkNum = false;
										break;
									}
								}
								if (checkNum == true){
									writer.write("		if(" + mymap.get(sr) + "==" + sr1 + ") \n");
								    return flag;
								}
								else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								}
							}
						} else if (flag == true && myIntset.contains(sr1) == true) {

							if (myStringset.contains(sr) == true) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else if (myIntset.contains(sr) == true) {
								writer.write("		if(" + mymap.get(sr) + "==" + mymap.get(sr1) + ") \n");
								return flag;
							} else {
								if (sr.contains("\"") == false && sr1.contains("\"") == false) {
									writer.write("		if(" + sr + "==" + sr1 + ") \n");
									return flag;
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								}
							}
						} else if (flag == true) {
							if (sr.contains("\"") && sr1.contains("\"")) {
								if (sr.charAt(0) == '"' && sr.charAt(sr.length() - 1) == '"' && sr1.charAt(0) == '"'
										&& sr1.charAt(sr1.length() - 1) == '"') {
									writer.write("		if(" + sr + "==" + sr1 + ") \n");
									return flag;
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								}
							} else {
								if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"' && sr1.charAt(0) != '"'
										&& sr1.charAt(sr1.length() - 1) != '"') {
									writer.write("		if(" + sr + "==" + sr1 + ") \n");
									return flag;
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									return flag;
								}
							}
						} else {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						}

					}

				} else if (s.contains(">")) {
					int p = t.indexOf('>');
					int q = t.lastIndexOf('>');
					if (p != q) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					}
					String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
					String sr1 = new String(array, p + 1, lastBrcket - p - 1);
					sr = sr.trim();
					sr1 = sr1.trim();

					if (sr.length() <= 0 || sr.length() <= 0) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					} else if (myStringset.contains(sr) == true && flag == true) {

						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					} else if (myStringset.contains(sr1) == true && flag == true) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					} else if (myIntset.contains(sr) == true) {
						if (myStringset.contains(sr1) == true) {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						} else if (myIntset.contains(sr1) == true) {
							writer.write("		if(" + mymap.get(sr) + ">" + mymap.get(sr1) + ") \n");
							return flag;
						} else {
							boolean checkNum = true;
							for (char c : sr1.toCharArray()) {
								if (Character.isDigit(c)) {
									continue;
								} else {
									checkNum = false;
									break;
								}
							}
							if (checkNum == true){
								writer.write("		if(" + mymap.get(sr) + ">" + sr1 + ") \n");
								return flag;
							}
							else {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							}
						}
					} else if (myIntset.contains(sr1) == true && flag == true) {
						if (myStringset.contains(sr) == true) {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						} else {
							if (sr.contains("\"") || sr1.contains("\"")) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else {
								writer.write("		if(" + sr + ">" + mymap.get(sr1) + ") \n");
								return flag;
							}
						}
					} else if (flag == true) {
						if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"' && sr1.charAt(0) != '"'
								&& sr1.charAt(sr1.length() - 1) != '"') {
							writer.write("		if(" + sr + ">" + sr1 + ") \n");
							return flag;
						} else {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						}
					} else {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					}

				} else if (s.contains("<")) {
					int p = t.indexOf('<');
					int q = t.lastIndexOf('<');
					if (p != q) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					}
					String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
					String sr1 = new String(array, p + 1, lastBrcket - p - 1);
					sr = sr.trim();
					sr1 = sr1.trim();
					if (sr.length() <= 0 || sr.length() <= 0) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					} else if (myStringset.contains(sr) == true && flag == true) {

						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					} else if (myStringset.contains(sr1) == true) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;

					} else if (myIntset.contains(sr) == true) {
						if (myIntset.contains(sr1) == true) {
							writer.write("		if(" + mymap.get(sr) + "<" + mymap.get(sr1) + ") \n");
							return flag;
						} else {
							boolean checkNum = true;
							for (char c : sr1.toCharArray()) {
								if (Character.isDigit(c)) {
									continue;
								} else {
									checkNum = false;
									break;
								}
							}
							if (checkNum == true){
								writer.write("		if(" + mymap.get(sr) + "<" + sr1 + ") \n");
								return flag;
							}
							else {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							}
						}
					} else if (myIntset.contains(sr1) == true) {
						if (myStringset.contains(sr) == true) {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						} else {
							if (sr.contains("\"") || sr1.contains("\"")) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								return flag;
							} else {
								writer.write("		if(" + sr + "<" + mymap.get(sr1) + ") \n");
								return flag;
							}
						}
					} else if (flag == true) {
						if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"' && sr1.charAt(0) != '"'
								&& sr1.charAt(sr1.length() - 1) != '"') {
							writer.write("		if(" + sr + "<" + sr1 + ") \n");
							return flag;
						} else {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
							return flag;
						}
					} else {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
						flag = false;
						return flag;
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return flag;
		
		return flag;
		
	

	}
}
