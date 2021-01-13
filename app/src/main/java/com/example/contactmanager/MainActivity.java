package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

//import Data.DatabaseHandler;
import Data.DatabaseHandler;
import Model.Record;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private EditText answer;
    private TextView one;
    private TextView sign;
    private TextView two;
    private TextView score;
    private TextView result;
    private TextView nextTxt;
    private SharedPreferences prefs;
    private static final String PREFS_NAME = "high score";
    private int count;
    private Button backBtn;
    private TextView mathTxt;
    private Typeface t1;
    private ImageView backImg;
    private String level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = Typeface.createFromAsset(getAssets(), "fonts/CHINESER.TTF");

        btn = findViewById(R.id.buttonID);
        answer = findViewById(R.id.asnwerID);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        sign = findViewById(R.id.sign);
        result = findViewById(R.id.winorlose);
        score = findViewById(R.id.scoreID);
        backBtn = findViewById(R.id.backBtn);
        nextTxt = findViewById(R.id.nextTxtID);
        mathTxt = findViewById(R.id.textView123);
        backImg = findViewById(R.id.imageView);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LevelPage.class);
                startActivity(intent);
            }
        });
        mathTxt.setTypeface(t1);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LevelPage.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        level = intent.getStringExtra("level");
        getRes();
        generateQuestion(level);

        final DatabaseHandler db = new DatabaseHandler(this);
        final Record rec = new Record();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefs = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("score", rec.getScore());
                editor.commit();

                if(btn.getText().toString().equals("Next")){
                    generateQuestion(level);
                    btn.setText("Check");
                    btn.setBackground(getResources().getDrawable(R.drawable.button_gradient));
                    btn.setTextColor(Color.WHITE);
                    result.setText("");
                }else {
                    if (checkAnswer()) {
                        result.setTextColor(Color.GREEN);
                        result.setText("Correct");
                        nextTxt.setText("Click Next to continue");
                        btn.setBackgroundColor(Color.GREEN);
                        btn.setTextColor(Color.BLACK);
                        btn.setText("Next");
                        count++;
                        answer.setText("");
                        updateRecord(rec, db);
                    } else {
                        result.setTextColor(Color.RED);
                        result.setText("Wrong");
                        nextTxt.setText("");
                        updateRecord(rec, db);
                    }

                }
            }
        });





    }

    public void updateRecord(Record rec, DatabaseHandler db){
        rec.setScore(""+count);
        if(score.getText().equals("Score:")){
            db.setRecord(rec);
        }else{
            if(Integer.parseInt(rec.getScore()) < count){
                rec.setScore(""+count);
                System.out.println("Count = " + count);
                System.out.println(rec.getScore());
                db.setRecord(rec);
            }
        }
    }

    public void generateQuestion(String level){
        String [] signs = {"+", "-", "*", "/"};
        Random random = new Random();
        int firstNumber;
        int secondNumber;
        int signsNumber = random.nextInt(4);
        if(level.equals("1")) {
             firstNumber = random.nextInt(9);
             secondNumber = random.nextInt(9) + 1;

        }else if(level.equals("2")){
            firstNumber = random.nextInt(100);
            secondNumber = random.nextInt(99) + 1;
        }else{
            firstNumber = random.nextInt(1000);
            secondNumber = random.nextInt(999) + 1;
        }
        one.setText(""+firstNumber);
        two.setText(""+secondNumber);
        sign.setText(signs[signsNumber]);
    }

    public boolean checkAnswer(){
        try {
            int ans = Integer.parseInt(answer.getText().toString());
            int oneN = Integer.parseInt(one.getText().toString());
            int twoN = Integer.parseInt(two.getText().toString());

            if (sign.getText().toString().equals("+")) {
                return oneN + twoN == ans;
            } else if (sign.getText().toString().equals("-")) {
                return oneN - twoN == ans;
            } else if (sign.getText().toString().equals("*")) {
                return oneN * twoN == ans;
            } else {
                return oneN / twoN == ans;
            }
        }catch (Exception e){
            nextTxt.setText("Enter your answer!");
        }
        return false;
    }

    public void getRes(){
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        if(prefs.contains("score")){
            String sc = prefs.getString("score", "0");
            score.setText("Score: " + sc);
        }
    }

    public String returnName(){
        return "Aziz was here";
    }
}