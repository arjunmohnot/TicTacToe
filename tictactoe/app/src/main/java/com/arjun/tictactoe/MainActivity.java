package com.arjun.tictactoe;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
//
import android.support.v7.widget.Toolbar;
//
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private ImageView img;
    private Button button1,button;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        email=(EditText) findViewById(R.id.editText);
        password=(EditText) findViewById(R.id.editText2);
        toolbar.setTitle(R.string.app_name);
        button1=(Button)findViewById(R.id.button2);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(email.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())) {
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("hello", "createUserWithEmail:success");
                                        Toast.makeText(MainActivity.this, "User Created Successfully",
                                                Toast.LENGTH_SHORT).show();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Log.d("User", "this is user" + user);
                                        Intent intent=new Intent(MainActivity.this,tictac.class);
                                        startActivity(intent);
                                    } else {
                                        String errorMessage = task.getException().getMessage();
                                        Toast.makeText(MainActivity.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();

                                        // If sign in fails, display a message to the user.
                                        Log.w("hi", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        Toast.makeText(MainActivity.this, "Try Using Password Of Minimum 6 Characters",
                                                Toast.LENGTH_LONG).show();

                                    }

                                    // ...
                                }

                            });
                }

                    progressBar.setVisibility(View.GONE);

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent var=new Intent(MainActivity.this,SecondActivity.class);
               startActivity(var);
            }
        });






        Log.d("hello","12345");
    }






}
