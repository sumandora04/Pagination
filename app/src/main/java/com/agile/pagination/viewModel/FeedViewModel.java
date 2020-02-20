package com.agile.pagination.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.agile.pagination.adapter.FeedDataFactory;
import com.agile.pagination.model.Result;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FeedViewModel extends ViewModel {

    private Executor executor;
    private LiveData<PagedList<Result>> articleLiveData;


    public FeedViewModel() {
        init();
    }

    private void init(){
        executor = Executors.newFixedThreadPool(5);

        FeedDataFactory feedDataFactory = new FeedDataFactory();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20).build();

        articleLiveData = (new LivePagedListBuilder(feedDataFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();
    }

    /*
     * Getter method for the pageList
     */
    public LiveData<PagedList<Result>> getMovieLiveData() {
        return articleLiveData;
    }

}
