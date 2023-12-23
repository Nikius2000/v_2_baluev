package com.example.baluev_v_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Search extends AppCompatActivity implements View.OnClickListener {

    ImageView back;
    TextView history_text;
    EditText inp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        back = findViewById(R.id.img_back);
        back.setOnClickListener(this);

        inp = findViewById(R.id.inp_newser);

        history_text = findViewById(R.id.textView6);

        inp.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                history_text.setText(inp.getText());

                SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app55.db", MODE_PRIVATE, null);
                db.execSQL("INSERT OR IGNORE INTO search VALUES (NULL, '" + inp.getText() + "')");

                db.close();

            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        // перезагрузка
        refresh();
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.img_back){
            startActivity(new Intent(this, Personal_area.class));
        }

    }

    public void refresh(){

        history_text = findViewById(R.id.textView6);

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app55.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS search (id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT)");
        // db.execSQL("INSERT OR IGNORE INTO search VALUES (NULL, 'HELL1O')");

        Cursor query = db.rawQuery("SELECT * FROM search ORDER BY ID DESC LIMIT 1;", null);
        history_text.setText("");
        while(query.moveToNext()){
            String text = query.getString(1);
            int id = query.getInt(0);
            history_text.append(text);
        }
        query.close();
        db.close();


    }

}