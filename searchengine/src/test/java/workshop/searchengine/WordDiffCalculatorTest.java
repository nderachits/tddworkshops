package workshop.searchengine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: nike
 * Date: 12/2/13
 */
public class WordDiffCalculatorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldReturnZeroWhenWordsAreEqual() throws Exception {
        assertEquals(0, new WordDiffCalculator("a","a").calculate());

    }

    @Test
    public void shouldReturnOneDiff() throws Exception {
        assertEquals(1, new WordDiffCalculator("b","ab").calculate());
    }

    @Test
    public void shouldReturnTwoDiff() throws Exception {
        assertEquals(2, new WordDiffCalculator("a","bc").calculate());
    }

    @Test
    public void shouldReturnOneDiffInTheMiddle() throws Exception {
        assertEquals(2, new WordDiffCalculator("1x1b1", "1b1").calculate());
    }

    @Test
    public void shouldReturnTwoDiffInTwoPlaces() throws Exception {
        assertEquals(2, new WordDiffCalculator("a1bc2d", "abcd").calculate());
    }

    @Test
    public void shouldReturn3WhenLengthDiffersBy3AndTargetLengthIs1() throws Exception {
        assertEquals(3, new WordDiffCalculator("bcbc","c").calculate());
    }

    @Test
    public void shouldReturn3WhenLengthDiffersBy3AndSourceLengthIs1() throws Exception {
        assertEquals(3, new WordDiffCalculator("a","aaaa").calculate());
    }
}
