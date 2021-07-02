package com.test.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnRegist,btnLog;
    EditText user,pass;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        db=new DBHelper(this);
        btnRegist = findViewById(R.id.btnRegist);
        btnLog = findViewById(R.id.btnLogin);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1=user.getText().toString();
                String pass1=pass.getText().toString();
                if (user1.equals("")||pass1.equals(""))
                    Toast.makeText(MainActivity.this,"Can not keep blank field",Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass=db.checkusernamepassword(user1,pass1);
                    if (checkuserpass==true){
                        Toast.makeText(MainActivity.this,"Sign in successful",Toast.LENGTH_SHORT).show();
                        Intent gotoHomeScreen = new Intent(MainActivity.this,HomeScreen.class);
                        startActivity(gotoHomeScreen);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Invalid username or password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegisterScreen = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(goToRegisterScreen);
            }
        });

    }
}