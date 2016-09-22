package com.example.haitr.testproject2016.Main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.haitr.testproject2016.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText edUsername, edPass;
    private ImageButton imgbtn_face, imgbtn_google;
    private Button btnLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        MapXml();
    }

    private void MapXml() {

        edUsername = (EditText) findViewById(R.id.edit_username);
        edPass = (EditText) findViewById(R.id.edit_pass);
        btnLogin = (Button) findViewById(R.id.button_login);
        imgbtn_face = (ImageButton) findViewById(R.id.image_facebook);
        imgbtn_google = (ImageButton) findViewById(R.id.image_google);
    }

    public void btnloginfacebook_Click(View view) {

    }

    public void btnlogingoogle_Click(View view) {

    }

    public void btnLogin_Click(View view) {
        onLogin();
    }

    public void txt_Register(View view) {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void onLogin() {
        String sUser = edUsername.getText().toString();
        String sPass = edPass.getText().toString();
        mAuth.signInWithEmailAndPassword(sUser, sPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isComplete()) {
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onFBlogin() {

    }
}
