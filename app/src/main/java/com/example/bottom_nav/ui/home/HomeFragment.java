package com.example.bottom_nav.ui.home;

import com.example.bottom_nav.Library;
import com.example.bottom_nav.Login;
import com.example.bottom_nav.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bottom_nav.About;
import com.example.bottom_nav.Calendar;
import com.example.bottom_nav.R;
import com.example.bottom_nav.Syllbus;
import com.example.bottom_nav.TimeTable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    RelativeLayout about1,timetbl,calendar1,libray,syl,book,result;
    ImageView usrlogin;
    String username,childname,username1,getemail;
    FirebaseDatabase database;
    DatabaseReference myref;
    TextView Tvname;
    FirebaseAuth firebaseAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GridView gridView;
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        about1 = (RelativeLayout) view.findViewById(R.id.about);
        timetbl = (RelativeLayout) view.findViewById(R.id.timeTable);
        calendar1 = view.findViewById(R.id.calendar);
        libray=view.findViewById(R.id.Liabray);
        syl=view.findViewById(R.id.syllabus);
        book=view.findViewById(R.id.Book);
        result=view.findViewById(R.id.myclass);
        usrlogin = view.findViewById(R.id.imageView);
        Tvname=view.findViewById(R.id.tvName);


        about1.setOnClickListener(this);
        timetbl.setOnClickListener(this);
        calendar1.setOnClickListener(this);
        usrlogin.setOnClickListener(this);
        libray.setOnClickListener(this);
        syl.setOnClickListener(this);
        book.setOnClickListener(this);
        result.setOnClickListener(this);




        /**Name retrive from database */
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        //userId=firebaseAuth.getUid().toString();
        SignUp s=new SignUp();
        username=s.getValue();

        Login login=new Login();
        username1=login.GetNameFromLogin();

        if(username!=null) {
            childname = username;
        }
         else if(username1!=null){
            childname=username1;
        }
         else{
        getemail= firebaseAuth.getCurrentUser().getEmail();
            childname=getemail.replaceAll("[^a-zA-Z0-9]","").trim();
        }

           DatabaseReference myref = database.getReference().child("member").child(childname);
           myref.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 String  name = dataSnapshot.child("name").getValue().toString();
                  // Tvname.setText(name);
                   if(name!=null){
                       Tvname.setText(name);
                   }
                   else
                   {
                       Tvname.setText("Null");
                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

               }
           });


        /**end of database work*/

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.imageView:
//                FragmentTransaction ft1=getFragmentManager().beginTransaction();
//                ft1.replace(R.id.fragment_container,new Login()).commit();
//                ft1.addToBackStack(null);


            case R.id.about:
                Intent intent = new Intent(v.getContext(), About.class);
                startActivity(intent);
                Toast toast;
                Toast.makeText(getActivity(), "about NHCE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.timeTable:
                Intent in1=new Intent(v.getContext(), TimeTable.class);
                startActivity(in1);
                Toast.makeText(getActivity(),"TimeActivity",Toast.LENGTH_SHORT).show();
                break;
            case R.id.calendar:
                startActivity(new Intent(getContext(), Calendar.class));
                break;
            case R.id.Liabray:
                startActivity(new Intent(v.getContext(), Library.class));
                break;
            case R.id.syllabus:
                Intent in2=new Intent(v.getContext(), Syllbus.class);
                startActivity(in2);
                Toast.makeText(getActivity(),"syllabus clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Book:
                Toast.makeText(getActivity(),"Book clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.myclass:
                Toast.makeText(getActivity(),"Result clicked",Toast.LENGTH_SHORT).show();
                break;


        }

    }



}
