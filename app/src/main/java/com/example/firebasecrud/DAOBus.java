package com.example.firebasecrud;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOBus {
    private DatabaseReference databaseReference;
    public DAOBus(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Bus.class.getSimpleName());
    }
    public Task<Void> add(Bus bs){
        return databaseReference.push().setValue(bs);
    }

    public Task<Void> update(String key, HashMap<String,Object> hashMap){
       return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }

    public Query get(String key){
        if(key==null){
            return databaseReference.orderByKey().limitToFirst(8);
        }
        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);
    }
}
