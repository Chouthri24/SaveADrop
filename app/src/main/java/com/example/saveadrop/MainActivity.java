package com.example.saveadrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    boolean doubleTapToExit=false;
    private Toast backToast;
    final int duration=2000;
    View view;
    TextView btnsignup,mainTextshow;
    EditText phoneNo, Password;
    FloatingActionButton btnSignIn;
    TextView forGot;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListenner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnsignup = findViewById(R.id.SignUp);
        mFirebaseAuth = FirebaseAuth.getInstance();
        phoneNo = findViewById(R.id.editTextTextPhone);
        Password = findViewById(R.id.editTextTextPassword);
        btnSignIn = findViewById(R.id.SignIn);
        forGot=findViewById(R.id.Forgotact);
        view=findViewById(android.R.id.content);

        mAuthStateListenner=new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser !=null){
                    Toast.makeText(MainActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity.this,HomePage.class);
                    startActivity(i);

                }
                else {
                    Toast.makeText(MainActivity.this,"Please Login",Toast.LENGTH_SHORT).show();
                }

            }
        };
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pneNo=phoneNo.getText().toString();
                String pwd=Password.getText().toString();
                if(pneNo.isEmpty()){
                    phoneNo.setError("Please Enter Your Email Id");

                }
                else if(pwd.isEmpty()){
                    Password.setError("Please Enter Your Password");
                    Password.requestFocus();
                }
                else if(pneNo.isEmpty()&&pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fields are Empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!(pneNo.isEmpty()&&pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(pneNo,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login Error,Please Login Again", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intToHome = new Intent(MainActivity.this, HomePage.class);
                                startActivity(intToHome);
                            }

                        }
                    });}

                else{
                    Toast.makeText(MainActivity.this,"Error Ocurred!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intsignUp=new Intent(MainActivity.this,SignUppage.class);
                startActivity(intsignUp);

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleTapToExit)
        {
            super.onBackPressed();
            backToast.cancel();
            return;
        }
        else {

            doubleTapToExit=true;
            backToast= Toast.makeText(getBaseContext(),"Press back again to exit Chouthri's Application",Toast.LENGTH_SHORT);
            backToast.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleTapToExit=false;

                }
            },duration);
        }

    }
}