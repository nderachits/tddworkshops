package workshop.searchengine;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/9/13
 */
@RunWith(JUnit4.class)
public class SearchIndexTest {

    private final String TEXT = "a b c!";

    private SearchIndex index = new SearchIndex();

    @Before
    public void setUp() throws Exception {
    }
    @Test
    public void should_store_index_data() throws Exception {

        int inputOffset = 2;
        String inputWord = "b";

        index.addPair(inputWord, inputOffset);

        assertEquals("text offset at result should the same as input data",
                inputOffset, index.queryWordOffset(inputWord));
    }

    @Test
    public void should_return_minus_one_when_word_not_found() throws Exception {

        assertEquals(-1, index.queryWordOffset("z"));
    }

    @Test
    public void should_store_many_words_in_index() throws Exception {
        int firstOccurance = 2;
        int secondOccurance = 5;
        String word = "b";

        index.addPair(word, firstOccurance);
        index.addPair(word, secondOccurance);
        Integer[] offsets = index.queryWordOffsets(word);
        assertEquals("should be 2 occurances", 2, offsets.length );
        assertArrayEquals("occurances should match", new Integer[]{firstOccurance, secondOccurance}, offsets);
    }
}
