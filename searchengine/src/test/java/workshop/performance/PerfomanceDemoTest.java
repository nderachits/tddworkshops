package workshop.performance;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import workshop.searchengine.SearchIndex;
import workshop.searchengine.SearchService;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/13/13
 */
public class PerfomanceDemoTest {

    SearchService searchService;
    @Before
    public void setUp() throws Exception {

        searchService = new SearchService();
    }

    private void checkWordInIndex(String text, SearchIndex index, String word) {
        Integer[] res = index.queryWordOffsets(word);
        for (Integer re : res) {
            assertEquals(word, text.substring(re, re + word.length()));
        }
    }

    private void comparePerformance(String test, SearchIndex index, String word) {
        int brootSearchOffset=0;
        int indexSearchOffset=-1;
        int repeatCount = 200;
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < repeatCount; i++) {
            brootSearchOffset = test.indexOf(word);
        }
        float deltaTimeBroot = (System.currentTimeMillis() - startTime) / 1000f;

        long startTimeIndex = System.currentTimeMillis();
        for(int i = 0; i < repeatCount; i++) {
            indexSearchOffset = index.queryWordOffset(word);
        }
        float deltaTimeIndex = (System.currentTimeMillis() - startTimeIndex) / 1000f;

        assertEquals(brootSearchOffset, indexSearchOffset);
        System.out.println("perfomance test(repeated "+repeatCount+"): " +
                "broot: "+deltaTimeBroot+", index: "+deltaTimeIndex);
    }
    @Test
    public void should_parse_rails_string() throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader("data/rails.txt"));
        StringBuilder stringBuilder = new StringBuilder();

        char[] chars = new char[256];
        int n;
        while((n = reader.read(chars)) != -1) {
            stringBuilder.append(chars, 0, n);
        }

        String text = stringBuilder.toString();
        long startTime = System.currentTimeMillis();
        SearchIndex index = searchService.parseText(text);

        long endTime = System.currentTimeMillis();

        checkWordInIndex(text, index, "validation");
        checkWordInIndex(text, index, "ISINDEX");

        float dt = (endTime - startTime) / 1000f;
        System.out.println("rails parsing time: "+dt);

        comparePerformance(text, index, "ISINDEX");

        System.out.println("assert_equal word appeared in rails "+ index.queryWordOffsets("assert_equal").length +" times");
    }

}
