package com.example.ticketbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Activity_addConcert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_concert);

        final EditText et_name= findViewById(R.id.et_name);
        final EditText et_ticketType= findViewById(R.id.et_ticketType);
        final EditText et_ticketCount= findViewById(R.id.et_ticketCount);
        Button btn = findViewById(R.id.btn_add);

        Button btn_open = findViewById(R.id.btn_open);
        btn_open.setOnClickListener(v->{
            Intent intent = new Intent(Activity_addConcert.this,RvActivity.class);
            startActivity(intent);
        });

        DataAccessObject dao = new DataAccessObject();
        Concert concert_edit = (Concert) getIntent().getSerializableExtra("Edit");

        if(concert_edit != null){
            btn.setText("Update");
            et_name.setText(concert_edit.getName());
            et_ticketType.setText(concert_edit.getTicketType());
            et_ticketCount.setText(concert_edit.getTicketCount());
            btn_open.setVisibility(View.GONE);
        }
        else{
            btn.setText("Submit");
            btn_open.setVisibility(View.VISIBLE);
        }

        btn.setOnClickListener(v->{
            Concert concert = new Concert(et_name.getText().toString(),et_ticketType.getText().toString(),et_ticketCount.getText().toString());
            if(concert_edit==null) {
                dao.add(concert).addOnSuccessListener(suc -> {

                    Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
            else{

                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("name",et_name.getText().toString());
                hashMap.put("ticketType",et_ticketType.getText().toString());
                hashMap.put("ticketCount",et_ticketCount.getText().toString());

                dao.update(concert_edit.getKey(),hashMap).addOnSuccessListener(suc ->{
                    Toast.makeText(this, "Record is updated",Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(er->{
                    Toast.makeText(this,""+er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }




        });


    }
}


