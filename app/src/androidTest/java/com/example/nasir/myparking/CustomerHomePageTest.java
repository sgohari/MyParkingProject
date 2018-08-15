package com.example.nasir.myparking;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

public class CustomerHomePageTest {

    @Rule
    public ActivityTestRule<CustomerHomePage>mActivityTestRule= new ActivityTestRule<CustomerHomePage>(CustomerHomePage.class);

    private CustomerHomePage mActivity = null;
    Instrumentation.ActivityMonitor monitor= getInstrumentation().addMonitor(ParkingAroundActivity.class.getName(),null,false);
    @Before
    public void setUp () throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }

    @Test
    public void testIntentonButtonClick(){
        assertNotNull(mActivity.findViewById(R.id.btnAroundMe));
        onView(withId(R.id.btnAroundMe)).perform(click());
        Activity nextActivity=getInstrumentation().waitForMonitorWithTimeout(monitor,500);

        assertNotNull(nextActivity);
        nextActivity.finish();
    }
    @After
    public void tearDown () throws Exception {

        mActivity=null;
    }
}