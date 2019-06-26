package com.sahil.capstonestage2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sahil.capstonestage2.Utilities.Utils;
import com.sahil.capstonestage2.models.DataBaseModel;

import java.util.List;

public class BookMark {
    private Context context;
    BookAdapter adapter;
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void bookmark(RecyclerView view,Context context,List<DataBaseModel> models,List<String> key){
        this.context=context;
        adapter = new BookAdapter(models,key);
        view.setAdapter(adapter);
    }
    public  class BookItemView extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        TextView author;
        TextView published_ad;
        TextView source;
        TextView time;
        ImageView imageView;
        ProgressBar progressBar;
        OnItemClickListener onItemClickListener;
        String key;
        public BookItemView(@NonNull ViewGroup parent, OnItemClickListener onItemClickListener) {
            super(LayoutInflater.from(context).inflate(R.layout.bookmark_item,parent,false));
            itemView.setOnClickListener((View.OnClickListener) this);
            title = itemView.findViewById(R.id.book_title);
            desc = itemView.findViewById(R.id.book_desc);
            author = itemView.findViewById(R.id.book_author);
            published_ad = itemView.findViewById(R.id.book_publishedAt);
            source = itemView.findViewById(R.id.book_source);
            time = itemView.findViewById(R.id.book_time);
            imageView = itemView.findViewById(R.id.bookimg);
            progressBar = itemView.findViewById(R.id.prograss_load_photo);

            this.onItemClickListener = onItemClickListener;
        }

        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }

        @SuppressLint("SetTextI18n")
        public void bindData(DataBaseModel model, String key){
            title.setText(model.getmTitle());
            desc.setText(model.getmTitle());
            source.setText(model.getmSource());
            time.setText(" \u2022 " + Utils.DateToTimeFormat(model.getmDate()));
            published_ad.setText(Utils.DateFormat(model.getmDate()));
            author.setText(model.getmAuthor());
            this.key = key;
        }
    }
    public class BookAdapter extends RecyclerView.Adapter<BookItemView >{
        private List<DataBaseModel> models;
        private List<String> key;

        public BookAdapter(List<DataBaseModel> models, List<String> key) {
            this.models = models;
            this.key = key;
        }

        @NonNull
        @Override
        public BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BookItemView holder, int position) {
            holder.bindData(models.get(position),key.get(position));
        }

        @Override
        public int getItemCount() {
            return models.size();
        }
    }
}
