package com.example.bottom_nav.ui.account;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bottom_nav.Login;
import com.example.bottom_nav.R;
import com.example.bottom_nav.SignUp;
import com.example.bottom_nav.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Account extends Fragment {

    TextView acuname,acUsn,acEmail;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    String Fmail,childName;
    String name,email,usn;
    Button logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_account,container,false);

        acuname=view.findViewById(R.id.acname);
        acUsn=view.findViewById(R.id.acusn);
        acEmail=view.findViewById(R.id.acemail);
        logout=view.findViewById(R.id.aclogout);

        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseDatabase database=FirebaseDatabase.getInstance();


        Fmail=firebaseAuth.getCurrentUser().getEmail();
        if(Fmail!=null)
        {
            childName=Fmail.replaceAll("[^a-zA-Z0-9]","").trim();
        }

        final DatabaseReference myref=database.getReference().child("member").child(childName);
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name=dataSnapshot.child("name").getValue().toString();
                usn=dataSnapshot.child("usn").getValue().toString();
                email=dataSnapshot.child("email").getValue().toString();

                acuname.setText(name);
                acUsn.setText(usn);
                acEmail.setText(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), SignUp.class));
                getActivity().finish();
            }
        });

        return view;
    }
}
