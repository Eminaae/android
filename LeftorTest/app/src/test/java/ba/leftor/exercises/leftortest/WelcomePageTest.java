package ba.leftor.exercises.leftortest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jasminsuljic on 15/01/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class WelcomePageTest {

    @Mock
    WelcomeScreenActivity activity;

    @Test
    public void testWelcomePageText() throws Exception {
//        verify();
        String text = activity.getText();
//        when(activity.getText())
        verify(activity).getText();

        when(activity.getWelcomeText()).thenReturn(activity.getString(R.string.welcome_text));

        verify(activity);


    }
}
