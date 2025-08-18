package com.example.appbasic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
private EditText txtEmail, txtPassword;
private Button btnConnect;
private TextView tvCreate;

DbHandle dbHandle;
    int number = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandle = DbHandle.getInstance(MainActivity.this);

        // referencement des vues au variable
        txtEmail = findViewById(R.id.txtemail);
        txtPassword = findViewById(R.id.txtPassword);
        btnConnect = findViewById(R.id.btnRegister);
        tvCreate = findViewById(R.id.txtCreate);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle(R.string.main_title);
        }

//        Executors.newSingleThreadExecutor().execute(() -> {
//            dbHandle.getUserDao().insertSingleUser(new User("Derisma","Hericka","herickaderisma.com",R.drawable.ednerimage));
//            dbHandle.getUserDao().insertSingleUser(new User("Derisma","Hericka","herickaderisma.com",R.drawable.ednerimage));
//
//        });

//        try {
//            DbHandle.getInstance(MainActivity.this).getUserDao().insertAll(new User(1,"Derisma","Hericka","herickaderisma.com",R.drawable.ednerimage));
//        }catch (Exception e){
//            Toast.makeText(this, "Error : "+e, Toast.LENGTH_SHORT).show();
//        }

//        long insert = 0;
//            dbHandle.insertUser(new User("Derisma","Hericka","herickaderisma.com",R.drawable.ednerimage));
//            dbHandle.insertUser(new User("Jacques","Jonathaline","herickaderisma.com",R.drawable.ednerimage));
//            dbHandle.insertUser(new User("Pierre","Kishayo","herickaderisma.com",R.drawable.ednerimage));
//            dbHandle.insertUser(new User("Letaille","Herickaya","herickaderisma.com",R.drawable.ednerimage));
//            dbHandle.insertUser(new User("Lacombe","Irmaya","herickaderisma.com",R.drawable.ednerimage));


        //List<User> userList = dbHandle.userList();

        //Toast.makeText(this, "Nombre User " + userList.size(), Toast.LENGTH_SHORT).show();
        //listener sur le boutton connecter
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName, passWord;
                userName = txtEmail.getText().toString();
                passWord = txtPassword.getText().toString();

                if (userName.isEmpty() && passWord.isEmpty()) {
                    Toast.makeText(MainActivity.this, "tu dois saisir un username et un mot de passe", Toast.LENGTH_SHORT).show();
                } else {
                    if (passWord.equals("1234")) {
                        Intent intentConnect = new Intent(MainActivity.this, HomeActivity.class);

                        intentConnect.putExtra("email",userName);
                        startActivity(intentConnect);
                    } else {
                        Toast.makeText(MainActivity.this, "Mot de passe", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCreate = new Intent(MainActivity.this, LogOnActivity.class);
                startActivity(intentCreate);
            }


        });
    }


//    @Override
//    public void onClick(View view) {
//
//        if(view.getId() == R.id.btnRegister ){
//            String userName, passWord;
//            userName = txtEmail.getText().toString();
//            passWord = txtPassword.getText().toString();
//
//            if(userName.isEmpty() && passWord.isEmpty()){
//                Toast.makeText(this, "tu dois saisir un username et un mot de passe", Toast.LENGTH_SHORT).show();
//            }
//            else{
//                if(passWord.equals("1234")){
//                    Toast.makeText(this, " Bienvenue sur mon Application "+userName+" ", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(this, "Mot de passe incorrect", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//        else if(view.getId() == R.id.txtCreate){
//            Toast.makeText(MainActivity.this,"Bienvenue sur mon Appli",Toast.LENGTH_LONG).show();
//
//        }
//
//    }
}