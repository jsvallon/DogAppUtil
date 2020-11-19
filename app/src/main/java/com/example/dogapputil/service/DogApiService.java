package com.example.dogapputil.service;
import com.example.dogapputil.model.BreedResult;
import com.example.dogapputil.model.DogBreedModel;
import java.util.List;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class DogApiService {

    private static final String base_url = "https://api.thedogapi.com";

    private IDogApi instance;

    public DogApiService() {
        instance = new Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(IDogApi.class);
    }


    public Single<List<DogBreedModel>> dogsList() {
        return instance.dogsList();
    }

    public Single<List<BreedResult>> dogsListImage() {
        return instance.dogsListImage();
    }

}
