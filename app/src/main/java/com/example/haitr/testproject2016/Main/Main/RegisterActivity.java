package com.example.haitr.testproject2016.Main.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.haitr.testproject2016.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText edregister_Username, edregister_Pass;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private String sName, sPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        MapXml();
    }

    private void MapXml() {
        edregister_Pass = (EditText) findViewById(R.id.edit_registerPass);
        edregister_Username = (EditText) findViewById(R.id.edit_registerUsername);
        btnRegister = (Button) findViewById(R.id.button_register);
    }

    private void onRegister() {

        mAuth.createUserWithEmailAndPassword(sName, sPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isComplete()) {
                    Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void btnRegister_Click(View view) {
        try {
            sName = edregister_Username.getText().toString();
            sPass = edregister_Pass.getText().toString();
            if (sName != null && sPass != null) {
                onRegister();
            }
        } catch (Exception e) {
            Toast.makeText(RegisterActivity.this, "Please fill all", Toast.LENGTH_SHORT).show();
        }

    }
}
