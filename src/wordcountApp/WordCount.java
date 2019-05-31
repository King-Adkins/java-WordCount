package wordcountApp;

import java.util.*;
import java.util.Map.Entry;

public class WordCount {
	private String text;
	private HashMap<String, Integer> wordsCount = new HashMap<>();

	WordCount(String text) {
		this.text = text;
		cleanText();
		addWordsToMap();
	}

	private void cleanText() {
		this.text = this.text.toLowerCase().replaceAll("[[\\.\\?\\!\\,\\;\\:\\{\\}\\(\\)\\']]", "");
	}

	private void addWordsToMap() {
		String[] words = this.text.split(" +");
		for (String word : words) {
			wordsCount.compute(word, (k, v) -> v == null ? 1 : v + 1);
		}
	}

	public void printAllWordCount() {
		for (Entry entry : wordsCount.entrySet()) {
			System.out.println("Word: " + entry.getKey() + " | " + "Count" + entry.getValue());
		}
	}

	public void printTop50Words() {
		List<Entry<String, Integer>> tempMap = sortByValue(wordsCount);
		int index = 0;

		for (Entry<String, Integer> entry : tempMap) {
			if (index < 50) {
				System.out.println("Word: " + entry.getKey() + " | " + "Count" + entry.getValue());
			}
			else {
				break;
			}
			index++;
		}
	}
 
	public List<Entry<String, Integer>> sortByValue(HashMap<String, Integer> wordMap) {
		List<Map.Entry<String, Integer>> list = new LinkedList<> (wordMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer> > () {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
		{
			return (o2.getValue()).compareTo(o1.getValue());

		 }
    });

		return list;
	}

	String getCleanInput() {
		return this.text;
	}
}