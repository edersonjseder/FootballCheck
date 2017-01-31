package com.soccer.soccercheck.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.soccer.soccercheck.model.FixtureDetail;
import com.soccer.soccercheck.model.FixturesData;
import com.soccer.soccercheck.path.FBPath;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ederson.js on 19/11/2016.
 */
public interface FixturesDataService {

    @GET(FBPath.FIXTURES)
    Observable<FixturesData> getFixtures();

    @GET(FBPath.FIXTURES + "/{number}")
    Observable<FixtureDetail> getFixtureDetails(@Path("number") int number);

    @GET(FBPath.COMPETITIONS + "/{id}/" + FBPath.FIXTURES)
    Call<FixturesData> fetchFixturesById(@Path("id") int id);

    @GET(FBPath.COMPETITIONS + "/{id}/" + FBPath.FIXTURES)
    Call<FixturesData> retrieveFixturesById(@Path("id") int id, @Query("matchday") int matchDay);

    class Factory {

        public static FixturesDataService create() {

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JodaModule());

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .header("X-Auth-Token", "b7076d8e1bf64d9ebbb9bfab768c4bb6")
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            Retrofit retrofit = new Retrofit.Builder().baseUrl(FBPath.URL_PATH)
                    .addConverterFactory(JacksonConverterFactory.create(mapper))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).client(httpClient.build())
                    .build();

            return retrofit.create(FixturesDataService.class);
        }
    }
}
