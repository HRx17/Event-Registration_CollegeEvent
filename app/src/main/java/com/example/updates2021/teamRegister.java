package com.example.updates2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.updates2021.ModelResponse.CheckResponse;
import com.example.updates2021.ModelResponse.EventResponse;
import com.example.updates2021.ModelResponse.RegisterResponse;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class teamRegister extends AppCompatActivity {
    ImageView img,img1;
    EditText tname1,tname2;
    Button button;
    int chk1,chk2=0;
    int i=1;
    TextView check1,check2,des;
    String enrollment,token,eventname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_team_register);

        check1 = findViewById(R.id.checkexist11);
        check2 = findViewById(R.id.checkexist2);
        button = findViewById(R.id.teamreg);
        tname1 = findViewById(R.id.enroll1);
        des = findViewById(R.id.showdest);
        tname2 = findViewById(R.id.enroll2);
        img = findViewById(R.id.teamevent);
        img1 = findViewById(R.id.singleeventt);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            int res_image = bundle.getInt("my_image");
            img.setImageResource(res_image);
            int re_image = bundle.getInt("des");
            img1.setImageResource(re_image);
            String token1 = bundle.getString("token");
            String en = bundle.getString("enroll");
            String eventnam = bundle.getString("event");
            enrollment = en;
            token = token1;
            eventname = eventnam;
        }
        img1.setVisibility(View.GONE);

        des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i ==1){
                    img1.setVisibility(View.VISIBLE);
                    i=0;
                }
                else{
                    img1.setVisibility(View.GONE);
                    i=1;
                }
            }
        });

        check1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkmember1();
            }
        });
        check2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkmember2();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chk1 == 1 && chk2 ==1) {
                    registerAction();
                }
                else{
                    Toast.makeText(teamRegister.this, "Please Check Both Members First!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkmember2() {
        Call<CheckResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .checkenroll(tname2.getText().toString(),eventname);
        call.enqueue(new Callback<CheckResponse>() {
            @Override
            public void onResponse(Call<CheckResponse> call, Response<CheckResponse> response) {
                CheckResponse checkResponse = response.body();
                if(response.isSuccessful()){
                    if(checkResponse.getMessage() == "ALREADY_REGISTERED"){
                        Toast.makeText(teamRegister.this, checkResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(teamRegister.this, checkResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        chk2 =1;
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckResponse> call, Throwable t) {
                Toast.makeText(teamRegister.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkmember1() {
        Call<CheckResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .checkenroll(tname2.getText().toString(),eventname);
        call.enqueue(new Callback<CheckResponse>() {
            @Override
            public void onResponse(Call<CheckResponse> call, Response<CheckResponse> response) {
                CheckResponse checkResponse = response.body();
                if(response.isSuccessful()){
                    if(checkResponse.getMessage() == "ALREADY_REGISTERED"){
                        Toast.makeText(teamRegister.this, checkResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(teamRegister.this, checkResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        chk1 =1;
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckResponse> call, Throwable t) {
                Toast.makeText(teamRegister.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerAction() {
        ArrayList<String> members = new ArrayList<>();
        members.add(enrollment);
        String tm1 = tname1.getText().toString();
        members.add(tm1);
        members.add(tname2.getText().toString());

        Call<EventResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .eventregister(eventname,token,members);
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                EventResponse eventResponse = response.body();
                if (response.isSuccessful()){
                    Toast.makeText(teamRegister.this, "Success!" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(teamRegister.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(teamRegister.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Toast.makeText(teamRegister.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}