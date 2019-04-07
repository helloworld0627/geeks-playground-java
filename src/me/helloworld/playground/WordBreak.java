package me.helloworld.playground;

import java.util.*;

public class WordBreak {
	
	public static void breakDown(int start, String input, Set<String> set, String out) {
		if (start == input.length()) {
			System.out.println(out);
			return;
		}
		
		for (int i = start; i <= input.length(); i++) {
			String sub = input.substring(start, i);
			if (set.contains(sub)) {
				String newStr = out + " " + sub;	
				breakDown(i, input, set, newStr);
			}
		}
	}
	
	public static void main(String... args) {
		
		String input = "jumpedoversomething";
		
		String[] ss = new String[] {
			"jump",
			"jumped",
			"jumpedov",
			"over",
			"some",
			"thing",
			"something"
		};
		
		List<String> tmp = Arrays.asList(ss);
		Set<String> set = new HashSet(tmp);
		
		breakDown(0, input, set, "");
	}
}
