package com.example.haitr.testproject2016.Main.Main;

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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private EditText edUsername, edPass;
    private ImageButton imgbtn_face, imgbtn_google;
    private Button btnLogin;
    private FirebaseAuth mAuth;
    private CallbackManager mCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.isInitialized();
        mCallback = new CallbackManager.Factory().create();
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
        onFBlogin();
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
        mCallback = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("email", "user-photos", "public-profile"));
        LoginManager.getInstance().registerCallback(mCallback, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                GraphRequest request = new GraphRequest().newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        handleFacebookToken(loginResult.getAccessToken());
                    }
                });
                Bundle bundle = new Bundle();
                bundle.putString("fileds", "id,name,email,gender");
                request.setParameters(bundle);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void handleFacebookToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallback.onActivityResult(requestCode, resultCode, data);
    }
}
