package com.example.updates2021;

import com.example.updates2021.ModelResponse.CheckResponse;
import com.example.updates2021.ModelResponse.EventResponse;
import com.example.updates2021.ModelResponse.LoginResponse;
import com.example.updates2021.ModelResponse.RegisterResponse;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    //@Headers({"x-access-token:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbnJvbGxtZW50IjoiMTkwNDIwMTA3MDE4IiwiaWF0IjoxNjMxMjY3NTg2fQ.Hv8XhPKQTLNrnNJv_UepHYaqHXZ2_MDNhcuJLb3MLbQ"})
    @FormUrlEncoded
    @POST("signup")
    Call<RegisterResponse> register(
            @Field("scetid") String scetid,
            @Field("enrollment") String enrollment,
            @Field("password") String password,
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("year") String year,
            @Field("dept") String dept,
            @Field("shift") String shift,
            @Field("phoneno") String phoneno,
            @Field("personalid") String personalid
    );

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("enrollment") String enrollment,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("event-register")
    Call<EventResponse> eventregister(
            @Field("eventname") String eventname,
            @Field("token") String token,
            @Field("members") ArrayList<String> members
    );

    @FormUrlEncoded
    @POST("enrollment-exist")
    Call<CheckResponse> checkenroll(
            @Field("enrollment") String enrollment,
            @Field("eventname") String eventname
    );
}
