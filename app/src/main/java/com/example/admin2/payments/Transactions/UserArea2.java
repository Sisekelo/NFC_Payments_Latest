package com.example.admin2.payments.Transactions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin2.payments.R;

public class UserArea2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        int age = intent.getIntExtra("age", -1);


        final TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);

        String  message = name + " welocome to your user area";
        tvWelcomeMsg.setText(message);
        etUsername.setText(username);

    }
}
