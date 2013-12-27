package workshop.search;

import java.util.HashMap;
import java.util.Map;

public class WordFinder {
	
	private static final String WORD_SLITTER = "\\W"; 
	
	private Map<String, Integer> wordsIndexes;

	public WordFinder(String testString) {
		wordsIndexes = new HashMap<String, Integer>();
		
		String[] words = testString.split(WORD_SLITTER);
		int currentIndex = 0;
		for (String word : words) {
			if (!wordsIndexes.containsKey(word.toUpperCase())){
				wordsIndexes.put(word.toUpperCase(), currentIndex);	
			}
			currentIndex += word.length() + 1;
		}
	}

	public int indexOf(String string) {
		Integer index = wordsIndexes.get(string.toUpperCase());
		return index == null ? -1 : index;
	}

}
