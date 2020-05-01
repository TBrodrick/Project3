package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import java.math.*;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project3.R;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final EditText usrName = (EditText) findViewById(R.id.usr);
        Button logIn = (Button) findViewById(R.id.logIn);

        final Intent intent = new Intent(this, CreateNewGame.class);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!usrName.getText().toString().equals("")) {
                    intent.putExtra("UserName", usrName.getText().toString());
                    init(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Enter a username or password", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }

    /**
     * Main Start screen
     * @param intent the intent to be passed into the game. Contains username
     */
    public void init(Intent intent) {
        setContentView(R.layout.activity_main);
        Button newRunButton = (Button) findViewById(R.id.newRun);
        Button cont = (Button) findViewById(R.id.continueRun);

        newRunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Continue", false);
                startActivity(intent);
            }
        });
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Continue", false);
                startActivity(intent);
            }
        });
    }
}