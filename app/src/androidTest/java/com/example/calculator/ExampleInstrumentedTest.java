package com.example.calculator;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.util.Log;
import com.squareup.spoon.Spoon;
import com.jraska.falcon.FalconSpoonRule;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import androidx.test.platform.app.InstrumentationRegistry;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.*;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

public class ExampleInstrumentedTest {
    //public FalconSpoonRule mFalconRule;

    @Rule
    public ActivityScenarioRule<MainActivity> activityActivityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    /*
   @Rule
   public FalconSpoonRule mFalconRule = new FalconSpoonRule();

    @Before
    public void Setup(){ mFalconRule = new FalconSpoonRule();}
    */

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.calculator", appContext.getPackageName());
    }


    @Test
    public void calculations() {
        doCalculation("12345+7890","20235.0");
        doCalculation("-0.5","20234.5");
        doCalculation("x2","40469.0");
        doCalculation("/4","10117.25");
    }

    @Test
    public void fail() {
        doCalculation("1+1","1.0");
    }

    @Test
    public void testScreenShot() {
        doCalculation("1+1","2.0");
        Spoon.screenshot(TestUtils.getCurrentActivity(), "testSuccess");
    }

    private void doCalculation(String padsToClick, String result){
        clickPads(padsToClick);
        onView(withId(R.id.buttonEqual)).perform(click());
        onView(withId(R.id.result)).check(matches(withText(result)));
    }

    private void clickPads(String padsToClick){
        for (int i = 0; i < padsToClick.length(); i++){
            String c = Character.toString(padsToClick.charAt(i));
            onView(withText(c)).perform(click());
        }
    }
}
