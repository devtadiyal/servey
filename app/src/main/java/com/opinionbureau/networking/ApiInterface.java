package com.opinionbureau.networking;


import com.opinionbureau.login.LoginRequest;
import com.opinionbureau.login.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {



    @POST("user/login")
    Call<LoginResponse> login(@Body LoginRequest accept);


}
