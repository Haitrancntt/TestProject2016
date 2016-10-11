package com.example.haitr.testproject2016.Main.Main;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.haitr.testproject2016.Main.MainApp.DetailedActivity;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private EditText edUsername, edPass;
    private Button imgbtn_face, imgbtn_google;
    private Button btnLogin;
    private FirebaseAuth mAuth;
    private CallbackManager mCallback;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions googleSignInOptions;
    private int RC_SIGN_IN = 1;
    private String DATABASE_NAME = "TestProject.sqlite";
    private String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;
    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]

    private void xuLySaoChepCSDLTuAssetsVaoHeThongMobile() {
        //Lấy đường dẫn tới tên database trong hệ thống
        File dbFile = getDatabasePath(DATABASE_NAME);
        //Xét xem nếu tồn tại tên database đó thì xử lý
        if (!dbFile.exists()) {
            try {
                //nếu chưa tồn tại database thì bắt đầu sao chép database từ Assets vào hệ thống
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Sao chép CSDL vào hệ thống thành công!.", Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void CopyDataBaseFromAsset() {
        try {
            //Đưa CSDL trong assets sang InputStream để bắt đầu sao chép
            InputStream myInput = getAssets().open(DATABASE_NAME);
            //Lấy đường dẫn databases
            String outFileName = layDuongDanLuuTru();
            //Tạo 1 file truy xuất đến đường dẫn /databases/
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            //Kiểm tra xem đường dẫn đó có tồn tại không
            if (!f.exists()) {
                //nếu không tồn tại thì tạo đường dẫn đó ra
                f.mkdir();
            }
            //Tạo OutputStream với đầu ra là đường dẫn databases
            OutputStream myOutPut = new FileOutputStream(outFileName);
            //tạo 1 mảng byte để đưa từng dữ liệu vào
            byte[] buffer = new byte[1024];
            int lenght;
            //Chạy vòng lặp cho tới khi đọc hết InputStream
            while ((lenght = myInput.read(buffer)) > 0) {
                //Ghi vào OutputStream
                myOutPut.write(buffer, 0, lenght);
            }
            myOutPut.flush();
            myInput.close();
            myOutPut.close();
        } catch (Exception ex) {
            Log.e("Loi_SaoChep: ", ex.toString());
        }
    }

    private String layDuongDanLuuTru() {
        //Trả về đường dẫn của database trong hệ thống
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        mCallback = new CallbackManager.Factory().create();
        setContentView(R.layout.activity_main);
        xuLySaoChepCSDLTuAssetsVaoHeThongMobile();
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    Toast.makeText(MainActivity.this, "signed in", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "signed out", Toast.LENGTH_SHORT).show();
//                }
            }
        };
        MapXml();
        setupGoogle();
    }


    private void setupGoogle() {
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("1014836832956-mt73h9u7a80p7oo9289fr15dbv3e2aff.apps.google                                                                     usercontent.com")
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    private void onGoogleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void MapXml() {

        edUsername = (EditText) findViewById(R.id.edit_username);
        edPass = (EditText) findViewById(R.id.edit_pass);
        btnLogin = (Button) findViewById(R.id.button_login);
        imgbtn_face = (Button) findViewById(R.id.image_facebook);
        imgbtn_google = (Button) findViewById(R.id.image_google);
    }

    public void btnloginfacebook_Click(View view) {
        onFBlogin();
    }

    public void btnlogingoogle_Click(View view) {
        onGoogleSignIn();
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
        if (sUser.equals("") && sPass.equals("")) {
            Toast.makeText(this, "Please insert username and password", Toast.LENGTH_SHORT).show();

        } else {
            mAuth.signInWithEmailAndPassword(sUser, sPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isComplete()) {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
                        startActivity(intent);

                        Log.v("credential", mAuth.getCurrentUser().getUid());
                    } else {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    private void onFBlogin() {
        mCallback = CallbackManager.Factory.create();

        // Set permissions
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "user_photos", "public_profile"));

        LoginManager.getInstance().registerCallback(mCallback,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        String token = loginResult.getAccessToken().getToken();
                        System.out.println("Success");
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject json, GraphResponse response) {
                                        handleFacebookAccessToken(loginResult.getAccessToken());
                                    }

                                });
                        Intent intentDetailed = new Intent(MainActivity.this, DetailedActivity.class);
                        startActivity(intentDetailed);
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender");
                        request.setParameters(parameters);
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


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("Token", "handleFacebookAccessToken:" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()) {
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        Log.v("credential", credential.getProvider());
        Log.v("credential", mAuth.getCurrentUser().getUid());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
            else {
                Toast.makeText(this, "Login không thành công\nMáy được máy không anh ơi\nEm chưa biết fix", Toast.LENGTH_SHORT).show();
            }
        }
        mCallback.onActivityResult(requestCode, resultCode, data);

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "sign in with gg thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "firebase failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // [START on_start_add_listener]
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    // [END on_stop_remove_listener]

}
