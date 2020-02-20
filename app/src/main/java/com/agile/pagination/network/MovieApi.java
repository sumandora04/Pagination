package com.agile.pagination.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApi {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "b32db8d250369fbe041a2200f0072027";

    public static MovieService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(MovieService.class);
    }
}
