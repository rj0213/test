package com.example.reueljohn.phonenumberspinner2;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SpinnerSelectionTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.reueljohn.phonenumberspinner2", appContext.getPackageName());
    }
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    public void iterateSpinnerItems(){
        String[] myArray = mActivityRule.getActivity().getResources().getStringArray(R.array.labels_array);

        int size = myArray.length;

        for (int i = 0; i<size; i++){
            onView(withId(R.id.label_spinner)).perform(click());
            onData(is(myArray[i])).perform(click());

            onView(withId(R.id.button_main)).perform(click());
            onView(withId(R.id.editText_main)).check(matches(withText(containsString(myArray[i]))));
        }
    }
}
