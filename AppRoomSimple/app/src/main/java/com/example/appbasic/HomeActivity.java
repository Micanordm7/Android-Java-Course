package com.example.appbasic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.concurrent.Executors;

public class HomeActivity extends AppCompatActivity {
    private TextView txtEmail;
    BottomNavigationView bottomNavigationView;

    public static DbHandle dbHandle;





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_home, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.homeItem){
            loadFragment(new homeFragment());
        } else if (item.getItemId() == R.id.searchItem) {
            loadFragment(new searchFragment());
        }
        else if(item.getItemId() == R.id.profileItem) {
            loadFragment(new profileFragment());
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigation);


        dbHandle = DbHandle.getInstance(HomeActivity.this);

        //database
        Executors.newSingleThreadExecutor().execute(() -> {
//            dbHandle.getUserDao().insertSingleUser(new User("Derisma","Hericka","herickaderisma.com",R.drawable.ednerimage));
        //    dbHandle.getUserDao().insertSingleUser(new User("Alino","Vando","herickaderisma.com",R.drawable.ednerimage));

        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle(R.string.home_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        loadFragment( new homeFragment());
        // creer le listenner
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.itemHome){
                    loadFragment(new homeFragment());
                } else if (item.getItemId() == R.id.itemSearch) {
                    loadFragment(new searchFragment());
                }
                else if(item.getItemId() == R.id.itemProfile) {
                    loadFragment(new profileFragment());
                }
                return false;
            }
        });

//        //creer la bar d'action
//        ActionBar actionBar = getSupportActionBar();
//
//        // ajoute le boutton back
//
//        assert actionBar != null;
//        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void loadFragment(Fragment fragment) {
        //chargement le fragement
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerViewMain,fragment);
        fragmentTransaction.commit();
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch(item.getItemId()){
//            case android.R.id.home:
//                this.finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}