package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Rest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        final Button rest = (Button) findViewById(R.id.Rest);
        rest.setOnClickListener(new View.OnClickListener() {    //The below two methods will be altered drastically when the code is merged
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You and your allies rested for a while.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

