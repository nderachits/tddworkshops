package workshop.searchengine.app;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;

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
        assertEquals(1, app.mone());
        assertEquals(0, mock.mone());
    }
}
