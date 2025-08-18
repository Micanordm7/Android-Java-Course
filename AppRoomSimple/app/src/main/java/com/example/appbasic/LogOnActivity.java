package com.example.appbasic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class LogOnActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    RadioGroup rdgSexe;
    Button btn_create;
    //declaration spiner
    Spinner spinner;
    //declaration checkbox
    CheckBox chbx1,chbx2,chbx3,chbx4;
    //declaration Editex
    EditText datenaiss;
    ImageButton imageButton;
    //declaration float...., image view
    FloatingActionButton pictureTaker;
    ImageView logonImage;



    //liste
    List<String> hobbies = new ArrayList<String>();
    List<String> pays = new ArrayList<String>(Arrays.asList("Haiti","Canada","Bresil","Cuba","colombie","Jamaique","Maroc"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on);
        //
        chbx1 = findViewById(R.id.checkBox1);
        chbx2 = findViewById(R.id.checkBox2);
        chbx3 = findViewById(R.id.checkBox3);
        //spinner
        spinner = findViewById(R.id.spinner);
        //image view, Floating....
        logonImage = findViewById(R.id.logonImage);
        pictureTaker = findViewById(R.id.pictureTaker);
        //reference editex et imagebutton
        datenaiss = findViewById(R.id.datenaiss);
        imageButton = findViewById(R.id.datepicture);

        rdgSexe = findViewById(R.id.sexe);

        btn_create = findViewById(R.id.btn_create);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle(R.string.logon_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setCurrentDate();
        //spinner
        ArrayAdapter adapter = new ArrayAdapter(LogOnActivity.this, android.R.layout.simple_spinner_item,pays);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String pay = pays.get(i);
                Toast.makeText(LogOnActivity.this, "Pays: "+pay, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // listener pour le boutton date naissance
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.appbasic.DatePicker datePicker = new com.example.appbasic.DatePicker();
                datePicker.show(getSupportFragmentManager(),"DatePicker");
            }
        });
        pictureTaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(LogOnActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        //checkbox
        chbx1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    Toast.makeText(LogOnActivity.this, "Infos :"+chbx1.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hobbies = check_hobbies();

                int selectedId = rdgSexe.getCheckedRadioButtonId();
                if (selectedId == -1){
                    Toast.makeText(LogOnActivity.this, "Rien", Toast.LENGTH_SHORT).show();
                }
                else {
                    RadioButton radioButton = rdgSexe.findViewById(selectedId);
                    String sexe = radioButton.getText().toString();
                    //Toast.makeText(LogOnActivity.this, "Votre sexe est: "+sexe, Toast.LENGTH_SHORT).show();
                }

                //check box value
                if(hobbies.isEmpty()){
                    Toast.makeText(LogOnActivity.this, "Rien", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LogOnActivity.this, "Hobbie"+hobbies.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        String choosingDate = DateFormat.getDateInstance().format(calendar.getTime());
        datenaiss.setText(choosingDate);

    }

    //methode de verification des valeurs selectionnees des checks box.
    public List<String> check_hobbies(){
        List<String> check_hobbies = new ArrayList<String>();

        if(chbx1.isChecked()){
            check_hobbies.add(chbx1.getText().toString());
        }
        if(chbx2.isChecked()){
            check_hobbies.add(chbx2.getText().toString());
        }
        if(chbx3.isChecked()){
            check_hobbies.add(chbx3.getText().toString());
        }
        return check_hobbies;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,i);
        calendar.set(Calendar.MONTH,i1);
        calendar.set(Calendar.DAY_OF_MONTH,i2);
        String choosingDate = DateFormat.getDateInstance().format(calendar.getTime());
        datenaiss.setText(choosingDate);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            logonImage.setImageURI(uri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, "erreur:"+ImagePicker.getError(data), Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "action annuller", Toast.LENGTH_SHORT).show();
        }
    }
}