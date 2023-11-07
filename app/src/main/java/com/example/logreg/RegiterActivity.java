package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegiterActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextFullName;
    private Button buttonBack;
    private Button buttonRegistration;
    private ContactDBHelper dhHelper;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiter);
        init();
        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                String fullname = editTextFullName.getText().toString();
                if (email.isEmpty() || username.isEmpty() || password.isEmpty() || fullname.isEmpty())
                {
                    Toast.makeText(RegiterActivity.this, "Nem lehet üres egyik mező sem!", Toast.LENGTH_SHORT).show();

                } else if ( !email.contains("@")) {
                    Toast.makeText(RegiterActivity.this, "Az email mezőnek tartalmaznia kell egy @ jelet!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    dhHelper.rogzites(email,username,password,fullname);
                    Toast.makeText(RegiterActivity.this, "Siker", Toast.LENGTH_SHORT).show();
                }
            }



        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegiterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void init()
    {
        dhHelper = new ContactDBHelper(this);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextFullName = findViewById(R.id.editTextFullName);
        buttonBack = findViewById(R.id.buttonBack);
        buttonRegistration = findViewById(R.id.buttonRegistration);
    }
}