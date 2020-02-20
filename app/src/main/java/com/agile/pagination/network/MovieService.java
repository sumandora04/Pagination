package com.agile.pagination.network;

import com.agile.pagination.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("top_rated")
    Call<Movie> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") Long pageIndex,
            @Query("pageSize") int pageSize
    );

}
