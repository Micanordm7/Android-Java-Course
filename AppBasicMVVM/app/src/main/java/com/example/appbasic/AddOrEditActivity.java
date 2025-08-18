package com.example.appbasic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.Executors;

public class AddOrEditActivity extends AppCompatActivity {
    EditText txtname,txtname2,txtemail;
    Button btn_edit, btn_add;

    UserViewModel userViewModel;

    User userToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userViewModel = new ViewModelProvider(AddOrEditActivity.this).get(UserViewModel.class);

        setContentView(R.layout.activity_add_or_edit);
        txtname = findViewById(R.id.txtName);
        txtname2 = findViewById(R.id.txtName2);
        txtemail = findViewById(R.id.txtEmail);

        btn_edit = findViewById(R.id.btnEdit);
        btn_add = findViewById(R.id.btnAdd);
        Intent intent = getIntent();
        int userId = intent.getIntExtra("item",-1);

        if (userId != -1){
            userViewModel.setSingleUser(userId);
            userViewModel.getSingleUser().observe(this, new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    txtname.setText(user.getNom());
                    txtname2.setText(user.getPrenom());
                    txtemail.setText(user.getEmail());
                    userToEdit = user;

                }
            });

            btn_edit.setText("Modifier");
            btn_add.setText("Supprimer");
        }
        else {
            btn_edit.setText("Annuler");
            btn_add.setText("Ajouter");

        }


        btn_edit.setOnClickListener(view -> {
            if(userId != -1) {
                String firstname = txtname.getText().toString();
                String lastname = txtname2.getText().toString();
                String email = txtemail.getText().toString();

                User newUser = new User(userId,firstname,lastname,email,R.drawable.datepicture);
                userViewModel.updateUser(newUser);
            }
            Intent intent1 = new Intent(this,HomeActivity.class);
            startActivity(intent1);
        });

        btn_add.setOnClickListener(view -> {
            if(userId == -1) {
                String firstname = txtname.getText().toString();
                String lastname = txtname2.getText().toString();
                String email = txtemail.getText().toString();

                User newUser = new User(firstname, lastname, email, R.drawable.datepicture);
                    userViewModel.insertUsers(newUser);

            }else {
                userViewModel.deleteUser(userToEdit);
            }

            Intent intent1 = new Intent(this,HomeActivity.class);
            startActivity(intent1);
        });
    }
}