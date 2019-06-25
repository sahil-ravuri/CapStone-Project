package com.sahil.capstonestage2.Helper;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sahil.capstonestage2.models.Article;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private List<Article> articles = new ArrayList<>();
    public DatabaseHelper(){
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("bookmarks");
    }
    public interface DataStatus{
        void dataIsLoaded(List<Article> articles,List<String> keys);
        void DataInserted();
        void DataUpdated();
        void DataDeleted();
    }
    public void readData(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                articles.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode:dataSnapshot.getChildren()){
                    keys.add( keyNode.getKey() );
                    Article article= keyNode.getValue(Article.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
