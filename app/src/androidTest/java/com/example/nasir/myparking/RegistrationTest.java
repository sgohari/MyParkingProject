package com.example.nasir.myparking;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationTest {



    @Rule
    public ActivityTestRule<Registration> mActivityRegistration =new ActivityTestRule<Registration>(Registration.class);

    private Registration mRegistratinActivity =null;
    @Before
    public void setUp () throws Exception {
        mRegistratinActivity=mActivityRegistration.getActivity();

    }

    @Test
    public void testLaunch(){

        View view =mRegistratinActivity.findViewById(R.id.custNameAdminET);
        view=mRegistratinActivity.findViewById(R.id.editUserName);
        view=mRegistratinActivity.findViewById(R.id.editPassword);
        view=mRegistratinActivity.findViewById(R.id.editAddress);
        view=mRegistratinActivity.findViewById(R.id.editCity);
        view=mRegistratinActivity.findViewById(R.id.editFName);
        view=mRegistratinActivity.findViewById(R.id.editLName);
        view=mRegistratinActivity.findViewById(R.id.editPostCode);
        assertNotNull(view);
    }

    @After
    public void tearDown () throws Exception {
        mRegistratinActivity=null;
    }
}