package com.example.satku.xfusion_platformapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.teramatrix.library.IoTSDK;
import com.teramatrix.library.exception.InvalidRequestParametersException;


public class LoginActivity extends AppCompatActivity implements
        IoTSDK.IUserAuthorizationCallback {
    Button b1;
    TextView t1,t2,t3;
    EditText e1,e2;
    ImageView imageView;
    EditText ed1,ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imageView = (ImageView) findViewById(R.id.imageView);
        t1 = (TextView) findViewById(R.id.txt_sub_title2);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Gotham-Medium.otf");
        t1.setTypeface(typeface);

        t2 = (TextView) findViewById(R.id.txt_sub_title);
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "Gotham-Medium.otf");
        t2.setTypeface(typeface1);

        t3 = (TextView) findViewById(R.id.txt_sub_title3);
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "Gotham-Bold.otf");
        t3.setTypeface(typeface2);

        e1 = (EditText) findViewById(R.id.editText);
        Typeface typeface3 = Typeface.createFromAsset(getAssets(), "Gotham-Medium.otf");
        e1.setTypeface(typeface3);
        e2 = (EditText) findViewById(R.id.editText2);
        Typeface typeface4 = Typeface.createFromAsset(getAssets(), "Gotham-Medium.otf");
        e2.setTypeface(typeface4);



        IoTSDK ioTSdk = IoTSDK.getInstance(getApplicationContext());
        ioTSdk.requestPermission(this);

        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText) findViewById(R.id.editText)).getText().toString();
                String password = ((EditText) findViewById(R.id.editText2)).getText().toString();
                try {
                    IoTSDK.getInstance(LoginActivity.this).login(LoginActivity.this, username,
                            password, LoginActivity.this);
                } catch (InvalidRequestParametersException e)
                {
                    e.printStackTrace();
                }
            }
        });
            }


    @Override
    public void onSuccess(String response_messg) {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
//Set flag to maintain logged in status-------------
        SharedPreferences sharedPreferences=
                getSharedPreferences("Iot",MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("is_login",true).commit();
        finish();
        startActivity(new Intent(this, MainActivity.class));


    }

    @Override
    public void onFailure(String s) {
Toast.makeText(getApplicationContext(), "fail",
        Toast.LENGTH_SHORT).show();
    }
}
