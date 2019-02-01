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
import android.widget.ProgressBar;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SecondActivity extends AppCompatActivity {
    private EditText loginEmailText;
    private EditText loginPassText;
    private Toolbar toolbar;
    private Button loginBtn;
    private FirebaseAuth mAuth;
    private ProgressBar loginProgress;
    private Button createbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        toolbar=(Toolbar)findViewById(R.id.toolbar2);
        toolbar.setTitle(R.string.app_name);

        mAuth = FirebaseAuth.getInstance();
        createbtn=findViewById(R.id.button3);
        loginEmailText = findViewById(R.id.editText3);
        loginPassText = findViewById(R.id.editText4);
        loginBtn = findViewById(R.id.button4);
        loginProgress = findViewById(R.id.progressBar2);
        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regint= new Intent(SecondActivity.this,MainActivity.class);
                startActivity(regint);
                finish();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail = loginEmailText.getText().toString();
                String loginPass = loginPassText.getText().toString();

                if(!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass)){
                    loginProgress.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(loginEmail, loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            loginProgress.setVisibility(View.GONE);
                            if(task.isSuccessful()){

                                sendToMain();

                            } else {

                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(SecondActivity.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();


                            }

                            loginProgress.setVisibility(View.GONE);

                        }
                    });

                }


            }
        });




    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

            sendToMain();

        }


    }


    private void sendToMain() {

        Intent mainIntent = new Intent(SecondActivity.this, tictac.class);
        startActivity(mainIntent);
        finish();

    }
}


