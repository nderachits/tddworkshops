package workshop.exchange;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SampleTest {
	
	private Sample sample;
	
	@Before
	public void setUp() {
		sample = new Sample();
	}

	@Test
	public void testSimple() {
		assertTrue(sample.doNothing());
	}
	

}
