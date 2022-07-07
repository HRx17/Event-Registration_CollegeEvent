package com.example.updates2021;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.updates2021.ModelResponse.CheckResponse;
import com.example.updates2021.ModelResponse.EventResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class teamof2 extends AppCompatActivity {

    EditText tname1;
    Button button;
    ImageView img,img1;
    String enrollment,tokn, eventname;
    int chk=0,i=1;
    TextView check,des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_teamof2);

        img = findViewById(R.id.teamevent);
        img1 = findViewById(R.id.showdes2);
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

        des =findViewById(R.id.singleevent2);
        check = findViewById(R.id.checkexist);
        button = findViewById(R.id.teamreg2);
        tname1 = findViewById(R.id.enroll12);
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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chk == 1) {
                    registerationAction();
                }
                else{
                    Toast.makeText(teamof2.this, "Please Check User First!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkuser();
            }
        });
    }

    private void checkuser() {
        Call<CheckResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .checkenroll(enrollment,eventname);
        call.enqueue(new Callback<CheckResponse>() {
            @Override
            public void onResponse(Call<CheckResponse> call, Response<CheckResponse> response) {
                CheckResponse checkResponse = response.body();
                if(response.isSuccessful()){
                    if(checkResponse.getMessage() == "ALREADY_REGISTERED"){
                        Toast.makeText(teamof2.this, checkResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(teamof2.this, checkResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        chk =1;
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckResponse> call, Throwable t) {
                Toast.makeText(teamof2.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public  void registerationAction(){
        ArrayList<String> members = new ArrayList<>();
        members.add(enrollment);
        String tmp = tname1.getText().toString();
        members.add(tmp);
        String token = tokn;

        Call<EventResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .eventregister(eventname, token, members);
        call.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                EventResponse eventResponse = response.body();
                Toast.makeText(teamof2.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                Toast.makeText(teamof2.this, "Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
