package com.agile.pagination.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.agile.pagination.R;
import com.agile.pagination.model.Result;

public class MovieAdapter extends PagedListAdapter<Result, RecyclerView.ViewHolder> {
    /*
     * There are two layout types we define
     * in this adapter:
     * 1. progrss view
     * 2. data view
     */
    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;

    private Context context;

    public MovieAdapter(Context context) {
        super(Result.DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent, false);
        return new MovieViewHolder(view);
//        switch (viewType) {
//            case TYPE_ITEM:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent, false);
//                return new MovieViewHolder(view);
//
//            case TYPE_PROGRESS:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_cell, parent, false);
//                return new ProgressHolder(view);
//
//            default:
//                return null;
//        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        if (holder instanceof MovieViewHolder){
//            ((MovieViewHolder) holder).bindMovie(getItem(position));
//        }else {
//
//        }
        ((MovieViewHolder)holder).bindMovie(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

       // ImageView poster;
        TextView movieName;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
         //   poster = itemView.findViewById(R.id.poster);
            movieName = itemView.findViewById(R.id.movie_name);
        }

        public void bindMovie(Result result){
            movieName.setText(result.getOriginalTitle());
        }
    }

    class ProgressHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;
        public ProgressHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress);
        }

        void bindProgress(){

        }
    }
}
