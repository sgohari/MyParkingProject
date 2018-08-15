package com.example.nasir.myparking.Database;

import org.junit.After;
import org.junit.Before;

import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.*;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import android.provider.Telephony;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.RenamingDelegatingContext;
import android.test.suitebuilder.annotation.LargeTest;

public class DataSourceTest {

    private DataSource db;

    @Before
    public void setUp () throws Exception {

        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new DataSource(context);
    }

    @After
    public void tearDown () throws Exception {
        db.close();
    }
}