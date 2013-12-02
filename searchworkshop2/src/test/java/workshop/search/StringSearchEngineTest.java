package workshop.search;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringSearchEngineTest {
	
	private StringSearchEngine searchEngine;
	
	
	@Before
	public void setUp(){
		searchEngine = new StringSearchEngine();
	}
	
	
	@Test
	public void shouldReturnZeroForOneWordInput(){
		searchEngine.setInputText("hello");
		assertEquals(0,searchEngine.findWordIndex("hello"));		
	}
	
	@Test
	public void shouldReturnNegativeValueForOneWordInput(){
		searchEngine.setInputText("worldhelloworld");
		assertEquals(-1, searchEngine.findWordIndex("hello"));
	}
	
	@Test
	
	public void shouldFindWordInsteadOfSubstring(){
		searchEngine.setInputText("whellow hello");
		assertEquals(8, searchEngine.findWordIndex("hello"));
	}
	
	@Test
	public void shouldFindWordSeparatedByManySpaces(){
		searchEngine.setInputText(" whellow   hello");
		assertEquals(11, searchEngine.findWordIndex("hello"));
	}
	
	@Test
	public void shouldReturnFirstIndexOfWordEntrance() {
		searchEngine.setInputText("hello hello");
		assertEquals(0, searchEngine.findWordIndex("hello"));
	}
	
	@Test
	public void shouldReturnIndexOfWordWithComa() {
		searchEngine.setInputText("$%^&*()/!@#?.~'\"{}[]hello, hello");
		assertEquals(20, searchEngine.findWordIndex("hello"));
	}
	
	@Test
	public void shouldReturnArrayIndexOfWordIncoming() {
		searchEngine.setInputText("hello, hello");
		Integer[] indexes={0,7};
		assertArrayEquals(indexes, searchEngine.findWordIndexes("hello"));
	}
}
