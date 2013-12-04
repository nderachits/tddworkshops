package workshop.searchengine;

import java.util.*;

/**
  * User: Mikalai_Dzerachyts
 * Date: 8/9/13
*/
public class SearchIndex {

    public static final int WORD_NOT_FOUND = -1;
    private final Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();

    public SearchIndex() {
    }

    public int queryWordOffset(String word) {
        Integer[] result = queryWordOffsets(word);
        return getFirst(result);
    }

    public void addPair(String inputWord, int offset) {
        List<Integer> offsets = map.get(inputWord);
        if(offsets == null) {
            offsets = emptyOffsetList();
            map.put(inputWord, offsets);
        }
        offsets.add(offset);
    }

    private List<Integer> emptyOffsetList() {
        return new ArrayList<Integer>();
    }

    public Integer[] queryWordOffsets(String word) {
        List<Integer> offsets = map.get(word);
        return convertToArray(offsets);
    }

    private Integer[] emptyResult() {
        return new Integer[0];
    }

    public String[] querySimilarWordOffset(String searchWord) {
        List<String> words = new ArrayList<String>();
        for (Iterator<Map.Entry<String, List<Integer>>>
                iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, List<Integer>> entry = iterator.next();
            String word = entry.getKey();
            if(wordsAreSimilar(searchWord, word)) {
                words.add(word);
            }
        }
        return words.toArray(new String[0]);
    }

    private boolean wordsAreSimilar(String searchWord, String word) {
        return new WordDiffCalculator(searchWord, word).calculate() <= 1;
    }

    private Integer getFirst(Integer[] value) {
        if(value.length == 0) {
            return WORD_NOT_FOUND;
        }
        return value[0];
    }

    private Integer[] convertToArray(List<Integer> offsets) {
        if(offsets == null) {
            return emptyResult();
        }
        return offsets.toArray(new Integer[0]);
    }

}
