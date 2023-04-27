package com.example.parseserver_email_login_in_advance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class WelcomeActivity extends AppCompatActivity {

    private TextView txtWelcome;
    private Button btnLogOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        txtWelcome = findViewById(R.id.txtWelcome);
        btnLogOut = findViewById(R.id.btnLogOut);


        txtWelcome.setText("Welcome ! " + ParseUser.getCurrentUser().get("username"));

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logOut();
                finish();

                ///FancyToast.makeText(WelcomeActivity.this,ParseUser.getCurrentUser().get("username") + " is LogOut" , FancyToast.LENGTH_SHORT ,FancyToast.SUCCESS,true).show();
                Toast.makeText(WelcomeActivity.this, ParseUser.getCurrentUser().get("username")+" is LogOut" , Toast.LENGTH_SHORT).show();

            }
        });


    }
}