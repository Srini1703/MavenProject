package MyProject.Utlities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Pract {

	public static void main(String[] args) {
		/*int m=0;
		ArrayList<String> list = new ArrayList<>();
		list.add("a");list.add("e");list.add("i");list.add("o");list.add("u");
		list.add("A");list.add("E");list.add("I");list.add("O");list.add("U");
		String s = "hellO, wOrld";
		char[] ch = new char[s.length()];
		for (int i = 0; i < s.length(); i++) {
            ch[i] = s.charAt(i);
        }
		ArrayList<Character> loadVowels = new ArrayList<Character>();
		for(char c:ch) {
			if(list.contains(String.valueOf(c))) {
				loadVowels.add(c);
			}
		}
		Collections.reverse(loadVowels);
		for(int k=0;k<ch.length;k++) {
			if(list.contains(String.valueOf(ch[k]))) {
				ch[k]=loadVowels.get(m);
				m++;
			}
		}
		String string = new String(ch);
		System.out.println(string);*/
		
		
		String overall="";
		String arr[] = {"Daisy", "Rose", "Hyacinth", "Poppy"};
		List<String> list= Arrays.asList(arr);
		String longestWord = list.stream().max(Comparator.comparingInt(String::length)).get();
		for(int k=0;k<longestWord.length();k++) {
			for(int i=0;i<arr.length;i++) {
				try {
					overall = overall+arr[i].charAt(k);
				}catch(StringIndexOutOfBoundsException se) {
					
				}
			}
		}
		System.out.println(overall);
	}

}
