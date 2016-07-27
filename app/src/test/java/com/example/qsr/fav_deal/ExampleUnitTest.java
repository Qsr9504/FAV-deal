package com.example.qsr.fav_deal;

import com.example.qsr.fav_deal.database.DBDao;
import com.example.qsr.fav_deal.database.DBOption;
import com.example.qsr.fav_deal.globle.App;

import org.junit.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void creatDb() {
        DBDao option = new DBOption(App.mContext);
//        option.insertData("123", "qqq", getCurentTime());
    }

    public String getCurentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }
}
