package workshop.searchengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * User: Mikalai_Dzerachyts
 * Date: 8/9/13
*/
public class SearchIndex {

    private final Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();

    public SearchIndex() {
    }

    public int queryWordOffset(String word) {
        Integer[] result = queryWordOffsets(word);
        if(result.length == 0) {
            return -1;
        }
        return result[0];
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
        if(offsets == null) {
            return emptyResult();
        }
        return offsets.toArray(new Integer[0]);
    }

    private Integer[] emptyResult() {
        return new Integer[0];
    }
}
