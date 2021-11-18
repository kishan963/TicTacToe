package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashSet;

public class online_mode extends AppCompatActivity implements View.OnClickListener {
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,reset;
    TextView head;boolean isgameon=true;int start=0;
    HashSet<Integer> hs=new HashSet<>();
    DatabaseReference myRef;
    OnlineCodeGenerator sam=new OnlineCodeGenerator();
    int dp[][]={ {-1,-2,-3},{-4,-5,-6},{-7,-8,-9} };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_mode);
        myRef = FirebaseDatabase.getInstance().getReference();

        btn1=findViewById(R.id.button1);
        btn2=findViewById(R.id.button2);
        btn3=findViewById(R.id.button3);
        btn4=findViewById(R.id.button4);
        btn5=findViewById(R.id.button5);
        btn6=findViewById(R.id.button6);
        btn7=findViewById(R.id.button7);
        btn8=findViewById(R.id.button8);
        btn9=findViewById(R.id.button9);
        head=findViewById(R.id.head);
        reset=findViewById(R.id.reset);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
       myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int x=snapshot.child("play").getValue(int.class);

                if(x!=-1 && !hs.contains(x))
                {
                hs.add(x);
                    dp[x/3][x%3]=1;
                head.setText("Your Turn");
                if(x==0)
                { btn1.setText("X");

                }
                else if(x==1)
                { btn2.setText("X");

                }
                else if(x==2)
                {  btn3.setText("X");

                }
                else if(x==3)
                {  btn6.setText("X");

                }
                else if(x==4)
                { btn5.setText("X");

                }
                else if(x==5)
                {   btn4.setText("X");

                }
                else if(x==6)
                {  btn7.setText("X");

                }
                else if(x==7)
                {  btn8.setText("X");

                }
                else if(x==8)
                {  btn9.setText("X");

                }
                    if( check())
                        head.setText("He is Winner");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {


           Button onclick = findViewById(v.getId());
           int tagclick= Integer.parseInt(v.getTag().toString());
           tagclick--;
            if(!isgameon)
                return ;
           if(!hs.contains(tagclick))
           {   hs.add(tagclick);
               dp[tagclick/3][tagclick%3]=sam.first_zero;
                   onclick.setText("O");

                   head.setText("His Turn");
                   MainActivity obj=new MainActivity();
                   if( check())
                       head.setText("You are Winner");
                   myRef.child("play").setValue(tagclick);


           }

    }
    public boolean check(){
        for(int i=0;i<3;i++ )
        {
            if(dp[i][0]==dp[i][1] && dp[i][1]==dp[i][2] )
            {

                isgameon=false;
                head.setBackgroundResource(android.R.color.holo_red_light);
                return true;
            }
            else if(dp[0][i]==dp[1][i] && dp[1][i]==dp[2][i])
            {
                 head.setBackgroundResource(android.R.color.holo_red_light);
                isgameon=false;
                return true;
            }
        }
        if(dp[0][0]==dp[1][1] && dp[1][1]==dp[2][2] )
        {

            head.setBackgroundResource(android.R.color.holo_red_light);
            isgameon=false;
            return true;
        }
        if(dp[0][2]==dp[1][1] && dp[1][1]==dp[2][0] )
        {

            head.setBackgroundResource(android.R.color.holo_red_light);
            isgameon=false;
            return true;
        }
        return false;
    }
    public void reset(){
        isgameon=true;
        dp= new int[][]{{-1,-2,-3},{-4,-5,-6},{-7,-8,-9}};
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        head.setText("Your Turn");
        head.setBackgroundResource(android.R.color.transparent);
        hs.clear();
        start=0;

    }
}