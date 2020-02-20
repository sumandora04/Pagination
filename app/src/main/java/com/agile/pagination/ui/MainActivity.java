package com.agile.pagination.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.agile.pagination.R;
import com.agile.pagination.adapter.MovieAdapter;
import com.agile.pagination.databinding.ActivityMainBinding;
import com.agile.pagination.model.Result;
import com.agile.pagination.viewModel.FeedViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FeedViewModel feedViewModel;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
       // feedViewModel = binding.getViewModel();

        final MovieAdapter adapter = new MovieAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));


        feedViewModel.getMovieLiveData().observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                adapter.submitList(results);
            }
        });

        binding.recyclerView.setAdapter(adapter);

    }
}
