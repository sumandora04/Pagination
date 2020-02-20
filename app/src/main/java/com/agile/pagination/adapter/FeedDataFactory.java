package com.agile.pagination.adapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.agile.pagination.adapter.Feedback;

public class FeedDataFactory extends DataSource.Factory {


    private MutableLiveData<Feedback> mutableLiveData;
    private Feedback feedDataSource;

    public FeedDataFactory() {
        this.mutableLiveData = new MutableLiveData<Feedback>();
    }

    @NonNull
    @Override
    public DataSource create() {
        feedDataSource = new Feedback();
        mutableLiveData.postValue(feedDataSource);
        return feedDataSource;
    }

    public MutableLiveData<Feedback> getMutableLiveData() {
        return mutableLiveData;
    }
}
