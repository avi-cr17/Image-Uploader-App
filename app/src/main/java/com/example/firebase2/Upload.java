package com.example.firebase2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Upload extends AppCompatActivity {

    Button upload;
    ImageView image;
    StorageReference mystorage;
    private static final int GALLERY =4;
    ProgressDialog progressDialog ;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        progressDialog = new ProgressDialog(Upload.this);


        if(requestCode==GALLERY){
            progressDialog.setMessage("UPLOADING...");

            Uri uri = data.getData();

           progressDialog.show();


            image.setImageURI(uri);



            StorageReference file = mystorage.child("photos/"+uri.getLastPathSegment()+".png");




            file.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Upload.this,"Upload done",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Upload.this,"Upload Failed \n FATAL ERROR",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


        upload=findViewById(R.id.upload);
        image=findViewById(R.id.imageView);
        mystorage = FirebaseStorage.getInstance().getReference();
        // WE CAN DO THE ABOVE LINE INTO TWO STEPS AS WELL

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");

                startActivityForResult(intent,GALLERY);
            }
        });




    }
}