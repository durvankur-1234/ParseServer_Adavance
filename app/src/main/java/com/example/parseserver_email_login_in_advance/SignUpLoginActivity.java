package com.example.parseserver_email_login_in_advance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtUserNameSignUp , edtUserNameLogin , edtPasswordSignUp , edtPasswordLogin;
    private Button btnSignUp , btnLogin ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login);

        edtUserNameSignUp = findViewById(R.id.edtUserNameSignUp);
        edtUserNameLogin = findViewById(R.id.edtUserNameLogin);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser appuser = new ParseUser();
                appuser.setUsername(edtUserNameSignUp.getText().toString());
                appuser.setPassword(edtPasswordSignUp.getText().toString());
                appuser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null){
                            Toast.makeText(SignUpLoginActivity.this, appuser.get("username")+" is signed up sucessfully", Toast.LENGTH_SHORT).show();


                        }else {
                            Toast.makeText(SignUpLoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(),
                                             new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {

                                if (user != null && e == null){

                                    Toast.makeText(SignUpLoginActivity.this, user.get("username")+ " is LogedIn successfully", Toast.LENGTH_SHORT).show();
                                    //FancyToast.makeText(SignUpLoginActivity.this,user.get("username")+ " is LogedIn successfully",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true);

                                }
                                else {

                                    Toast.makeText(SignUpLoginActivity.this, e.getMessage() + " TRY AGAIN", Toast.LENGTH_SHORT).show();

                                    FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,true);
                                }

                            }
                        });

            }
        });
    }
}