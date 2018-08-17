package com.example.nasir.myparking;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminHomepageTest {


    @Rule
    public ActivityTestRule<AdminHomepage> mActivityTest =new ActivityTestRule<AdminHomepage>(AdminHomepage.class);

    private AdminHomepage mAdminActivity =null;

    @Before
    public void setUp () throws Exception {

        mAdminActivity=mActivityTest.getActivity();

    }

    @Test
    public void testLaunch(){

        View view =mAdminActivity.findViewById(R.id.custNameAdminET);
        view=mAdminActivity.findViewById(R.id.btnDelete);
        view=mAdminActivity.findViewById(R.id.btnSearchAdmin);
        view=mAdminActivity.findViewById(R.id.btnAdd);
        view=mAdminActivity.findViewById(R.id.btnViewAll);

        assertNotNull(view);
    }
    @After
    public void tearDown () throws Exception {
        mAdminActivity=null;
    }
}