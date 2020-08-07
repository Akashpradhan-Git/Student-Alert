package com.example.bottom_nav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    public EditText loginEmailId, logInpasswd;
    Button btnLogIn;
    FirebaseAuth firebaseAuth;
    TextView signup;
    String username;
    static String ChildName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginEmailId=findViewById(R.id.loginEmail);
        logInpasswd=findViewById(R.id.loginpaswd);
        btnLogIn=findViewById(R.id.btnLogIn);
        signup = findViewById(R.id.TVSignUp);

        /**Check for 1st user*/

        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            finish();
            startActivity(new Intent(Login.this,MainActivity.class));
        }


        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String userEmail = loginEmailId.getText().toString();
                String userPaswd = logInpasswd.getText().toString();
                ChildName=userEmail.replaceAll("[^a-zA-Z0-9]","").trim();
                if (userEmail.isEmpty()) {
                    loginEmailId.setError("Provide your Email first!");
                    loginEmailId.requestFocus();
                }

                else if (userPaswd.isEmpty()) {
                    logInpasswd.setError("Enter Password!");
                    logInpasswd.requestFocus();
                }

                else if (userEmail.isEmpty() && userPaswd.isEmpty()) {
                    Toast.makeText(Login.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                }

                else if (!(userEmail.isEmpty() && userPaswd.isEmpty())) {
                        firebaseAuth.signInWithEmailAndPassword(userEmail, userPaswd).addOnCompleteListener(Login.this, new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Not sucessfull", Toast.LENGTH_SHORT).show();
                                }

                                else {

                                    FirebaseUser user=firebaseAuth.getCurrentUser();
                                    String  us=user.toString();
                                    Toast.makeText(Login.this, ChildName+""+user, Toast.LENGTH_SHORT).show();

                                    Intent intent=new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                    }
                }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(Login.this,SignUp.class));
            finish();
            }
        });

    }
    public static String GetNameFromLogin(){
        return ChildName;
    }

}
