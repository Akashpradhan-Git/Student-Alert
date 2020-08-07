package com.example.bottom_nav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    public EditText emailId, passwd,uname,usn;
    Button btnSignUp;
    TextView signIn;
    FirebaseAuth firebaseAuth;
    Member member;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    static String ChildName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.ETemail);
        passwd = findViewById(R.id.ETpassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        signIn = findViewById(R.id.TVSignIn);
        uname=findViewById(R.id.name);
        usn=findViewById(R.id.Usn);


        member=new Member();

        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        myref= FirebaseDatabase.getInstance().getReference().child("member");



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String emailID = emailId.getText().toString();
                String paswd = passwd.getText().toString();
                String Name = uname.getText().toString().trim();
                String Usn=usn.getText().toString().trim();




                member.setName(Name);
                member.setUsn(Usn);
                member.setEmail(emailID);


               if(Name.isEmpty()){
                   uname.setError("Provide your Name!");
                   uname.requestFocus();
               }
               else if(Usn.isEmpty()){

                   usn.setError("Provide your Age!");
                   usn.requestFocus();
               }


               else if (emailID.isEmpty()) {
                    emailId.setError("Provide your Email first!");
                    emailId.requestFocus();
                } else if (paswd.isEmpty()) {
                    passwd.setError("Set your password");
                    passwd.requestFocus();
                } else if (emailID.isEmpty() && paswd.isEmpty()) {
                    Toast.makeText(SignUp.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(emailID.isEmpty() && paswd.isEmpty())) {
                    firebaseAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(SignUp.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUp.this.getApplicationContext(),
                                        "SignUp unsuccessful: " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                            else {

                                ChildName=emailID.replaceAll("[^a-zA-Z0-9]","").trim();
                                myref.child(ChildName).setValue(member);
                                Intent intent=new Intent(SignUp.this, MainActivity.class);
                                intent.putExtra("nameKey",ChildName);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,Login.class));
                finish();
            }
        });
    }

    public static String getValue(){
        return ChildName;
    }

}


