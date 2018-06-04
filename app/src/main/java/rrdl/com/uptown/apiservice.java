package rrdl.com.uptown;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface apiservice {
    @GET("/api/Trips")
    Call<List<Trip>> gettrips(@Header("Authorization") String token);

    @POST("/api/users/login")
    Call<JsonObject>login(@Body JsonObject usr);
}
