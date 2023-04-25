package com.example.parseserver_email_login_in_advance;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName , edtPuchSpeed , edtPuchPower ,edtKickSpeed,edtKickPower ;

    private TextView txtGetData;

    private Button btnGetAllData;

    private String allKickboxer ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ParseInstallation.getCurrentInstallation().saveInBackground();

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(SignUp.this);

        edtName = findViewById(R.id.edtName);
        edtPuchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPuchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);

        txtGetData = findViewById(R.id.txtGetData);
        btnGetAllData = findViewById(R.id.btnGetAllData);

        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                allKickboxer = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");

                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if (e == null){
                            if (objects.size() > 0){


                                for (ParseObject kickboxer : objects){
                                    allKickboxer = allKickboxer + kickboxer.get("name")+"\n" ;
                                }
                                Toast.makeText(SignUp.this, allKickboxer, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(SignUp.this, e.getMessage() , Toast.LENGTH_SHORT).show();

                            }


                        }

                    }
                });

            }
        });

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

            ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
            parseQuery.getInBackground("SPQYHui5tw", new GetCallback<ParseObject>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void done(ParseObject object, ParseException e) {

                    if (e == null && object != null){

                        txtGetData.setText(object.get("name") + "" +"\n"+ "Punch Power: "+object.get("punchPower") + "" + "\n");


                    }
                }
            });

            }
        });


    }

    @Override
    public void onClick(View v) {
        try {

            ParseObject kickboxer = new ParseObject("KickBoxer");
            kickboxer.put("name",edtName.getText().toString());
            kickboxer.put("punchSpeed",Integer.parseInt(edtPuchSpeed.getText().toString()));
            kickboxer.put("punchPower",Integer.parseInt(edtPuchPower.getText().toString()));
            kickboxer.put("kickPower",edtKickPower.getText().toString());
            kickboxer.put("kickSpeed", edtKickSpeed.getText().toString());

            kickboxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null){
                        Toast.makeText(SignUp.this, "message succefully created", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }


    }


//        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punch_speed", 2000);
//
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null){
//
//                    Toast.makeText(SignUp.this, "boxer object is saved successfully", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
//
//
}