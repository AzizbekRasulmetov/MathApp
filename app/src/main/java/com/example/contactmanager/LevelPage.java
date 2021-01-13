package com.example.contactmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LevelPage extends AppCompatActivity {

    private Button l1Btn;
    private Button l2Btn;
    private Button l3Btn;
    private Button backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_layout);

        l1Btn = (Button) findViewById(R.id.button1);
        l2Btn = (Button) findViewById(R.id.button2);
        l3Btn = (Button) findViewById(R.id.button3);
        backBtn = (Button) findViewById(R.id.backBtn2);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LevelPage.this, MainPage.class);
                startActivity(intent);
            }
        });


        l1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LevelPage.this, MainActivity.class);
                intent.putExtra("level", "1");
                startActivity(intent);
            }
        });
        l2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LevelPage.this, MainActivity.class);
                intent.putExtra("level", "3");
                startActivity(intent);
            }
        });
        l3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LevelPage.this, MainActivity.class);
                intent.putExtra("level", "2");
                startActivity(intent);
            }
        });
    }
}
