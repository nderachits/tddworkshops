package workshop.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Index {

//	private String text;
	
	private Map<String, List<Integer>> indexedText = new HashMap<String, List<Integer>>();

	public List<Integer> query(String searchTerm) {
		if (searchTerm == null) {
		throw new IllegalArgumentException("cannot find null");
		}
		
		return indexedText.get(searchTerm);
	}

	public void addWord(String word, Integer wordOffset) {
		List<Integer> offsets = indexedText.get(word);
		if(offsets== null) {
			offsets = new ArrayList<Integer>();
			indexedText.put(word, offsets);
		}
		
		offsets.add(wordOffset);
	}



}
