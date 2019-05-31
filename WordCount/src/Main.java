package WordCount

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
	//read in text
	public static String readFile(String filename) throws Exception {
		String data = "";
		data = new String(Files.readAll(Paths.get(filename)));
		return data;
	}

	public static void main(String[] args) throws Exception {
		//read in string
		String data = readFile("./HumanRights.txt");
		//make lowercase
		data = data.toLowerCase();
		//remove punctuation
		data = data.replaceAll("[[\\.\\?\\!\\,\\;\\:\\{\\}\\(\\)\\']]", "");
		//split on space
		String[] words = data.split(" + ");
		//hash map
		HashMap<String, Integer> wordsHashMap = new HashMap<String, Integer>();
		//iterate over string 
		for (int i = 0; i < words.length; i++) {
			if (wordsHashMap.keyset().contains(words[i])) {
				int count = wordsHashMap.get(words[i]);
				wordsHashMap.put(words[i], count + 1);
			}
			else {
				wordsHashMap.put(words[i], 1);
			}
		}

		//iterate over hash map
		ArrayList<String> topFifty = new ArrayList<String>();
		for (int i = 0; i < 50; i++) {
			for(Map.Entry<String, Integer> entry: wordsHashMap.entrySet()) {
				int largestCount = 0;
				String largestWord = "";
				String currentWord = entry.getKey();
				int currentCount = entry.getValue();
				if ((currentCount > largestCount) && (!topFifty.contains(currentWord))) {
					largestCount = currentCount;
					largestWord = currentWord;
				}
				topFifty.add(currentWord);
			}
		}
		for (int i = 0; i < 50; i++) {
			System.out.println(topFifty.get(i));
		}

	}
}