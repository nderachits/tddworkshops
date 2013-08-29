package workshop.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexService {

	public Map<String, List<Integer>> parse(String indexedString) {
		
		HashMap<String, List<Integer>> indexes = new HashMap<>();
		String[] words = indexedString.split(" ");
		int currentIndex = 0;
		for(String word : words) {
			List<Integer> listOfIndexes = new ArrayList<Integer>();
			int index = indexedString.indexOf(word, currentIndex);
			listOfIndexes.add(index);
			indexes.put(word, listOfIndexes);
			currentIndex += word.length();
		}
		
		return indexes;
	}

}
