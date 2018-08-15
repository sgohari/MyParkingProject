package com.example.nasir.myparking;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {

    @Rule
    public ActivityTestRule<Login>mActivityTestRules =new ActivityTestRule<Login>(Login.class);

    private Login mActivity =null;

    @Before
    public void setUp () throws Exception {

        mActivity=mActivityTestRules.getActivity();

    }

    @Test
    public void testLaunch(){

        View view =mActivity.findViewById(R.id.userNameET);
        view=mActivity.findViewById(R.id.passwordET);
        view=mActivity.findViewById(R.id.btnLogin);
        view=mActivity.findViewById(R.id.btnRegister);
        assertNotNull(view);
    }
    @After
    public void tearDown () throws Exception {
        mActivity=null;
    }
}