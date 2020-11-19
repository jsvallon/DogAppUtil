package com.example.dogapputil.service;

import com.example.dogapputil.model.BreedResult;
import com.example.dogapputil.model.DogBreedModel;
import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface IDogApi {
    @GET("v1/breeds")
    Single<List<DogBreedModel>> dogsList();

    @GET("v1/images/search")
    Single<List<BreedResult>> dogsListImage();
}
