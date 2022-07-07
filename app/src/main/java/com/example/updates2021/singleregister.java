package com.example.updates2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.updates2021.ModelResponse.EventResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class singleregister extends AppCompatActivity {
    Button button;
    int i2;
    String enrollment,tokn,eventname;
    ImageView img1,img;
    TextView des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_singleregister);

        img1 = findViewById(R.id.showdes);
        img = findViewById(R.id.eventimg);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            int res_image = bundle.getInt("my_image");
            img.setImageResource(res_image);
            int re_image = bundle.getInt("des");
            img1.setImageResource(re_image);
            String token1 = bundle.getString("token");
            String en = bundle.getString("enroll");
            String ev = bundle.getString("event");
            enrollment = en;
            tokn = token1;
            eventname = ev;

        }

        des =findViewById(R.id.singleevent);
        img1.setVisibility(View.GONE);
        button = findViewById(R.id.singlereg);

        des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i2 ==1){
                    img1.setVisibility(View.VISIBLE);
                    i2=0;
                }
                else{
                    img1.setVisibility(View.GONE);
                    i2=1;
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerationAction();
            }
        });
    }
    public  void registerationAction(){
        ArrayList<String> members = new ArrayList<>();
        members.add(enrollment);
        members.add("");
        String token = tokn;

        Call<EventResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .eventregister(eventname, token, members);
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                EventResponse eventResponse = response.body();
                if(response.isSuccessful()){
                    Toast.makeText(singleregister.this, "Please wait...", Toast.LENGTH_SHORT).show();
                    if(eventResponse.getMessage() == "ALREADY_REGISTERED"){
                        Toast.makeText(singleregister.this, eventResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(singleregister.this, eventResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Toast.makeText(singleregister.this, "Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}