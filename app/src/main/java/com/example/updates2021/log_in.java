package com.example.updates2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.updates2021.ModelResponse.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class log_in extends AppCompatActivity {

    EditText log_enroll, log_pass;
    Button login;
    Button skiptoo;

    public void onBackPressed(){
        return;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_log_in);
        log_enroll = findViewById(R.id.log_enroll);
        log_pass = findViewById(R.id.log_pass);
        login = findViewById(R.id.log_in);
        skiptoo = findViewById(R.id.skip);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmp1,tmp2;
                tmp1 = log_enroll.getText().toString();
                tmp2 = log_pass.getText().toString();
                if(tmp1.isEmpty() || tmp2.isEmpty()){
                    Toast.makeText(log_in.this, "Please enter details!", Toast.LENGTH_SHORT).show();
                }
                else{
                    userLogin();
                }
            }
        });

        skiptoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(log_in.this,sign_in.class);
                startActivity(intent);
            }
        });

    }

    private void userLogin() {
        String enrollment = log_enroll.getText().toString();
        String password = log_pass.getText().toString();

        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().login(enrollment,password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if(response.isSuccessful()){
                        Toast.makeText(log_in.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        String tm = loginResponse.getToken();
                        Intent intent = new Intent(log_in.this, MainActivity.class);
                        startActivity(intent);
                        SharedPreferences sharedPreferences = getSharedPreferences("token", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", tm);
                        editor.apply();
                        SharedPreferences sharedPreferences1 = getSharedPreferences("enrollment", 0);
                        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                        editor1.putString("enrollment", enrollment);
                        editor1.apply();
                        //sharedPrefManager.SaveToken(loginResponse.getToken());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(log_in.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}