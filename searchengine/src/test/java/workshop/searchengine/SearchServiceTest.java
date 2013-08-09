package workshop.searchengine;

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

    private final String TEXT = "a b c!";

    @Test
    public void should_return_index_by_text() throws Exception {

        SearchService searchService = new SearchService();
        SearchIndex searchIndex = searchService.parseText(TEXT);
        assertNotNull("returned index should be not null SearchIndex instance", searchIndex);
    }
}
