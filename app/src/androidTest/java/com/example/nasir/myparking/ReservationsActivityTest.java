package com.example.nasir.myparking;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class ReservationsActivityTest {




    @Rule
    public ActivityTestRule<ReservationsActivity> mReservationRules =new ActivityTestRule<ReservationsActivity>(ReservationsActivity.class);

    private ReservationsActivity mReservation =null;
    @Before
    public void setUp () throws Exception {
        mReservation=mReservationRules.getActivity();

    }

    @Test
    public void testLaunch(){

        View view =mReservation.findViewById(R.id.parkingNameET);
        view =mReservation.findViewById(R.id.parkingLotAddressET);
        view =mReservation.findViewById(R.id.btnConfirmRV);
        view =mReservation.findViewById(R.id.toET);
        view =mReservation.findViewById(R.id.fromET);
        view =mReservation.findViewById(R.id.cardNumberET);


        assertNotNull(view);
    }

    @After
    public void tearDown () throws Exception {
        mReservation=null;
    }
}