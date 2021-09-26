package com.example.firebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText edit_to = findViewById(R.id.edit_to);
        final EditText edit_type = findViewById(R.id.edit_type);
        final EditText edit_count = findViewById(R.id.edit_count);
        Button btn = findViewById(R.id.btn_submit);

        Button btn_open = findViewById(R.id.btn_open);
        btn_open.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,RVActivity.class);
            startActivity(intent);
        });
        DAOBus dao = new DAOBus();
        Bus bs_edit = (Bus) getIntent().getSerializableExtra("Edit");
        if (bs_edit!=null){
            btn.setText("UPDATE");
            edit_to.setText(bs_edit.getWhereto());
            edit_type.setText(bs_edit.getType());
            edit_count.setText(bs_edit.getCount());
            btn_open.setVisibility(View.GONE);
        }
        else{
            btn.setText("SUBMIT");
            btn_open.setVisibility(View.VISIBLE);
        }
        btn.setOnClickListener(v->
        {
            Bus bs = new Bus(edit_to.getText().toString(),edit_type.getText().toString(),edit_count.getText().toString());
            if (bs_edit==null) {
                dao.add(bs).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "record is inserted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT);
                });
            }
            else {

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("whereto", edit_to.getText().toString());
                hashMap.put("type", edit_type.getText().toString());
                hashMap.put("count", edit_count.getText().toString());
                dao.update(bs_edit.getKey(), hashMap).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "record is updated", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT);
                });
            }


        });
    }
}