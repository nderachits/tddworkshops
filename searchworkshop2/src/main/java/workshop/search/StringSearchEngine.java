package workshop.search;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class StringSearchEngine {

	private String text;
	private Hashtable<String, ArrayList<Integer>> indexTable = new Hashtable<String, ArrayList<Integer>>();

	public void setInputText(String text) {
		this.text = text;
		indexText(text);
	}

	public int findWordIndex(String input) {
		
		ArrayList<Integer> result = indexTable.get(input);
		if (result == null) {
			return -1;
		}
		return result.get(0);
	}

	private void indexText(String text) {
		String[] words = text.split("\\W");
		int currentIndex = 0;
		for (String word : words) {
			if (indexTable.get(word) == null){
				indexTable.put(word, new ArrayList<Integer>());
			}
			indexTable.get(word).add(currentIndex);
			currentIndex += word.length()+1;
		}
		
	}
	
	public Integer[] findWordIndexes(String word){
		return indexTable.get(word).toArray(new Integer[]{});
	}
}
