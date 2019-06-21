package com.example.retrofittestsd.api;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface APIService {

    @GET("akif01-06-2019.json")
    Call<List<SModel>> getSimList();



    @GET("akif01-06-2019.json")
    Call<ResponseBody>getHomedata();

}
