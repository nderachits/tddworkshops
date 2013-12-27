package workshop.search;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class WordFinderTest {
	
	private static final String VERY_LONG_STRING = "  V string ";
	private static final String LONG_STRING_WITH_DOUBLE_SPACES = "V  string ";
	private static final String SINGLE_SYMBOL_TEST_STRING = "a"; 
	private static final String MULTIPLE_SYMBOL_TEST_STRING = "a b";
	private static final String DUPLICATE_CONTAINED_STRING = "a b b";
	private static final String STRING_WITH_COMMA_SEPARATION = "a,b c";

	@Test
	public void testIndexOfWithContainedWord(){
		WordFinder wordFinder = new WordFinder(SINGLE_SYMBOL_TEST_STRING);
		int returnedIndex = wordFinder.indexOf("a");
		assertEquals(0, returnedIndex);
	}
	
	@Test
	public void testIndexOfNotContainedSymbolCombination() {
		WordFinder wordFinder = new WordFinder(SINGLE_SYMBOL_TEST_STRING);
		assertEquals(-1, wordFinder.indexOf("b"));
	}
	
	@Test
	public void testIndexOfSecondWordInTestString() {
		WordFinder wordFinder = new WordFinder(MULTIPLE_SYMBOL_TEST_STRING);
		assertEquals(2,  wordFinder.indexOf("b"));
	}
	
	@Test
	public void testIndexWhenStringContainsDuplicates() {
		WordFinder wordFinder = new WordFinder(DUPLICATE_CONTAINED_STRING);
		assertEquals(2, wordFinder.indexOf("b"));
	}

	@Test
	public void testIndexWhenStringContainsWordsSeparatedByComma() {
		WordFinder wordFinder = new WordFinder(STRING_WITH_COMMA_SEPARATION);
		assertEquals(2, wordFinder.indexOf("b"));
	}
	
	@Test
	public void shouldFindIndexWhenStringContainsFewWords(){
		WordFinder wordFinder = new WordFinder(VERY_LONG_STRING);
		assertEquals(4, wordFinder.indexOf("string"));
	}
	
	@Test
	public void shouldFindIndexWhenStringContainsFewSpaces(){
		WordFinder wordFinder = new WordFinder(LONG_STRING_WITH_DOUBLE_SPACES);
		assertEquals(3, wordFinder.indexOf("string"));
	}
	
	@Test
	public void shouldAllowCaseIncensitiveComparation() {
		WordFinder wordFinder = new WordFinder(VERY_LONG_STRING);
		assertEquals(4, wordFinder.indexOf("sTrInG"));
	}

}
