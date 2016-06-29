package com.app.njl.subject.mine.http;

import com.app.njl.subject.hotel.model.entity.Fruit;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by chenguang on 16/3/4.
 */
public interface MineClient {

    /*@GET("news/latest")
    Observable<NewsEntity> getLastestNews();

    @GET("news/before/{date}")
    Observable<NewsEntity> getBeforeNews(@Path("date") String date);

    @GET("news/{news_id}")
    Observable<StoryDetailsEntity> getNewsDetails(@Path("news_id") int news_id);

    @GET("start-image/1080*1776")
    Observable<StartImageEntity> getStartImage();*/

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("{path}")
    Observable<String> registerCode(@Path("path") String path, @Query("phoneNumber") String phoneNumber, @Query("expireSeconds") int expireSeconds, @Query("isUserExist")int isUserExist);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("{path}")
    Observable<List<Fruit>> queryShopDetailStay(@Path("path") String path, @Query("pageIndex") String pageIndex);

}
