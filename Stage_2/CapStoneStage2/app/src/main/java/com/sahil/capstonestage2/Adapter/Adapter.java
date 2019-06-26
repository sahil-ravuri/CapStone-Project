package com.sahil.capstonestage2.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.sahil.capstonestage2.MainActivity;
import com.sahil.capstonestage2.NewsDetailActivity;
import com.sahil.capstonestage2.R;
import com.sahil.capstonestage2.Utilities.Utils;
import com.sahil.capstonestage2.models.Article;
import com.sahil.capstonestage2.models.DataBaseModel;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    private  List<Article> articles=null;
    private  List<DataBaseModel> dataBaseModels=null;
    private  Context context=null;
    int i=0;
    //private OnItemClickListener onItemClickListener;


    public Adapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;

    }
    public Adapter(Context context,List<DataBaseModel> dataBaseModels,int i)
    {
        this.context=context;
        this.dataBaseModels=dataBaseModels;
        this.i=i;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);//, onItemClickListener
    }

    @SuppressLint({"CheckResult", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();
        if(i==1)
        {
            DataBaseModel models=null;
            if(dataBaseModels!=null) {
                models = dataBaseModels.get(position);
                Glide.with(context).load(models.getmImg()).apply(requestOptions)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                holder.progressBar.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                holder.progressBar.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(holder.imageView);
                holder.title.setText(models.getmTitle());
                holder.desc.setText(models.getmTitle());
                holder.source.setText(models.getmSource());
                holder.time.setText(" \u2022 " + Utils.DateToTimeFormat(models.getmDate()));
                holder.published_ad.setText(Utils.DateFormat(models.getmDate()));
                holder.author.setText(models.getmAuthor());
            }
        }
        else {
            Article model=null;
            if(articles!=null) {
                model = articles.get(position);
                Glide.with(context).load(model.getUrlToImage()).apply(requestOptions)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                holder.progressBar.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                holder.progressBar.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(holder.imageView);

                holder.title.setText(model.getTitle());
                holder.desc.setText(model.getDescription());
                holder.source.setText(model.getSource().getName());
                holder.time.setText(" \u2022 " + Utils.DateToTimeFormat(model.getPublishedAt()));
                holder.published_ad.setText(Utils.DateFormat(model.getPublishedAt()));
                holder.author.setText(model.getAuthor());
            }

        }

    }

    @Override
    public int getItemCount() {
        if(i==1)
        {
            if(dataBaseModels!=null)
                return  dataBaseModels.size();
            else
                return 0;
        }else if(articles!=null) {
            return articles.size();
        }
        else
            return 0;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder  {
        final TextView title;
        final TextView desc;
        final TextView author;
        final TextView published_ad;
        final TextView source;
        final TextView time;
        final ImageView imageView;
        final ProgressBar progressBar;

        private MyViewHolder(View itemView) {

            super(itemView);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            author = itemView.findViewById(R.id.author);
            published_ad = itemView.findViewById(R.id.publishedAt);
            source = itemView.findViewById(R.id.source);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.img);
            progressBar = itemView.findViewById(R.id.prograss_load_photo);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    if(i!=1) {
                        intent = new Intent(context, NewsDetailActivity.class);
                        Article article = articles.get(getAdapterPosition());
                        intent.putExtra("url", article.getUrl());
                        intent.putExtra("title", article.getTitle());
                        intent.putExtra("img", article.getUrlToImage());
                        intent.putExtra("date", article.getPublishedAt());
                        intent.putExtra("source", article.getSource().getName());
                        intent.putExtra("author", article.getAuthor());
                    }else{
                        intent = new Intent(context, NewsDetailActivity.class);
                        DataBaseModel modelt = dataBaseModels.get(getAdapterPosition());
                        intent.putExtra("url", modelt .getmUrl());
                        intent.putExtra("title", modelt .getmTitle());
                        intent.putExtra("img", modelt .getmImg());
                        intent.putExtra("date", modelt .getmDate());
                        intent.putExtra("source", modelt .getmSource());
                        intent.putExtra("author", modelt .getmAuthor());

                    }
                       context.startActivity(intent);
                }
            });
        }
    }
}
