package com.example.updates2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.updates2021.ModelResponse.RegisterResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class sign_in extends AppCompatActivity {

    public void onBackPressed(){
        Intent intent = new Intent(sign_in.this,log_in.class);
        startActivity(intent);
    }
    public String firstname,lastname,scetid,pid,enrollment,phoneno,year,dept,password,shift,personalid;
    EditText sign_name,sign_enroll,sign_email,sign_pid,sign_phone,sign_pass,sign_lastname,signnshift;
    AutoCompleteTextView sign_year,sign_dept;
    Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_in);

        sign_pid = findViewById(R.id.sign_pemail);
        sign_name = findViewById(R.id.sign_name);
        sign_enroll = findViewById(R.id.sign_enroll);
        sign_lastname = findViewById(R.id.sign_lastname);
        sign_email = findViewById(R.id.sign_email);
        sign_phone = findViewById(R.id.sign_phone);
        sign_pass = findViewById(R.id.sign_pass);
        sign_year = findViewById(R.id.sign_yar);
        signnshift = findViewById(R.id.sign_shft);
        sign_dept = findViewById(R.id.sign_dept);
        signin = findViewById(R.id.sign_in);

        String[] depts = {"CO","EC","IT","IC","TT","Chem","Other"};
        ArrayAdapter<String> itemAdapter=new ArrayAdapter<>(sign_in.this,R.layout.department_names,depts);
        sign_dept.setAdapter(itemAdapter);
        String[] yr = {"1","2","3","4"};
        ArrayAdapter<String> itemAdapter1=new ArrayAdapter<>(sign_in.this,R.layout.department_names,yr);
        sign_year.setAdapter(itemAdapter1);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = sign_name.getText().toString();
                scetid = sign_email.getText().toString();
                password = sign_pass.getText().toString();
                phoneno = sign_phone.getText().toString();
                year = sign_year.getText().toString();
                dept = sign_dept.getText().toString();
                lastname = sign_lastname.getText().toString();
                pid = sign_pid.getText().toString();
                shift = signnshift.getText().toString();

               if (firstname.isEmpty() || scetid.isEmpty() || shift.isEmpty() || password.isEmpty() || phoneno.isEmpty() || year.isEmpty() || dept.isEmpty() || lastname.isEmpty() || pid.isEmpty()) {
                 Toast.makeText(sign_in.this, "Please Fill all the Details!", Toast.LENGTH_LONG).show();
                }
               else {
                    registerUser();

                }
            }
        });

    }

    private void registerUser() {

        firstname = sign_name.getText().toString();
        scetid = sign_email.getText().toString();
        enrollment = sign_enroll.getText().toString();
        phoneno = sign_phone.getText().toString();
        password = sign_pass.getText().toString();
        year = sign_year.getText().toString();
        dept = sign_dept.getText().toString();
        shift = signnshift.getText().toString();
        personalid = sign_pid.getText().toString();


        Call<RegisterResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .register(scetid,enrollment,password,firstname,lastname,year,dept,shift,phoneno,personalid);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();
                if (response.isSuccessful()){
                    if(registerResponse.getMessage()=="SUCCESS"){
                        Toast.makeText(sign_in.this, "Success!" , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(sign_in.this, log_in.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(sign_in.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(sign_in.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(sign_in.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}