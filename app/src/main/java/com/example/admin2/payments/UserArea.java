package com.example.admin2.payments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserArea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String username = intent.getStringExtra("username");
        int balance = intent.getIntExtra("balance", -1);


        final EditText etBalance = (EditText) findViewById(R.id.etBalance);
        final TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final Button charge = (Button) findViewById(R.id.Chargebutton);
        final Button receive = (Button) findViewById(R.id.Receivebutton);
        final Button enter = (Button) findViewById(R.id.EnterButton);


        String  message = name + " welcome to your user area";
        tvWelcomeMsg.setText(message);
        etUsername.setText(username);
        etBalance.setText(balance + "");

        //what happens when I click receive

        receive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent registerIntent = new Intent(UserArea.this,ReceiveMoneyPage.class);
                registerIntent.putExtra("username", username);
                UserArea.this.startActivity(registerIntent);
            }
        });

        //what happens when I click receive

        charge.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent registerIntent = new Intent(UserArea.this,SendMoneyPage.class);
                registerIntent.putExtra("username", username);
                UserArea.this.startActivity(registerIntent);
            }
        });

        //what happens when I click enter

        enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent registerIntent = new Intent(UserArea.this,Enter.class);
                //registerIntent.putExtra("username", username);
                UserArea.this.startActivity(registerIntent);
            }
        });



    }
}
