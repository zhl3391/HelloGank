package com.zhl.hellogank.net;

import com.zhl.hellogank.business.model.bean.Article;
import com.zhl.hellogank.business.model.bean.DayData;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zhouhl on 2016/11/1.
 * 请求接口
 */

public interface RequestApi {

    String BASE_URL = "http://gank.io/api/";

    @GET("day/{date}")
    Observable<Response<DayData>> getDayData(@Path("date") String date);

    @GET("data/{type}/{count}/{page}")
    Observable<Response<List<Article>>> getArticleList(@Path("type") String type,
                                                       @Path("count") int count,
                                                       @Path("page") int page);
}
