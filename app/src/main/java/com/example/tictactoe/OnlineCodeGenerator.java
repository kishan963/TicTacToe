package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class OnlineCodeGenerator extends AppCompatActivity {
    Button create,join;
    EditText joinCode;


    int first_zero=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_code_generator);
        join=findViewById(R.id.join);
        joinCode =findViewById(R.id.joincode);
        create=findViewById(R.id.create);
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        join.setOnClickListener(v -> myRef.child("game").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren() )
                {

                    if(dataSnapshot.getKey().toString().equals(joinCode.getText().toString()))
                    {
                        Intent intent1=new Intent(OnlineCodeGenerator.this,online_mode.class);
                        startActivity(intent1);
                    }
                }

                return ;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));



        create.setOnClickListener(v -> {

            if(joinCode.getText().toString().equals(""))
                return;
            create.setVisibility(View.GONE);
             myRef.child("game").removeValue();
             myRef.child("play").setValue(-1);
            myRef.child("game")
                    .child(joinCode.getText().toString()).setValue("");


        });


    }

}