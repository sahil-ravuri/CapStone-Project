package com.sahil.capstonestage2.Helper;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sahil.capstonestage2.models.DataBaseModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private final DatabaseReference databaseReference;
    private final List<DataBaseModel> articles = new ArrayList<>();
    Context context1;

    public DatabaseHelper(Context context){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("bookmarks");
        this.context1 = context;
    }
    @SuppressWarnings("unused")
    public interface DataStatus{
        void dataIsLoaded(List<DataBaseModel> articles,List<String> keys);

    }
    public void readData(final DataStatus dataStatus){
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                articles.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode:dataSnapshot.getChildren()){
                    keys.add( keyNode.getKey() );
                    DataBaseModel article=keyNode.getValue(DataBaseModel.class);
                  articles.add(article);
                }
                dataStatus.dataIsLoaded(articles,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
