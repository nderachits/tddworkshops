package workshop.searchengine;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/9/13
 */
@RunWith(JUnit4.class)
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
    public void should_return_the_index_of_last_word_with_commas_and_stops() throws Exception {
        SearchIndex index = searchService.parseText("a1, b2 c3.");
        assertEquals(7, index.queryWordOffset("c3"));
    }

    @Test
    public void should_not_fail_when_empty_text() throws Exception {
        SearchIndex index = searchService.parseText("");
        assertEquals(-1, index.queryWordOffset("a"));
    }

}
