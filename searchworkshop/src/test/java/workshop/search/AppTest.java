package workshop.search;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


@RunWith(JUnit4.class)
public class AppTest{

    @Mock App mock;

    App app = new App();

   @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testApp()
    {
        assertEquals(1, app.method());
        assertEquals(0, mock.method());
    }
}
