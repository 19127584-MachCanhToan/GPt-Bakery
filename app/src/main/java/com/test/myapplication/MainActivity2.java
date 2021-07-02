package com.test.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    Button btnLog,btnRes;
    EditText username,password,name,phone,confirmpass;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DB=new DBHelper(this);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        confirmpass=findViewById(R.id.cfpassword);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        btnLog = findViewById(R.id.btnLogin);
        btnRes = findViewById(R.id.btnRegister);
        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String name1=name.getText().toString();
                String phone1=phone.getText().toString();
                String cfpass=confirmpass.getText().toString();
                if (user.equals("")||pass.equals("")||name1.equals("")||phone1.equals(""))
                    Toast.makeText(MainActivity2.this,"Can not keep blank field",Toast.LENGTH_SHORT).show();
                else{
                    if (pass.equals(cfpass)){
                        Boolean checkuser=DB.checkusername(user);
                        if(checkuser==false) {
                            Boolean insert=DB.insertData(user,pass,name1,phone1);
                            if (insert==true) {
                                Toast.makeText(MainActivity2.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(MainActivity2.this,"Registered failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity2.this,"Existed user, please choose another user",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity2.this,"Password not matching",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLoginScreen = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(goToLoginScreen);
            }
        });
    }
}