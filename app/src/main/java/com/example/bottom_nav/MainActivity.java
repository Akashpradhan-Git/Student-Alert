package com.example.bottom_nav;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottom_nav.ui.account.Account;
import com.example.bottom_nav.ui.dashboard.DashboardFragment;
import com.example.bottom_nav.ui.home.HomeFragment;
import com.example.bottom_nav.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
 int k=0;
public static String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.navigation);


        loadFragment(new HomeFragment());
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        Intent intent=getIntent();
        if (intent != null) {
            str=intent.getStringExtra("body");
        }


    }
    public String getTextN(){
        return str;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment=null;
        switch (item.getItemId())
        {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_dashboard:
                fragment = new DashboardFragment();
                break;

            case R.id.navigation_notifications:
                fragment = new NotificationsFragment();
                //FragmentTransaction fragmentTransaction=getSupportFragmentManager()
                Bundle b = new Bundle();
                b.putString("message", str);
                fragment.setArguments(b);
                // fragmentTransaction.add(R.id.frameLayout, myFragment).commit();
                break;
            case R.id.navigation_account:
                fragment = new Account();
                break;
        }
        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {

    //start

        if (doubleBackToExitPressedOnce) {
            finish();
            super.onBackPressed();

            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);

        //end

    }

}
