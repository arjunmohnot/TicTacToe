package com.arjun.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class tictac extends AppCompatActivity {

    private FirebaseAuth mauth;
    private Button button,button1;
    private EditText text;
    private Toolbar toolbar3;
    private FirebaseUser user;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictac);
        text=(EditText)findViewById(R.id.editText5);
        button=(Button)findViewById(R.id.button5);
        button1=(Button)findViewById(R.id.button6);
        user = FirebaseAuth.getInstance().getCurrentUser();
        textView=(TextView)findViewById(R.id.textView);
        String email = user.getEmail();
        String emails[]=email.split("@");
        final String finemail=emails[0];
        textView.setText("Hello, "+finemail);
        mauth=FirebaseAuth.getInstance();
        toolbar3=(Toolbar)findViewById(R.id.toolbar3);
        toolbar3.setTitle(R.string.app_name);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(text.getText().toString())){

                    Intent ab=new Intent(tictac.this,fin.class);
                    ab.putExtra("Player1",finemail+" ");
                    ab.putExtra("Player2",text.getText().toString()+" ");
                    startActivity(ab);
                }
                else{
                    Toast.makeText(tictac.this,"Enter name for the opponent",Toast.LENGTH_LONG).show();
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              logOut();
            }
        });

    }

    private void logOut() {
        mauth.signOut();
        sendToLogin();
    }
    private void sendToLogin() {
        Intent a=new Intent(tictac.this,SecondActivity.class);
        startActivity(a);
    }

}
