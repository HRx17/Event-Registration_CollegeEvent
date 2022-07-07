package com.example.updates2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.updates2021.ModelResponse.EventResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //private eventAdaptor.RecyclerViewClickListner listner;
    ImageView abhivyakti,avishkar,bidwiser,codefiesta,blindcode,codestorm,enigma,predictaholic,scavengerhunt,watchcraft,niyukti;
    String tokn,enrollment;
    ImageView timetable,toinsta;
    ImageView linearLayout;
    LinearLayout coordinator;
    Button button;
    int i=1;
    @Override
    public void onBackPressed(){
        moveTaskToBack(false);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        /*Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String token1 = bundle.getString("token");
            String en = bundle.getString("enroll");
            enrollment = en;
            tokn = token1;
        }
        Toast.makeText(this, tokn, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, enrollment, Toast.LENGTH_SHORT).show();
*/
        SharedPreferences sharedPreferences = getSharedPreferences("token",0);
        tokn = sharedPreferences.getString("token",null);
        SharedPreferences sharedPreferences1 = getSharedPreferences("enrollment",0);
        enrollment = sharedPreferences1.getString("enrollment",null);

        coordinator = findViewById(R.id.coordinators);
        coordinator.setVisibility(View.GONE);
        linearLayout = findViewById(R.id.mainlayout);
        button = findViewById(R.id.logout);
        toinsta = findViewById(R.id.toinsta);
        timetable = findViewById(R.id.timetable);
        abhivyakti = findViewById(R.id.event1);
        avishkar = findViewById(R.id.event2);
        bidwiser = findViewById(R.id.event3);
        codefiesta = findViewById(R.id.event4);
        blindcode = findViewById(R.id.event5);
        codestorm = findViewById(R.id.event6);
        niyukti = findViewById(R.id.event8);
        predictaholic = findViewById(R.id.event9);
        scavengerhunt = findViewById(R.id.event10);
        watchcraft = findViewById(R.id.event11);
        enigma = findViewById(R.id.event12);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==1){
                    coordinator.setVisibility(View.VISIBLE);
                    i=0;
                    linearLayout.setImageResource(R.drawable.ic_baseline_last_page_24);
                }
                else{
                    coordinator.setVisibility(View.GONE);
                    i=1;
                    linearLayout.setImageResource(R.drawable.ic_baseline_first_page_24);
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences2 = getSharedPreferences("token",0);
                SharedPreferences.Editor editor2 = sharedPreferences2.edit();
                editor2.clear();
                editor2.apply();
                SharedPreferences sharedPreferences3 = getSharedPreferences("enrollment",0);
                SharedPreferences.Editor editor3 = sharedPreferences3.edit();
                editor3.clear();
                editor3.apply();
                Intent intent = new Intent(MainActivity.this,log_in.class);
                startActivity(intent);
            }
        });

        toinsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/_u/scet.co.dept");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/scet.co.dept/")));
                }

            }
        });

        abhivyakti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, teamof2.class);
                intent.putExtra("my_image",R.drawable.abhivyakti_final);
                intent.putExtra("event","Abhivyakti");
                intent.putExtra("des",R.drawable.abhivyakti_rules);
                intent.putExtra("token",tokn);
                intent.putExtra("enroll",enrollment);
                startActivity(intent);
            }
        });
        avishkar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,teamRegister.class);
                intent.putExtra("my_image",R.drawable.avishkar_final);
                intent.putExtra("event","Avishkar");
                intent.putExtra("des",R.drawable.avishkar_rules);
                intent.putExtra("token",tokn);
                intent.putExtra("enroll",enrollment);
                startActivity(intent);
            }
        });
        bidwiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,teamof2.class);
                intent.putExtra("my_image",R.drawable.bidwars);
                intent.putExtra("des",R.drawable.bidwars_rules);
                intent.putExtra("event","Bidwars");
                intent.putExtra("token",tokn);
                intent.putExtra("enroll",enrollment);
                startActivity(intent);
            }
        });
        codefiesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,teamof2.class);
                intent.putExtra("my_image",R.drawable.codefiesta);
                intent.putExtra("des",R.drawable.codefiestarules);
                intent.putExtra("event","Codefiesta");
                intent.putExtra("token",tokn);
                intent.putExtra("enroll",enrollment);
                startActivity(intent);
            }
        });
        blindcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,teamRegister.class);
                intent.putExtra("my_image",R.drawable.blindcodefinal);
                intent.putExtra("des",R.drawable.blindcode_rules);
                intent.putExtra("token",tokn);
                intent.putExtra("enroll",enrollment);
                intent.putExtra("event","Blindcode");
                startActivity(intent);
            }
        });
        codestorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,teamof2.class);
                intent.putExtra("my_image",R.drawable.codestorm);
                intent.putExtra("des",R.drawable.codestorm_rules);
                intent.putExtra("event","Codestorm");
                intent.putExtra("token",tokn);
                intent.putExtra("enroll",enrollment);
                startActivity(intent);
            }
        });
        niyukti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,singleregister.class);
                intent.putExtra("my_image",R.drawable.niyukti_final);
                intent.putExtra("des",R.drawable.niyuktirules);
                intent.putExtra("event","Niyukti");
                intent.putExtra("token",tokn);
                intent.putExtra("enroll",enrollment);
                startActivity(intent);

            }
        });
        predictaholic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,teamof2.class);
                intent.putExtra("my_image",R.drawable.predictaholicfinal);
                intent.putExtra("des",R.drawable.predictaholic_rules);
                intent.putExtra("event","Predictaholic");
                intent.putExtra("token",tokn);
                intent.putExtra("enroll",enrollment);
                startActivity(intent);

            }
        });
        scavengerhunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,teamof2.class);
                intent.putExtra("my_image",R.drawable.scavengerhunt);
                intent.putExtra("des",R.drawable.scavengerhunt_rules);
                intent.putExtra("event","Scavengerhunt");
                intent.putExtra("token",tokn);
                intent.putExtra("enroll",enrollment);
                startActivity(intent);

            }
        });
        watchcraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,teamRegister.class);
                intent.putExtra("my_image",R.drawable.watchcraft);
                intent.putExtra("des",R.drawable.watchcraftrules);
                intent.putExtra("event","Watchcraft");
                intent.putExtra("token",tokn);
                intent.putExtra("enroll",enrollment);
                startActivity(intent);
            }
        });
        enigma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,teamof2.class);
                intent.putExtra("my_image",R.drawable.enigmafinal);
                intent.putExtra("des",R.drawable.enigmarules);
                intent.putExtra("event","Enigma");
                intent.putExtra("token",tokn);
                intent.putExtra("enroll",enrollment);
                startActivity(intent);
            }
        });
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                startActivity(intent);
            }
        });
    }
}
