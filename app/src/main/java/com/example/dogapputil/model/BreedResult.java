package com.example.dogapputil.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class BreedResult {

    @SerializedName("breeds")
    private List<DogBreedModel> breeds;

    @SerializedName("url")
    private String url;

    public List<DogBreedModel> getBreeds() {
      return breeds;
    }

    public String getUrl() {
      return url;
    }
}