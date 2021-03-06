package com.example.liveharshit.spider;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    EditText e1,e2;
    private boolean back = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button2);
        b2=(Button)findViewById(R.id.button3);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1= e1.getText().toString();
                String s2= e2.getText().toString();
                if(s1.equals("")||s2.equals("")) {
                    Toast.makeText(MainActivity.this, "Please fill all!", Toast.LENGTH_SHORT).show();
                }
                else {
                    SQLiteDatabase data = openOrCreateDatabase("function",MODE_PRIVATE,null);
                    data.execSQL("create table if not exists student(username varchar,email varchar,password varchar)");
                    String s="select * from student where username='"+s1+"' and password='"+s2+"'";
                    Cursor cursor=data.rawQuery(s,null);
                    if(cursor.getCount()>0) {
                        Toast.makeText(MainActivity.this, "Login done", Toast.LENGTH_SHORT).show();
                        Intent a= new Intent(MainActivity.this,Home.class);
                        startActivity(a);
                        finish();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Incorrect details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(MainActivity.this,SignUp.class);
                startActivity(b);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(!back) {

            Toast.makeText(MainActivity.this, "Press back again to exit", Toast.LENGTH_SHORT).show();
            back = true;
        }
        else {
            super.onBackPressed();
        }
        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                back = false;
            }
        }.start();
    }
}
