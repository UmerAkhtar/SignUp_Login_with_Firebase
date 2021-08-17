package com.example.task_app1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class Reset_Password extends AppCompatActivity {

    EditText reset;
    Button btnreset;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset__password);
        reset =(EditText)findViewById(R.id.resettext);
        btnreset =(Button)findViewById(R.id.btnreset);
        auth=FirebaseAuth.getInstance();
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPass();
            }
        });
    }

    private void ResetPass() {
        String email=reset.getText().toString();
        if(!email.contains("@"))
        {
            reset.setText("Invalid Email");
            reset.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            reset.setText("Provide Valid Email");
            reset.requestFocus();
            return;

        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
           if(task.isSuccessful())
           {
               Toast.makeText(Reset_Password.this,"Password Reset Check Email",Toast.LENGTH_LONG).show();
               Reset_Password.super.onBackPressed();
           }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Reset_Password.this,"Error"+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}