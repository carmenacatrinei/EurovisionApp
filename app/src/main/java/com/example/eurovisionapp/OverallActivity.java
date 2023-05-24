package com.example.eurovisionapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class OverallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overall_top_layout);

        updateTop();

        ImageButton backButton = findViewById(R.id.auth_back);
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(OverallActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }

    @SuppressLint("SetTextI18n")
    public void updateTop() {

        HashMap<String, Integer> dictionary = new HashMap<>();
        List<String> countries2023 = new ArrayList<>(Arrays.asList("Albania","Armenia","Australia","Austria","Azerbaijan","Belgium","Croatia","Cyprus","Czech Republic","Denmark","Estonia","Finland","France","Georgia","Germany","Greece","Iceland","Ireland","Israel","Italy","Latvia","Lithuania","Malta","Moldova","Netherlands","Norway","Poland","Portugal","Romania","San Marino","Serbia","Slovenia","Spain","Sweden","Switzerland","Ukraine","United Kingdom"));

        for (String country : countries2023) {
            dictionary.put(country, 0);
        }

        SharedPreferences preferences = getSharedPreferences("USER_INFO", 0);
        int userCounter = preferences.getInt("id", 0);
        int val = 0;


        for (int i = 1; i <= userCounter; i++) {
            String c1 = preferences.getString("1_" + i, "");
            val = dictionary.get(c1);
            dictionary.put(c1, val + 12);
            String c2 = preferences.getString("2_" + i, "");
            val = dictionary.get(c2);
            dictionary.put(c2, val + 10);
            String c3 = preferences.getString("3_" + i, "");
            val = dictionary.get(c3);
            dictionary.put(c3, val + 8);
            String c4 = preferences.getString("4_" + i, "");
            val = dictionary.get(c4);
            dictionary.put(c4, val + 7);
            String c5 = preferences.getString("5_" + i, "");
            val = dictionary.get(c5);
            dictionary.put(c5, val + 6);
            String c6 = preferences.getString("6_" + i, "");
            val = dictionary.get(c6);
            dictionary.put(c6, val + 5);
            String c7 = preferences.getString("7_" + i, "");
            val = dictionary.get(c7);
            dictionary.put(c7, val + 4);
            String c8 = preferences.getString("8_" + i, "");
            val = dictionary.get(c8);
            dictionary.put(c8, val + 3);
            String c9 = preferences.getString("9_" + i, "");
            val = dictionary.get(c9);
            dictionary.put(c9, val + 2);
            String c10 = preferences.getString("10_" + i, "");
            val = dictionary.get(c10);
            dictionary.put(c10, val + 1);
        }

        List<Integer> points = new ArrayList<>();

        for (int i = 0; i < 37; i++) points.add(dictionary.get(countries2023.get(i)));

        boolean ord = false;

        while (!ord) {
            ord = true;

            for (int i = 0; i < 36; i++)
                if (points.get(i) < points.get(i + 1)) {
                    int aux = points.get(i);
                    points.set(i, points.get(i + 1));
                    points.set(i + 1, aux);
                    String strAux = countries2023.get(i);
                    countries2023.set(i, countries2023.get(i + 1));
                    countries2023.set(i + 1, strAux);
                    ord = false;
                }
        }

        List<TextView> textList = new ArrayList<>(Arrays.asList(findViewById(R.id.number1), findViewById(R.id.number2), findViewById(R.id.number3), findViewById(R.id.number4), findViewById(R.id.number5), findViewById(R.id.number6), findViewById(R.id.number7), findViewById(R.id.number8), findViewById(R.id.number9),findViewById(R.id.number10),findViewById(R.id.number11), findViewById(R.id.number12),findViewById(R.id.number13),findViewById(R.id.number14),findViewById(R.id.number15),findViewById(R.id.number16), findViewById(R.id.number17),findViewById(R.id.number18),findViewById(R.id.number19),findViewById(R.id.number20),findViewById(R.id.number21),findViewById(R.id.number22),findViewById(R.id.number23),findViewById(R.id.number24),findViewById(R.id.number25), findViewById(R.id.number26), findViewById(R.id.number27), findViewById(R.id.number28), findViewById(R.id.number29), findViewById(R.id.number30), findViewById(R.id.number31), findViewById(R.id.number32), findViewById(R.id.number33), findViewById(R.id.number34), findViewById(R.id.number35), findViewById(R.id.number36), findViewById(R.id.number37)));

        for (int i = 0; i < 37; i++) {
            System.out.println(countries2023.get(i) + points.get(i));
            textList.get(i).setText((i + 1) + ". " + countries2023.get(i) + " - " + points.get(i));
        }

    }
}
