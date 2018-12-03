package com.korginska.sofia.lab5sofia;

import com.korginska.sofia.lab5sofia.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DrinkInterface {
    @GET("/api/json/v1/1/search.php?s=margarita")
    Call<Example>imageOfAlcohol();
}
