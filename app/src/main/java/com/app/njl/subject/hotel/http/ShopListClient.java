package com.app.njl.subject.hotel.http;

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
public interface ShopListClient {

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
    Observable<List<Fruit>> getAllShops(@Path("path") String path, @Query("pageIndex") String pageIndex);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("{path}")
    Observable<List<Fruit>> queryShopDetailStay(@Path("path") String path, @Query("pageIndex") String pageIndex);

}
