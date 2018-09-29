package com.example.ashutoshshrivastava.weatherinformation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by ashutoshshrivastava on 29/08/18.
 */

public interface latlongservice {


    @GET("/latlon.php")
    Call<latlong1> getlatlon(@Header("X-Mashape-Key") String key, @Query("location") String place);
}


/////////////////////////////////////////////CREATED BY ASHU SRV//////////////////////////////////////////////////////////