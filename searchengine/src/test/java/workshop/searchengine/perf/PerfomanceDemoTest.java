package workshop.searchengine.perf;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.assertEquals;
import workshop.searchengine.SearchIndex;
import workshop.searchengine.SearchService;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * User: Mikalai_Dzerachyts
 * Date: 8/13/13
 */
@RunWith(JUnit4.class)
public class PerfomanceDemoTest {

    SearchService searchService;
    @Before
    public void setUp() throws Exception {

        searchService = new SearchService();
    }

    @Test
    public void should_parse_twbs_string() throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader("data/twbs.txt"));
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

        checkWordInIndex(text, index, "optional");
        checkWordInIndex(text, index, "integral");

        float dt = (endTime - startTime) / 1000f;
        System.out.println("twbs parsing time: "+dt);
    }

    private void checkWordInIndex(String text, SearchIndex index, String word) {
        Integer[] res = index.queryWordOffsets(word);
        for (int i = 0; i < res.length; i++) {
            Integer re = res[i];
            assertEquals(word, text.substring(re, re + word.length()));
        }
    }

    private void comparePerfomance(String test, SearchIndex index, String word) {
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
        System.out.println("perf test(repeated "+repeatCount+"): " +
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

        comparePerfomance(text, index, "ISINDEX");
    }

}
