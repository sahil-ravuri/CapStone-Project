package com.sahil.capstonestage2.Helper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sahil.capstonestage2.MainActivity;
import com.sahil.capstonestage2.models.DataBaseModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private List<DataBaseModel> articles = new ArrayList<>();
    List<String> check;
    private Context context ;
    public DatabaseHelper(Context context){
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("bookmarks");
        this.context=context;
    }
    public interface DataStatus{
        void dataIsLoaded(List<DataBaseModel> articles,List<String> keys);
        void DataInserted();
        void DataUpdated();
        void DataDeleted();
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
                   // Toast.makeText(context,article, Toast.LENGTH_SHORT).show();
                  articles.add(article);
                    //check.add(article);
                }
                dataStatus.dataIsLoaded(articles,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
