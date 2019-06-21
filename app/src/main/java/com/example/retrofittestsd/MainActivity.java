package com.example.retrofittestsd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.retrofittestsd.api.APIService;
import com.example.retrofittestsd.api.SModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SAdapter.onAdapterOnClickListner{

    public static String TAG ="MainActivity";
    private static String baseUrl="http://192.168.1.6/test/";
    List<SModel> sModels = new ArrayList<>();
    private RecyclerView recyclerView;
    private Retrofit retrofit=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        // retrofit object creating
        RetroObjectCreation();
        loadRetrofit();

    }




    public void RetroObjectCreation(){

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }



    public void loadRetrofit(){

        APIService apiService = retrofit.create(APIService.class);

        Call<ResponseBody> call = apiService.getHomedata();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String s = response.body().string();
                    Log.i(TAG, "onResponse: >>>>>>>>>>>>>>> "+s);
                    JSONObject object = new JSONObject(s);
                    JSONArray jsonArray = object.getJSONArray("simdata");
                    for (int i=0;i<jsonArray.length();i++){
                        sModels.add(new SModel(jsonArray.getJSONObject(i)));
                        Log.i(TAG, "onResponse: >>>>><<<<<<<<>>>>>>>>>> "+new SModel(jsonArray.getJSONObject(i)));
                    }
                    loadRecycleView();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplication(),"Failed ======= <<<<<< >>>>>>>> =="+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

       /* Call<List<SModel>> call = apiService.getSimList();

        call.enqueue(new Callback<List<SModel>>() {
            @Override
            public void onResponse(Call<List<SModel>> call, Response<List<SModel>> response) {
                sModels = response.body();
                for (SModel sModel : sModels){
                    Log.i(TAG, "onResponse: "+sModel.getAddress());



                }


            }

            @Override
            public void onFailure(Call<List<SModel>> call, Throwable t) {
                Toast.makeText(getApplication(),"Failed========="+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });*/

    }
    public void loadRecycleView(){
        Log.i(TAG, "loadRecycleView: >>>>>>>>>>>>>>"+sModels.size());
        SAdapter sa = new SAdapter(getApplicationContext(),sModels,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sa);
    }

    @Override
    public void getSmodelPossition(int id) {
        Toast.makeText(getApplication(),"hi >>"+id,Toast.LENGTH_LONG).show();
    }
}
