package com.agile.pagination.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.agile.pagination.model.Movie;
import com.agile.pagination.model.Result;
import com.agile.pagination.network.MovieApi;
import com.agile.pagination.network.MovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Feedback extends PageKeyedDataSource<Long, Result> {

    private static final String TAG = "Feedback";

    /*
     * Step 1: Initialize the restApiFactory.
     * The networkState and initialLoading variables
     * are for updating the UI when data is being fetched
     * by displaying a progress bar
     */

    private MovieService restApiFactory;

    public Feedback() {
        this.restApiFactory = MovieApi.create();
    }


    /*
     * Step 2: This method is responsible to load the data initially
     * when app screen is launched for the first time.
     * We are fetching the first page data from the api
     * and passing it via the callback method to the UI.
     */

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Result> callback) {
        Call<Movie> call = restApiFactory.getTopRatedMovies(MovieApi.API_KEY,"en",1L,params.requestedLoadSize);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()){
                    callback.onResult(response.body().getResults(),null,2L);
                }else {
                    Log.d(TAG, "onResponse: Failed");
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d(TAG, "onFailure: Network error.");
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Result> callback) {

    }

    /*
     * Step 3: This method it is responsible for the subsequent call to load the data page wise.
     * This method is executed in the background thread
     * We are fetching the next page data from the api
     * and passing it via the callback method to the UI.
     * The "params.key" variable will have the updated value.
     */

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Result> callback) {
        restApiFactory.getTopRatedMovies(MovieApi.API_KEY,"en",params.key,params.requestedLoadSize)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if(response.isSuccessful()) {
                            long nextKey = (params.key == Long.valueOf(response.body().getTotalResults())) ? null : params.key+1;
                            callback.onResult(response.body().getResults(), nextKey);
                        }else {
                            Log.d(TAG, "onResponse: Failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        Log.d(TAG, "onFailure: Network error");
                    }
                });
    }
}
