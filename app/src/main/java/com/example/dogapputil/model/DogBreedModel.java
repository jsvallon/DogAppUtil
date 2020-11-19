package com.example.dogapputil.model;


import com.google.gson.annotations.SerializedName;

public class DogBreedModel {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("bred_for")
    private String bred_for;

    public DogBreedModel(int id, String name, String bred_for) {
        this.id = id;
        this.name = name;
        this.bred_for = bred_for;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBred_for() {
        return bred_for;
    }
}
