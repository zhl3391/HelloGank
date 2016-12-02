package com.zhl.hellogank;

import com.zhl.hellogank.business.model.bean.DayData;
import com.zhl.hellogank.net.RequestApi;
import com.zhl.hellogank.net.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;

/**
 * Created by zhouhl on 2016/11/7.
 * ApiTest api测试
 */

public class ApiTest {

    private RequestApi mRequestApi;

    @Before
    public void setUp() throws Exception {
        RxUnitTestTools.openRxTools();
        mRequestApi = MockRetrofitHelper.create(RequestApi.class);
    }

    @Test
    public void testGetDayData() throws Exception {
        TestSubscriber<Response<DayData>> testSubscriber = new TestSubscriber<>();

        mRequestApi.getDayData("2016/11/07").toBlocking().subscribe(testSubscriber);

        Response<DayData> response = testSubscriber.getOnNextEvents().get(0);

        Assert.assertEquals(response.error, false);
        Assert.assertNotNull(response.results);
    }
}
