package com.example.test123;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button stop_watch,tikTakToe,DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stop_watch = findViewById(R.id.stop_watch);
        tikTakToe = findViewById(R.id.tiktaktoe);
        DB =  findViewById(R.id.db);

        DB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Db.class);
                startActivity(intent);
            }
        });


        tikTakToe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this,TikTakToe.class);
                MainActivity.this.startActivity(intent);
            }
        });

        stop_watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Clock.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}