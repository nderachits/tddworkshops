package workshop.searchengine;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/9/13
 */
public class SearchServiceTest {

    private SearchService searchService;

    @Before
    public void setUp() throws Exception {
        searchService = new SearchService();
    }

    @Test
    public void should_return_index_by_text() throws Exception {
        SearchIndex index = searchService.parseText("a b c!");
        assertNotNull("returned index should be not null SearchIndex instance", index);
    }

    @Test
    public void should_return_zero_offset_when_text_equals_query_word() throws Exception {
        SearchIndex index = searchService.parseText("a");
        assertEquals(0, index.queryWordOffset("a"));
    }

    @Test
    public void should_return_first_non_whitespace_word_surrunded_by_spaces() throws Exception {
        SearchIndex index = searchService.parseText(" a ");
        assertEquals(1, index.queryWordOffset("a"));
    }

    @Test
    public void should_return_the_index_of_second_word() throws Exception {
        SearchIndex index = searchService.parseText(" a b ");
        assertEquals(3, index.queryWordOffset("b"));
    }

    @Test
    public void should_count_underscore_as_letter() throws Exception {
        SearchIndex index = searchService.parseText(" a_1 b_2 ");
        assertEquals(5, index.queryWordOffset("b_2"));
    }

    @Test
    public void should_return_the_index_of_last_word_with_commas_and_stops() throws Exception {
        SearchIndex index = searchService.parseText("a1, b2 c3.");
        assertEquals(7, index.queryWordOffset("c3"));
    }

    @Test
    public void should_not_fail_when_empty_text() throws Exception {
        SearchIndex index = searchService.parseText("");
        assertEquals(-1, index.queryWordOffset("a"));
    }

    @Test
    public void shouldFindSimilarWord() throws Exception {
        SearchIndex index = searchService.parseText("abc");
        List<String> foundWords = index.querySimilarWordOffset("ab");
        assertEquals(1, foundWords.size());
        assertTrue(foundWords.contains("abc"));
    }

    @Test
    public void shouldFindTwoSimilarWords() throws Exception {
        SearchIndex index = searchService.parseText("abcde abcxxe abxe xabde abc");
        List<String> foundWords = index.querySimilarWordOffset("abde");
        String[] expectedWords = new String[]{"abcde", "xabde", "abxe"};
        assertEquals(expectedWords.length, foundWords.size());
        assertTrue(foundWords.contains(expectedWords[0]));
        assertTrue(foundWords.contains(expectedWords[1]));
        assertTrue(foundWords.contains(expectedWords[2]));
    }

    @Test
    public void shouldNotFindWordWith2fifferentChars() throws Exception {
        SearchIndex index = searchService.parseText("abc");
        List<String> foundWords = index.querySimilarWordOffset("a");
        assertEquals(0, foundWords.size());
    }
}
