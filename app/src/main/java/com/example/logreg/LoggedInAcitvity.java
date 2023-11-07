package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoggedInAcitvity extends AppCompatActivity {

    private TextView textViewUser;
    private Button buttonLogout;
    private ContactDBHelper dhHelper;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_acitvity);
        init();

        Cursor adatok = dhHelper.getName();
        if (adatok.getCount() == 0)
        {
            Toast.makeText(this,"default", Toast.LENGTH_SHORT).show();
        }
        else {
            StringBuffer builder = new StringBuffer();
            while (adatok.moveToNext())
            {
                builder.append("Username: ").append((adatok.getInt(0))).append(System.lineSeparator());
                break;
            }
            textViewUser.setText(builder);
        }

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedInAcitvity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void init()
    {
        dhHelper = new ContactDBHelper(this);
        textViewUser = findViewById(R.id.textViewUser);
        buttonLogout = findViewById(R.id.buttonLogout);
        sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
    }
}