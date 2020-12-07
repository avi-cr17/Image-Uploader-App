package com.example.firebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference rootref = db.getReference();
    DatabaseReference userref = rootref.child("Users");

    EditText name,usename,email;
    Button button,fetch,upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        usename= findViewById(R.id.usename);
        email = findViewById(R.id.email);
        button= findViewById(R.id.button);
        fetch = findViewById(R.id.fetch);
        upload = findViewById(R.id.upload);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String musername = usename.getText().toString().trim();
                String mname = name.getText().toString().trim();
                String memail= email.getText().toString().trim();


                HashMap<String,String> usermap = new HashMap<String, String>();
                usermap.put("name",mname);
                usermap.put("username",musername);
                usermap.put("email",memail);

                userref.push().setValue(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this,"Sucess",Toast.LENGTH_SHORT).show();
                    }
                });
                // if we had user id we could do
                // userref.child(user_id).setValue(usermap);



            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(getApplicationContext(),Fetch.class);
                startActivity(inten);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Upload.class);
                startActivity(intent);
            }
        });


    }





}
