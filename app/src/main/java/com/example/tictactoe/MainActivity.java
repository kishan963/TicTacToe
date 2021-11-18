package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,reset,online;
    TextView head;
    HashSet<Button> hs=new HashSet<>();
    int start=0;boolean isgameon=true;
    int dp[][]={ {-1,-2,-3},{-4,-5,-6},{-7,-8,-9} };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=findViewById(R.id.button1);
        btn2=findViewById(R.id.button2);
        btn3=findViewById(R.id.button3);
        btn4=findViewById(R.id.button4);
        btn5=findViewById(R.id.button5);
        btn6=findViewById(R.id.button6);
        btn7=findViewById(R.id.button7);
        btn8=findViewById(R.id.button8);
        btn9=findViewById(R.id.button9);
        online=findViewById(R.id.online);
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
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, OnlineCodeGenerator.class);
                startActivity(intent);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Button onclick = findViewById(v.getId());
        int tagclick= Integer.parseInt(v.getTag().toString());
        tagclick--;
        if(!isgameon)
            return ;
        if(!hs.contains(onclick ))
        {   hs.add(onclick);
            dp[tagclick/3][tagclick%3]=start;
            if (start == 0) {
                onclick.setText("O");
                start = 1;
                head.setText("X Turn");
            } else {
                onclick.setText("X");
                start = 0;
                head.setText("O Turn");
            }

            check();
        }
    }
    public void check(){
        for(int i=0;i<3;i++ )
        {
            if(dp[i][0]==dp[i][1] && dp[i][1]==dp[i][2] )
            {
                if(1-start==1 )
                    head.setText("X Winner " );
                else
                    head.setText(1-start+ " Winner " );
                isgameon=false;
                head.setBackgroundResource(android.R.color.holo_red_light);
            }
            else if(dp[0][i]==dp[1][i] && dp[1][i]==dp[2][i])
            {   if(1-start==1 )
                head.setText("X Winner " );
            else
                head.setText(1-start+ " Winner " );
                head.setBackgroundResource(android.R.color.holo_red_light);
                isgameon=false;
            }
        }
        if(dp[0][0]==dp[1][1] && dp[1][1]==dp[2][2] )
        {
            if(1-start==1 )
                head.setText("X Winner " );
            else
                head.setText(1-start+ " Winner " );
            head.setBackgroundResource(android.R.color.holo_red_light);
            isgameon=false;
        }
        if(dp[0][2]==dp[1][1] && dp[1][1]==dp[2][0] )
        {
            if(1-start==1 )
                head.setText("X Winner " );
            else
                head.setText(1-start+ " Winner " );
            head.setBackgroundResource(android.R.color.holo_red_light);
            isgameon=false;
        }
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