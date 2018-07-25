package com.serch.cars_implements.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface iData {

    @POST("post")
    Call<ResponseBody> respuesta(@Body response response);

}
