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
    Call<List<Trip>> gettrips(@Header("Authorization") String AuthToken);

    @POST("/api/Trips")
    Call<Trip> posttrip(@Header("Authorization") String AuthToken,@Body Trip trip);

    @POST("/api/users/login")
    Call<Token>login(@Body JsonObject usr);
}
