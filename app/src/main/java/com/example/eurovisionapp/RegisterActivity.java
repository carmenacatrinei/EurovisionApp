package com.example.eurovisionapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
//import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText email, password, firstName, secondName;
    private Button cancel, register;
    private RadioGroup gender;
    public SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.emailText);
        password = findViewById(R.id.passText);
        cancel = findViewById(R.id.cancelButton);
        register = findViewById(R.id.registerButton);
        firstName = findViewById(R.id.fstName);
        secondName = findViewById(R.id.sndName);
        gender = findViewById(R.id.gender);

        preferences = getSharedPreferences("USER_INFO", 0);

        //culori pentru butoane

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailValue = email.getText().toString().trim();
                String passValue = password.getText().toString().trim();
                String firstValue = firstName.getText().toString().trim();
                String secondValue = secondName.getText().toString().trim();
                RadioButton checked = findViewById(gender.getCheckedRadioButtonId());
                String genderValue = checked.getText().toString().trim();
                int ok = 1;

                if (firstValue.isEmpty()) {
                    firstName.setError("Please insert your first name!");
                    firstName.requestFocus();
                    ok = 0;
                    return;
                }

                if (secondValue.isEmpty()) {
                    secondName.setError("Please insert your second name!");
                    secondName.requestFocus();
                    ok = 0;
                    return;
                }

                if (emailValue.isEmpty()) {
                    email.setError("Please insert your email!");
                    email.requestFocus();
                    ok = 0;
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()){
                    email.setError("Insert a valid email address!");
                    email.requestFocus();
                    ok = 0;
                    return;
                }

                if (passValue.isEmpty()) {
                    password.setError("Please insert your password!");
                    password.requestFocus();
                    ok = 0;
                    return;
                }

                if (passValue.length() < 8) {
                    password.setError("Your password should be at least 8 characters long!");
                    password.requestFocus();
                    ok = 0;
                    return;
                }

                if (ok == 1) {
                    int usersCount = preferences.getInt("id", 0) + 1;
                    //usersCount += 1;
                    SharedPreferences.Editor editor = preferences.edit();
                    //editor.putString("id", Integer.toString(usersCount));
                    editor.putString("email" + usersCount, emailValue);
                    editor.putString("password" + usersCount, passValue);
                    editor.putString("first_name" + usersCount, firstValue);
                    editor.putString("second_name" + usersCount, secondValue);
                    editor.putString("gender" + usersCount, genderValue);
                    editor.putString("1_" + usersCount, "12p");
                    editor.putString("2_" + usersCount, "10p");
                    editor.putString("3_" + usersCount, "8p");
                    editor.putString("4_" + usersCount, "7p");
                    editor.putString("5_" + usersCount, "6p");
                    editor.putString("6_" + usersCount, "5p");
                    editor.putString("7_" + usersCount, "4p");
                    editor.putString("8_" + usersCount, "3p");
                    editor.putString("9_" + usersCount, "2p");
                    editor.putString("10_" + usersCount, "1p");
                    editor.putInt("id", usersCount);
                    editor.apply();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    Toast.makeText(RegisterActivity.this, "User registered. Please login now!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyField();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void emptyField() {
        email.setText("");
        password.setText("");
        firstName.setText("");
        secondName.setText("");
    }

}
