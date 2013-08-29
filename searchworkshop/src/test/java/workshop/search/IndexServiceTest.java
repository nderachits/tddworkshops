package workshop.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class IndexServiceTest {
	private static final String INDEXED_STRING = "word1 word2";
	private IndexService indexService = new IndexService();
	
	
	
	@Test
	public void shouldCreateIndex() {
		Map<String, List<Integer>> index = indexService.parse(INDEXED_STRING);

		assertNotNull(index);
	}

	@Test
	public void shouldBuildIndexWithTestWords() {
		Map<String, List<Integer>> index = indexService.parse(INDEXED_STRING);
		
		assertEquals("not correct index size", 2, index.size());
		
		List<Integer> word1 = index.get("word1");
		List<Integer> word2 = index.get("word2");
		
		assertNotNull("index doesn't contain word 'word1'", word1);
		assertNotNull("index doesn't contain word 'word2'", word2);
		
		assertEquals("Incorrect word1 indexes size",1, word1.size());
		assertEquals("Incorrect word2 indexes size",1, word2.size());
		
		assertEquals("Incorrect index for 'word1'", (Integer)0, word1.get(0));
		assertEquals("Incorrect index for 'word2'", (Integer)6, word2.get(0));
		
	}
}
