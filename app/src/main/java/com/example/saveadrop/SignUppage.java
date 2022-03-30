package com.example.saveadrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUppage extends AppCompatActivity {
    TextView btnsignin;
    EditText phoneNo, Password,Name,EmailId;
    FloatingActionButton btnSignUp;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_uppage);

        btnsignin = findViewById(R.id.SignIn);
        mFirebaseAuth=FirebaseAuth.getInstance();
        EmailId=findViewById(R.id.editTextTextEmailAddress);
        Password=findViewById(R.id.editTextTextPassword);
        phoneNo=findViewById(R.id.editTextTextPhone);
        Name=findViewById(R.id.Nm);
        btnSignUp=findViewById(R.id.SignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eMl =EmailId.getText().toString();
                String pwd=Password.getText().toString();
                String name=Name.getText().toString();
                String phone=phoneNo.getText().toString();
                if(name.isEmpty())
                {
                    Name.setError("Please Enter Your Name");
                }
                else if(eMl.isEmpty()){
                    EmailId.setError("Please Enter Your Email Id");

                }
                else if(phone.isEmpty())
                {
                    phoneNo.setError("Please Enter Your Phone Number");

                }
                else if(pwd.isEmpty()){
                    Password.setError("Please Enter your Password");
                    Password.requestFocus();
                }
                else if(eMl.isEmpty()&&pwd.isEmpty()){
                    Toast.makeText(SignUppage.this,"Fields are Empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!(eMl.isEmpty()&&pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(eMl,pwd).addOnCompleteListener(SignUppage.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SignUppage.this,"SignUp Unsuccessful,Please Try Again",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(SignUppage.this,HomePage.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(SignUppage.this,"Error Ocurred!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intsignIn = new Intent(SignUppage.this, MainActivity.class);
                startActivity(intsignIn);

            }
        });

    }
}