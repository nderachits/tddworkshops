package workshop.search;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class IndexTest {

	private static final Integer SEARCH_TERM1_FIRST_INDEX = 0;
	private static final Integer SEARCH_TERM2_FIRST_INDEX = 6;
	private static final Integer SEARCH_TERM1_SECOND_INDEX = 12;
	private static final String SEARCH_TERM1 = "word1";
	private static final String SEARCH_TERM2 = "word2";
	private Index index;

	@Before
	public void setUp() {
		
		index = new Index();
		index.addWord(SEARCH_TERM1, SEARCH_TERM1_FIRST_INDEX);
		index.addWord(SEARCH_TERM2, SEARCH_TERM2_FIRST_INDEX);
		index.addWord(SEARCH_TERM1, SEARCH_TERM1_SECOND_INDEX);
	}
	
	@Test
	public void shouldFindAWordInText() {
		List<Integer> indexes = index.query(SEARCH_TERM2);

		assertEquals("Index not found", SEARCH_TERM2_FIRST_INDEX, indexes.get(0));
	}
	
	@Test
	public void shouldFind2Words(){
		List<Integer> indexes = index.query(SEARCH_TERM1);
		
		assertEquals("Index is not correct", SEARCH_TERM1_FIRST_INDEX, indexes.get(0));
		assertEquals("Index is not correct", SEARCH_TERM1_SECOND_INDEX, indexes.get(1));
	}

	@Test
	public void shouldThrowIllegalArgumentException() {
		try {
			index.query(null);
			fail("should fail on null search term");
		} catch (IllegalArgumentException e) {
			assertEquals("not correct exception was thrown", "cannot find null", e.getMessage());
		}
	}
	
	


	

}
