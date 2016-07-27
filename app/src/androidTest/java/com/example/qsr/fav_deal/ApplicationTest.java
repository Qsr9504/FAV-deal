package com.example.qsr.fav_deal;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.example.qsr.fav_deal.database.DBDao;
import com.example.qsr.fav_deal.database.DBOption;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private String curentTime;

    public ApplicationTest() {
        super(Application.class);
    }

}