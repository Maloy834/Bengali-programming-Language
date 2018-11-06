
package file_systems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.lang.ProcessBuilder.Redirect;
import java.lang.reflect.Executable;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.imageio.stream.ImageOutputStreamImpl;
import javax.swing.plaf.FileChooserUI;
import javax.swing.text.html.HTMLDocument.Iterator;

public class ReadBengaliFile {
	char[] number = { '০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯' };

	public ReadBengaliFile() {
		// StartFileParse();
		
		
		
		TreeMap<String, Character> mymap = new TreeMap<String, Character>();
		TreeMap<String, Integer> mymap1 = new TreeMap<String, Integer>();
		TreeSet<String> Initalizevariable = new TreeSet<String>();
		TreeSet myIntset = new TreeSet<String>();
		TreeSet myStringset = new TreeSet<String>();
        Stack<String>myCondionalSet=new Stack<String>();
		FileInputStream is;
		FileInputStream iss;
		FileReader fr;
		String fileNam=null;
		int counter = 0;
		int lineNumber = 0;
		boolean cntLine = false;
		boolean cntNum = false;
		boolean flag = true;
		boolean cnt = true;

		BufferedReader br = null;
		BufferedReader br1 = null;
		char[] nonchar = { '~', '`', '!', '@', '#', '$', '%', '^', '&', '*', '}', '\'', '\\', '/', ',', '.', '|' };
		char[]nonchar1={ '~', '`',  '@', '#', '$',  '^', '&', '*', '}', '\'', '\\', '/', ',', '.', '|' };
		char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		
		char[] operators = { '+', '-', '*', '/', '(', ')' };
		String line = null;
		String inputFile = null;
		FileChooser file = new FileChooser();

		try {

			String filepath[] = new String[100];
			while(fileNam==null){
			
			    fileNam = file.SelectedFile(filepath);
			
			}
			
			is=new FileInputStream(fileNam);
			String fileName = filepath[0];
			System.out.println(fileName);
			fr=new FileReader(fileNam);
			iss = new FileInputStream("store/keyword.txt");
			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			br1 = new BufferedReader(new InputStreamReader(iss, "UTF-8"));

			Writer writer = null;
			Writer output = null;
			try {
				// writer = new BufferedWriter(
				// new OutputStreamWriter(new
				// FileOutputStream("src/file_systems/SimpleBanglatest.java"),
				// "UTF-8"));
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream("src/file_systems/" + fileName + ".java"), "UTF-8"));

				writer.write("package file_systems;\n\n");
				// writer.write("public class SimpleBanglatest {\n\n");
				// writer.write(" public SimpleBanglatest() {\n\n");
				writer.write("public class  " + fileName + "{\n\n");
				//writer.write("public " + fileName + "() {\n\n");
				writer.write("	public static void main(String[] args) {\n\n");

				String readLine = null;
				String readText = null;
				String prev = null;
				Stack st = new Stack();

				char C = 'a';
				char t1 = 'A';

				TreeMap<String, String> keywordSet = new TreeMap<String, String>();
				while ((readText = br1.readLine()) != null) {
					readText = readText.trim();
					if (readText.length() == 1 || readText.length() == 0) {
						continue;
					} else {
						String array2[] = readText.split(",");
						array2[0] = array2[0].trim();
						array2[1] = array2[1].trim();
						keywordSet.put(array2[0], array2[1]);

					}

				}
				
				while ((readLine = br.readLine()) != null) {

					readLine = readLine.trim();
                    
					lineNumber++;
					String array1[] = readLine.split(" ");
					if (counter == 0 && readLine.length() == 1) {
						continue;
					}

					if (readLine.length() != 0) {

						counter++;
						prev = readLine;

					}

					char[] ar = readLine.toCharArray();

					for (int i = 0; i < ar.length; i++) {
						if (ar[i] == '{')
							st.push('{');
						else if (ar[i] == '}' && !st.empty())
							st.pop();
						else if (ar[i] == '}' && st.empty() == true) {
							System.out.println("{ অসামঞ্জ্যসতা হয়ছে");
							flag = false;
						}

					}

					if (counter == 1 && readLine.contains("শুরু")) {
						
						ErrorChecking error = new ErrorChecking();
						flag = error.ChecckStartWithMain(readLine, array1, nonchar, lineNumber, flag);
                        
					}
					if (flag == true) {
						ErrorChecking error = new ErrorChecking();
						flag = error.checkError(readLine, array1, lineNumber, flag);
						
					}
                   
					if (array1[0].contains("সংখ্যা") && flag == true && !readLine.contains("=")&&flag==true) {
						ReadInt read = new ReadInt();
						flag = read.checkIntWithoutValue(readLine, array1, keywordSet, mymap, lineNumber, flag);

						char[] array = readLine.toCharArray();

						if (flag == true) {
							String t = new String(array);

							int first = t.indexOf(';');
							int last = t.lastIndexOf(';');
							int l = array1[0].length();
							String sr = new String(array, l + 1, first - l - 1);
							sr = sr.trim();
							if(sr.indexOf(' ')!=-1){
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে\n"+"ভ্যারিয়েবল এর মাঝে ফাকা স্থান হবে না ");
								flag = false;
							}
							if(flag==true){
							writer.write("		int  ");
							writer.write(C);

							writer.write(";\n\n");

							mymap.put(sr, C);
							myIntset.add(sr);

							C++;
						}

					}
					}
					else if (array1[0].contains("লাইন") && flag == true && !readLine.contains("=")&&flag==true) {

						char[] array = readLine.toCharArray();
						ReadString readstring = new ReadString();
						flag = readstring.ReadStringWithoutValue(readLine, array1, keywordSet, mymap, lineNumber, flag);

						if (flag == true) {
							String t = new String(array);
							int first = t.indexOf(';');
							int last = t.indexOf(';');
							int l = array1[0].length();
							String sr = new String(array, l + 1, first - l - 1);
							sr = sr.trim();
							if(sr.indexOf(' ')!=-1){
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে\n"+"ভ্যারিয়েবল এর মাঝে ফাকা স্থান হবে না ");
								flag = false;
							}
							if(flag==true){
							writer.write("		String  ");
							writer.write(t1);

							writer.write(";\n\n");

							mymap.put(sr, t1);
							myStringset.add(sr);

							t1++;
						}

					}
					}
					if (readLine.contains("=") && !readLine.contains("+") && flag == true
							&& array1[0].contains("যদি") == false && array1[0].contains("লুপ")==false) {
						char[] array = readLine.toCharArray();

						String t = new String(array);
						boolean count = false;
						if (readLine.startsWith("লাইন") || readLine.startsWith("সংখ্যা")) {
							count = true;
						}
						for (int i = 0; i < nonchar.length; i++) {
							if (t.indexOf(nonchar[i]) != -1) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
								break;
							}
						}

						int first = t.indexOf('=');
						int lt = t.lastIndexOf('=');
						if (first != lt && flag == true) {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
						}
						if (flag == true) {
							String sr;
							if (count == true) {
								int l = array1[0].length();
								sr = new String(array, l + 1, first - l - 1);
								sr = sr.trim();
							} else {
								sr = new String(array, 0, first - 0);

							}
							int n = t.indexOf(';');
							int last = t.lastIndexOf(';');

							sr = sr.trim();
							if (n != last) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
							}
							if (sr.length() == 0 && flag == true) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
							}
							if (sr.length() == 1 && flag == true) {
								for (int i = 0; i < digit.length; i++) {
									if (sr.indexOf(digit[i]) != -1) {
										System.out.println(lineNumber + " নম্বর লাইনে  ভ্যারিয়েবল  বসানো ভুল হয়েছে");
										flag = false;
										break;
									}
								}
							}
							if (mymap.containsKey(sr) && flag == true && count == false) {

								char c = mymap.get(sr);

								writer.write("		" + c);

								writer.write("= ");
							}

							else if (count == true && flag == true) {
								if (readLine.startsWith("সংখ্যা")) {
									ReadInt read = new ReadInt();
									flag = read.checkIntWithValue(readLine, array1, sr, mymap, keywordSet, lineNumber,
											cnt);

									if (flag == true) {
										if(sr.indexOf(' ')!=-1){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে\n"+"ভ্যারিয়েবল এর মাঝে ফাকা স্থান হবে না ");
											flag = false;
										}
										if(flag==true){
										writer.write("		int  ");
										writer.write(C + "=");

										sr = sr.trim();
										mymap.put(sr, C);
										myIntset.add(sr);

										C++;
									}
									}
								} else if (readLine.startsWith("লাইন")) {

									ReadString readstring = new ReadString();
									flag = readstring.checkStringwithValue(readLine, array1, sr, mymap, keywordSet,
											lineNumber, flag);

									if (flag == true) {
										if(sr.indexOf(' ')!=-1){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে\n"+"ভ্যারিয়েবল এর মাঝে ফাকা স্থান হবে না ");
											flag = false;
										}
										if(flag==true){
										writer.write("		String  ");
										writer.write(t1 + "=");

										mymap.put(sr, t1);
										myStringset.add(sr);

										t1++;

									}
								}
							}
							}
							else {
								System.out.println(lineNumber + " নম্বর লাইনে   ভুল হয়েছে");

								flag = false;
							}

							if (flag == true) {
								String l = new String(array, first + 1, last - first - 1);
								l = l.trim();

								if (l.length() <= 0) {
									System.out.println(lineNumber + " নম্বর লাইনে  ভ্যারিয়েবলের  মান বসাতে হবে");
									flag = false;
								}
								if (l.indexOf('"') != -1 && myStringset.contains(sr) == false) {
									System.out.println(
											lineNumber + " নম্বর লাইনে  ভ্যারিয়েবলের  মানের অসামঞ্জ্যসতা হয়ছে");
									flag = false;
								}
								if (l.indexOf('"') < 0 && myStringset.contains(sr) == true) {
									System.out.println(
											lineNumber + " নম্বর লাইনে  ভ্যারিয়েবলের  মানের অসামঞ্জ্যসতা হয়ছে");

									flag = false;
								}
								if (flag == true && l.indexOf('"') != -1 && myStringset.contains(sr) == true) {
									int frt = t.indexOf('"');
									int lst = t.lastIndexOf('"');

									if (frt == lst) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									}
									if (flag == true) {
										String sr2 = new String(array, frt + 1, lst - frt - 1);

										if (sr2.indexOf('"') != -1) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
									if (n - lst > 1 && flag == true) {

										String sr1 = new String(array, lst + 1, n - lst - 1);
										sr1 = sr1.trim();

										if (sr1.length() != 0) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}

								}
								if (flag == true && myIntset.contains(sr) == true) {
									boolean checkNum=false;
									boolean Valuecheck = false;
									char[] value = l.toCharArray();
									for (int i = 0; i < value.length; i++) {
										for (int j = 0; j < number.length; j++) {
											if (value[i] == number[j]) {
												Valuecheck = true;
												break;
											}

										}
										if (Valuecheck == true) {
											checkNum = true;
											Valuecheck = false;
										} else {
											checkNum = false;
											break;
										}
									}
									if(checkNum==true){
									l=ConvertNumber(value);
									int num= Integer.parseInt(l);
									mymap1.put(l, num);
									Initalizevariable.add(sr);
									writer.write(l + ";");

									writer.write("\n");
									}
									else{
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									}
								} else if (flag == true && myStringset.contains(sr) == true) {

									writer.write(l + ";");
									Initalizevariable.add(sr);
									writer.write("\n");

								}

							}
						}

					} else if (array1[0].contains("দেখাও") && flag == true) {
						char[] array = readLine.toCharArray();
						String t = new String(array);
						int firstbracket = t.indexOf('(');
						int lastbracket = t.lastIndexOf(')');
						ErrorChecking error = new ErrorChecking();
						flag = error.cheeckPrintingError(readLine, nonchar, lineNumber, flag);

						if (!readLine.contains("\"") && flag == true && readLine.contains("+")==false) {
                          
							PrintLine print = new PrintLine();
							flag = print.printLinecheckWithoutQuote(readLine, array1, mymap, lineNumber, flag);

							if (flag == true) {
								String sr1 = new String(array, firstbracket + 1, lastbracket - firstbracket - 1);
								sr1 = sr1.trim();
								if (mymap.containsKey(sr1) == false && readLine.contains("+")==false) {
									
									char [] arr=sr1.toCharArray();
									boolean check=CheckNumber(arr);
									if(check==true){
	
									  sr1=ConvertNumber(arr); 
									  writer.write("      	System.out.println("+sr1+");");
									}
									else{
										
										System.out.println(lineNumber + " নম্বর লাইনে ভুল ভ্যারিয়েবল  মান প্রদর্শন করতে বলা হয়েছে");
										flag = false;
									}
									
								

							}
								else{
								char c = mymap.get(sr1);

								writer.write("		System.out.println(");
								writer.write(c);

								writer.write(");\n");
							}
							}
						} else if (readLine.contains("\"") && flag == true && readLine.contains("+")==false) {

							PrintLine print = new PrintLine();
							flag = print.PrintLineCheckWithQuote(readLine, lineNumber, flag);
							if (flag == true) {
								int first = t.indexOf('"');
								int last = t.lastIndexOf('"');
								line = new String(array, first + 1, last - first - 1);
								writer.write("		System.out.println(");
								writer.write("\"");
								writer.write(line);

								writer.write("\"");
								writer.write(");\n");

							}

						}
						else if(readLine.contains("+")&&flag==true){
						  
							String sr=new String(array,firstbracket+1,lastbracket-firstbracket-1);
							char[] tempArray=sr.toCharArray();
							
							for(int i=0;i<tempArray.length;i++){
								if(tempArray[i]=='+')
									tempArray[i]='.';
							}
							sr=new String(tempArray);
							String [] arr=sr.split("\\.",-1);
							/*for(int i=0;i<arr.length;i++){
								System.out.println(arr[i]);
							}*/
							int checker=0;
							for(int i=0;i<arr.length;i++){
								arr[i]=arr[i].trim();
								
								if(mymap.containsKey(arr[i])){
									
									if(checker==0){
										writer.write("System.out.println( ");
										writer.write(mymap.get(arr[i]));
										counter=1;
									}
									else
										writer.write("+ "+ mymap.get(arr[i]));
								}
								else if(arr[i].contains("\"")){
									
									String temp=arr[i];
									
									int p=temp.indexOf('"');
									int q=temp.lastIndexOf('"');
									
									if(p==q){
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										break;
									}
									else{
										char[] ar1=temp.toCharArray();
										if(ar1[ar1.length-1]!='"'){
											
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে ");
											flag = false;
											break;
										}
										else{
											for(int j=1;j<q-1;j++){
												if(ar1[j]=='"'){
	                                                
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে ");
													flag = false;
													break;
												}
											}
											if(flag==true){
												String temporary=new String(ar1,p+1,q-p-1);
												
												if(checker==0){
													writer.write("		System.out.println( ");
													writer.write("\""+temporary+"\"");
													checker=1;
												}
												else{
													writer.write("+ "+ "\""+temporary+"\"");
												}
											}
										}
									}
								}
								else if(arr[i].contains("'	'")){
									
									if(checker==0){
										writer.write("System.out.println( ");
										writer.write(arr[i]);
										checker=1;
									}
									else{
									writer.write("+"+arr[i]);
									}
								}
								else if(arr[i].contains("\"")){
									int m=arr[i].indexOf("\"");
									int n=arr[i].lastIndexOf("\"");
									if(m==n){
										System.out.println(lineNumber + " নম্বর লাইনে ভুল ভ্যারিয়েবল  মান প্রদর্শন করতে বলা হয়েছে");
										flag = false;
										break;
									}
									else{
										char[] pr=arr[i].toCharArray();
										for(int k=m+1;k<n-1;k++){
											if(pr[i]=='"'){
												System.out.println(lineNumber + " নম্বর লাইনে ভুল ভ্যারিয়েবল  মান প্রদর্শন করতে বলা হয়েছে");
												flag = false;
												break;
											}
										}
										if(flag==true){
											if(checker==0){
												writer.write("System.out.println( ");
												writer.write(arr[i]);
												checker=1;
											}
											else{
												writer.write("+ "+arr[i]);
											}
										}
									}
								}
								else{
									char[]ar1=arr[i].toCharArray();
									boolean found;
									found=CheckNumber(ar1);
									if(found==true){
										String temp=ConvertNumber(ar1);
										if(checker==0){
											writer.write("System.out.println( ");
											writer.write(temp);
											checker=1;
										}
										else{
											writer.write("+ "+ temp);
										}
									}
									else{
										System.out.println(lineNumber + " নম্বর লাইনে ভুল ভ্যারিয়েবল  মান প্রদর্শন করতে বলা হয়েছে");
										flag = false;
										break;
									}
								} 
							}
							if(flag==true){
								writer.write(");\n");
							}
						}
						else {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
						}

					} else if (array1[0].contains("যদি") == true && flag == true) {

					   
						char[] array = readLine.toCharArray();
						String t = new String(array);
						int firstBracket = t.indexOf('(');
						int lastBrcket = t.indexOf(')');
						if(firstBracket==-1||lastBrcket==-1){
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
						}
						if(flag==true){
						ErrorChecking error = new ErrorChecking();
						flag = error.checkConditionalError(readLine, array1, nonchar1, lineNumber, flag);
						myCondionalSet.add("if");
						if (readLine.endsWith("{") == true && flag == true) {

							String s = new String(array, firstBracket + 1, lastBrcket - firstBracket - 1);
							
							ErrorChecking error1 = new ErrorChecking();
							flag = error1.checkSpace(readLine, lastBrcket, lineNumber, flag);
							
							if (s.contains("<=") && flag == true && s.contains("%")==false) {

								int p = t.indexOf('<');
								int m = t.lastIndexOf('<');
								int q = t.indexOf('=');
								int n = t.lastIndexOf('=');
								if (p > q || p != m || q != n) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else {
									for (int i = p + 1; i < q; i++) {

										if (array[i] != ' ') {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
											break;
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
										} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myIntset.contains(sr) == true && flag == true) {

											if (myStringset.contains(sr1) == true) {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											} else if (myIntset.contains(sr1) == true) {
												if (Initalizevariable.contains(sr1) && Initalizevariable.contains(sr)) {
													writer.write("		if(" + mymap.get(sr) + "<=" + mymap.get(sr1)
															+ ") {\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												boolean checkNum;
		

												
												char[] value = sr1.toCharArray();
												checkNum=CheckNumber(value);
												
												if (checkNum == true) {

													if (Initalizevariable.contains(sr)) {
														sr1 = ConvertNumber(value);

														writer.write(
																"		if(" + mymap.get(sr) + "<=" + sr1 + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										} else if (myStringset.contains(sr) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;

										} else if (flag == true) {
											boolean check;

											
											
											char[] value = sr.toCharArray();
											check=CheckNumber(value);
											
											if (check == true) {
												if (myStringset.contains(sr1) == true) {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												} else if (myIntset.contains(sr1) == true) {
													if (Initalizevariable.contains(sr1) == true) {
														sr = ConvertNumber(value);
														writer.write(
																"		if(" + sr + "<=" + mymap.get(sr1) + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}

												} else if (sr.contains("\"") == true || sr1.contains("\"") == true) {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												} else {
													
													boolean checkNum;
													char[] value1 = sr1.toCharArray();
													checkNum=CheckNumber(value1);
													
													if (checkNum == true) {

														sr = ConvertNumber(value);
														sr1 = ConvertNumber(value1);
														writer.write("		if(" + sr + "<=" + sr1 + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
														flag = false;
													}

												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									}
								}
							} else if (s.contains(">=")&& s.contains("%")==false) {

								int p = t.indexOf('>');
								int m = t.lastIndexOf('>');
								int q = t.indexOf('=');
								int n = t.lastIndexOf('=');
								if (p > q || p != m || q != n) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else {
									for (int i = p + 1; i < q; i++) {

										if (array[i] != ' ') {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
											break;
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
										} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myStringset.contains(sr) == true && flag == true) {

											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;

										} else if (myIntset.contains(sr) == true) {
											if (myStringset.contains(sr1) == true) {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											} else if (myIntset.contains(sr1) == true) {
												if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
													writer.write("		if(" + mymap.get(sr) + ">=" + mymap.get(sr1)
															+ ") {\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											} else {
												boolean checkNum;
												
												char[] value = sr1.toCharArray();
												checkNum=CheckNumber(value);
												
												if (checkNum == true) {
													if (Initalizevariable.contains(sr)) {
														sr1 = ConvertNumber(value);
														writer.write(
																"		if(" + mymap.get(sr) + ">=" + sr1 + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										} else if (flag == true) {
											boolean check;

											
											char[] value = sr.toCharArray();
											check=CheckNumber(value);
											
											if (check == true) {
												if (myStringset.contains(sr1) == true) {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												} else if (myIntset.contains(sr1) == true) {
													if (Initalizevariable.contains(sr1)) {
														sr = ConvertNumber(value);
														writer.write(
																"		if(" + sr + ">=" + mymap.get(sr1) + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}

												} else if (sr.contains("\"") == true || sr1.contains("\"") == true) {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												} else {
													
													boolean checkNum = false;
													char[] value1 = sr1.toCharArray();
													checkNum=CheckNumber(value1);
													
													if (checkNum == true) {

														sr = ConvertNumber(value);
														sr1 = ConvertNumber(value1);
														writer.write("		if(" + sr + ">=" + sr1 + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
														flag = false;
													}

												}
											}

											else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}

									}
								}

							} else if (s.contains("==")&& s.contains("%")==false) {
								int p = t.indexOf('=');
								int q = t.lastIndexOf('=');
								for (int i = p + 1; i < q; i++) {
									if (array[i] != ' ') {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										break;
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
									} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									} else if (myStringset.contains(sr) == true && flag == true) {
										if (myIntset.contains(sr1) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myStringset.contains(sr1) == true) {
											if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
												writer.write(
														"		if(" + mymap.get(sr) + "==" + mymap.get(sr1) + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}

										} else {
											if (sr1.charAt(0) == '"' && sr1.charAt(sr1.length() - 1) == '"') {
												if (Initalizevariable.contains(sr)) {
													writer.write("		if(" + mymap.get(sr) + "==" + sr1 + ") {\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									} else if (myStringset.contains(sr1) == true && flag == true) {
										if (myIntset.contains(sr) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myStringset.contains(sr) == true) {
											if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
												writer.write(
														"		if(" + mymap.get(sr) + "==" + mymap.get(sr1) + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										} else {
											if (sr.charAt(0) == '"' && sr.charAt(sr.length() - 1) == '"') {
												if (Initalizevariable.contains(sr1)) {
													writer.write("		if(" + sr + "==" + mymap.get(sr1) + ") {\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									} else if (myIntset.contains(sr) == true) {
										if (myStringset.contains(sr1) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myIntset.contains(sr1) == true) {
											if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
												writer.write(
														"		if(" + mymap.get(sr) + "==" + mymap.get(sr1) + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										} else {
											boolean checkNum;
											
											char[] value = sr1.toCharArray();
											  checkNum=CheckNumber(value);											
													  
												

											if (checkNum == true) {
												if (Initalizevariable.contains(sr)) {
													sr1 = ConvertNumber(value);
													writer.write("		if(" + mymap.get(sr) + "==" + sr1 + ") {\n");
												}

												else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									} else if (flag == true && myIntset.contains(sr1) == true) {

										if (myStringset.contains(sr) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myIntset.contains(sr) == true) {
											if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
												writer.write(
														"		if(" + mymap.get(sr) + "==" + mymap.get(sr1) + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}

										} else {
											boolean checkNum;
											
											char[] value = sr.toCharArray();
											checkNum=CheckNumber(value);
											
											if (checkNum==true) {
												if (Initalizevariable.contains(sr1)) {
												
													sr = ConvertNumber(value);
													writer.write("		if(" + sr + "==" + mymap.get(sr1) + ") {\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									} else if (flag == true) {
										if (sr.contains("\"") && sr1.contains("\"")) {
											if (sr.charAt(0) == '"' && sr.charAt(sr.length() - 1) == '"'
													&& sr1.charAt(0) == '"' && sr1.charAt(sr1.length() - 1) == '"') {
												writer.write("		if(" + sr + "==" + sr1 + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										} else {
											if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"'
													&& sr1.charAt(0) != '"' && sr1.charAt(sr1.length() - 1) != '"') {
												boolean checkNum;
												char[] value = sr.toCharArray();
												checkNum=CheckNumber(value);
												
												if (checkNum == true) {
													
													boolean check;
													char[] value1 = sr1.toCharArray();
													check=CheckNumber(value1);
													
													if (check == true) {

														sr = ConvertNumber(value);
														sr1 = ConvertNumber(value1);
														writer.write("		if(" + sr + "==" + sr1 + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										}
									} else {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									}

								}

							}
							else if (s.contains("!=")&& s.contains("%")==false) {
								int p = t.indexOf('=');
								int q = t.lastIndexOf('=');
								int m=t.indexOf('!');
								int n=t.lastIndexOf('!');
								if(p!=q||m!=n){
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}
								for (int i = p + 1; i < q; i++) {
									if (array[i] != ' ') {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										break;
									}
								}
								
								if (flag == true) {
									String sr = new String(array, firstBracket + 1, m - firstBracket - 1);
									String sr1 = new String(array, q + 1, lastBrcket - q - 1);
									sr = sr.trim();
									sr1 = sr1.trim();
									if (sr.length() <= 0 || sr.length() <= 0) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									} else if (myStringset.contains(sr) == true && flag == true) {
										if (myIntset.contains(sr1) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myStringset.contains(sr1) == true) {
											if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
												writer.write(
														"		if(" + mymap.get(sr) + "!=" + mymap.get(sr1) + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}

										} else {
											if (sr1.charAt(0) == '"' && sr1.charAt(sr1.length() - 1) == '"') {
												if (Initalizevariable.contains(sr)) {
													writer.write("		if(" + mymap.get(sr) + "!=" + sr1 + ") {\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									} else if (myStringset.contains(sr1) == true && flag == true) {
										if (myIntset.contains(sr) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myStringset.contains(sr) == true) {
											if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
												writer.write(
														"		if(" + mymap.get(sr) + "!=" + mymap.get(sr1) + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										} else {
											if (sr.charAt(0) == '"' && sr.charAt(sr.length() - 1) == '"') {
												if (Initalizevariable.contains(sr1)) {
													writer.write("		if(" + sr + "!=" + mymap.get(sr1) + ") {\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									} else if (myIntset.contains(sr) == true) {
										if (myStringset.contains(sr1) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myIntset.contains(sr1) == true) {
											if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
												writer.write(
														"		if(" + mymap.get(sr) + "!=" + mymap.get(sr1) + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										} else {
											boolean checkNum;
											
											char[] value = sr1.toCharArray();
											  checkNum=CheckNumber(value);											
													  
												

											if (checkNum == true) {
												if (Initalizevariable.contains(sr)) {
													sr1 = ConvertNumber(value);
													writer.write("		if(" + mymap.get(sr) + "!=" + sr1 + ") {\n");
												}

												else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									} else if (flag == true && myIntset.contains(sr1) == true) {

										if (myStringset.contains(sr) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myIntset.contains(sr) == true) {
											if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
												writer.write(
														"		if(" + mymap.get(sr) + "!=" + mymap.get(sr1) + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}

										} else {
											boolean checkNum;
											
											char[] value = sr.toCharArray();
											checkNum=CheckNumber(value);
											
											if (checkNum==true) {
												if (Initalizevariable.contains(sr1)) {
												
													sr = ConvertNumber(value);
													writer.write("		if(" + sr + "!=" + mymap.get(sr1) + ") {\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									} else if (flag == true) {
										if (sr.contains("\"") && sr1.contains("\"")) {
											if (sr.charAt(0) == '"' && sr.charAt(sr.length() - 1) == '"'
													&& sr1.charAt(0) == '"' && sr1.charAt(sr1.length() - 1) == '"') {
												writer.write("		if(" + sr + "!=" + sr1 + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										} else {
											if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"'
													&& sr1.charAt(0) != '"' && sr1.charAt(sr1.length() - 1) != '"') {
												boolean checkNum;
												char[] value = sr.toCharArray();
												checkNum=CheckNumber(value);
												
												if (checkNum == true) {
													
													boolean check;
													char[] value1 = sr1.toCharArray();
													check=CheckNumber(value1);
													
													if (check == true) {

														sr = ConvertNumber(value);
														sr1 = ConvertNumber(value1);
														writer.write("		if(" + sr + "!=" + sr1 + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										}
									} else {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									}

								}

							}
							else if(s.contains("%")&&flag==true){
								
								char[] aray=readLine.toCharArray();
								int p=readLine.indexOf('%');
								int q=readLine.lastIndexOf('%');
								if(p!=q){
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}
								else{
									int sign;
									String temp=null,sr = null,sr1=null,sr2=null;
									if(s.contains("<=")){
										sign=readLine.indexOf('=');
										int r=readLine.lastIndexOf('=');
										int m=readLine.indexOf('<');
										int n=readLine.lastIndexOf('<');
										if(sign!=r ||m!=n){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										temp="<=";
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(array,p+1,m-p-1);
										sr2=new String(aray, sign+1,lastBrcket-sign-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
									}
									else if(s.contains(">=")){
										sign=readLine.indexOf('=');
										int r=readLine.lastIndexOf('=');
										int m=readLine.indexOf('>');
										int n=readLine.lastIndexOf('>');
										if(sign!=r ||m!=n){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(array,p+1,m-p-1);
										sr2=new String(aray, sign+1,lastBrcket-sign-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp=">=";
									}
									else if(s.contains("==")){
										sign=readLine.indexOf('=');
										int r=readLine.lastIndexOf('=');
										
										if((sign+1)!=r){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(aray,p+1,sign-p-1);
										sr2=new String(aray, r+1,lastBrcket-r-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp="==";
									}
									else if(s.contains("!=")){
										sign=readLine.indexOf('=');
										int r=readLine.lastIndexOf('=');
										int m=readLine.indexOf('!');
										int n=readLine.lastIndexOf('!');
										if(sign!=r ||m!=n){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(aray, p+1, m-p-1);
										 
										sr2=new String(aray, sign+1, lastBrcket-sign-1);
			
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp="!=";
									}
									else if(s.contains("<")){
										sign=readLine.indexOf('<');
										int r=readLine.lastIndexOf('<');
										
										if(sign!=r){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(aray, p+1,sign-p-1);
										sr2=new String(aray, sign+1,lastBrcket-sign-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp="<";
									}
									else if(s.contains(">")){
										sign=readLine.indexOf('>');
										int r=readLine.lastIndexOf('>');
										
										if(sign!=r){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(aray, p+1,sign-p-1);
										sr2=new String(aray, sign+1,lastBrcket-sign-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp=">";
									}
									else{
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									}
									if(flag==true){
										if(sr.contains("\"")||sr1.contains("\"")||sr2.contains("\"")){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										boolean chek,chek1,chek2;
										char []ap=sr.toCharArray();
										char[]ap1=sr1.toCharArray();
										char[]ap2=sr2.toCharArray();
										chek=CheckNumber(ap);
										chek1=CheckNumber(ap1);
										chek2=CheckNumber(ap2);
										if(chek==true&& chek1==true&&chek2==true){
											sr=ConvertNumber(ap);
											sr1=ConvertNumber(ap1);
											sr2=ConvertNumber(ap2);
											writer.write("     if("+sr+"%"+sr1+temp+sr2+"){\n");
										}
										else if(chek==true && chek1==true&& chek2==false){
											if(myIntset.contains(sr2)){
												if(Initalizevariable.contains(sr2)){
													sr=ConvertNumber(ap);
													sr1=ConvertNumber(ap1);
													writer.write("       if("+sr+"%"+sr1+temp+mymap.get(sr2)+"){\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==true && chek1==false&& chek2==true){
											if(myIntset.contains(sr1)){
												if(Initalizevariable.contains(sr1)){
													sr=ConvertNumber(ap);
													sr2=ConvertNumber(ap2);
													writer.write("       if("+sr+"%"+mymap.get(sr1)+temp+sr2+"){\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==false && chek1==true&& chek2==true){
											if(myIntset.contains(sr)){
												if(Initalizevariable.contains(sr)){
													sr1=ConvertNumber(ap1);
													sr2=ConvertNumber(ap2);
													writer.write("       if("+mymap.get(sr)+"%"+sr1+temp+sr2+"){\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==false && chek1==false&& chek2==false){
										  if(myIntset.contains(sr)&&myIntset.contains(sr1)&&myIntset.contains(sr2)){
											  if(Initalizevariable.contains(sr)&& Initalizevariable.contains(sr1)&&Initalizevariable.contains(sr2)){
												  writer.write("       if("+mymap.get(sr)+"%"+mymap.get(sr1)+temp+mymap.get(sr2)+"){\n");
											  }
											  else{
												  System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
											  }
											  
										  }
										  else{
											  System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
										  }
										}
										else if(chek==false && chek1==false&& chek2==true){
											if(myIntset.contains(sr)&& myIntset.contains(sr1)){
												if(Initalizevariable.contains(sr)&& Initalizevariable.contains(sr1)){
													sr2=ConvertNumber(ap2);
													writer.write("      if("+mymap.get(sr)+"%"+mymap.get(sr1)+temp+sr2+"){\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==true && chek1==false&& chek2==false){
											if(myIntset.contains(sr1)&& myIntset.contains(sr2)){
												if(Initalizevariable.contains(sr1)&& Initalizevariable.contains(sr2)){
													sr=ConvertNumber(ap);
													writer.write("      if("+sr+"%"+mymap.get(sr1)+temp+mymap.get(sr2)+"){\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==false && chek1==true&& chek2==false){
											if(myIntset.contains(sr)&& myIntset.contains(sr2)){
												if(Initalizevariable.contains(sr)&& Initalizevariable.contains(sr2)){
													sr1=ConvertNumber(ap1);
													writer.write("      if("+mymap.get(sr)+"%"+sr1+temp+mymap.get(sr2)+"){\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else{
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										
									}
								}
							}
							
							else if (s.contains(">")&& s.contains("%")==false) {
								int p = t.indexOf('>');
								int q = t.lastIndexOf('>');
								if (p != q) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}
								String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
								String sr1 = new String(array, p + 1, lastBrcket - p - 1);
								sr = sr.trim();
								sr1 = sr1.trim();

								if (sr.length() <= 0 || sr.length() <= 0) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myStringset.contains(sr) == true && flag == true) {

									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myStringset.contains(sr1) == true && flag == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myIntset.contains(sr) == true) {
									if (myIntset.contains(sr1) == true) {
										if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
											writer.write("		if(" + mymap.get(sr) + ">" + mymap.get(sr1) + ") {\n");
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
													+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
											flag = false;
										}

									} else {
										boolean checkNum;
										
										char[] value = sr1.toCharArray();
										checkNum=CheckNumber(value);
										
										if (checkNum == true) {
											if (Initalizevariable.contains(sr)) {
												sr1 = ConvertNumber(value);
												writer.write("		if(" + mymap.get(sr) + ">" + sr1 + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
								} else if (myIntset.contains(sr1) == true && flag == true) {
									if (myStringset.contains(sr) == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									} else {
										if (sr.contains("\"") || sr1.contains("\"")) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else {
											boolean checkNum;
											char[] value = sr.toCharArray();
											checkNum=CheckNumber(value);
											
											if (checkNum == true) {
												if (Initalizevariable.contains(sr1)) {

													sr = ConvertNumber(value);
													writer.write("		if(" + sr + ">" + mymap.get(sr1) + ") {\n");
												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									}
								} else if (flag == true) {
									if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"' && sr1.charAt(0) != '"'
											&& sr1.charAt(sr1.length() - 1) != '"') {

										boolean checkNum;
										
										char[] value = sr.toCharArray();
										checkNum=CheckNumber(value);
										
										if (checkNum == true) {
											
											boolean check;
											char[] value1 = sr1.toCharArray();
											check=CheckNumber(value1);
											
											if (check == true) {

												sr = ConvertNumber(value);
												sr1 = ConvertNumber(value1);
												writer.write("		if(" + sr + ">" + sr1 + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}

							} else if (s.contains("<")&&s.contains("%")==false) {
								int p = t.indexOf('<');
								int q = t.lastIndexOf('<');
								if (p != q) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}
								String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
								String sr1 = new String(array, p + 1, lastBrcket - p - 1);
								sr = sr.trim();
								sr1 = sr1.trim();
								if (sr.length() <= 0 || sr.length() <= 0) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myStringset.contains(sr) == true && flag == true) {

									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myStringset.contains(sr1) == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;

								} else if (myIntset.contains(sr) == true) {
									if (myIntset.contains(sr1) == true) {
										if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
											writer.write("		if(" + mymap.get(sr) + "<" + mymap.get(sr1) + ") {\n");
										}
									} else {
										boolean checkNum;
										
										char[] value = sr1.toCharArray();
										checkNum=CheckNumber(value);
										
										if (checkNum == true) {
											if (Initalizevariable.contains(sr)) {
												sr1=ConvertNumber(value);
												writer.write("		if(" + mymap.get(sr) + "<" + sr1 + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
								} else if (myIntset.contains(sr1) == true) {
									if (myStringset.contains(sr) == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									} else {
										if (sr.contains("\"") || sr1.contains("\"")) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else {
											if (Initalizevariable.contains(sr1)) {
												boolean checkNum;
												char[] value = sr1.toCharArray();
												checkNum=CheckNumber(value);
												
												if(checkNum==true){
												  sr=ConvertNumber(value);
												  writer.write("		if(" + sr + "<" + mymap.get(sr1) + ") {\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										}
									}
								} else if (flag == true) {
									if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"' && sr1.charAt(0) != '"'
											&& sr1.charAt(sr1.length() - 1) != '"') {
										boolean checkNum;
										
										char[] value = sr.toCharArray();
										checkNum=CheckNumber(value);
										
										if (checkNum == true) {
											
											boolean check;
											char[] value1 = sr1.toCharArray();
											check=CheckNumber(value1);
											
											if (check == true) {

												sr = ConvertNumber(value);
												sr1 = ConvertNumber(value1);
												writer.write("		if(" + sr + "<" + sr1 + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}

							}
							else{
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
							}
						} else if (readLine.contains("{") == false && flag == true) {
							String s = new String(array, firstBracket + 1, lastBrcket - firstBracket - 1);
							cntLine = true;
							for (int i = lastBrcket + 1; i < readLine.length() - 1; i++) {
								if (array[i] != ' ') {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									break;
								}

							}
							if (s.contains("<=") && flag == true && s.contains("%")==false) {

								int p = t.indexOf('<');
								int m = t.lastIndexOf('<');
								int q = t.indexOf('=');
								int n = t.lastIndexOf('=');
								if (p > q || p != m || q != n) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else {
									for (int i = p + 1; i < q; i++) {

										if (array[i] != ' ') {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
											break;
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
										} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myIntset.contains(sr) == true && flag == true) {

											if (myStringset.contains(sr1) == true) {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											} else if (myIntset.contains(sr1) == true) {
												if (Initalizevariable.contains(sr1) && Initalizevariable.contains(sr)) {
													writer.write("		if(" + mymap.get(sr) + "<=" + mymap.get(sr1)
															+ ") \n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												boolean checkNum;
												
												
												char[] value = sr1.toCharArray();
												checkNum=CheckNumber(value);
												
												if (checkNum == true) {

													if (Initalizevariable.contains(sr)) {
														sr1 = ConvertNumber(value);

														writer.write(
																"		if(" + mymap.get(sr) + "<=" + sr1 + ") \n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										} else if (myStringset.contains(sr) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;

										} else if (flag == true) {
											boolean check;

											
											char[] value = sr.toCharArray();
											check=CheckNumber(value);
											
											if (check == true) {
												if (myStringset.contains(sr1) == true) {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												} else if (myIntset.contains(sr1) == true) {
													if (Initalizevariable.contains(sr1) == true) {
														sr = ConvertNumber(value);
														writer.write(
																"		if(" + sr + "<=" + mymap.get(sr1) + ") \n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}

												} else if (sr.contains("\"") == true || sr1.contains("\"") == true) {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												} else {
													
													boolean checkNum;
													char[] value1 = sr1.toCharArray();
													checkNum=CheckNumber(value1);
													
													if (checkNum == true) {

														sr = ConvertNumber(value);
														sr1 = ConvertNumber(value1);
														writer.write("		if(" + sr + "<=" + sr1 + ") \n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
														flag = false;
													}

												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									}
								}
							} else if (s.contains(">=")&& s.contains("%")==false) {

								int p = t.indexOf('>');
								int m = t.lastIndexOf('>');
								int q = t.indexOf('=');
								int n = t.lastIndexOf('=');
								if (p > q || p != m || q != n) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else {
									for (int i = p + 1; i < q; i++) {

										if (array[i] != ' ') {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
											break;
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
										} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myStringset.contains(sr) == true && flag == true) {

											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;

										} else if (myIntset.contains(sr) == true) {
											if (myStringset.contains(sr1) == true) {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											} else if (myIntset.contains(sr1) == true) {
												if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
													writer.write("		if(" + mymap.get(sr) + ">=" + mymap.get(sr1)
															+ ") \n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											} else {
												boolean checkNum;
												
												char[] value = sr1.toCharArray();
												checkNum=CheckNumber(value);
												
												if (checkNum == true) {
													if (Initalizevariable.contains(sr)) {
														sr1 = ConvertNumber(value);
														writer.write(
																"		if(" + mymap.get(sr) + ">=" + sr1 + ") \n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										} else if (flag == true) {
											boolean check;
											
											char[] value = sr.toCharArray();
											check=CheckNumber(value);
											

											if (check == true) {
												if (myStringset.contains(sr1) == true) {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												} else if (myIntset.contains(sr1) == true) {
													if (Initalizevariable.contains(sr1)) {
														sr = ConvertNumber(value);
														writer.write(
																"		if(" + sr + ">=" + mymap.get(sr1) + ") \n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}

												} else if (sr.contains("\"") == true || sr1.contains("\"") == true) {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												} else {
													
													boolean checkNum;
													char[] value1 = sr1.toCharArray();
													checkNum=CheckNumber(value1);
													
													if (checkNum == true) {

														sr = ConvertNumber(value);
														sr1 = ConvertNumber(value1);
														writer.write("		if(" + sr + ">=" + sr1 + ") \n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
														flag = false;
													}

												}
											}

											else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}

									}
								}

							} else if (s.contains("==")&& s.contains("%")==false) {
								int p = t.indexOf('=');
								int q = t.lastIndexOf('=');
								for (int i = p + 1; i < q; i++) {
									if (array[i] != ' ') {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										break;
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
									} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									} else if (myStringset.contains(sr) == true && flag == true) {
										if (myIntset.contains(sr1) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myStringset.contains(sr1) == true) {
											if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
												writer.write(
														"		if(" + mymap.get(sr) + "==" + mymap.get(sr1) + ") \n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}

										} else {
											if (sr1.charAt(0) == '"' && sr1.charAt(sr1.length() - 1) == '"') {
												if (Initalizevariable.contains(sr)) {
													writer.write("		if(" + mymap.get(sr) + "==" + sr1 + ") \n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									} else if (myStringset.contains(sr1) == true && flag == true) {
										if (myIntset.contains(sr) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myStringset.contains(sr) == true) {
											if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
												writer.write(
														"		if(" + mymap.get(sr) + "==" + mymap.get(sr1) + ") \n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										} else {
											if (sr.charAt(0) == '"' && sr.charAt(sr.length() - 1) == '"') {
												if (Initalizevariable.contains(sr1)) {
													writer.write("		if(" + sr + "==" + mymap.get(sr1) + ") \n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									} else if (myIntset.contains(sr) == true) {
										if (myStringset.contains(sr1) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myIntset.contains(sr1) == true) {
											if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
												writer.write(
														"		if(" + mymap.get(sr) + "==" + mymap.get(sr1) + ") \n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										} else {
											boolean checkNum;
											
										
											char[] value = sr1.toCharArray();
											checkNum=CheckNumber(value);
											
											if (checkNum == true) {
												if (Initalizevariable.contains(sr)) {
													sr1 = ConvertNumber(value);
													writer.write("		if(" + mymap.get(sr) + "==" + sr1 + ") \n");
												}

												else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									} else if (flag == true && myIntset.contains(sr1) == true) {

										if (myStringset.contains(sr) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myIntset.contains(sr) == true) {
											if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
												writer.write(
														"		if(" + mymap.get(sr) + "==" + mymap.get(sr1) + ") \n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}

										} else {
											boolean checkNum;
											
											char[] value = sr.toCharArray();
											checkNum=CheckNumber(value);
											
											if (checkNum==true) {
												if (Initalizevariable.contains(sr1)) {
													sr = ConvertNumber(value);
													writer.write("		if(" + sr + "==" + mymap.get(sr1) + ") \n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									} else if (flag == true) {
										if (sr.contains("\"") && sr1.contains("\"")) {
											if (sr.charAt(0) == '"' && sr.charAt(sr.length() - 1) == '"'
													&& sr1.charAt(0) == '"' && sr1.charAt(sr1.length() - 1) == '"') {
												writer.write("		if(" + sr + "==" + sr1 + ") \n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										} else {
											if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"'
													&& sr1.charAt(0) != '"' && sr1.charAt(sr1.length() - 1) != '"') {
												boolean checkNum;
												
												char[] value = sr.toCharArray();
												checkNum=CheckNumber(value);
												
												if (checkNum == true) {
													
													boolean check;
													char[] value1 = sr1.toCharArray();
													check=CheckNumber(value1);
																										if (check == true) {

														sr = ConvertNumber(value);
														sr1 = ConvertNumber(value1);
														writer.write("		if(" + sr + "==" + sr1 + ") \n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										}
									} else {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									}

								}

							}
							else if (s.contains("!=")&& s.contains("%")==false) {
								int p = t.indexOf('=');
								int q = t.lastIndexOf('=');
								int m=t.indexOf('!');
								int n=t.lastIndexOf('!');
								if(p!=q||m!=n){
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}
								for (int i = p + 1; i < q; i++) {
									if (array[i] != ' ') {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
										break;
									}
								}
								
								if (flag == true) {
									String sr = new String(array, firstBracket + 1, m - firstBracket - 1);
									String sr1 = new String(array, q + 1, lastBrcket - q - 1);
									sr = sr.trim();
									sr1 = sr1.trim();
									if (sr.length() <= 0 || sr.length() <= 0) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									} else if (myStringset.contains(sr) == true && flag == true) {
										if (myIntset.contains(sr1) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myStringset.contains(sr1) == true) {
											if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
												writer.write(
														"		if(" + mymap.get(sr) + "!=" + mymap.get(sr1) + ")\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}

										} else {
											if (sr1.charAt(0) == '"' && sr1.charAt(sr1.length() - 1) == '"') {
												if (Initalizevariable.contains(sr)) {
													writer.write("		if(" + mymap.get(sr) + "!=" + sr1 + ")\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									} else if (myStringset.contains(sr1) == true && flag == true) {
										if (myIntset.contains(sr) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myStringset.contains(sr) == true) {
											if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
												writer.write(
														"		if(" + mymap.get(sr) + "!=" + mymap.get(sr1) + ")\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										} else {
											if (sr.charAt(0) == '"' && sr.charAt(sr.length() - 1) == '"') {
												if (Initalizevariable.contains(sr1)) {
													writer.write("		if(" + sr + "!=" + mymap.get(sr1) + ")\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									} else if (myIntset.contains(sr) == true) {
										if (myStringset.contains(sr1) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myIntset.contains(sr1) == true) {
											if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
												writer.write(
														"		if(" + mymap.get(sr) + "!=" + mymap.get(sr1) + ")\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										} else {
											boolean checkNum;
											
											char[] value = sr1.toCharArray();
											  checkNum=CheckNumber(value);											
													  
												

											if (checkNum == true) {
												if (Initalizevariable.contains(sr)) {
													sr1 = ConvertNumber(value);
													writer.write("		if(" + mymap.get(sr) + "!=" + sr1 + ")\n");
												}

												else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									} else if (flag == true && myIntset.contains(sr1) == true) {

										if (myStringset.contains(sr) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myIntset.contains(sr) == true) {
											if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
												writer.write(
														"		if(" + mymap.get(sr) + "!=" + mymap.get(sr1) + ")\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}

										} else {
											boolean checkNum;
											
											char[] value = sr.toCharArray();
											checkNum=CheckNumber(value);
											
											if (checkNum==true) {
												if (Initalizevariable.contains(sr1)) {
												
													sr = ConvertNumber(value);
													writer.write("		if(" + sr + "!=" + mymap.get(sr1) + ")\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									} else if (flag == true) {
										if (sr.contains("\"") && sr1.contains("\"")) {
											if (sr.charAt(0) == '"' && sr.charAt(sr.length() - 1) == '"'
													&& sr1.charAt(0) == '"' && sr1.charAt(sr1.length() - 1) == '"') {
												writer.write("		if(" + sr + "!=" + sr1 + ") \n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										} else {
											if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"'
													&& sr1.charAt(0) != '"' && sr1.charAt(sr1.length() - 1) != '"') {
												boolean checkNum;
												char[] value = sr.toCharArray();
												checkNum=CheckNumber(value);
												
												if (checkNum == true) {
													
													boolean check;
													char[] value1 = sr1.toCharArray();
													check=CheckNumber(value1);
													
													if (check == true) {

														sr = ConvertNumber(value);
														sr1 = ConvertNumber(value1);
														writer.write("		if(" + sr + "!=" + sr1 + ")\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										}
									} else {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									}

								}

							}
                             else if(s.contains("%")&&flag==true){
								
								char[] aray=readLine.toCharArray();
								int p=readLine.indexOf('%');
								int q=readLine.lastIndexOf('%');
								if(p!=q){
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}
								else{
									int sign;
									String temp=null,sr = null,sr1=null,sr2=null;
									if(s.contains("<=")){
										sign=readLine.indexOf('=');
										int r=readLine.lastIndexOf('=');
										int m=readLine.indexOf('<');
										int n=readLine.lastIndexOf('<');
										if(sign!=r ||m!=n){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										temp="<=";
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(array,p+1,m-p-1);
										sr2=new String(aray, sign+1,lastBrcket-sign-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
									}
									else if(s.contains(">=")){
										sign=readLine.indexOf('=');
										int r=readLine.lastIndexOf('=');
										int m=readLine.indexOf('>');
										int n=readLine.lastIndexOf('>');
										if(sign!=r ||m!=n){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(array,p+1,m-p-1);
										sr2=new String(aray, sign+1,lastBrcket-sign-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp=">=";
									}
									else if(s.contains("==")){
										sign=readLine.indexOf('=');
										int r=readLine.lastIndexOf('=');
										
										if((sign+1)!=r){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(aray,p+1,sign-p-1);
										sr2=new String(aray, r+1,lastBrcket-r-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp="==";
									}
									else if(s.contains("!=")){
										sign=readLine.indexOf('=');
										int r=readLine.lastIndexOf('=');
										int m=readLine.indexOf('!');
										int n=readLine.lastIndexOf('!');
										if(sign!=r ||m!=n){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(aray, p+1, m-p-1);
										 
										sr2=new String(aray, sign+1, lastBrcket-sign-1);
			
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp="!=";
									}
									else if(s.contains("<")){
										sign=readLine.indexOf('<');
										int r=readLine.lastIndexOf('<');
										
										if(sign!=r){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(aray, p+1,sign-p-1);
										sr2=new String(aray, sign+1,lastBrcket-sign-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp="<";
									}
									else if(s.contains(">")){
										sign=readLine.indexOf('>');
										int r=readLine.lastIndexOf('>');
										
										if(sign!=r){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(aray, p+1,sign-p-1);
										sr2=new String(aray, sign+1,lastBrcket-sign-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp=">";
									}
									else{
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									}
									if(flag==true){
										if(sr.contains("\"")||sr1.contains("\"")||sr2.contains("\"")){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										boolean chek,chek1,chek2;
										char []ap=sr.toCharArray();
										char[]ap1=sr1.toCharArray();
										char[]ap2=sr2.toCharArray();
										chek=CheckNumber(ap);
										chek1=CheckNumber(ap1);
										chek2=CheckNumber(ap2);
										if(chek==true&& chek1==true&&chek2==true){
											sr=ConvertNumber(ap);
											sr1=ConvertNumber(ap1);
											sr2=ConvertNumber(ap2);
											writer.write("     if("+sr+"%"+sr1+temp+sr2+")\n");
										}
										else if(chek==true && chek1==true&& chek2==false){
											if(myIntset.contains(sr2)){
												if(Initalizevariable.contains(sr2)){
													sr=ConvertNumber(ap);
													sr1=ConvertNumber(ap1);
													writer.write("       if("+sr+"%"+sr1+temp+mymap.get(sr2)+")\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==true && chek1==false&& chek2==true){
											if(myIntset.contains(sr1)){
												if(Initalizevariable.contains(sr1)){
													sr=ConvertNumber(ap);
													sr2=ConvertNumber(ap2);
													writer.write("       if("+sr+"%"+mymap.get(sr1)+temp+sr2+")\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==false && chek1==true&& chek2==true){
											if(myIntset.contains(sr)){
												if(Initalizevariable.contains(sr)){
													sr1=ConvertNumber(ap1);
													sr2=ConvertNumber(ap2);
													writer.write("       if("+mymap.get(sr)+"%"+sr1+temp+sr2+")\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==false && chek1==false&& chek2==false){
										  if(myIntset.contains(sr)&&myIntset.contains(sr1)&&myIntset.contains(sr2)){
											  if(Initalizevariable.contains(sr)&& Initalizevariable.contains(sr1)&&Initalizevariable.contains(sr2)){
												  writer.write("       if("+mymap.get(sr)+"%"+mymap.get(sr1)+temp+mymap.get(sr2)+")\n");
											  }
											  else{
												  System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
											  }
											  
										  }
										  else{
											  System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
										  }
										}
										else if(chek==false && chek1==false&& chek2==true){
											if(myIntset.contains(sr)&& myIntset.contains(sr1)){
												if(Initalizevariable.contains(sr)&& Initalizevariable.contains(sr1)){
													sr2=ConvertNumber(ap2);
													writer.write("      if("+mymap.get(sr)+"%"+mymap.get(sr1)+temp+sr2+")\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==true && chek1==false&& chek2==false){
											if(myIntset.contains(sr1)&& myIntset.contains(sr2)){
												if(Initalizevariable.contains(sr1)&& Initalizevariable.contains(sr2)){
													sr=ConvertNumber(ap);
													writer.write("      if("+sr+"%"+mymap.get(sr1)+temp+mymap.get(sr2)+")\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==false && chek1==true&& chek2==false){
											if(myIntset.contains(sr)&& myIntset.contains(sr2)){
												if(Initalizevariable.contains(sr)&& Initalizevariable.contains(sr2)){
													sr1=ConvertNumber(ap1);
													writer.write("      if("+mymap.get(sr)+"%"+sr1+temp+mymap.get(sr2)+")\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else{
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										
									}
								}
							}
							
							else if (s.contains(">")&& s.contains("%")==false) {
								int p = t.indexOf('>');
								int q = t.lastIndexOf('>');
								if (p != q) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}
								String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
								String sr1 = new String(array, p + 1, lastBrcket - p - 1);
								sr = sr.trim();
								sr1 = sr1.trim();

								if (sr.length() <= 0 || sr.length() <= 0) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myStringset.contains(sr) == true && flag == true) {

									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myStringset.contains(sr1) == true && flag == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myIntset.contains(sr) == true) {
									if (myIntset.contains(sr1) == true) {
										if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
											writer.write("		if(" + mymap.get(sr) + ">" + mymap.get(sr1) + ") \n");
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
													+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
											flag = false;
										}

									} else {
										boolean checkNum;
										
										char[] value = sr1.toCharArray();
										checkNum=CheckNumber(value);
										
										if (checkNum == true) {
											if (Initalizevariable.contains(sr)) {
												sr1 = ConvertNumber(value);
												writer.write("		if(" + mymap.get(sr) + ">" + sr1 + ") \n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
								} else if (myIntset.contains(sr1) == true && flag == true) {
									if (myStringset.contains(sr) == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									} else {
										if (sr.contains("\"") || sr1.contains("\"")) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else {
											boolean checkNum;
											char[] value = sr.toCharArray();
											checkNum=CheckNumber(value);
											
											if (checkNum == true) {
												if (Initalizevariable.contains(sr1)) {

													sr = ConvertNumber(value);
													writer.write("		if(" + sr + ">" + mymap.get(sr1) + ") \n");
												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									}
								} else if (flag == true) {
									if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"' && sr1.charAt(0) != '"'
											&& sr1.charAt(sr1.length() - 1) != '"') {

										boolean checkNum;
										
										char[] value = sr.toCharArray();
										checkNum=CheckNumber(value);
																				if (checkNum == true) {
									
											boolean check = false;
											char[] value1 = sr1.toCharArray();
											check=CheckNumber(value1);
											
											if (check == true) {

												sr = ConvertNumber(value);
												sr1 = ConvertNumber(value1);
												writer.write("		if(" + sr + ">" + sr1 + ") \n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}

							}
							else if (s.contains("<")&& s.contains("%")==false) {
								int p = t.indexOf('<');
								int q = t.lastIndexOf('<');
								if (p != q) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}
								String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
								String sr1 = new String(array, p + 1, lastBrcket - p - 1);
								sr = sr.trim();
								sr1 = sr1.trim();
								if (sr.length() <= 0 || sr.length() <= 0) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myStringset.contains(sr) == true && flag == true) {

									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myStringset.contains(sr1) == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;

								} else if (myIntset.contains(sr) == true) {
									if (myIntset.contains(sr1) == true) {
										if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
											writer.write("		if(" + mymap.get(sr) + "<" + mymap.get(sr1) + ") \n");
										}
									} else {
										boolean checkNum;
										
										char[] value = sr1.toCharArray();
										checkNum=CheckNumber(value);
										
										if (checkNum == true) {
											if (Initalizevariable.contains(sr)) {
												sr1=ConvertNumber(value);
												writer.write("		if(" + mymap.get(sr) + "<" + sr1 + ") \n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
								} else if (myIntset.contains(sr1) == true) {
									if (myStringset.contains(sr) == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									} else {
										if (sr.contains("\"") || sr1.contains("\"")) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else {
											if (Initalizevariable.contains(sr1)) {
												boolean checkNum;
												char[] value = sr1.toCharArray();
												checkNum=CheckNumber(value);
												
												if(checkNum==true){
												  sr=ConvertNumber(value);
												  writer.write("		if(" + sr + "<" + mymap.get(sr1) + ") \n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										}
									}
								} else if (flag == true) {
									if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"' && sr1.charAt(0) != '"'
											&& sr1.charAt(sr1.length() - 1) != '"') {
										boolean checkNum;
										
										char[] value = sr.toCharArray();
										checkNum=CheckNumber(value);
										
										if (checkNum == true) {
											
											boolean check = false;
											char[] value1 = sr1.toCharArray();
											check=CheckNumber(value1);
											
											if (check == true) {

												sr = ConvertNumber(value);
												sr1 = ConvertNumber(value1);
												writer.write("		if(" + sr + "<" + sr1 + ") \n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}

							}
							else{
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
							}
						}
						}
					} else if (cntLine == true && flag == true) {

						readLine = readLine.trim();
						if (readLine.endsWith("{") == false) {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
						} else if (array1[0].contains("{")) {
							writer.write("        {\n");
							cntLine = false;
						}

					} else if (readLine.endsWith("}") && flag == true) {
						readLine = readLine.trim();
						if (readLine.endsWith("}") == false) {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
						} else if (array1[0].contains("}")) {

							writer.write("        }\n");

						}
					} else if (array1[0].contains("নাহলে") && flag == true) {
						char[] array = readLine.toCharArray();
						String t = new String(array);
						array1[0] = array1[0].trim();
						if(myCondionalSet.empty()==true){
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
						}
						else{
							myCondionalSet.pop();
						}
						if (array1[0].equals("নাহলে") == false && array1[0].equals("নাহলে{") == false &&flag==true) {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;

						} else if (readLine.endsWith("{") == true && flag == true) {
							int p = t.indexOf('{');
							int q = t.lastIndexOf('{');
							if (p != q) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
							} else if (flag == true) {
								String checkLine = new String(array, 0, p - 0);
								checkLine = checkLine.trim();
								if (checkLine.equals("নাহলে") == false) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");

									flag = false;
								} else {
									writer.write("      	else {\n");
								}
							} else {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
							}
						} else if (array1[0].equals("নাহলে") == true && flag == true) {
							writer.write("      	else\n");
							cntLine = true;
						}
					} else if (readLine.contains("+") && readLine.contains("=")&&flag==true) {

						char[] variable = readLine.toCharArray();
						int p = readLine.lastIndexOf('=');
						int q = readLine.indexOf('=');
						int m = readLine.indexOf(';');
						if (p != q) {
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
						} else {
							String l = new String(variable, p + 1, m - p - 1);
							String value = l;
							String sr = new String(variable, 0, p);
							// System.out.println(l);
							sr = sr.trim();
							// System.out.println(sr);
							if (mymap.containsKey(sr) == false) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
							} else {
								String[] allvariable = new String[1000];

								Stack<String> Number = new Stack<String>();
								Stack<String> mystack = new Stack<String>();
								Stack<String> reversestack = new Stack<String>();

								String sp = "";
								// if()
								// System.out.println(readLine);

								for (int i = p + 1; i < m; i++) {

									if (variable[i] == '+') {

										if (sp != "") {
											// System.out.println(sp);
											mystack.push(sp);

										}
										char t = variable[i];

										// System.out.println(t);
										mystack.push(Character.toString(t));

										sp = "";
										
									} else if (variable[i] == '-') {
										if (sp != "") {
											// System.out.println(sp);
											mystack.push(sp);

										}
										char t = variable[i];

										// System.out.println(t);
										mystack.push(Character.toString(t));

										sp = "";
										
									} else if (variable[i] == '*') {
										if (sp != "") {
											// System.out.println(sp);
											mystack.push(sp);

										}
										char t = variable[i];

										// System.out.println(t);
										mystack.push(Character.toString(t));

										sp = "";
										// break;
									} else if (variable[i] == '/') {
										if (sp != "") {
											// System.out.println(sp);
											mystack.push(sp);

										}
										char t = variable[i];
										mystack.push(Character.toString(t));

										sp = "";
										
									} else if (variable[i] == '(') {
										if (sp != "") {
											// System.out.println(sp);
											mystack.push(sp);

										}
										char t = variable[i];

										mystack.push(Character.toString(t));

										sp = "";
										
									} else if (variable[i] == ')') {
										if (sp != "") {
											// System.out.println(sp);
											mystack.push(sp);

										}
										char t = variable[i];

										// System.out.println(t);
										mystack.push(Character.toString(t));

										sp = "";

									} else if (variable[i] != ' ') {

										sp += variable[i];

									}

								}

								if (sp != "") {
									mystack.push(sp);

								}

								while (!mystack.empty()) {
									String ch1 = mystack.pop();
									reversestack.push(ch1);
									Number.push(ch1);
									
								}
								
								ErrorChecking err = new ErrorChecking();

								flag = err.checkArithmaticError(readLine, myIntset, myStringset, Number, lineNumber,
										flag);
								if (flag == true) {

									writer.write("		" + mymap.get(sr) + " = ");
									while (!reversestack.empty()) {
										
										String ch1 = reversestack.pop();
										if (myIntset.contains(ch1)) {
											char var = mymap.get(ch1);

											writer.write(Character.toString(var));
										} else if (myStringset.contains(ch1) || ch1.contains("\"")) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else {
                                            char [] numberValue=ch1.toCharArray();
                                            ch1=ConvertNumber(numberValue);
											writer.write(ch1);
										}
									}

								}

							}
							if(flag==true){
								if(Initalizevariable.contains(sr)==false){
								   Initalizevariable.add(sr);
								}
							}
						}
						if (flag == true) {
							writer.write(";\n");
							
						} else if (readLine.contains("++")) {
							System.out.println("tt");
							char[] array = readLine.toCharArray();
							int lst = readLine.lastIndexOf('+');
							int fst = readLine.indexOf('+');
							if (fst + 1 != lst) {
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
							} else {
								String var = new String(array, 0, fst - 1);
								System.out.println(var);
							}
						}
					} else if (array1[0].contains("লুপ") == true && flag == true) {
						
						char[] array = readLine.toCharArray();
						String t = new String(array);
						int firstBracket = t.indexOf('(');
						int lastBrcket = t.indexOf(')');
						if(firstBracket==-1||lastBrcket==-1){
							System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
							flag = false;
						}
						if(flag==true){
						ErrorChecking error = new ErrorChecking();
						flag = error.checkLoopingError(readLine, array1, nonchar1, lineNumber, flag);
						if (readLine.endsWith("{") == true && flag == true) {

							String s = new String(array, firstBracket + 1, lastBrcket - firstBracket - 1);
							ErrorChecking error1 = new ErrorChecking();
							flag = error1.checkSpace(readLine, lastBrcket, lineNumber, flag);
							if (s.contains("<=") && flag == true&& s.contains("%")==false) {

								int p = t.indexOf('<');
								int m = t.lastIndexOf('<');
								int q = t.indexOf('=');
								int n = t.lastIndexOf('=');
								if (p > q || p != m || q != n) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else {
									for (int i = p + 1; i < q; i++) {

										if (array[i] != ' ') {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
											break;
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
										} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myIntset.contains(sr) == true && flag == true) {

											if (myStringset.contains(sr1) == true) {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											} else if (myIntset.contains(sr1) == true) {
												if (Initalizevariable.contains(sr1) && Initalizevariable.contains(sr)) {
													writer.write("		while(" + mymap.get(sr) + "<=" + mymap.get(sr1)
															+ ") {\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												boolean checkNum;
												
												
												char[] value = sr1.toCharArray();
												checkNum=CheckNumber(value);
												
												if (checkNum == true) {

													if (Initalizevariable.contains(sr)) {
														sr1 = ConvertNumber(value);

														writer.write(
																"		while(" + mymap.get(sr) + "<=" + sr1 + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										} else if (myStringset.contains(sr) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;

										} else if (flag == true) {
											boolean check;

											
											char[] value = sr.toCharArray();
											check=CheckNumber(value);
											

											if (check == true) {
												if (myStringset.contains(sr1) == true) {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												} else if (myIntset.contains(sr1) == true) {
													if (Initalizevariable.contains(sr1) == true) {
														sr = ConvertNumber(value);
														writer.write(
																"		while(" + sr + "<=" + mymap.get(sr1) + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}

												} else if (sr.contains("\"") == true || sr1.contains("\"") == true) {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												} else {
													
													boolean checkNum;
													char[] value1 = sr1.toCharArray();
													checkNum=CheckNumber(value1);
													
													if (checkNum == true) {

														sr = ConvertNumber(value);
														sr1 = ConvertNumber(value1);
														writer.write("		while(" + sr + "<=" + sr1 + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
														flag = false;
													}

												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									}
								}
							} else if (s.contains(">=")&& s.contains("%")==false) {

								int p = t.indexOf('>');
								int m = t.lastIndexOf('>');
								int q = t.indexOf('=');
								int n = t.lastIndexOf('=');
								if (p > q || p != m || q != n) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else {
									for (int i = p + 1; i < q; i++) {

										if (array[i] != ' ') {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
											break;
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
										} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myStringset.contains(sr) == true && flag == true) {

											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;

										} else if (myIntset.contains(sr) == true) {
											if (myStringset.contains(sr1) == true) {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											} else if (myIntset.contains(sr1) == true) {
												if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
													writer.write("		while(" + mymap.get(sr) + ">=" + mymap.get(sr1)
															+ ") {\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											} else {
												boolean checkNum;
												
												char[] value = sr1.toCharArray();
												checkNum=CheckNumber(value);
												
												if (checkNum == true) {
													if (Initalizevariable.contains(sr)) {
														sr1 = ConvertNumber(value);
														writer.write(
																"		while(" + mymap.get(sr) + ">=" + sr1 + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										} else if (flag == true) {
											boolean check;

											
											
											char[] value = sr.toCharArray();
											check=CheckNumber(value);
											
											if (check == true) {
												if (myStringset.contains(sr1) == true) {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												} else if (myIntset.contains(sr1) == true) {
													if (Initalizevariable.contains(sr1)) {
														sr = ConvertNumber(value);
														writer.write(
																"		while(" + sr + ">=" + mymap.get(sr1) + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}

												} else if (sr.contains("\"") == true || sr1.contains("\"") == true) {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												} else {
													
													boolean checkNum;
													char[] value1 = sr1.toCharArray();
													checkNum=CheckNumber(value1);
													
													if (checkNum == true) {

														sr = ConvertNumber(value);
														sr1 = ConvertNumber(value1);
														writer.write("		while(" + sr + ">=" + sr1 + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
														flag = false;
													}

												}
											}

											else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}

									}
								}

							} else if (s.contains("!=")&&s.contains("%")==false) {
								int p = t.indexOf('!');
								int r = t.lastIndexOf('!');
								int q = t.lastIndexOf('=');
								int m = t.indexOf('=');
								if (p != r && q != m) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else {
									for (int i = p + 1; i < q; i++) {
										if (array[i] != ' ') {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
											break;
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
										} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myStringset.contains(sr) == true && flag == true) {
											if (myIntset.contains(sr1) == true) {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											} else if (myStringset.contains(sr1) == true) {
												if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
													writer.write("		while(" + mymap.get(sr) + "!=" + mymap.get(sr1)
															+ ") {\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												if (sr1.charAt(0) == '"' && sr1.charAt(sr1.length() - 1) == '"') {
													if (Initalizevariable.contains(sr)) {
														writer.write(
																"		while(" + mymap.get(sr) + "!=" + sr1 + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										} else if (myStringset.contains(sr1) == true && flag == true) {
											if (myIntset.contains(sr) == true) {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											} else if (myStringset.contains(sr) == true) {
												if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
													writer.write("		while(" + mymap.get(sr) + "!=" + mymap.get(sr1)
															+ ") {\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												if (sr.charAt(0) == '"' && sr.charAt(sr.length() - 1) == '"') {
													if (Initalizevariable.contains(sr1)) {
														writer.write(
																"		while(" + sr + "!=" + mymap.get(sr1) + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}

												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										} else if (myIntset.contains(sr) == true) {
											
											if (myStringset.contains(sr1) == true) {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											} else if (myIntset.contains(sr1) == true) {
												if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
													writer.write("		while(" + mymap.get(sr) + "!=" + mymap.get(sr1)
															+ ") {\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												boolean checkNum;
												
												char[] value = sr1.toCharArray();
												checkNum=CheckNumber(value);
												
												if (checkNum == true) {
													if (Initalizevariable.contains(sr)) {
														sr1=ConvertNumber(value);
														writer.write(
																"		while(" + mymap.get(sr) + "!=" + sr1 + ") {\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										} else if (flag == true && myIntset.contains(sr1) == true) {

											if (myStringset.contains(sr) == true) {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											} else if (myIntset.contains(sr) == true) {
												if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
													writer.write("		while(" + mymap.get(sr) + "!=" + mymap.get(sr1)
															+ ") {\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												boolean checkNum;
												char[] value = sr.toCharArray();
												checkNum=CheckNumber(value);
												
													if(checkNum==true){
													   
													   if(Initalizevariable.contains(sr1)){
														 sr=ConvertNumber(value);
													      writer.write("		while(" + sr + "!=" + mymap.get(sr1) + ") {\n");
													   }
													   else{
														   System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																	+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
															flag = false; 
													   }
													}
													else{
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
														flag = false;
												} 
											}
										} else if (flag == true) {
											if (sr.contains("\"") && sr1.contains("\"")) {
												if (sr.charAt(0) == '"' && sr.charAt(sr.length() - 1) == '"'
														&& sr1.charAt(0) == '"'
														&& sr1.charAt(sr1.length() - 1) == '"') {
													writer.write("		while(" + sr + "!=" + sr1 + ") {\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											} else {
												if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"'
														&& sr1.charAt(0) != '"'
														&& sr1.charAt(sr1.length() - 1) != '"') {
													boolean checkNum;
												
													char[] value = sr.toCharArray();
													checkNum=CheckNumber(value);
													
													if (checkNum == true) {
														
														boolean check;
														char[] value1 = sr1.toCharArray();
														check=CheckNumber(value1);
														
														if (check == true) {

															sr = ConvertNumber(value);
															sr1 = ConvertNumber(value1);
															writer.write("		while(" + sr + "!=" + sr1 + ") \n");
														} else {
															System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
															flag = false;
														}
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}

									}
								}
							}
                             else if(s.contains("%")&&flag==true){
								
								char[] aray=readLine.toCharArray();
								int p=readLine.indexOf('%');
								int q=readLine.lastIndexOf('%');
								if(p!=q){
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}
								else{
									int sign;
									String temp=null,sr = null,sr1=null,sr2=null;
									if(s.contains("<=")){
										sign=readLine.indexOf('=');
										int r=readLine.lastIndexOf('=');
										int m=readLine.indexOf('<');
										int n=readLine.lastIndexOf('<');
										if(sign!=r ||m!=n){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										temp="<=";
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(array,p+1,m-p-1);
										sr2=new String(aray, sign+1,lastBrcket-sign-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
									}
									else if(s.contains(">=")){
										sign=readLine.indexOf('=');
										int r=readLine.lastIndexOf('=');
										int m=readLine.indexOf('>');
										int n=readLine.lastIndexOf('>');
										if(sign!=r ||m!=n){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(array,p+1,m-p-1);
										sr2=new String(aray, sign+1,lastBrcket-sign-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp=">=";
									}
									else if(s.contains("==")){
										sign=readLine.indexOf('=');
										int r=readLine.lastIndexOf('=');
										
										if((sign+1)!=r){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(aray,p+1,sign-p-1);
										sr2=new String(aray, r+1,lastBrcket-r-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp="==";
									}
									else if(s.contains("!=")){
										sign=readLine.indexOf('=');
										int r=readLine.lastIndexOf('=');
										int m=readLine.indexOf('!');
										int n=readLine.lastIndexOf('!');
										if(sign!=r ||m!=n){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(aray, p+1, m-p-1);
										 
										sr2=new String(aray, sign+1, lastBrcket-sign-1);
			
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp="!=";
									}
									else if(s.contains("<")){
										sign=readLine.indexOf('<');
										int r=readLine.lastIndexOf('<');
										
										if(sign!=r){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(aray, p+1,sign-p-1);
										sr2=new String(aray, sign+1,lastBrcket-sign-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp="<";
									}
									else if(s.contains(">")){
										sign=readLine.indexOf('>');
										int r=readLine.lastIndexOf('>');
										
										if(sign!=r){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(aray, p+1,sign-p-1);
										sr2=new String(aray, sign+1,lastBrcket-sign-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp=">";
									}
									else{
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									}
									if(flag==true){
										if(sr.contains("\"")||sr1.contains("\"")||sr2.contains("\"")){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										boolean chek,chek1,chek2;
										char []ap=sr.toCharArray();
										char[]ap1=sr1.toCharArray();
										char[]ap2=sr2.toCharArray();
										chek=CheckNumber(ap);
										chek1=CheckNumber(ap1);
										chek2=CheckNumber(ap2);
										if(chek==true&& chek1==true&&chek2==true){
											sr=ConvertNumber(ap);
											sr1=ConvertNumber(ap1);
											sr2=ConvertNumber(ap2);
											writer.write("     while("+sr+"%"+sr1+temp+sr2+"){\n");
										}
										else if(chek==true && chek1==true&& chek2==false){
											if(myIntset.contains(sr2)){
												if(Initalizevariable.contains(sr2)){
													sr=ConvertNumber(ap);
													sr1=ConvertNumber(ap1);
													writer.write("       while("+sr+"%"+sr1+temp+mymap.get(sr2)+"){\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==true && chek1==false&& chek2==true){
											if(myIntset.contains(sr1)){
												if(Initalizevariable.contains(sr1)){
													sr=ConvertNumber(ap);
													sr2=ConvertNumber(ap2);
													writer.write("       while("+sr+"%"+mymap.get(sr1)+temp+sr2+"){\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==false && chek1==true&& chek2==true){
											if(myIntset.contains(sr)){
												if(Initalizevariable.contains(sr)){
													sr1=ConvertNumber(ap1);
													sr2=ConvertNumber(ap2);
													writer.write("       while("+mymap.get(sr)+"%"+sr1+temp+sr2+"){\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==false && chek1==false&& chek2==false){
										  if(myIntset.contains(sr)&&myIntset.contains(sr1)&&myIntset.contains(sr2)){
											  if(Initalizevariable.contains(sr)&& Initalizevariable.contains(sr1)&&Initalizevariable.contains(sr2)){
												  writer.write("       while("+mymap.get(sr)+"%"+mymap.get(sr1)+temp+mymap.get(sr2)+"){\n");
											  }
											  else{
												  System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
											  }
											  
										  }
										  else{
											  System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
										  }
										}
										else if(chek==false && chek1==false&& chek2==true){
											if(myIntset.contains(sr)&& myIntset.contains(sr1)){
												if(Initalizevariable.contains(sr)&& Initalizevariable.contains(sr1)){
													sr2=ConvertNumber(ap2);
													writer.write("      while("+mymap.get(sr)+"%"+mymap.get(sr1)+temp+sr2+"){\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==true && chek1==false&& chek2==false){
											if(myIntset.contains(sr1)&& myIntset.contains(sr2)){
												if(Initalizevariable.contains(sr1)&& Initalizevariable.contains(sr2)){
													sr=ConvertNumber(ap);
													writer.write("      while("+sr+"%"+mymap.get(sr1)+temp+mymap.get(sr2)+"){\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==false && chek1==true&& chek2==false){
											if(myIntset.contains(sr)&& myIntset.contains(sr2)){
												if(Initalizevariable.contains(sr)&& Initalizevariable.contains(sr2)){
													sr1=ConvertNumber(ap1);
													writer.write("      while("+mymap.get(sr)+"%"+sr1+temp+mymap.get(sr2)+"){\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else{
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										
									}
								}
							}
							
							else if (s.contains(">")&& s.contains("%")==false) {
								int p = t.indexOf('>');
								int q = t.lastIndexOf('>');
								if (p != q) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}
								String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
								String sr1 = new String(array, p + 1, lastBrcket - p - 1);
								sr = sr.trim();
								sr1 = sr1.trim();

								if (sr.length() <= 0 || sr.length() <= 0) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myStringset.contains(sr) == true && flag == true) {

									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myStringset.contains(sr1) == true && flag == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myIntset.contains(sr) == true) {
									if (myIntset.contains(sr1) == true) {
										if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
											writer.write("		while(" + mymap.get(sr) + ">" + mymap.get(sr1) + ") {\n");
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
													+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
											flag = false;
										}

									} else {
										boolean checkNum;
										
										char[] value = sr1.toCharArray();
										checkNum=CheckNumber(value);
										
										if (checkNum == true) {
											if (Initalizevariable.contains(sr)) {
												sr1 = ConvertNumber(value);
												writer.write("		while(" + mymap.get(sr) + ">" + sr1 + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
								} else if (myIntset.contains(sr1) == true && flag == true) {
									if (myStringset.contains(sr) == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									} else {
										if (sr.contains("\"") || sr1.contains("\"")) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else {
											boolean checkNum;
											char[] value = sr.toCharArray();
											checkNum=CheckNumber(value);
											
											if (checkNum == true) {
												if (Initalizevariable.contains(sr1)) {

													sr = ConvertNumber(value);
													writer.write("		while(" + sr + ">" + mymap.get(sr1) + ") {\n");
												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									}
								} else if (flag == true) {
									if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"' && sr1.charAt(0) != '"'
											&& sr1.charAt(sr1.length() - 1) != '"') {

										boolean checkNum;
										char[] value = sr.toCharArray();
										checkNum=CheckNumber(value);
										
										if (checkNum == true) {
											
											boolean check;
											char[] value1 = sr1.toCharArray();
											check=CheckNumber(value1);
											
											if (check == true) {

												sr = ConvertNumber(value);
												sr1 = ConvertNumber(value1);
												writer.write("		while(" + sr + ">" + sr1 + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}

							} else if (s.contains("<")&& s.contains("%")==false) {
								int p = t.indexOf('<');
								int q = t.lastIndexOf('<');
								if (p != q) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}
								String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
								String sr1 = new String(array, p + 1, lastBrcket - p - 1);
								sr = sr.trim();
								sr1 = sr1.trim();
								if (sr.length() <= 0 || sr.length() <= 0) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myStringset.contains(sr) == true && flag == true) {

									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myStringset.contains(sr1) == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;

								} else if (myIntset.contains(sr) == true) {
									if (myIntset.contains(sr1) == true) {
										if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
											writer.write("		while(" + mymap.get(sr) + "<" + mymap.get(sr1) + ") {\n");
										}
									} else {
										boolean checkNum;
										
										char[] value = sr1.toCharArray();
										checkNum=CheckNumber(value);
										
										if (checkNum == true) {
											if (Initalizevariable.contains(sr)) {
												sr1=ConvertNumber(value);
												writer.write("		while(" + mymap.get(sr) + "<" + sr1 + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
								} else if (myIntset.contains(sr1) == true) {
									if (myStringset.contains(sr) == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									} else {
										if (sr.contains("\"") || sr1.contains("\"")) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else {
											if (Initalizevariable.contains(sr1)) {
												boolean checkNum;
												char[] value = sr1.toCharArray();
												checkNum=CheckNumber(value);
												
												if(checkNum==true){
												  sr=ConvertNumber(value);
												  writer.write("		while(" + sr + "<" + mymap.get(sr1) + ") {\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										}
									}
								} else if (flag == true) {
									if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"' && sr1.charAt(0) != '"'
											&& sr1.charAt(sr1.length() - 1) != '"') {
										boolean checkNum ;
										
										char[] value = sr.toCharArray();
										checkNum=CheckNumber(value);
										
										if (checkNum == true) {
										
											boolean check ;
											char[] value1 = sr1.toCharArray();
											check=CheckNumber(value1);
											
											if (check == true) {

												sr = ConvertNumber(value);
												sr1 = ConvertNumber(value1);
												writer.write("		while(" + sr + "<" + sr1 + ") {\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}

							}
							else{
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
							}
						} else if (readLine.contains("{") == false && flag == true) {
							
							String s = new String(array, firstBracket + 1, lastBrcket - firstBracket - 1);
							cntLine = true;
							for (int i = lastBrcket + 1; i < readLine.length() - 1; i++) {
								if (array[i] != ' ') {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
									break;
								}

							}
							if (s.contains("<=") && flag == true&&s.contains("%")==false) {

								int p = t.indexOf('<');
								int m = t.lastIndexOf('<');
								int q = t.indexOf('=');
								int n = t.lastIndexOf('=');
								if (p > q || p != m || q != n) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else {
									for (int i = p + 1; i < q; i++) {

										if (array[i] != ' ') {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
											break;
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
										} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myIntset.contains(sr) == true && flag == true) {

											if (myStringset.contains(sr1) == true) {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											} else if (myIntset.contains(sr1) == true) {
												if (Initalizevariable.contains(sr1) && Initalizevariable.contains(sr)) {
													writer.write("		while(" + mymap.get(sr) + "<=" + mymap.get(sr1)
															+ ")\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												boolean checkNum;
												

												
												char[] value = sr1.toCharArray();
												checkNum=CheckNumber(value);
												
												if (checkNum == true) {

													if (Initalizevariable.contains(sr)) {
														sr1 = ConvertNumber(value);

														writer.write(
																"		while(" + mymap.get(sr) + "<=" + sr1 + ")\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										} else if (myStringset.contains(sr) == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;

										} else if (flag == true) {
											boolean check = true;

											
											char[] value = sr.toCharArray();
											check=CheckNumber(value);
											
											if (check == true) {
												if (myStringset.contains(sr1) == true) {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												} else if (myIntset.contains(sr1) == true) {
													if (Initalizevariable.contains(sr1) == true) {
														sr = ConvertNumber(value);
														writer.write(
																"		while(" + sr + "<=" + mymap.get(sr1) + ")\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}

												} else if (sr.contains("\"") == true || sr1.contains("\"") == true) {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												} else {
													
													boolean checkNum = false;
													char[] value1 = sr1.toCharArray();
													checkNum=CheckNumber(value1);
													
													if (checkNum == true) {

														sr = ConvertNumber(value);
														sr1 = ConvertNumber(value1);
														writer.write("		while(" + sr + "<=" + sr1 + ")\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
														flag = false;
													}

												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									}
								}
							} else if (s.contains(">=")&&s.contains("%")==false) {

								int p = t.indexOf('>');
								int m = t.lastIndexOf('>');
								int q = t.indexOf('=');
								int n = t.lastIndexOf('=');
								if (p > q || p != m || q != n) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else {
									for (int i = p + 1; i < q; i++) {

										if (array[i] != ' ') {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
											break;
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
										} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myStringset.contains(sr) == true && flag == true) {

											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;

										} else if (myIntset.contains(sr) == true) {
											if (myStringset.contains(sr1) == true) {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											} else if (myIntset.contains(sr1) == true) {
												if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
													writer.write("		while(" + mymap.get(sr) + ">=" + mymap.get(sr1)
															+ ")\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											} else {
												boolean checkNum;
												
												char[] value = sr1.toCharArray();
												checkNum=CheckNumber(value);
												
												if (checkNum == true) {
													if (Initalizevariable.contains(sr)) {
														sr1 = ConvertNumber(value);
														writer.write(
																"		while(" + mymap.get(sr) + ">=" + sr1 + ")\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										} else if (flag == true) {
											boolean check;

											
											char[] value = sr.toCharArray();
											check=CheckNumber(value);
											
											if (check == true) {
												if (myStringset.contains(sr1) == true) {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												} else if (myIntset.contains(sr1) == true) {
													if (Initalizevariable.contains(sr1)) {
														sr = ConvertNumber(value);
														writer.write(
																"		while(" + sr + ">=" + mymap.get(sr1) + ")\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}

												} else if (sr.contains("\"") == true || sr1.contains("\"") == true) {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												} else {
													
													boolean checkNum;
													char[] value1 = sr1.toCharArray();
													checkNum=CheckNumber(value1);
													
													if (checkNum == true) {

														sr = ConvertNumber(value);
														sr1 = ConvertNumber(value1);
														writer.write("		while(" + sr + ">=" + sr1 + ")\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
														flag = false;
													}

												}
											}

											else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}

									}
								}

							} else if (s.contains("!=")&& s.contains("%")==false) {
								int p = t.indexOf('!');
								int r = t.lastIndexOf('!');
								int q = t.lastIndexOf('=');
								int m = t.indexOf('=');
								if (p != r && q != m) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else {
									for (int i = p + 1; i < q; i++) {
										if (array[i] != ' ') {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
											break;
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
										} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else if (myStringset.contains(sr) == true && flag == true) {
											if (myIntset.contains(sr1) == true) {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											} else if (myStringset.contains(sr1) == true) {
												if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
													writer.write("		while(" + mymap.get(sr) + "!=" + mymap.get(sr1)
															+ ")\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												if (sr1.charAt(0) == '"' && sr1.charAt(sr1.length() - 1) == '"') {
													if (Initalizevariable.contains(sr)) {
														writer.write(
																"		while(" + mymap.get(sr) + "!=" + sr1 + ")\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										} else if (myStringset.contains(sr1) == true && flag == true) {
											if (myIntset.contains(sr) == true) {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											} else if (myStringset.contains(sr) == true) {
												if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
													writer.write("		while(" + mymap.get(sr) + "!=" + mymap.get(sr1)
															+ ")\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												if (sr.charAt(0) == '"' && sr.charAt(sr.length() - 1) == '"') {
													if (Initalizevariable.contains(sr1)) {
														writer.write(
																"		while(" + sr + "!=" + mymap.get(sr1) + ")\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}

												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										} else if (myIntset.contains(sr) == true) {
											if (myStringset.contains(sr1) == true) {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											} else if (myIntset.contains(sr1) == true) {
												if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
													writer.write("		while(" + mymap.get(sr) + "!=" + mymap.get(sr1)
															+ ")\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												boolean checkNum ;
												
												char[] value = sr1.toCharArray();
												checkNum=CheckNumber(value);
												
												if (checkNum == true) {
													if (Initalizevariable.contains(sr)) {
														sr1=ConvertNumber(value);
														writer.write(
																"		while(" + mymap.get(sr) + "!=" + sr1 + ")\n");
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										} else if (flag == true && myIntset.contains(sr1) == true) {

											if (myStringset.contains(sr) == true) {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											} else if (myIntset.contains(sr) == true) {
												if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
													writer.write("		while(" + mymap.get(sr) + "!=" + mymap.get(sr1)
															+ ")\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}

											} else {
												boolean checkNum;
												char[] value = sr.toCharArray();
												checkNum=CheckNumber(value);
												
													if(checkNum==true){
													   
													   if(Initalizevariable.contains(sr1)){
														 sr=ConvertNumber(value);
													      writer.write("		while(" + sr + "!=" + mymap.get(sr1) + ")\n");
													   }
													   else{
														   System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
																	+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
															flag = false; 
													   }
													}
													else{
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
														flag = false;
												} 
											}
										} else if (flag == true) {
											if (sr.contains("\"") && sr1.contains("\"")) {
												if (sr.charAt(0) == '"' && sr.charAt(sr.length() - 1) == '"'
														&& sr1.charAt(0) == '"'
														&& sr1.charAt(sr1.length() - 1) == '"') {
													writer.write("		while(" + sr + "!=" + sr1 + ")\n");
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											} else {
												if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"'
														&& sr1.charAt(0) != '"'
														&& sr1.charAt(sr1.length() - 1) != '"') {
													boolean checkNum;
													
													char[] value = sr.toCharArray();
													checkNum=CheckNumber(value);
													
													if (checkNum == true) {
														
														boolean check;
														char[] value1 = sr1.toCharArray();
														check=CheckNumber(value1);
														
														if (check == true) {

															sr = ConvertNumber(value);
															sr1 = ConvertNumber(value1);
															writer.write("		while(" + sr + "!=" + sr1 + ")\n");
														} else {
															System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
															flag = false;
														}
													} else {
														System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
														flag = false;
													}
												} else {
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}

									}
								}
							} 
							
							else if(s.contains("%")&&flag==true){
								
								char[] aray=readLine.toCharArray();
								int p=readLine.indexOf('%');
								int q=readLine.lastIndexOf('%');
								if(p!=q){
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}
								else{
									int sign;
									String temp=null,sr = null,sr1=null,sr2=null;
									if(s.contains("<=")){
										sign=readLine.indexOf('=');
										int r=readLine.lastIndexOf('=');
										int m=readLine.indexOf('<');
										int n=readLine.lastIndexOf('<');
										if(sign!=r ||m!=n){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										temp="<=";
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(array,p+1,m-p-1);
										sr2=new String(aray, sign+1,lastBrcket-sign-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
									}
									else if(s.contains(">=")){
										sign=readLine.indexOf('=');
										int r=readLine.lastIndexOf('=');
										int m=readLine.indexOf('>');
										int n=readLine.lastIndexOf('>');
										if(sign!=r ||m!=n){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(array,p+1,m-p-1);
										sr2=new String(aray, sign+1,lastBrcket-sign-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp=">=";
									}
									else if(s.contains("==")){
										sign=readLine.indexOf('=');
										int r=readLine.lastIndexOf('=');
										
										if((sign+1)!=r){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(aray,p+1,sign-p-1);
										sr2=new String(aray, r+1,lastBrcket-r-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp="==";
									}
									else if(s.contains("!=")){
										sign=readLine.indexOf('=');
										int r=readLine.lastIndexOf('=');
										int m=readLine.indexOf('!');
										int n=readLine.lastIndexOf('!');
										if(sign!=r ||m!=n){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(aray, p+1, m-p-1);
										 
										sr2=new String(aray, sign+1, lastBrcket-sign-1);
			
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp="!=";
									}
									else if(s.contains("<")){
										sign=readLine.indexOf('<');
										int r=readLine.lastIndexOf('<');
										
										if(sign!=r){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(aray, p+1,sign-p-1);
										sr2=new String(aray, sign+1,lastBrcket-sign-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp="<";
									}
									else if(s.contains(">")){
										sign=readLine.indexOf('>');
										int r=readLine.lastIndexOf('>');
										
										if(sign!=r){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										sr=new String(aray,firstBracket+1,p-firstBracket-1);
										sr1=new String(aray, p+1,sign-p-1);
										sr2=new String(aray, sign+1,lastBrcket-sign-1);
										sr=sr.trim();
										sr1=sr1.trim();
										sr2=sr2.trim();
										temp=">";
									}
									else{
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									}
									if(flag==true){
										if(sr.contains("\"")||sr1.contains("\"")||sr2.contains("\"")){
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										boolean chek,chek1,chek2;
										char []ap=sr.toCharArray();
										char[]ap1=sr1.toCharArray();
										char[]ap2=sr2.toCharArray();
										chek=CheckNumber(ap);
										chek1=CheckNumber(ap1);
										chek2=CheckNumber(ap2);
										if(chek==true&& chek1==true&&chek2==true){
											sr=ConvertNumber(ap);
											sr1=ConvertNumber(ap1);
											sr2=ConvertNumber(ap2);
											writer.write("     while("+sr+"%"+sr1+temp+sr2+"){\n");
										}
										else if(chek==true && chek1==true&& chek2==false){
											if(myIntset.contains(sr2)){
												if(Initalizevariable.contains(sr2)){
													sr=ConvertNumber(ap);
													sr1=ConvertNumber(ap1);
													writer.write("       while("+sr+"%"+sr1+temp+mymap.get(sr2)+")\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==true && chek1==false&& chek2==true){
											if(myIntset.contains(sr1)){
												if(Initalizevariable.contains(sr1)){
													sr=ConvertNumber(ap);
													sr2=ConvertNumber(ap2);
													writer.write("       while("+sr+"%"+mymap.get(sr1)+temp+sr2+")\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==false && chek1==true&& chek2==true){
											if(myIntset.contains(sr)){
												if(Initalizevariable.contains(sr)){
													sr1=ConvertNumber(ap1);
													sr2=ConvertNumber(ap2);
													writer.write("       while("+mymap.get(sr)+"%"+sr1+temp+sr2+")\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==false && chek1==false&& chek2==false){
										  if(myIntset.contains(sr)&&myIntset.contains(sr1)&&myIntset.contains(sr2)){
											  if(Initalizevariable.contains(sr)&& Initalizevariable.contains(sr1)&&Initalizevariable.contains(sr2)){
												  writer.write("       while("+mymap.get(sr)+"%"+mymap.get(sr1)+temp+mymap.get(sr2)+")\n");
											  }
											  else{
												  System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
											  }
											  
										  }
										  else{
											  System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
										  }
										}
										else if(chek==false && chek1==false&& chek2==true){
											if(myIntset.contains(sr)&& myIntset.contains(sr1)){
												if(Initalizevariable.contains(sr)&& Initalizevariable.contains(sr1)){
													sr2=ConvertNumber(ap2);
													writer.write("      while("+mymap.get(sr)+"%"+mymap.get(sr1)+temp+sr2+")\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==true && chek1==false&& chek2==false){
											if(myIntset.contains(sr1)&& myIntset.contains(sr2)){
												if(Initalizevariable.contains(sr1)&& Initalizevariable.contains(sr2)){
													sr=ConvertNumber(ap);
													writer.write("      while("+sr+"%"+mymap.get(sr1)+temp+mymap.get(sr2)+")\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else if(chek==false && chek1==true&& chek2==false){
											if(myIntset.contains(sr)&& myIntset.contains(sr2)){
												if(Initalizevariable.contains(sr)&& Initalizevariable.contains(sr2)){
													sr1=ConvertNumber(ap1);
													writer.write("      while("+mymap.get(sr)+"%"+sr1+temp+mymap.get(sr2)+")\n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
															+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
													flag = false;
												}
											}
											else{
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
										else{
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
										
									}
								}
							}
							
							else if (s.contains(">")&& s.contains("%")==false) {
								int p = t.indexOf('>');
								int q = t.lastIndexOf('>');
								if (p != q) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}
								String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
								String sr1 = new String(array, p + 1, lastBrcket - p - 1);
								sr = sr.trim();
								sr1 = sr1.trim();

								if (sr.length() <= 0 || sr.length() <= 0) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myStringset.contains(sr) == true && flag == true) {

									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myStringset.contains(sr1) == true && flag == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myIntset.contains(sr) == true) {
									if (myIntset.contains(sr1) == true) {
										if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
											writer.write("		while(" + mymap.get(sr) + ">" + mymap.get(sr1) + ")\n");
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
													+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
											flag = false;
										}

									} else {
										boolean checkNum;
										
										char[] value = sr1.toCharArray();
										checkNum=CheckNumber(value);
										
										if (checkNum == true) {
											if (Initalizevariable.contains(sr)) {
												sr1 = ConvertNumber(value);
												writer.write("		while(" + mymap.get(sr) + ">" + sr1 + ")\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
								} else if (myIntset.contains(sr1) == true && flag == true) {
									if (myStringset.contains(sr) == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									} else {
										if (sr.contains("\"") || sr1.contains("\"")) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else {
											boolean checkNum;
											char[] value = sr.toCharArray();
											checkNum=CheckNumber(value);
											
											if (checkNum == true) {
												if (Initalizevariable.contains(sr1)) {

													sr = ConvertNumber(value);
													writer.write("		while(" + sr + ">" + mymap.get(sr1) + ")\n");
												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										}
									}
								} else if (flag == true) {
									if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"' && sr1.charAt(0) != '"'
											&& sr1.charAt(sr1.length() - 1) != '"') {

										boolean checkNum ;
										char[] value = sr.toCharArray();
										checkNum=CheckNumber(value);
										
										if (checkNum == true) {
											
											boolean check;
											char[] value1 = sr1.toCharArray();
											check=CheckNumber(value1);
											
											if (check == true) {

												sr = ConvertNumber(value);
												sr1 = ConvertNumber(value1);
												writer.write("		while(" + sr + ">" + sr1 + ")\n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}

							} else if (s.contains("<")&&s.contains("%")==false) {
								int p = t.indexOf('<');
								int q = t.lastIndexOf('<');
								if (p != q) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}
								String sr = new String(array, firstBracket + 1, p - firstBracket - 1);
								String sr1 = new String(array, p + 1, lastBrcket - p - 1);
								sr = sr.trim();
								sr1 = sr1.trim();
								if (sr.length() <= 0 || sr.length() <= 0) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (sr.contains(" ") == true || sr1.contains(" ") == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myStringset.contains(sr) == true && flag == true) {

									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								} else if (myStringset.contains(sr1) == true) {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;

								} else if (myIntset.contains(sr) == true) {
									if (myIntset.contains(sr1) == true) {
										if (Initalizevariable.contains(sr) && Initalizevariable.contains(sr1)) {
											writer.write("		while(" + mymap.get(sr) + "<" + mymap.get(sr1) + ") \n");
										}
									} else {
										boolean checkNum;
										
										char[] value = sr1.toCharArray();
										checkNum=CheckNumber(value);
										
										if (checkNum == true) {
											if (Initalizevariable.contains(sr)) {
												sr1=ConvertNumber(value);
												writer.write("		while(" + mymap.get(sr) + "<" + sr1 + ") \n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
								} else if (myIntset.contains(sr1) == true) {
									if (myStringset.contains(sr) == true) {
										System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
										flag = false;
									} else {
										if (sr.contains("\"") || sr1.contains("\"")) {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										} else {
											if (Initalizevariable.contains(sr1)) {
												boolean checkNum;
												char[] value = sr.toCharArray();
												checkNum=CheckNumber(value);
												
												if(checkNum==true){
												  sr=ConvertNumber(value);
												  writer.write("		while(" + sr + "<" + mymap.get(sr1) + ") \n");
												}
												else{
													System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
													flag = false;
												}
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে \n"
														+ "ভ্যারিয়্যাবল এর পূর্ব মান দেওয়া হয়নি");
												flag = false;
											}
										}
									}
								} else if (flag == true) {
									if (sr.charAt(0) != '"' && sr.charAt(sr.length() - 1) != '"' && sr1.charAt(0) != '"'
											&& sr1.charAt(sr1.length() - 1) != '"') {
										boolean checkNum ;
										
										char[] value = sr.toCharArray();
										checkNum=CheckNumber(value);
										
										if (checkNum == true) {
											
											boolean check;
											char[] value1 = sr1.toCharArray();
											check=CheckNumber(value1);
											
											if (check == true) {

												sr = ConvertNumber(value);
												sr1 = ConvertNumber(value1);
												writer.write("		while(" + sr + "<" + sr1 + ") \n");
											} else {
												System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
												flag = false;
											}
										} else {
											System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
											flag = false;
										}
									}
								} else {
									System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
									flag = false;
								}
							}
							else{
								System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");
								flag = false;
							}
							}
						}
					} else if (keywordSet.containsKey(array1[0]) == false && flag == true && readLine.length() != 0
							&& readLine.endsWith("}") == false && array1[0].contains("শুরু") == false
							&& readLine.endsWith("{") == false) {
						System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে");

						flag = false;
					}

				}

				if (prev.length() != 1 && prev.endsWith("}") == false) {

					System.out.println(lineNumber + " নম্বর লাইনে ভুল আছে ");
				}
				if (!st.empty())
					System.out.println("{ অসামঞ্জ্যসতা হয়ছে");

			} catch (IOException ex) {

			} finally {
				writer.write("	}\n");
				
				try {
					writer.flush();
					writer.close();
					br.close();

					if (flag == true) {

						// SimpleBanglatest a = new SimpleBanglatest();
						// TestForDu a=new TestForDu();
						// fileName a =new fileName();
						//t a=new t();
						simplecode a=new simplecode();
						//Dunia a=new Dunia();

					}
					else if (flag == false) {
						File file1=new File("src/file_systems/" + fileName + ".java");
						
						file1.delete();
						//writer.write("}\n");
						// mymap.clear();
						// mymap1.clear();
						// myIntset.clear();
						// myStringset.clear();

					}

				} catch (Exception ex) {
				}

			}

		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public String ConvertNumber(char[] ar) {
		for (int i = 0; i < ar.length; i++) {
			char grade = ar[i];
			switch (grade) {
			case '০':
				ar[i] = '0';
				break;

			case '১':
				ar[i] = '1';
				break;

			case '২':
				ar[i] = '2';
				break;
			case '৩':
				ar[i] = '3';
				break;
			case '৪':
				ar[i] = '4';
				break;
			case '৫':
				ar[i] = '5';
				break;
			case '৬':
				ar[i] = '6';
				break;
			case '৭':
				ar[i] = '7';
				break;
			case '৮':
				ar[i] = '8';
				break;
			case '৯':
				ar[i] = '9';
				break;
			}
		}
		String t = new String(ar);
		return t;
	}

	public boolean CheckNumber(char[] array) {
		boolean checkNum = false;
		boolean count = false;

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < number.length; j++) {
				if (array[i] == number[j]) {
					count = true;
					break;
				}

			}
			if (count == true) {
				checkNum = true;
				count = false;
			} else {
				checkNum = false;
				return checkNum;
			}
		}
		return checkNum;
	}
}
