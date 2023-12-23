package com.example.baluev_v_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Personal_area extends AppCompatActivity implements View.OnClickListener {
    ImageView search, exit;
    Button web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);

        search = findViewById(R.id.img_search);
        exit = findViewById(R.id.img_exit);
        web = findViewById(R.id.btn_buy);

        search.setOnClickListener(this);
        exit.setOnClickListener(this);
        web.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_search){
            startActivity(new Intent(this, Search.class));
        }else if (v.getId() == R.id.img_exit){
            startActivity(new Intent(this, MainActivity.class));
        }else if (v.getId() == R.id.btn_buy){
            Uri uriUrl = Uri.parse("https://www.kinopoisk.ru/");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        }
    }
}