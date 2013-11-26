package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AmazonOnline {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input;
		while ((input = scanner.nextLine()) != null) {
			input = input.replace("[", "").replace("]", "").replace(" ", "");
			String[] is = input.split(",");
			List<String> s = new ArrayList<String>();
			for (String e : is) {
				if (s.contains(e)) {
					s.remove(e);
				} else {
					s.add(e);
				}
			}
			if (s.size() == 1) {
				System.out.println(s.get(0));
			} else {
				System.out.println(-1);
			}
        }
	}
	public static int process(String input) {
		  System.out.println(input);
		  // Write your code here
		  return 0;
		 }

	
	private static void test() {
		
	}

}
